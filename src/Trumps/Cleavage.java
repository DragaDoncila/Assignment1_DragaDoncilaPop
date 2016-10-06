package Trumps;

/**
 * Class parses string version of attribute to an enum value, and sets up methods for display and
 * comparison Created by Draga on 14/09/2016.
 */
public class Cleavage extends Trump {

  private enum CleavageScale {
    NONE,
    POOR_NONE,
    ONE_POOR,
    TWO_POOR,
    ONE_GOOD,
    ONE_GOOD_ONE_POOR,
    TWO_GOOD,
    THREE_GOOD,
    ONE_PERFECT,
    ONE_PERFECT_ONE_GOOD,
    ONE_PERFECT_TWO_GOOD,
    TWO_PERFECT_ONE_GOOD,
    THREE_PERFECT,
    FOUR_PERFECT,
    SIX_PERFECT
  }

  private CleavageScale cleavage;
  private String cleavageStr;

  /**
   * Constructs a Trump object of category cleavage, and sets its value to the given string
   *
   * @param cleavage the cleavage value as string
   */
  public Cleavage(String cleavage) {
    this.category = TrumpCategories.CLEAVAGE;
    setCleavage(cleavage);
  }

  /**
   * Converts the given cleavage value string into an enum for comparison
   *
   * @param cleavage the cleavage value as string
   */
  private void setCleavage(String cleavage) {
    this.cleavageStr = cleavage;
    cleavage = cleavage.replaceAll("\\s+", "");
    switch (cleavage) {
      case "none":
        this.cleavage = CleavageScale.NONE;
        break;
      case "poor/none":
        this.cleavage = CleavageScale.POOR_NONE;
        break;
      case "1poor":
        this.cleavage = CleavageScale.ONE_POOR;
        break;
      case "2poor":
        this.cleavage = CleavageScale.TWO_POOR;
        break;
      case "1good":
        this.cleavage = CleavageScale.ONE_GOOD;
        break;
      case "1good,1poor":
        this.cleavage = CleavageScale.ONE_GOOD_ONE_POOR;
        break;
      case "2good":
        this.cleavage = CleavageScale.TWO_GOOD;
        break;
      case "3good":
        this.cleavage = CleavageScale.THREE_GOOD;
        break;
      case "1perfect":
        this.cleavage = CleavageScale.ONE_PERFECT;
        break;
      case "1perfect,1good":
        this.cleavage = CleavageScale.ONE_PERFECT_ONE_GOOD;
        break;
      case "1perfect,2good":
        this.cleavage = CleavageScale.ONE_PERFECT_TWO_GOOD;
        break;
      case "2perfect,1good":
        this.cleavage = CleavageScale.TWO_PERFECT_ONE_GOOD;
        break;
      case "3perfect":
        this.cleavage = CleavageScale.THREE_PERFECT;
        break;
      case "4perfect":
        this.cleavage = CleavageScale.FOUR_PERFECT;
        break;
      case "6perfect":
        this.cleavage = CleavageScale.SIX_PERFECT;
        break;
    }
  }

  private CleavageScale getCleavageVal() {
    return cleavage;
  }

  @Override
  public String toString() {
    return cleavageStr;
  }

  public String getValueString() {
    return this.toString();
  }

  /**
   * Returns true if the passed cleavage is lower than this value
   *
   * @param otherCleavage the other cleavage to compare
   * @return boolean of comparison
   */
  public boolean isHigherThan(Cleavage otherCleavage) {
    return this.cleavage.compareTo(otherCleavage.getCleavageVal()) > 0;
  }
}
