/*
Acceso a documentos XML con la API DOM de Java
 */
package code;

import libs.Leer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeerXMLconDOM {
    public static void leerArchivo(){
        //pedimos la ruta del archivo a leer
        Path p = Path.of(Leer.pedirCadena("Introduzca la ruta del archivo a leer: "));
        //comprobamos si el archivo existe y se puede leer
        if (Files.isReadable(p)){
            //Esta clase abstracta permite crear el parseador de XML a DOM de Java
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try{
                //El parseador permite crear un objeto Document que represente el archivo xml
                DocumentBuilder parser = factory.newDocumentBuilder();
                Document document = parser.parse(p.toFile());
                //obtener el nombre del nodo raíz:
                String nodoRaiz = document.getDocumentElement().getNodeName();
                System.out.println("El elemento raíz del archivo " + p.getFileName() + " es " + nodoRaiz);
                //obtener los nodos hijos:
                Element elRaiz = document.getDocumentElement();
                NodeList hijos = elRaiz.getChildNodes();
                //obtener información de los nodos hijos si no sabemos la estructura del XML:
                System.out.println("El nodo raíz " + nodoRaiz + " tiene " + hijos.getLength() + " hijos");
                //NodeList no admite ser recorrido con una estructura for each:
                for (int i=0; i< hijos.getLength(); i++){
                    //como conozco la estructura, me quedo sólo con el tipo de nodo que me interesa
                    //si elimino este condicional me saldrán nodos vacíos
                    if (hijos.item(i).getNodeType()==Node.ELEMENT_NODE) { //O 1
                        System.out.println("Nodo " + (i + 1) + ": " + hijos.item(i).getNodeName());
                        NodeList datosCoche = hijos.item(i).getChildNodes();
                        for (int j=0; j<datosCoche.getLength(); j++){
                            if(datosCoche.item(j).getNodeType()==Node.ELEMENT_NODE) {
                                System.out.println("\t" + datosCoche.item(j).getNodeName() + ":  " +
                                        datosCoche.item(j).getChildNodes().item(0).getNodeValue());
                            }
                        }
                    }
                }
                //Si sabemos la estructura, utilizamos las etiquetas de los nodos para acceder al contenido que nos interesa:
                NodeList coches = document.getElementsByTagName("coche");
                System.out.println("El concesionario tiene " + coches.getLength() + " coches.");
                for (int i=0; i<coches.getLength(); i++){
                    //Para buscar nodos por etiqueta debo hacerlo sobre un elemento, no sobre un nodo
                    Element coche = (Element) coches.item(i);
                    System.out.println("Coche " + (i+1) + " (id "+ coche.getAttribute("id") + "):");
                    System.out.println("\t Marca: " + coche.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("\t Model: " + coche.getElementsByTagName("modelo").item(0).getTextContent());
                    System.out.println("\t Cilindrada: " + coche.getElementsByTagName("cilindrada").item(0).getTextContent());
                }
            } catch (ParserConfigurationException e) {
                System.out.println("No se ha podido crear el parser de DOM");;
            } catch (IOException e) {
                System.out.println("No se ha podido acceder al fichero");;
            } catch (SAXException e) {
                System.out.println("Ha habido un error al parsear el archivo XML.");;
            }
        }
    }


}
