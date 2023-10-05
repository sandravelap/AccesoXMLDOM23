package code;

import classes.Coche;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Array;
import java.util.ArrayList;

public class CocheHandlerClase extends DefaultHandler {
    //variable donde almacenar coches
    private ArrayList<Coche> coches = new ArrayList<Coche>();
    //coche que leo y que añado al arraylist
    private Coche cocheAux;
    //para almacenar el texto contenido en un nodo texto
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<Coche> getCoches() {
        return coches;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName){
            case "coches":
                break;
            case "coche":
                cocheAux = new Coche();
                break;
            case "marca", "modelo", "cilindrada":
                buffer.delete(0,buffer.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName){
            case "coches":
                break;
            case "coche":
                coches.add(cocheAux);
                break;
            case "marca":
                cocheAux.setMarca(buffer.toString());
                break;
            case "modelo":
                cocheAux.setModelo(buffer.toString());
                break;
            case "cilindrada":
                cocheAux.setCilindrada(Double.parseDouble(buffer.toString()));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }
}
