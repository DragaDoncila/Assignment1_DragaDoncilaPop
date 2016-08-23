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


public class practice {
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
            NodeList nodeList = properties.getElementsByTagName("dict");

            for (int i = 1; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element card = (Element) node;

/*
* Need to treat differently:
* 1. skip filename/image
* 2. skip cards key
* 3. treat card_type value as value, NOT key
* 4. if the key is "occurrence", we need to get array "tag" NOT string "tag"
*
*
* */
                    for (int j = 0, y = 0, z = 0; j <= 11; ++j) {
                        String currentKey = card.getElementsByTagName("key").item(j).getTextContent();
                        switch (currentKey) {
                            case ("fileName"):
                            case ("imageName"):
                            case ("card_type"):
                            case ("play"):
                            case ("rule"):
                            case ("trump"):
                                break;
                            case ("occurrence"):
                                Node cardOccurrence = card.getElementsByTagName("array").item(0);
                                System.out.println("TYPING HERE" + cardOccurrence.getTextContent());
                                break;
                            default:
//                                NEED the 5th key in order, but the 3rd string in order
                                    System.out.println(currentKey);
                                    System.out.println(card.getElementsByTagName("string").item(j - 2).getTextContent());
                                    System.out.println();
                                }
                        }

                    }
                }
                System.out.println("------------------------------------------------------");

//            System.out.print(nodeList.getLength());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
