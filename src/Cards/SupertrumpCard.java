package Cards;

import java.util.ArrayList;

import Trumps.Trump;

/**
 * Class extends Card and implements all fields and methods necessary for a Mineral Supertrumps Supertrump card
 * Created by Draga on 6/09/2016.
 */
public class SupertrumpCard extends Card {

    private String cardEffect;
    private String cardInfo;

    /**
     * Constructor takes an array of attributes and parses this array into the relevant fields
     *
     * @param attributes variable string array
     */
    public SupertrumpCard(ArrayList<String> attributes) {
        super(attributes);
        setCardEffect(attributes.get(4));
    }

    @Override
    public String getTrumpVal(Trump.TrumpCategories category) {
        return null;
    }

    @Override
    public String getInfo() {
        return cardInfo;
    }

    /**
     * Sets the effect the card will have when played based on the info string
     *
     * @param cardEffect the information string
     */
    private void setCardEffect(String cardEffect) {
        cardEffect = cardEffect.replaceAll("\\s+", "").toLowerCase();
        this.cardInfo = cardEffect;
        switch (cardEffect) {
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

    /**
     * Returns true if the title this card is The Geologist, otherwise false
     * @return boolean of comparison
     */
    @Override
    public boolean isGeologist() {
        return this.getTitle().equals("The Geologist");
    }

    /**
     * Returns true as Supertrump cards can play on anything
     *
     * @param lastPlayedCard  the card last played in the game
     * @param currentCategory the category currently in play
     * @return true
     */
    @Override
    public boolean canPlayOn(Card lastPlayedCard, Trump.TrumpCategories currentCategory) {
        return true;
    }

    public String toString() {
        return super.toString() + String.format("%-30s", "Card Effect: ") + cardEffect + "\n------------------------------------------\n";

    }


}
