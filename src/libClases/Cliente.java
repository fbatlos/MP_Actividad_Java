package libClases;
import libClases.Fecha;


public class Cliente implements Proceso,Cloneable { 
	private final String nif;       //dni del cliente (letra incluida) (NO puede cambiar)   
	private int codCliente = 0;   //codigo único (y fijo) generado por la aplicación 
	private String nombre;          //nombre completo del cliente (SI se puede modificar) 
	private final Fecha fechaNac;   //fecha nacimiento del cliente (NO se puede cambiar) 
	private Fecha fechaAlta;  //fecha de alta del cliente (SI se puede modificar) 
  
	private static int contadorGlobal = 0;
	private static Fecha fechaPorDefecto = new Fecha(1,1,2018);
	 
	public static Fecha getFechaPorDefecto(){
		return fechaPorDefecto;
	}
	  
	public static void setFechaPorDefecto(Fecha newFechaDF){
		fechaPorDefecto = newFechaDF;
	}
	  
	public Cliente (String NIF, String nom, Fecha fNac, Fecha fAlta) {
		contadorGlobal++;  
		this.codCliente = contadorGlobal;
		this.nif = NIF;
		this.nombre = nom;
		this.fechaNac = fNac;
		this.fechaAlta = fAlta;  
	}

	public Cliente (String NIF, String nom, Fecha fNac) {
		contadorGlobal++;
		this.codCliente = contadorGlobal;
		this.nif = NIF;
		this.nombre = nom;
		this.fechaNac = fNac;
		this.fechaAlta = fechaPorDefecto;  
	}
  
	public Cliente (Cliente c) {
		this.nif = c.nif;
		this.nombre = c.nombre;
		this.fechaNac = c.fechaNac;
		this.fechaAlta = c.fechaAlta;
		
		contadorGlobal++;
	    this.codCliente = contadorGlobal;
	}
  
	@Override
	public Object clone() { 
		Cliente obj=null; 
	    try { 
	    	obj=(Cliente)super.clone(); //se llama al clone() de la clase base (Object) 
	        obj.fechaAlta = (Fecha) this.fechaAlta.clone();
	        
	        contadorGlobal++;      
	        obj.codCliente = contadorGlobal;
	    } catch(CloneNotSupportedException ex) {  
	        System.out.println(" no se puede duplicar"); 
	    } 
	    return obj; 
	} 
	
	
	@Override
	public String toString() {
	    return nif + " " + fechaNac + ": " + nombre + " (" + codCliente + " " + fechaAlta + ")";
	}
	
	@Override
	public void ver() {
	    System.out.println(this.toString());
	}
  
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null) return false;
	    	if (getClass() != obj.getClass()) return false; 
	    	Cliente other = (Cliente) obj;
	        if (nif == null) {
	            if (other.nif != null) return false;
	        } else if (!nif.equals(other.nif)) return false;
	       
	        return true;
	}

	public float factura() {return 0.0f;};

	public void setFechaAlta(Fecha newFechaAlta) {
		this.fechaAlta = newFechaAlta;
	}

	public void setNombre(String newNombre) {
		this.nombre = newNombre;
	}

	public Fecha getFechaNac() {
		return (Fecha) fechaNac.clone();
	}

	public Fecha getFechaAlta() {
		return (Fecha) fechaAlta.clone();
	}

	public String getNombre() {
		return nombre;
	}

	public String getNif() {
		return nif;
	}
  
} 
