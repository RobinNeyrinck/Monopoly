package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RollDiceTest {

    @Test
    void checkDiceBounds() {
        int topBound = 12;
        int bottomBound = 2;
        Dice dice = new Dice();
        for (int i = 0; i < 10000; i++) {
            dice.calculateDiceRoll();
            assertTrue(dice.getLastRollAsInt() <= topBound);
            assertTrue(dice.getLastRollAsInt() >= bottomBound);
        }
    }

    @Test
    void changePositionOfPlayer() {
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Blue");
        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);
        int startPosition = 0;

        game.rollDice(player.getName());

        assertNotEquals(startPosition, player.getPosition());
    }

    @Test
    void getLastRollTestAsInt() {
        Game game = new Game(2, "group_02");
        Player player1 = new Player("green");
        Player player2 = new Player("blue");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        int startPosition = 0;
        game.rollDice(player1.getName());

        assertEquals(player1.getPosition(), startPosition + game.getLastRoll().getLastRollAsInt());

    }
}