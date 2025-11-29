package libClases;

public class ClienteMovil extends Cliente {
	private Fecha fechaPermanencia;
	private float minHablados;
	private float precioMinHablado;
	

	
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, Fecha fPermanencia, float minHabla, float precioMinHabla) {
		super(NIF, nom, fNac, fAlta);
		fechaPermanencia = fPermanencia;
		minHablados = minHabla;
		precioMinHablado = precioMinHabla;
		
	}

	public ClienteMovil(String NIF, String nom, Fecha fnac, float minHabla, float precioMinHabla) {
		super(NIF,nom,fnac);
		Fecha alta = this.getFechaAlta();
		fechaPermanencia = new Fecha(alta.getDia(), alta.getMes(), alta.getAnio()+1);
		minHablados = minHabla;
		precioMinHablado = precioMinHabla;
	}

	public void setFPermanencia(Fecha fPermanencia) {
		fechaPermanencia = fPermanencia;
		
	}

	public Fecha getFPermanencia() {
		return fechaPermanencia;
	}
	
	public float getMinutos() { return minHablados; }
    public void setMinutos(float m) { minHablados = m; }
    
    public float getPrecio() { return precioMinHablado; }
    public void setPrecio(float p) { precioMinHablado = p; }
    
    
    @Override
    public Object clone() {
      
        ClienteMovil obj = (ClienteMovil) super.clone();
        obj.fechaPermanencia = (Fecha) this.fechaPermanencia.clone();
        return obj;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClienteMovil) {
            return super.equals(obj); 
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " " + fechaPermanencia + " " + minHablados + " x " + precioMinHablado;
    }
    
    @Override
    public float factura() {
        return precioMinHablado * minHablados;
    }

}
