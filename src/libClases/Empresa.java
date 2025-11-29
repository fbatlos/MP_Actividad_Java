package libClases;

import java.util.ArrayList;
import java.util.Scanner;

public class Empresa implements Cloneable, Proceso {
    
    private ArrayList<Cliente> clientes;

    public Empresa() {
        this.clientes = new ArrayList<>();
    }

   
    public void alta(Cliente c) {
        boolean existe = false;
        for (Cliente cliente : clientes) {
            if (cliente.getNif().equals(c.getNif())) {
                existe = true;
                break;
            }
        }
        
        if (existe) {
            System.out.println("Ya existe un Cliente con ese dni: " + c.toString());
        } else {
            clientes.add(c);
        }
    }

    public void baja(String nif) {
        boolean borrado = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNif().equals(nif)) {
                clientes.remove(i);
                borrado = true;
                break; 
            }
        }
        if (borrado)
             System.out.println("El cliente con nif " + nif + " ha sido eliminado");
    }

 
    public void descuento(float dto) {
        for (Cliente c : clientes) {
            if (c instanceof ClienteMovil) {
                ClienteMovil cm = (ClienteMovil) c;
                float precioActual = cm.getPrecio();
                float nuevoPrecio = precioActual - (precioActual * dto / 100f);
                cm.setPrecio(nuevoPrecio);
            }
        }
    }

    public float factura() {
        float total = 0f;
        for (Cliente c : clientes) {
            total += c.factura(); 
        }
        return total;
    }

    public int nClienteMovil() {
        int cont = 0;
        for (Cliente c : clientes) {
            if (c instanceof ClienteMovil) {
                cont++;
            }
        }
        return cont;
    }
    
    public int getN() {
        return clientes.size();
    }

    public void alta() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("DNI: ");
            String dni = sc.next();
            sc.nextLine();
            
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            
            System.out.println("Fecha Nacimiento: ");
            Fecha fNac = Fecha.pedirFecha();
            
            System.out.println("Fecha de Alta: ");
            Fecha fAlta = Fecha.pedirFecha();
            
            System.out.print("Minutos que habla al mes: ");
            float minutos = Float.parseFloat(sc.next().replace(',', '.'));
            
            System.out.print("Indique tipo de cliente (1-Movil, 2-Tarifa Plana): ");
            int tipo = sc.nextInt();
            
            Cliente nuevoCliente = null;
            
            if (tipo == 1) {
                System.out.print("Precio por minuto: ");
                float precio = Float.parseFloat(sc.next().replace(',', '.'));
                
                System.out.println("Fecha fin permanencia: ");
                Fecha fPerm = Fecha.pedirFecha();
                
                nuevoCliente = new ClienteMovil(dni, nombre, fNac, fAlta, fPerm, minutos, precio);
                
            } else if (tipo == 2) {
                System.out.print("Nacionalidad: ");
                String nac = sc.next();
                
                nuevoCliente = new ClienteTarifaPlana(dni, nombre, fNac, fAlta, minutos, nac);
            }
            
            if (nuevoCliente != null) {
                this.alta(nuevoCliente);
            }
            
        } catch (Exception e) {
            System.out.println("Error en la introducción de datos.");
        }
    }

    public void baja() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca nif cliente a dar de baja: ");
        String nif = sc.next();
        
        Cliente candidato = null;
        for (Cliente c : clientes) {
            if (c.getNif().equals(nif)) {
                candidato = c;
                break;
            }
        }
        
        if (candidato != null) {
            System.out.println(candidato.toString()); // Mostramos datos [cite: 261]
            System.out.print("¿Seguro que desea eliminarlo (s/n)? ");
            String respuesta = sc.next();
            
            if (respuesta.equalsIgnoreCase("s")) {
                this.baja(nif);
            } else {
                System.out.println("El cliente con nif " + nif + " no se elimina");
            }
        } else {
            System.out.println("No existe cliente con ese NIF.");
        }
    }


    @Override
    public String toString() {
        String s = "";
        for (Cliente c : clientes) {
            s += c.toString() + "\n";
        }
        return s;
    }

    @Override
    public void ver() {
        System.out.println(this.toString());
    }

    @Override
    public Object clone() {
        Empresa obj = null;
        try {
            obj = (Empresa) super.clone();
            
            obj.clientes = new ArrayList<>();
            for (Cliente c : this.clientes) {
                obj.clientes.add((Cliente) c.clone());
            }
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar la empresa");
        }
        return obj;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true; 
    }
}