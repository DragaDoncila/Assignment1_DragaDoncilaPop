/**Class handles Trump objects of the Hardness category. Provides methods for parsing string to appropriate value type
 * for comparison, as well as methods for comparison and display.
 * Created by Draga on 13/09/2016.
 */
public class Hardness extends Trump{

    double hardnessVal;
    String hardnessStr;

    Hardness(String hardnessStr){
        this.category = TrumpCategories.HARDNESS;
        setHardness(hardnessStr);
    }

    private void setHardness(String hardnessStr) {
        double hardness = 0.0;
        boolean hasRange = false;
//        get rid of any spaces/trailing whitespace from file
        hardnessStr = hardnessStr.replaceAll("\\s+", "");
        for (int i = 0; i < hardnessStr.length(); i++) {
            char currentChar = hardnessStr.charAt(i);
            if(currentChar == '-'){
                String highValStr = hardnessStr.substring(i+1);
                hardness = Double.parseDouble(highValStr);
                hasRange = true;
            }
        }
        if (!hasRange){
            hardness = Double.parseDouble(hardnessStr);
        }
        this.hardnessStr = hardnessStr;
        this.hardnessVal = hardness;
    }

    public double getHardness() {
        return hardnessVal;
    }

    @Override
    public String toString() {
        return hardnessStr;
    }

    boolean isHigherThan(Hardness otherHardness) {
        if (this.getHardness() > otherHardness.getHardness()){
            return true;
        }
        else {
            return false;
        }
    }
}
