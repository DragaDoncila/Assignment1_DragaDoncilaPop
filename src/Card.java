import java.util.ArrayList;

/**Class designed to handle generic card objects.
 * Created by Draga on 6/09/2016.
 */
public class Card {

    enum cardTypes{PLAY, TRUMP}
    String filename;
    String imagename;
    cardTypes cardType;
    String title;

    public cardTypes getCardType() {
        return cardType;
    }

    public void setCardType(cardTypes cardType) {
        this.cardType = cardType;
    }

    public Card(ArrayList<String> attributes){
        this.filename = attributes.get(0);
        this.imagename = attributes.get(1);
        String cardTypeStr = attributes.get(2);
        if (cardTypeStr.equals("play")){
            this.cardType = cardTypes.PLAY;
        }
        else{
            this.cardType = cardTypes.TRUMP;
        }
        this.title = attributes.get(3).toString();

    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
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

    @Override
    public String toString(){
        String displayString = "---------------------------------------------------------------------\n" +
                title +
                "\n" + "---------------------------------------------------------------------\n";
        return displayString;
    }

    public boolean canPlayOn(Card lastPlayedCard) {
        if (cardType.equals(cardTypes.TRUMP)){
            return true;
        }
        else{
            return false;
        }
    }

}
