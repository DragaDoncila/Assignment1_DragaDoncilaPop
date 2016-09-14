/**
 * Created by Draga on 13/09/2016.
 */
public class Hardness extends Trump{

    double hardnessVal;

    Hardness(String hardnessStr){
        this.category = TrumpCategories.HARDNESS;
        this.hardnessVal = setHardness(hardnessStr);
    }

    private double setHardness(String hardnessStr) {
        double hardness = 0.0;
        boolean hasRange = false;
//        get rid of any spaces/trailing whitespace from file
        hardnessStr.replaceAll("\\s+", "");
//        System.out.println(hardnessStr);
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

        return hardness;
    }

    public double getHardnessVal() {
        return hardnessVal;
    }

    @Override
    boolean compareTo() {
        return false;
    }
}
