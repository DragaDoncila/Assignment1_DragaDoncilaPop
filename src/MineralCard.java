import java.util.ArrayList;

/**Class designed to store information and methods about a playing card.
 * Created by Draga on 30/08/2016.
 */
public class MineralCard extends Card{

    public void setCrustalAbundance(String crustalAbundance) {
        this.crustalAbundance = new CrustalAbundance(crustalAbundance);
    }

    String chemistry;
    String classification;
    String crystalSystem;
    String occurrence;
    Hardness hardness;
    SpecificGravity specificGravity;
    Cleavage cleavage;
    CrustalAbundance crustalAbundance;
    EconomicValue economicValue;

    public MineralCard(ArrayList<String> attributes) {
        super(attributes);
//        setChemistry(); method takes string and converts it to enum value
        this.chemistry = attributes.get(4);
        this.classification = attributes.get(5);
        this.crystalSystem = attributes.get(6);
        this.occurrence = attributes.get(7);
        setHardness(attributes.get(8));
        setSpecificGravity(attributes.get(9));
        setCleavage(attributes.get(10));
        setCrustalAbundance(attributes.get(11));
        setEconomicValue(attributes.get(12));
    }

    private void setEconomicValue(String economicValStr) {
        this.economicValue = new EconomicValue(economicValStr);
    }


    public void setHardness(String hardness) {
        this.hardness = new Hardness(hardness);
    }

    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = new SpecificGravity(specificGravity);
    }

    public void setCleavage(String cleavage) {
        this.cleavage = new Cleavage(cleavage);
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

    public SpecificGravity getSpecificGravity() {
        return specificGravity;
    }

    public Cleavage getCleavage() {
        return cleavage;
    }

    public CrustalAbundance getCrustalAbundance() {
        return crustalAbundance;
    }

    public EconomicValue getEconomicValue() {
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
                SpecificGravity myGravity = this.getSpecificGravity();
                SpecificGravity otherGravity = otherCard.getSpecificGravity();
                if (myGravity.isHigherThan(otherGravity)){
                    return true;
                }
            case CLEAVAGE:
                Cleavage myCleavage = this.getCleavage();
                Cleavage otherCleavage = otherCard.getCleavage();
                if (myCleavage.isHigherThan(otherCleavage)){
                    return true;
                }
            case CRUSTAL_ABUNDANCE:
                CrustalAbundance myCrustalAbundance = this.getCrustalAbundance();
                CrustalAbundance otherCrustalAbundance = otherCard.getCrustalAbundance();
                if (myCrustalAbundance.isHigherThan(otherCrustalAbundance)){
                    return true;
                }
            case ECONOMIC_VALUE:
                EconomicValue myEconomicValue = this.getEconomicValue();
                EconomicValue otherEconomicValue = otherCard.getEconomicValue();
                if (myEconomicValue.isHigherThan(otherEconomicValue)){
                    return true;
                }
        }
        return false;
    }
    }

