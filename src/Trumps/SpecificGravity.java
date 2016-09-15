package Trumps;

/**Class handles Trumps.Trump objects of the Specific Gravity category. Provides methods for parsing string to appropriate value type
 * for comparison, as well as methods for comparison and display.
 * Created by Draga on 14/09/2016.
 */
public class SpecificGravity extends Trump{
    private double gravityVal;
    private String gravityStr;

    public SpecificGravity(String gravityStr){
        this.category = TrumpCategories.SPECIFIC_GRAVITY;
        setSpecificGravity(gravityStr);
    }

    private void setSpecificGravity(String specificGravity) {
        double gravity = 0.0;
        boolean hasRange = false;
//        get rid of any spaces/trailing whitespace from file
        specificGravity = specificGravity.replaceAll("\\s+", "");
        for (int i = 0; i < specificGravity.length(); i++) {
            char currentChar = specificGravity.charAt(i);
            if(currentChar == '-'){
                String highValStr = specificGravity.substring(i+1);
                gravity = Double.parseDouble(highValStr);
                hasRange = true;
            }
        }
        if (!hasRange){
            gravity = Double.parseDouble(specificGravity);
        }
        this.gravityVal = gravity;
        this.gravityStr = specificGravity;
    }

    private double getGravity(){
        return this.gravityVal;
    }

    @Override
    public String toString() {
        return this.gravityStr;
    }

    public boolean isHigherThan(SpecificGravity otherGravity){
        return this.getGravity() > otherGravity.getGravity();
    }
}
