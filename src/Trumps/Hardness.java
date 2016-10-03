package Trumps;

/**Class handles Trumps.Trump objects of the Trumps.Hardness category. Provides methods for parsing string to appropriate value type
 * for comparison, as well as methods for comparison and display.
 * Created by Draga on 13/09/2016.
 */
public class Hardness extends Trump{

    private double hardnessVal;
    private String hardnessStr;

    /**
     * Constructs a trump object of category hardness and sets its value
     *
     * @param hardnessStr the given hardness value
     */
    public Hardness(String hardnessStr){
        this.category = TrumpCategories.HARDNESS;
        setHardness(hardnessStr);
    }

    /**
     * Parses the string given as a value into a double
     *
     * @param hardnessStr the given value string
     */
    private void setHardness(String hardnessStr) {
        double hardness = 0.0;
        boolean hasRange = false;
//        get rid of any spaces/trailing whitespace from file
        hardnessStr = hardnessStr.replaceAll("\\s+", "");
        for (int i = 0; i < hardnessStr.length(); i++) {
            char currentChar = hardnessStr.charAt(i);
            //if the string contains two values split at the '-'
            if(currentChar == '-'){
                //get the highest value (value to the right of the '-')
                String highValStr = hardnessStr.substring(i+1);
                hardness = Double.parseDouble(highValStr);
                hasRange = true;
            }
        }
        //if it doesn't have a range of values
        if (!hasRange){
            hardness = Double.parseDouble(hardnessStr);
        }
        this.hardnessStr = hardnessStr;
        this.hardnessVal = hardness;
    }

    private double getHardness() {
        return hardnessVal;
    }

    @Override
    public String toString() {
        return hardnessStr;
    }

    /**
     * Returns true if this value is higher than the other, otherwise false
     *
     * @param otherHardness the other value for comparison
     * @return boolean of comparison
     */
    public boolean isHigherThan(Hardness otherHardness) {
        return this.getHardness() > otherHardness.getHardness();
    }

    public String getValueString() {
        return Double.toString(hardnessVal);
    }
}
