import java.util.ArrayList;

/**Class designed to handle generic card objects.
 * Created by Draga on 6/09/2016.
 */
public class Card {
    String filename;
    String imagename;
    String cardType;
    String title;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Card(ArrayList attributes){
        this.filename = attributes.get(0).toString();
        this.imagename = attributes.get(1).toString();
        this.cardType = attributes.get(2).toString();
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
        String displayString = title + "\n" + "---------------------------------------------------------------------\n";
        return displayString;
    }
}
