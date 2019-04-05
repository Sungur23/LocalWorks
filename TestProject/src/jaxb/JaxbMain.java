package jaxb;

import java.io.File;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbMain {
	private static final String FILE_NAME = "jaxb-prdct.xml";

    public static void main(String[] args) {
        Product prdct = new Product(0, "PC", "ASUS");
        prdct.setUserList(Arrays.asList(
        		new User(0,"Ahmet","Erkek",25), new User(1,"Serap","Kadýn",27)
        		));
        
        jaxbObjectToXML(prdct);

        Product prdctFromFile = jaxbXMLToObject();
        System.out.println(prdctFromFile.toString());
    }


    private static Product jaxbXMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Unmarshaller un = context.createUnmarshaller();
            Product prdct = (Product) un.unmarshal(new File(FILE_NAME));
            return prdct;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void jaxbObjectToXML(Product prdct) {

        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // m.marshal(prdct, System.out);

            // Write to File
            m.marshal(prdct, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
