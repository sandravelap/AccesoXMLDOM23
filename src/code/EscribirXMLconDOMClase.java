package code;

import classes.Coche;
import libs.CheckFiles;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class EscribirXMLconDOMClase {
    public static void escribir(){
        //creamos los archivos Coche a escribir
        Coche coche1 = new Coche(1, "Peugeot", "306", 1.9 );
        Coche coche2 = new Coche(2, "Mercedes", "GLA", 1.7 );
        Coche coche3 = new Coche(3, "Renault", "Twingo", 1.1 );
        ArrayList<Coche> coches = new ArrayList<Coche>();
        coches.add(coche1);
        coches.add(coche2);
        coches.add(coche3);
        //pedimos la ruta del archivo donde vamos a escribir:
        //Path p = Path.of("introduzca la ruta del archivo a escribir: ");
        Path p = Path.of("src/resources/concesionario3.xml");
        //Nos creamos la instancia de Document que representa un archivo xml

        if (CheckFiles.ficheroEscribible(p)){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder parser = factory.newDocumentBuilder();
                //creamos el Document vacío
                Document document = parser.newDocument();
                //metemos en el Document los elemento de xml
                //elemento raíz
                Node nodoRaiz = document.createElement("coches");
                //lo colocamos colgando del documento, por eso es el raíz
                document.appendChild(nodoRaiz);
                //creamos tantos elementos coche como coches tenemos
                for (Coche coche : coches){
                    //creamos una etiqueta coche por coche
                    Element etiqCoche = document.createElement("coche");
                    //colgamos la etiqueta del elemento "coches" o raíz
                    nodoRaiz.appendChild(etiqCoche);
                    //creamos las etiquetas que contiene Coche
                    Element etiqMarca = document.createElement("marca");
                    etiqCoche.appendChild(etiqMarca);
                    //añadimos contenido al elemento marca con un nodo de texto
                    //Node txtMarca = document.createTextNode(coche.getMarca());
                    //etiqMarca.appendChild(txtMarca);
                    etiqMarca.appendChild(document.createTextNode(coche.getMarca()));
                    Element etiqModelo = document.createElement("modelo");
                    etiqCoche.appendChild(etiqModelo);
                    etiqModelo.appendChild(document.createTextNode(coche.getModelo()));

                    Element etiqCilin = document.createElement("cilindrada");
                    etiqCoche.appendChild(etiqCilin);
                    etiqCilin.appendChild(document.createTextNode(String.valueOf(coche.getCilindrada())));

                    etiqCoche.setAttribute("id", String.valueOf(coche.getId()));
                }
                //vamos a pasar nuestro Document al archivo XML:
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                Source source = new DOMSource(document);
                Result result = new StreamResult(p.toFile());
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http:xml.apache.org/xslt}indent-amount", "4");
                transformer.transform(source,result);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (TransformerConfigurationException e) {
                throw new RuntimeException(e);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
