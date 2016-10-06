package Players;

import Cards.Card;

import java.util.Random;

/**
 * Class handles behaviours for bots involved in MineralSupertrumpts games. Created by Draga on
 * 17/09/2016.
 */
public class AIPlayer extends Player {

  private final String[] trumpCats = {
    "hardness", "cleavage", "crustalabundance", "economicvalue", "specificgravity"
  };

  /**
   * Constructs a player object of the BOT type and gives it a name
   *
   * @param id the player's id
   */
  public AIPlayer(int id) {
    this.type = PlayerTypes.BOT;
    this.id = id;
    String[] BOTNAMES = {"Terminator", "Geodude", "Rocker", "Colminer"};
    this.name = BOTNAMES[id - 1];
    this.isOut = false;
  }

  /**
   * Chooses a random trump category from those available
   *
   * @return trumpCategory
   */
  @Override
  public String chooseCategory() {
    int trumpChoice = new Random().nextInt(trumpCats.length);
    return trumpCats[trumpChoice];
  }

  /**
   * Selects a playable card to return and removes it from the hand
   *
   * @param cardChoice the chosen card
   * @return chosenCard
   */
  @Override
  public Card playCard(int cardChoice) {
    //Randomly select a playable card
    cardChoice = new Random().nextInt(playableCards.size());
    Card chosenCard = playableCards.get(cardChoice);
    //Remove the card from the hand, not just playable array before returning
    int handIndex = -1;
    for (int i = 0; i < currentHand.size(); i++) {
      Card currentCard = currentHand.get(i);
      if (currentCard.getTitle().equals(chosenCard.getTitle())) {
        handIndex = i;
      }
    }
    currentHand.remove(handIndex);
    return playableCards.remove(cardChoice);
  }

  /**
   * Returns false as bots cannot be the user
   *
   * @return false
   */
  @Override
  public boolean isUser() {
    return false;
  }

  /**
   * Chooses a card based on it being either a new round or the start of the game and removes it
   * from hand
   *
   * @param cardChoice the chosen card
   * @param isStartofGame true when it's the very first turn of a game
   * @return the card choice
   */
  @Override
  public Card playFirstCard(int cardChoice, boolean isStartofGame) {
    cardChoice = new Random().nextInt(currentHand.size());
    Card potentialCard = currentHand.get(cardChoice);
    if (isStartofGame) {
      while (potentialCard.isTrump()) {
        cardChoice = new Random().nextInt(currentHand.size());
        potentialCard = currentHand.get(cardChoice);
      }
      return currentHand.remove(cardChoice);
    } else {
      return currentHand.remove(cardChoice);
    }
  }
}
