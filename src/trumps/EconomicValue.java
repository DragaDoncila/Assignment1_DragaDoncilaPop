package trumps;

/**
 * Class handles enumerating, display and comparison of economic value trump category. Created by
 * Draga on 14/09/2016.
 */
public class EconomicValue extends Trump {
  private enum EconomicScale {
    TRIVIAL,
    LOW,
    MODERATE,
    HIGH,
    VERY_HIGH,
    IM_RICH
  }

  private EconomicScale economicVal;
  private String economicValStr;

  /**
   * Constructs a trump object of category economic value and sets its value
   *
   * @param economicValStr the given value string
   */
  public EconomicValue(String economicValStr) {
    this.category = TrumpCategories.ECONOMIC_VALUE;
    this.economicValStr = economicValStr;
    setEconomicVal(economicValStr);
  }

  /**
   * Converts the given string into the equivalent enum value for comparison
   *
   * @param economicValStr the given value string
   */
  private void setEconomicVal(String economicValStr) {
    economicValStr = economicValStr.replaceAll("\\s+", "");
    switch (economicValStr) {
      case "trivial":
        this.economicVal = EconomicScale.TRIVIAL;
        break;
      case "low":
        this.economicVal = EconomicScale.LOW;
        break;
      case "moderate":
        this.economicVal = EconomicScale.MODERATE;
        break;
      case "high":
        this.economicVal = EconomicScale.HIGH;
        break;
      case "veryhigh":
        this.economicVal = EconomicScale.VERY_HIGH;
        break;
      case "I'mrich!":
        this.economicVal = EconomicScale.IM_RICH;
        break;
    }
  }

  @Override
  public String toString() {
    return economicValStr;
  }

  private EconomicScale getEconomicVal() {
    return this.economicVal;
  }

  /**
   * Returns true if this economic value is higher than the other, otherwise false
   *
   * @param otherEconomicValue the other economic value
   * @return boolean of comparison
   */
  public boolean isHigherThan(EconomicValue otherEconomicValue) {
    return this.economicVal.compareTo(otherEconomicValue.getEconomicVal()) > 0;
  }

  public String getValueString() {
    return this.toString();
  }
}
