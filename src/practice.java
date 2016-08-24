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
            NodeList deckList = properties.getElementsByTagName("dict");

            for (int i = 1; i < deckList.getLength(); ++i) {
                Node cardNode = deckList.item(i);
                if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element card = (Element) cardNode;

/*
* Need to treat differently:
* 1. skip filename/image
* 2. skip cards key
* 3. treat card_type value as value, NOT key
* 4. if the key is "occurrence", we need to get array "tag" NOT string "tag"
*
*
* */
//                    for (int j = 0, y = 0, z = 0; j <= 11; ++j) {
//                        String currentKey = card.getElementsByTagName("key").item(j).getTextContent();
//                        switch (currentKey) {
//                            case ("fileName"):
//                            case ("imageName"):
//                            case ("card_type"):
//                            case ("play"):
//                            case ("rule"):
//                            case ("trump"):
//                                break;
//                            case ("occurrence"):
//                                Node cardOccurrence = card.getElementsByTagName("array").item(0);
//                                System.out.println("TYPING HERE" + cardOccurrence.getTextContent());
//                                break;
//                            default:
////                                NEED the 5th key in order, but the 3rd string in order
//                                    System.out.println(currentKey);
//                                    System.out.println(card.getElementsByTagName("string").item(j - 2).getTextContent());
//                                    System.out.println();
//                                }
//                        }
                        NodeList allAttributes = card.getChildNodes();
//                        for (int j = 0; j < allAttributes.getLength(); ++j){
//                            Node attribute = allAttributes.item(j);
//                            NodeList attributeNodes = attribute.getChildNodes();
//                            for (int k = 0; k < attributeNodes.getLength(); ++k){
//                                NodeList arrayValues = attributeNodes.item(k).getChildNodes();
//                                for (int l = 0; l < arrayValues.getLength(); ++l){
//                                    System.out.println(arrayValues.item(l).getNodeValue());
//                                }
//                                String attributeString = attributeNodes.item(k).getNodeValue();
//                                System.out.println(attributeString);
//                                System.out.println("*********");
//                            }
//                        }

                        for (int j = 0; j < allAttributes.getLength(); j += 2){
                            ArrayList cardAttributes = new ArrayList();
                            ArrayList eachAttribute = new ArrayList();
//                            System.out.println(allAttributes.item(j));
                            eachAttribute.add(allAttributes.item(j).getTextContent());
                            eachAttribute.add(allAttributes.item(j).getNextSibling().getTextContent());
                            System.out.println(eachAttribute);
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
