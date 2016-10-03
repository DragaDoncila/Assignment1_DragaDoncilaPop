package Trumps;

import Cards.Card;
import Cards.MineralCard;
import Deck.Deck;
import Deck.DeckBuilder;

/**
 * Class used to test instantiation and comparison methods of trump category classes
 * Created by Draga on 12/09/2016.
 */
public class ComparisonTests {
    public static void main(String[] args) {
        Deck myDeck = DeckBuilder.buildDeckFromPlist();
//        Trumps.Hardness myHardness = new Trumps.Hardness("6.5-5");
//        Trumps.SpecificGravity myGravity = new Trumps.SpecificGravity("3-4");
//        Trumps.CrustalAbundance myCrustal = new Trumps.CrustalAbundance("low");
        EconomicValue myEcon = new EconomicValue("low");
        int i = 1;
        for (Card card :
                myDeck.getCards()) {
            if (card instanceof MineralCard) {
//                Trumps.Hardness yourHardness = ((Cards.MineralCard) card).getHardness();
//                System.out.println(yourHardness);
//                System.out.println(i + ": " + ((Cards.MineralCard) card).getHardness().isHigherThan(myHardness));
//                Trumps.SpecificGravity yourGravity = ((Cards.MineralCard) card).getSpecificGravity();
//                System.out.println(yourGravity);
//                System.out.println(yourGravity.gravityVal);
//                Trumps.Cleavage yourCleavage = ((Cards.MineralCard) card).getCleavage();
//                Trumps.CrustalAbundance yourCrustal = ((Cards.MineralCard) card).getCrustalAbundance();
                EconomicValue yourEcon = ((MineralCard) card).getEconomicValue();
                System.out.println(i + "Mine: " + myEcon);
                System.out.println(i + "Yours: " + yourEcon);
                System.out.println(yourEcon.isHigherThan(myEcon));
                ++i;
            }
        }
    }
}
