package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetOutOfJailTest {

    @Test
    void checkIfPlayerIsJailedAndCurrentPlayer() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");
        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        player.setPosition(30);
        jail.sendToJail(player);

        assertTrue(player.isJailed());
        assertEquals(game.getCurrentPlayer(), player);
    }

    @Test
    void tryPayingForFineNotCurrentPlayer() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        assertThrows(IllegalMonopolyActionException.class, () -> game.getOutOfJailFine(player2.getName()));

    }

    @Test
    void tryPayingForFineNotJailed() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        assertThrows(IllegalMonopolyActionException.class, () -> game.getOutOfJailFine(player.getName()));

    }

    @Test
    void tryPayingForWithoutMoney() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);
        player.setPosition(30);
        jail.sendToJail(player);
        assertTrue(player.isJailed());
        assertEquals(game.getCurrentPlayer(), player);

        int totalAmountOfMoney = 1500;
        player.removeMoney(totalAmountOfMoney);

        assertFalse(game.getOutOfJailFine(player.getName()));
        assertTrue(player.isBankrupt());
    }

    @Test
    void getOutOfJailFine() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);
        player.setPosition(30);
        jail.sendToJail(player);
        assertTrue(player.isJailed());
        assertEquals(game.getCurrentPlayer(), player);

        assertTrue(game.getOutOfJailFine(player.getName()));
        assertFalse(player.isJailed());
        int totalAmount = 1500;
        int jailFineMoney = 50;
        int playerMoney = totalAmount - jailFineMoney;
        assertEquals(playerMoney, player.getMoney());
    }

    @Test
    void testGetOutOfJailCard() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");
        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);
        player.getOutOfJailCard();

        player.setPosition(30);
        jail.sendToJail(player);
        assertTrue(player.isJailed());

        assertTrue(game.useOutOfJailCard(player.getName()));
    }

    @Test
    void testGetOutOfJailWithoutCards() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");
        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);
        player.useOutOfJailCard();

        player.setPosition(30);
        jail.sendToJail(player);
        assertTrue(player.isJailed());

        assertThrows(IllegalMonopolyActionException.class, () -> game.useOutOfJailCard(player.getName()));
    }

    @Test
    void testGetOutOfJailCardWithoutBeingJailed() {
        JailExecuting jail = new JailExecuting();
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");
        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        assertFalse(player2.isJailed());

        assertThrows(IllegalMonopolyActionException.class, () -> game.useOutOfJailCard(player2.getName()));
    }
}
