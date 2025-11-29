package libClases;

public class ClienteTarifaPlana extends Cliente {
	private String nacionalidad;
    private float minHablados; 

    private static float limiteMinutos = 300f; 
    private static float precioTarifa = 20f;
    private static final float PRECIO_EXCESO = 0.15f;

	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float minHabla, String nacion) {
		super(NIF,nom,fNac,fAlta);
		nacionalidad= nacion;
		minHablados= minHabla;
		
	}

	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float minHabla, String nacion) {
		super(NIF,nom,fNac);
		nacionalidad= nacion;
		minHablados= minHabla;
	}

	public void setNacionalidad(String nacion) {
		nacionalidad = nacion;
		
	}

	public void setMinutos(float minHabla) {
		minHablados= minHabla;
	}

	public static float getLimite() {
		return limiteMinutos;
	}

	public static void setTarifa(float limite , float precio) {
		limiteMinutos = limite;
		precioTarifa = precio;
	}

	public static float getTarifa() {
        return precioTarifa;
    }

	@Override
    public float factura() {
        float importe = precioTarifa;
        
        if (minHablados > limiteMinutos) {
            float exceso = minHablados - limiteMinutos;
            importe += (exceso * PRECIO_EXCESO);
        }
        return importe;
    }

    @Override
    public String toString() {
      
        return super.toString() + " " + nacionalidad + " [" + limiteMinutos + " por " + precioTarifa + "] " + minHablados + " --> " + this.factura();
    }

    @Override
    public Object clone() {
        ClienteTarifaPlana obj = (ClienteTarifaPlana) super.clone();
        return obj;
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
