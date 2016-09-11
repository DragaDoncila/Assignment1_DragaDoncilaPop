import java.util.ArrayList;

/**Class designed to store information and methods about a playing card.
 * Created by Draga on 30/08/2016.
 */
public class MineralCard extends Card{


    String chemistry;
    String classification;
    String crystalSystem;
    String occurrence;
    String hardness;
    String specificGravity;
    String cleavage;
    String crustalAbundance;
    String economicValue;

    public MineralCard(ArrayList attributes) {
        super(attributes);
        this.chemistry = (String) attributes.get(4);
        this.classification = (String) attributes.get(5);
        this.crystalSystem = (String) attributes.get(6);
        this.occurrence = attributes.get(7).toString();
        this.hardness = attributes.get(8).toString();
        this.specificGravity = attributes.get(9).toString();
        this.cleavage = (String) attributes.get(10);
        this.crustalAbundance = (String) attributes.get(11);
        this.economicValue = (String) attributes.get(12);
    }

    @Override
    public String toString(){
        String displayString = super.toString();
        displayString += String.format("%-40s%-20s%-20s%-40s", "Chemistry", "Classification", "Crystal System", "Occurrence");
        displayString += "\n";
        displayString += String.format("%-40s%-20s%-20s-%40s", chemistry, classification, crystalSystem, occurrence);
        displayString += "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n" ;
        displayString += String.format("%-20s%-20s%-20s%-20s%-20s", "Hardness", "Specific Gravity", "Cleavage", "Abundance", "Value");
        displayString += "\n";
        displayString += String.format("%-20s%-20s%-20s%-20s%-20s", hardness, specificGravity, cleavage, crustalAbundance, economicValue);
        displayString += "\n";
        return displayString;
    }

    public String getChemistry() {
        return chemistry;
    }

    public String getClassification() {
        return classification;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public String getHardness() {
        return hardness;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getCrustalAbundance() {
        return crustalAbundance;
    }

    public String getEconomicValue() {
        return economicValue;
    }

    public boolean canPlayOn(Card lastPlayedCard){
        return false;
    }
}
