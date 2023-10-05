package code;

import classes.Coche;
import libs.Leer;
import libs.CheckFiles;
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
import java.util.ArrayList;

import static libs.CheckFiles.ficheroEscribible;

public class LeerXMLconDOMClase {
    public static void leerArchivo(){
        //pedir la ruta del archivo xml
        //Path p = Path.of(Leer.pedirCadena("Introduzca la ruta del archivo xml a leer: "));
        Path p = Path.of("src/resources/concesionario.xml");
        //comprobaciones de archivo (existe, se puede leer, hay permisos, es un xml...
        if (Files.isReadable(p)){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder parser = factory.newDocumentBuilder();
                Document document = parser.parse(p.toFile());
                //ahora ya accedemos al contenido del xml, con nodos o con elementos
                Element elRaiz = document.getDocumentElement();
                System.out.println("El elemento raíz del archivo es " + elRaiz.getNodeName());
                NodeList nodosDoc = elRaiz.getChildNodes();
                for (int i=0;i<nodosDoc.getLength();i++){
                    //recorro los nodos elemento del nodo raíz concesionario -- <coche>
                    if(nodosDoc.item(i).getNodeType()== Node.ELEMENT_NODE) {
                        //recorro de nuevo los nodos elemento de <coche>
                        System.out.println("Coche " + (i + 1));
                        NodeList datosCoche = nodosDoc.item(i).getChildNodes();
                        for (int j=0;j<datosCoche.getLength();j++) {
                            //solo recorre los nodos elemento
                            if (datosCoche.item(j).getNodeType()==Node.ELEMENT_NODE) {
                                //printeo la etiqueta
                                System.out.println(nodosDoc.item(i).getNodeName());
                                //printeo el contenido de texto de la etiqueta
                                System.out.println(nodosDoc.item(i).getTextContent());
                                System.out.println("");
                            }
                        }
                    }
                }
                //y si leemos conociendo la estructura del documento, podemos acceder directamente
                //a los nodos con la información que nos interesa.
                NodeList marcas = elRaiz.getElementsByTagName("marca");
                NodeList modelos = elRaiz.getElementsByTagName("modelo");
                NodeList cilindradas = elRaiz.getElementsByTagName("cilindrada");
                NodeList coches = elRaiz.getElementsByTagName("coche");
                ArrayList<Coche> arrayCoches = new ArrayList<Coche>();
                Coche cocheAux;
                for (int i=0; i<marcas.getLength();i++){
                    System.out.println("Coche"+(i+1));
                    cocheAux = new Coche();
                    Element coche = (Element) coches.item(i);
                    System.out.println("\t id: " + coche.getAttribute("id"));
                    cocheAux.setId(Integer.valueOf(coche.getAttribute("id")));
                    System.out.println("\t Marca: " + marcas.item(i).getTextContent());
                    cocheAux.setMarca(marcas.item(i).getTextContent());
                    System.out.println("\t Modelo: " + modelos.item(i).getTextContent());
                    cocheAux.setModelo(modelos.item(i).getTextContent());
                    System.out.println("\t Cilindrada: " + cilindradas.item(i).getTextContent());
                    cocheAux.setCilindrada(Double.parseDouble(cilindradas.item(i).getTextContent()));
                    arrayCoches.add(cocheAux);
                }
                //para comprobar
                for (Coche c : arrayCoches){
                    System.out.println(c.getMarca());
                }
                escribirCoches(arrayCoches);
            } catch (ParserConfigurationException e) {
                System.out.println("No se ha podido crear el document builder");;
            } catch (IOException e) {
                System.out.println("Algo ha pasado leyendo el archivo");
            } catch (SAXException e) {
                System.out.println("No se ha podido parsear el archivo");
            }

        }
    }

    private static void escribirCoches(ArrayList<Coche> arrayCoches) {
        //pedimos el nombre del archivo a crear
        //Path p = Path.of(Leer.pedirCadena("Introduce la ruta del archivo a crear: "));
        //por practicidad para probar
        Path p = Path.of("src/resources/concesionario2.xml");
        //comprobamos que el fichero nos vale
        if (ficheroEscribible(p)){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder parser = factory.newDocumentBuilder();
                Document document = parser.newDocument();

            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
