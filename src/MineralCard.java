import java.util.ArrayList;

/**Class designed to store information and methods about a playing card.
 * Created by Draga on 30/08/2016.
 */
public class MineralCard extends Card{
//    declare enum sequences here

    enum CleavageScale{NONE, POOR_NONE, ONE_POOR, TWO_POOR, ONE_GOOD, ONE_GOOD_ONE_POOR, TWO_GOOD, THREE_GOOD, ONE_PERFECT,
    ONE_PERFECT_ONE_GOOD, ONE_PERFECT_TWO_GOOD, TWO_PERFECT_ONE_GOOD, THREE_PERFECT, FOUR_PERFECT, SIX_PERFECT};
    enum AbundanceScale{ULTRATRACE, TRACE, LOW, MODERATE, HIGH, VERY_HIGH}
    enum EconomicValue{TRIVIAL, LOW, MODERATE, HIGH, VERY_HIGH, IM_RICH}

    String chemistry;
    String classification;
    String crystalSystem;
    String occurrence;
    Hardness hardness;
    String specificGravity;
    String cleavage;
    String crustalAbundance;
    String economicValue;

    public MineralCard(ArrayList<String> attributes) {
        super(attributes);
//        setChemistry(); method takes string and converts it to enum value
        this.chemistry = attributes.get(4);
        this.classification = attributes.get(5);
        this.crystalSystem = attributes.get(6);
        this.occurrence = attributes.get(7);
        setHardness(attributes.get(8));
        this.specificGravity = attributes.get(9);
        this.cleavage = attributes.get(10);
        this.crustalAbundance = attributes.get(11);
        this.economicValue = attributes.get(12);
    }


    public void setHardness(String hardness) {
        this.hardness = new Hardness(hardness);
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

    public Hardness getHardness() {
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

    @Override
    public boolean canPlayOn(int countRounds, Card lastPlayedCard, Trump currentCategory){
        MineralCard otherCard = (MineralCard) lastPlayedCard;
        switch (currentCategory.category){
            case HARDNESS:
                Hardness myHardness = this.getHardness();
                Hardness otherHardness = otherCard.getHardness();
                if (myHardness.isHigherThan(otherHardness)){
                return true;
                }
            case SPECIFIC_GRAVITY:
                break;
            case CLEAVAGE:
                break;
            case CRUSTAL_ABUNDANCE:
                break;
            case ECONOMIC_VALUE:
                break;
        }
        return false;
    }
    }

