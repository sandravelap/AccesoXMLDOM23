package code;

import classes.Coche;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class CocheHandler extends DefaultHandler {
    private ArrayList<Coche> coches = new ArrayList<Coche>();//para almacenar lo que encontremos
    private Coche c; //auxiliar para crearla y almacenarla en el array
    private StringBuilder buffer = new StringBuilder(); //para almacenar los nodos de texto.

    public ArrayList<Coche> getCoches() {
        return coches;
    }

    public void setCoches(ArrayList<Coche> coches) {
        this.coches = coches;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }
}
