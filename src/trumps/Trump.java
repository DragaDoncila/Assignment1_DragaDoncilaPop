package trumps;

/** Interface defines attributes every trump category must have Created by Draga on 13/09/2016. */
public class Trump {

  public enum TrumpCategories {
    HARDNESS,
    SPECIFIC_GRAVITY,
    CLEAVAGE,
    CRUSTAL_ABUNDANCE,
    ECONOMIC_VALUE
  }

  TrumpCategories category;
}
