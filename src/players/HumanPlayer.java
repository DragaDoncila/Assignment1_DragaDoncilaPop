package players;

import cards.Card;

/**
 * Class describes current attributes of a player as well as their methods for interacting with the
 * game Created by Draga on 6/09/2016.
 */
public class HumanPlayer extends Player {

  /**
   * Constructs a human player object and assigns it a name
   *
   * @param id player's id
   * @param name player's name
   */
  public HumanPlayer(int id, String name) {
    this.type = PlayerTypes.USER;
    this.id = id;
    this.name = name;
    this.isOut = false;
  }

  @Override
  public String chooseCategory() {
    return null;
  }

  @Override
  public Card playCard(int cardChoice) {
    return currentHand.remove(cardChoice);
  }

  @Override
  public boolean isUser() {
    return true;
  }

  @Override
  public Card playFirstCard(int cardChoice, boolean b) {
    return currentHand.remove(cardChoice);
  }
}
