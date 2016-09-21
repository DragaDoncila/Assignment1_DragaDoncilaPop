package Cards;

import java.util.ArrayList;
import Trumps.Trump;

/**Class extends Cards.MineralCard and handles supertrump objects.
 * Created by Draga on 6/09/2016.
 */
public class SupertrumpCard extends Card{

    public enum CardEffects {SET_CLEAVAGE, SET_CRUSTAL_ABUNDANCE, SET_ECON_VALUE, SET_SPECIFIC_GRAVITY, SET_HARDNESS, WILDCARD}
    CardEffects cardEffect;
    String cardInfo;



    public SupertrumpCard(ArrayList<String> attributes){
        super(attributes);
        setCardEffect(attributes.get(4));
    }

    public void setCardEffect(String cardEffect) {
        cardEffect = cardEffect.replaceAll("\\s+", "").toLowerCase();
        this.cardInfo = cardEffect;
        switch (cardEffect){
            case "cleavage":
                this.cardEffect = CardEffects.SET_CLEAVAGE;
                break;
            case "crustalabundance":
                this.cardEffect = CardEffects.SET_CRUSTAL_ABUNDANCE;
                break;
            case "economicvalue":
                this.cardEffect = CardEffects.SET_ECON_VALUE;
                break;
            case "hardness":
                this.cardEffect = CardEffects.SET_HARDNESS;
                break;
            case "specificgravity":
                this.cardEffect = CardEffects.SET_SPECIFIC_GRAVITY;
                break;
//            default:
//                this.cardEffect = CardEffects.WILDCARD;
//                break;

        }
    }

    @Override
    public boolean canPlayOn(int countRounds, Card lastPlayedCard, Trump.TrumpCategories currentCategory){
        return true;
    }

    @Override
    public String getInfo() {
        return cardInfo;
    }
}
