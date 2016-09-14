/**Class handles enumeration, comparison and display of Crustal Abundance trump category.
 * Created by Draga on 14/09/2016.
 */
public class CrustalAbundance extends Trump{
    enum AbundanceScale{ULTRATRACE, TRACE, LOW, MODERATE, HIGH, VERY_HIGH}

    AbundanceScale abundanceVal;
    String abundanceStr;

    public CrustalAbundance(String crustalAbundance) {
        this.category = TrumpCategories.CRUSTAL_ABUNDANCE;
        setCrustalAbundance(crustalAbundance);
    }

    public void setCrustalAbundance(String crustalAbundance) {
        this.abundanceStr = crustalAbundance;
        crustalAbundance = crustalAbundance.replaceAll("\\s+", "");
        switch (crustalAbundance){
            case "ultratrace":
                this.abundanceVal = AbundanceScale.ULTRATRACE;
                break;
            case "trace":
                this.abundanceVal = AbundanceScale.TRACE;
                break;
            case "low":
                this.abundanceVal = AbundanceScale.LOW;
                break;
            case "moderate":
                this.abundanceVal = AbundanceScale.MODERATE;
                break;
            case "high":
                this.abundanceVal = AbundanceScale.HIGH;
                break;
            case "veryhigh":
                this.abundanceVal = AbundanceScale.VERY_HIGH;
                break;
        }
    }

    public AbundanceScale getAbundanceVal() {
        return abundanceVal;
    }

    @Override
    public String toString() {
        return abundanceStr;
    }

    boolean isHigherThan(CrustalAbundance otherAbundance) {
        AbundanceScale otherAbundanceVal = otherAbundance.getAbundanceVal();
        if (this.abundanceVal.compareTo(otherAbundance.getAbundanceVal()) > 0){
            return true;
        }
        else return false;
    }
}
