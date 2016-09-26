package Cards;

import java.util.ArrayList;
import Trumps.*;
import Trumps.Trump.TrumpCategories;

/**Class designed to store information and methods about a playing card.
 * Created by Draga on 30/08/2016.
 */
public class MineralCard extends Card{


    private String chemistry;
    private String classification;
    private String crystalSystem;
    private String occurrence;
    private Hardness hardness;
    private SpecificGravity specificGravity;
    private Cleavage cleavage;
    private CrustalAbundance crustalAbundance;
    private EconomicValue economicValue;


    @Override
    public boolean isGeologist() {
//        Mineral Card cannot be the Geologist
        return false;
    }

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


    private void setHardness(String hardness) {
        this.hardness = new Hardness(hardness);
    }

    private void setSpecificGravity(String specificGravity) {
        this.specificGravity = new SpecificGravity(specificGravity);
    }

    private void setCleavage(String cleavage) {
        this.cleavage = new Cleavage(cleavage);
    }

    private void setCrustalAbundance(String crustalAbundance) {
        this.crustalAbundance = new CrustalAbundance(crustalAbundance);
    }

    @Override
    public String toString(){
        String displayString = getInfo();
        displayString += String.format("%-30s", "Hardness: ") + this.getTrumpVal(TrumpCategories.HARDNESS) + "\n";
        displayString += String.format("%-30s", "Crustal Abundance: ") + this.getTrumpVal(TrumpCategories.CRUSTAL_ABUNDANCE) + "\n";
        displayString += String.format("%-30s", "Economic Value: ") + this.getTrumpVal(TrumpCategories.ECONOMIC_VALUE) + "\n";
        displayString += String.format("%-30s", "Cleavage: ") + this.getTrumpVal(TrumpCategories.CLEAVAGE) + "\n";
        displayString += String.format("%-30s", "Specific Gravity: ") + this.getTrumpVal(TrumpCategories.SPECIFIC_GRAVITY) + "\n";
        displayString += "------------------------------------------\n";
        return displayString;
    }

    @Override
    public String getInfo() {
        String infoString = super.toString();
        infoString += String.format("%-30s", "Chemistry: ") + chemistry + "\n";
        infoString += String.format("%-30s", "Classification: ") + classification + "\n";
        infoString += String.format("%-30s", "Crystal System: ") + crystalSystem + "\n";
        infoString += String.format("%-30s", "Occurrence: ") + occurrence + "\n";
        infoString += "------------------------------------------\n";
        return infoString;

    }

    @Override
    public String getTrumpVal(TrumpCategories category) {
        switch (category){
            case HARDNESS:
                return this.getHardness().getValueString();
            case ECONOMIC_VALUE:
                return this.getEconomicValue().getValueString();
            case CRUSTAL_ABUNDANCE:
                return this.getCrustalAbundance().getValueString();
            case CLEAVAGE:
                return this.getCleavage().getValueString();
            case SPECIFIC_GRAVITY:
                return this.getSpecificGravity().getValueString();
            default:
                return "Not Found";

        }
    }

    private String getChemistry() {
        return chemistry;
    }

    private String getClassification() {
        return classification;
    }

    private String getCrystalSystem() {
        return crystalSystem;
    }

    private String getOccurrence() {
        return occurrence;
    }

    private Hardness getHardness() {
        return hardness;
    }

    private SpecificGravity getSpecificGravity() {
        return specificGravity;
    }

    private Cleavage getCleavage() {
        return cleavage;
    }

    private CrustalAbundance getCrustalAbundance() {
        return crustalAbundance;
    }

    public EconomicValue getEconomicValue() {
        return economicValue;
    }

    @Override
    public boolean canPlayOn(Card lastPlayedCard, TrumpCategories currentCategory){
        boolean canPlayOn = false;
        if (lastPlayedCard.isTrump()) {
            canPlayOn = true;
        }
        else {
            MineralCard otherCard = (MineralCard) lastPlayedCard;
            switch (currentCategory){
                case HARDNESS:
                    Hardness myHardness = this.getHardness();
                    Hardness otherHardness = otherCard.getHardness();
                    if (myHardness.isHigherThan(otherHardness)){
                        canPlayOn =  true;
                    }
                    break;
                case SPECIFIC_GRAVITY:
                    SpecificGravity myGravity = this.getSpecificGravity();
                    SpecificGravity otherGravity = otherCard.getSpecificGravity();
                    if (myGravity.isHigherThan(otherGravity)){
                        canPlayOn = true;
                    }
                    break;
                case CLEAVAGE:
                    Cleavage myCleavage = this.getCleavage();
                    Cleavage otherCleavage = otherCard.getCleavage();
                    if (myCleavage.isHigherThan(otherCleavage)){
                        canPlayOn =  true;
                    }
                    break;
                case CRUSTAL_ABUNDANCE:
                    CrustalAbundance myCrustalAbundance = this.getCrustalAbundance();
                    CrustalAbundance otherCrustalAbundance = otherCard.getCrustalAbundance();
                    if (myCrustalAbundance.isHigherThan(otherCrustalAbundance)){
                        canPlayOn = true;
                    }
                    break;
                case ECONOMIC_VALUE:
                    EconomicValue myEconomicValue = this.getEconomicValue();
                    EconomicValue otherEconomicValue = otherCard.getEconomicValue();
                    if (myEconomicValue.isHigherThan(otherEconomicValue)){
                        canPlayOn = true;
                    }
            }
        }

        return canPlayOn;
    }
    }

