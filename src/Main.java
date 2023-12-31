/*
Acceso a documentos XML con la API DOM de Java
 */
public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        String opcion = "";
        do {
            System.out.println("0. Salir");
            System.out.println("1. Leer fichero XML con DOM");
            System.out.println("2. Escribir fichero XML con DOM");
            System.out.println("3. Leer fichero XML con SAX");

            opcion = libs.Leer.pedirCadena("Introduce una opción");
            switch (opcion) {
                case "0" -> {salir = true;}
                case "1" -> {code.LeerXMLconDOMClase.leerArchivo();}
                case "2" -> {code.EscribirXMLconDOMClase.escribir();}
                case "3" -> {code.LeerXMLconSAX.leerXML();}
                default -> {System.out.println("Opción incorrecta");}
            }
        }while (!salir);
        }

}