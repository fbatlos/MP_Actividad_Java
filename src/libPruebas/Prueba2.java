package libPruebas;

import libClases.*;

public class Prueba2 {
    public static void main(String[] args) {
        // 1. Creación de Fechas y Clientes sueltos
        Fecha f1 = new Fecha(29, 2, 2001), f2 = new Fecha(f1), f3 = new Fecha(29, 2, 2004);
        Fecha fnac1 = new Fecha(7, 3, 1980), fnac2 = new Fecha(27, 06, 1995);
        
        System.out.println("Fechas: " + f1 + ", " + f2 + ", " + f3);
        
        ClienteTarifaPlana[] ct = new ClienteTarifaPlana[4];
        
        ClienteMovil cm1 = new ClienteMovil("547B", "Luis Perez", fnac2, 50.50f, 0.03f);
        ClienteMovil cm2 = (ClienteMovil) cm1.clone(); // lo crea con los mismos datos que cm1
        ClienteMovil cm3 = new ClienteMovil("777F", "Joe Sam", fnac2.diaSig(), 50.50f, 0.0f);
        
        ct[0] = new ClienteTarifaPlana("805W", "Luz Casal", fnac1, f3, 375.09f, "Española");
        ct[1] = new ClienteTarifaPlana("953H", "Paz Padilla", fnac2, f2, 290.00f, "Española");
        ct[2] = new ClienteTarifaPlana("106T", "Elton John", fnac2, 340.75f, "Inglesa");
        ct[3] = new ClienteTarifaPlana("467X", "Messi", fnac2.diaSig(), 300.00f, "Argentina");

        System.out.println("Codigos: " + cm1.getCodCliente() + ", " + cm2.getCodCliente() + ", "
                + ct[0].getCodCliente() + ", " + ct[2].getCodCliente() + "\n");

        // 2. Gestión de la Empresa
        Empresa g = new Empresa(), gcopia;
        
        g.alta(cm1);
        g.alta(ct[0]);
        g.alta(ct[2]);
        g.alta(cm2);
        g.alta(ct[1]);
        g.alta(cm3);
        
        
        g.alta(); 
        
        // Pide otro con nif 106T (debe fallar porque ya existe Elton John)
        g.alta(); 
        g.alta(ct[1]); // no lo debe de añadir ya que existe (Paz Padilla)

        System.out.println("Grupo g:\n" + g);

        g.baja("547B"); // elimina el cliente con nif 547B (Luis Perez)
        
        // --- INTERACCIÓN POR TECLADO ---
        g.baja(); 
        g.baja();
        // -------------------------------
        
        g.alta(cm2); // Vuelve a añadir a Luis Perez (clon)

        System.out.println("#####\nClientes del grupo g:");
        System.out.println(g + "Factura: " + g.factura() + "\n---\n");

        // 3. Clonación y modificación de la copia
        gcopia = (Empresa) g.clone();
        
        gcopia.baja("805W"); // Borra en copia
        gcopia.baja("106T"); // Borra en copia (ojo, el 106T original ya se borró antes, ver si existe)
        gcopia.alta(ct[3]);  // Añade a Messi en copia
        
        g.baja("953H"); // elimina el cliente con 953H en ORIGINAL (Paz Padilla)

        gcopia.descuento(50); // Aplica 50% descuento a móviles en COPIA

        System.out.println("Grupo g:\n" + g + "\nGrupo gcopia:\n" + gcopia + "\n");
        
        System.out.println("g tiene " + g.getN() + " clientes y gcopia " + gcopia.getN());
        System.out.print("g tiene " + g.nClienteMovil() + " clientes de Tarifa Movil ");
        System.out.println("y " + (g.getN() - g.nClienteMovil()) + " de Tarifa Plana");
    }
}
