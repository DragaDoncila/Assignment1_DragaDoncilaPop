package Cards;

import java.util.ArrayList;
import Trumps.Trump;

/**Class designed to handle generic card objects.
 * Created by Draga on 6/09/2016.
 */
public abstract class Card {

    public abstract boolean isGeologist();

    private enum CardTypes {PLAY, TRUMP}
    private String filename;
    private String imagename;
    private CardTypes cardType;
    private String title;

    public CardTypes getCardType() {
        return cardType;
    }

    Card(ArrayList<String> attributes){
        this.filename = attributes.get(0);
        this.imagename = attributes.get(1);
        String cardTypeStr = attributes.get(2);
        if (cardTypeStr.equals("play")){
            this.cardType = CardTypes.PLAY;
        }
        else{
            this.cardType = CardTypes.TRUMP;
        }
        this.title = attributes.get(3);

    }

    private String getFilename() {
        return filename;
    }

    private void setFilename(String filename) {
        this.filename = filename;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTrump(){
        if (this.cardType == CardTypes.TRUMP){
            return true;
        }
        else return false;
    }

    public boolean isMineral(){
        if (this.cardType == CardTypes.PLAY){
            return true;
        }
        else return false;
    }

    @Override
    public String toString(){
        return "---------------------------------------------------------------------\n" +
                title +
                "\n" + "---------------------------------------------------------------------\n";
    }

    public abstract boolean canPlayOn(Card lastPlayedCard, Trump.TrumpCategories currentCategory);

    public abstract String getInfo();

    public abstract String getTrumpVal(Trump.TrumpCategories category);

}
