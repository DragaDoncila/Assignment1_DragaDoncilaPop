import java.util.ArrayList;

/**Class extends MineralCard and handles supertrump objects.
 * Created by Draga on 6/09/2016.
 */
public class SupertrumpCard extends Card{
    String subtitle;

    public SupertrumpCard(ArrayList<String> attributes){
        super(attributes);
        this.subtitle = attributes.get(4);
    }

    @Override
    public boolean canPlayOn(int countRounds, Card lastPlayedCard, Trump currentCategory){
        return true;
    }
}
