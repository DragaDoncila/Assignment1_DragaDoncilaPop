package Deck;

import Cards.Card;
import Cards.MineralCard;
import Cards.SupertrumpCard;
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

/**
 * Class uses static methods to build a Mineral Supertrumps deck of cards
 * Created by Draga on 23/08/2016.
 */
public class DeckBuilder {
    private static final String FILENAME = "MstCards_151021.plist";

    /**
     * Builds a deck from the given file and returns the completed deck object
     *
     * @return deck the deck object with cards
     */
    public static Deck buildDeckFromPlist() {
        Deck superTrumpsDeck = new Deck();
        File cardsFile = new File(FILENAME);
        //making a doc builder to help parse the XML into a DOM
        DocumentBuilderFactory factoryBuilder = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuild = factoryBuilder.newDocumentBuilder();
            Document properties = docBuild.parse(cardsFile);
            properties.getDocumentElement().normalize();
            //made list of all the nodes in the document with the tag dict. These are all the cards, each list element is 1 card.
            NodeList deckList = properties.getElementsByTagName("dict");
            //Last three cards in deck are rule cards so iterate only to 60
            for (int i = 1; i <= 60; ++i) {
                ArrayList<String> valArray = new ArrayList<>();
                //Accessing just one card at a time. Checking that the node is an element node, and typecasting.
                Node cardNode = deckList.item(i);
                if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element card = (Element) cardNode;

                    NodeList childNodes = card.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node attributeValNode = childNodes.item(j);
                        String attributeValName = attributeValNode.getNodeName();
                        switch (attributeValName) {
                            //The current attribute is the occurrence array
                            case "array":
                                ArrayList<String> occurrenceArray = new ArrayList<>();
                                NodeList arrayStrings = attributeValNode.getChildNodes();
                                for (int k = 0; k < arrayStrings.getLength(); ++k) {
                                    Node stringNode = arrayStrings.item(k);
                                    //if the current node is a String node
                                    if (stringNode.getNodeType() == Node.ELEMENT_NODE && stringNode.getNodeName().equals("string")) {
                                        Element occurrenceString = (Element) stringNode;
                                        //get its text content and add it to the occurrence array
                                        String occurrenceText = occurrenceString.getTextContent();
                                        occurrenceArray.add(occurrenceText);
                                    }
                                }
                                //add the whole array as one element to the values array
                                valArray.add(occurrenceArray.toString());
                                break;
                            //the majority of attributes are string
                            case "string":
                                valArray.add(attributeValNode.getTextContent());
                                break;
                            case "key":
                                String keyText = attributeValNode.getTextContent();
                                //Only these words are values stored as <key>, the rest are all geniune keys & skipped.
                                if (keyText.equals("play") || keyText.equals("trump") || keyText.equals("rule")) {
                                    valArray.add(attributeValNode.getTextContent());
                                }
                                break;
                        }
                    }
                    //The first 54 cards in the deck are mineral cards
                    if (i <= 54) {
                        Card tempCard = new MineralCard(valArray);
                        superTrumpsDeck.addCard(tempCard);
                    }
                    //The rest are trump cards
                    else {
                        Card tempCard = new SupertrumpCard(valArray);
                        superTrumpsDeck.addCard(tempCard);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error when parsing plist");
        }
        return superTrumpsDeck;
    }

}
