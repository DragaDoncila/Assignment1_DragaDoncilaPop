/**
 * Created by Draga on 12/09/2016.
 */
public class ComparisonTests {
    public static void main(String[] args) {
        Deck myDeck = DeckBuilder.buildDeck();
//        Hardness myHardness = new Hardness("6.5-5");
//        SpecificGravity myGravity = new SpecificGravity("3-4");
//        CrustalAbundance myCrustal = new CrustalAbundance("low");
        EconomicValue myEcon = new EconomicValue("low");
        int i = 1;
        for (Card card :
                myDeck.cards) {
            if (card instanceof MineralCard){
//                Hardness yourHardness = ((MineralCard) card).getHardness();
//                System.out.println(yourHardness);
//                System.out.println(i + ": " + ((MineralCard) card).getHardness().isHigherThan(myHardness));
//                SpecificGravity yourGravity = ((MineralCard) card).getSpecificGravity();
//                System.out.println(yourGravity);
//                System.out.println(yourGravity.gravityVal);
//                Cleavage yourCleavage = ((MineralCard) card).getCleavage();
//                CrustalAbundance yourCrustal = ((MineralCard) card).getCrustalAbundance();
                EconomicValue yourEcon = ((MineralCard) card).getEconomicValue();
                System.out.println(i + "Mine: " + myEcon);
                System.out.println(i + "Yours: " + yourEcon);
                System.out.println(yourEcon.isHigherThan(myEcon));
                ++i;
            }
        }
    }
}
