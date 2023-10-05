package code;

import classes.Coche;
import libs.Leer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static libs.CheckFiles.ficheroEscribible;

public class LeerXMLconSAX {

    public static void leerXML(){
        //pedimos la ruta del archivo a leer
        //Path p = Path.of(Leer.pedirCadena("Introduzca la ruta del archivo a leer: "));
        Path p = Path.of("src/resources/concesionario3.xml");
        ArrayList<Coche> cochesXML = new ArrayList<Coche>();
        //comprobamos si el archivo existe y se puede crear
        if (Files.isReadable(p)){
            //creamos las instancias de SAX
            SAXParserFactory saxPF= SAXParserFactory.newInstance();
            CocheHandlerClase cocheHandler = new CocheHandlerClase();
            try {
                SAXParser parser = saxPF.newSAXParser();
                parser.parse(p.toFile(),cocheHandler);
                cochesXML = cocheHandler.getCoches();
                //para comprobar que se han cargado bien los coches del XML en mis objetos
                for (Coche c : cochesXML){
                    System.out.println(c.getMarca());
                }
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
