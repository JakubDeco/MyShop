package sk.kosickaakademia.deco.shop.receipt;


import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sk.kosickaakademia.deco.shop.Cart;
import sk.kosickaakademia.deco.shop.items.Item;
import sk.kosickaakademia.deco.shop.items.countable.CountItems;
import sk.kosickaakademia.deco.shop.items.services.Service;
import sk.kosickaakademia.deco.shop.items.weightable.WeightItems;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class XmlReceipt {
    public static void buildReceiptDoc(Cart cart){
        try {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document document=db.newDocument();

            //root element
            Element bill=document.createElement("bill");
            document.appendChild(bill);

            //date element
            LocalDateTime dateNow=LocalDateTime.now();
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Element date=document.createElement("date");
            bill.appendChild(date);
            date.appendChild(document.createTextNode(dateNow.format(dtf)));

            //time element
            dtf=DateTimeFormatter.ofPattern("HH:mm:ss");

            Element time=document.createElement("time");
            bill.appendChild(time);
            time.appendChild(document.createTextNode(dateNow.format(dtf)));

            //items element
            Element items=document.createElement("items");
            bill.appendChild(items);
            //count attribute
            items.setAttribute("count",String.valueOf(cart.getCount()));

            //create elements for cart items
            for (Item temp : cart.getCart()) {
                if (temp instanceof CountItems) {
                    countItemSubTree(temp, document, items);
                }
                if (temp instanceof WeightItems){
                    weightItemSubTree(temp, document, items);
                }
                if (temp instanceof Service){
                    serviceSubTree(temp, document, items);
                }
            }

            //totalPrice element
            Element totalPrice=document.createElement("totalPrice");
            bill.appendChild(totalPrice);
            totalPrice.appendChild(document.createTextNode(String.valueOf(cart.getTotalPrice())));

            //create xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("output/receipt.xml"));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void serviceSubTree(Item temp, Document document, Element items) {
        //item element
        Element item= document.createElement("item");
        items.appendChild(item);
        //item attribute
        item.setAttribute("type","service");

        //name element
        Element name= document.createElement("name");
        item.appendChild(name);
        item.appendChild(document.createTextNode(temp.getName()));

        //price element
        Element price= document.createElement("price");
        item.appendChild(price);
        String tmpString2=Double.toString(temp.getItemPrice());
        item.appendChild(document.createTextNode(tmpString2));
        //price attribute
        price.setAttribute("unit","eur");
    }

    private static void weightItemSubTree(Item temp, Document document, Element items) {
        //item element
        Element item= document.createElement("item");
        items.appendChild(item);
        //item attribute
        item.setAttribute("type","weight");

        //name element
        Element name= document.createElement("name");
        item.appendChild(name);
        item.appendChild(document.createTextNode(temp.getName()));

        //weight element
        Element weight= document.createElement("weight");
        item.appendChild(weight);
        String tmpString=Double.toString(((WeightItems)temp).getWeight());
        item.appendChild(document.createTextNode(tmpString));

        //pricePerKg element
        Element pricePerKg= document.createElement("pricePerKg");
        item.appendChild(pricePerKg);
        String tmpString1=Double.toString(temp.getPrice());
        item.appendChild(document.createTextNode(tmpString1));

        //price element
        Element price= document.createElement("price");
        item.appendChild(price);
        String tmpString2=Double.toString(temp.getItemPrice());
        item.appendChild(document.createTextNode(tmpString2));
        //price attribute
        price.setAttribute("unit","eur");
    }

    private static void countItemSubTree(Item temp, Document document, Element items) {
        //item element
        Element item= document.createElement("item");
        items.appendChild(item);
        //item attribute
        item.setAttribute("type","count");

        //name element
        Element name= document.createElement("name");
        item.appendChild(name);
        item.appendChild(document.createTextNode(temp.getName()));

        //count element
        Element count= document.createElement("count");
        item.appendChild(count);
        String tmpString=Integer.toString(((CountItems) temp).getCount());
        item.appendChild(document.createTextNode(tmpString));

        //pricePerUnit element
        Element pricePerUnit= document.createElement("pricePerUnit");
        item.appendChild(pricePerUnit);
        String tmpString1=Double.toString(temp.getPrice());
        item.appendChild(document.createTextNode(tmpString1));

        //price element
        Element price= document.createElement("price");
        item.appendChild(price);
        String tmpString2=Double.toString(temp.getItemPrice());
        item.appendChild(document.createTextNode(tmpString2));
        //price attribute
        price.setAttribute("unit","eur");
    }

    public static void printXmlReceipt(String filePath){
        try {
            File file=new File(filePath);
            DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc=db.parse(file);

            System.out.println("\nDate: "+doc.getElementsByTagName("date").item(0).getTextContent());
            System.out.println("Time: "+doc.getElementsByTagName("time").item(0).getTextContent());
            
            //print of receipt items
            NodeList itemsNL=doc.getElementsByTagName("item");
            for (int i = 0; i < itemsNL.getLength(); i++) {
                Node temp=itemsNL.item(i);

                NodeList itemNL=itemsNL.item(i).getChildNodes();
                for (int j = 0; j < itemNL.getLength(); j++) {
                    Node tempChild=itemNL.item(j);
                    if (!tempChild.getNodeName().equals("#text")) {
                        System.out.print(tempChild.getNodeName() + ": ");
                    }
                    if (!tempChild.getTextContent().equals("")){
                        System.out.print(tempChild.getTextContent() + ", ");
                    }

                }
                System.out.println();
            }


            System.out.println("Total price: "+doc.getElementsByTagName("totalPrice")
                    .item(0).getTextContent());




        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }
}
