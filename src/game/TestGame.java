package game;

import cards.Card;
import players.Player;

/** Testing a hard coded game object Created by Draga on 17/09/2016. */
public class TestGame {

  public static void main(String[] args) {
    Game newGame = new Game(5, "Jane");
    //print all the players
    System.out.println("Playing today:");
    Player[] allPlayers = newGame.getPlayers();
    for (Player player : allPlayers) {
      System.out.println(player.getName());
    }
    System.out.println();
    System.out.println("#####################");
    System.out.println();
    //select a dealer- hard coded so user is up first
    System.out.println("Dealer is: " + newGame.selectDealer(allPlayers[allPlayers.length - 1]));
    newGame.dealInitialHands();
    //test dealing cards
    for (Player pl : allPlayers) {
      System.out.println(pl.getName() + "'s hand: ");
      System.out.println("#######################");
      for (Card c : pl.getCurrentHand()) {
        System.out.println(c.getTitle());
      }
      System.out.println("#################################################");
    }
    //test getting the next player
    Player playerUp = newGame.getNextPlayer();
    System.out.println("Next up: " + playerUp.getName());
    if (newGame.userIsUp()) {
      PlayGame.userPlayFirstTurn(newGame, playerUp);
      PlayGame.displayTurnResults(newGame, playerUp);
    }
    while (!newGame.isWon()) {
      //testing rounds
      if (newGame.isNewRound()) {
        System.out.println("YOU WON ROUND");
        System.out.println("NEW ROUND");
      }
      System.out.println("Your turn again");
      System.out.println(
          newGame.getLastPlayedCard().getTitle() + "\n" + newGame.getLastPlayedCard().toString());
      System.out.println("Trump category is " + newGame.getCurrentCategory());
      for (Card card : playerUp.getCurrentHand()) {
        System.out.println(
            card.getTitle()
                + " : "
                + card.getTrumpVal(newGame.getCurrentCategory())
                + "======"
                + newGame.getLastPlayedCard().getTrumpVal(newGame.getCurrentCategory()));
      }
      //testing playing a normal turn
      PlayGame.userPlayTurn(newGame, playerUp);
      PlayGame.displayTurnResults(newGame, playerUp);
      for (Card card : playerUp.getCurrentHand()) {
        System.out.println(card.getTitle());
      }
    }
  }
}
