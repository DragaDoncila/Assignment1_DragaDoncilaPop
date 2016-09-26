package Cards;

import java.util.ArrayList;
import Trumps.Trump;

/**Class extends Cards.MineralCard and handles supertrump objects.
 * Created by Draga on 6/09/2016.
 */
public class SupertrumpCard extends Card{

    String cardEffect;
    String cardInfo;


    @Override
    public boolean isGeologist() {
        return this.getTitle().equals("The Geologist");
    }

    public SupertrumpCard(ArrayList<String> attributes){
        super(attributes);
        setCardEffect(attributes.get(4));
    }

    public void setCardEffect(String cardEffect) {
        cardEffect = cardEffect.replaceAll("\\s+", "").toLowerCase();
        this.cardInfo = cardEffect;
        switch (cardEffect){
            case "cleavage":
                this.cardEffect = "Set to Cleavage";
                break;
            case "crustalabundance":
                this.cardEffect = "Set to Crustal Abundance";
                break;
            case "economicvalue":
                this.cardEffect = "Set to Economic Value";
                break;
            case "hardness":
                this.cardEffect = "Set to Hardness";
                break;
            case "specificgravity":
                this.cardEffect = "Set to Specific Gravity";
                break;
            default:
                this.cardEffect = "Set to Trump of Your Choice";
                break;

        }
    }

    @Override
    public boolean canPlayOn(Card lastPlayedCard, Trump.TrumpCategories currentCategory){
        return true;
    }

    @Override
    public String getInfo() {
        return cardInfo;
    }

    public String toString(){
        return super.toString() + String.format("%-30s", "Card Effect: ") + cardEffect + "\n------------------------------------------\n";

    }

    @Override
    public String getTrumpVal(Trump.TrumpCategories category) {
        return null;
    }
}
