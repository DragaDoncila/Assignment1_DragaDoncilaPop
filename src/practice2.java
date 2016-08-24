/**Practising parsing an XML plist file
 * Created by Draga on 23/08/2016.
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class practice2 {
    public static void main(String[] args) {
//        Open the plist file as a file
        File cardsFile = new File("MstCards_151021.plist");
//        making a doc builder to help parse the XML into a DOM
        DocumentBuilderFactory factoryBuilder = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuild = factoryBuilder.newDocumentBuilder();
            Document properties = docBuild.parse(cardsFile);
            properties.getDocumentElement().normalize();
//         made list of all the nodes in the document with the tag dict
            NodeList deckList = properties.getElementsByTagName("dict");

            for (int i = 1; i < deckList.getLength(); ++i) {
                Node cardNode = deckList.item(i);
                if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element card = (Element) cardNode;

                    NodeList allAttributes = card.getElementsByTagName("key");
                    for (int j = 0; j < allAttributes.getLength(); ++j) {

                        Node attributeNode = allAttributes.item(j);
//                        NodeList childNodes = card.getChildNodes();
                        if (attributeNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element attribute = (Element) attributeNode;
                            System.out.println(attribute.getTextContent());
//                            for (int k = 0; k < childNodes.getLength(); k++){
//                                Node attributeValNode = childNodes.item(k);
//                                if (attributeValNode.getNodeName().equals("string")){
//                                    System.out.println("You're a child   " + attributeValNode.getTextContent());
//                                }

                        }


                    }
                    System.out.println("***********This is the end of your keys******************");
                    NodeList childNodes = card.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node attributeValNode = childNodes.item(j);
                        if (attributeValNode.getNodeName().equals("array")){
                            System.out.println("You've found the array");
                        }
                        else if (attributeValNode.getNodeName().equals("string")) {
                            System.out.println(attributeValNode.getTextContent());


                        }
                    }
                    System.out.println("------------------------------------------------------");
                }
            }

//            System.out.print(deckList.getLength());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
