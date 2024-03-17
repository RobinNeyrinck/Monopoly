package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.implementation.non_properties.executing_tiles.Go;
import be.howest.ti.monopoly.logic.implementation.properties.Street;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerMethodsTest {
    @Test
    void checkGetName(){
        Player player = new Player("Blue");
        assertEquals("Blue", player.getName());
    }
    @Test
    void testRemoveMoney(){
        Game game = new Game(2, "Group_02");
        Player player1 = new Player("Blue");
        Player player2 = new Player("Green");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.removeMoney(200);

        assertEquals(1300, player1.getMoney());
    }

    @Test
    void testRemoveMoneyNegativeAmount(){
        Game game = new Game(2, "Group_02");
        Player player1 = new Player("Blue");
        Player player2 = new Player("Green");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        assertThrows(IllegalStateException.class, () -> player1.removeMoney(-200));
    }
    @Test
    void checkGetCurrentTile(){
        Player player = new Player("Green");
        Tile startTile = new Go(0, "Start");
        assertEquals(player.getPosition(), startTile.getPosition());
    }

    @Test
    void checkIsJailed(){
        Player player = new Player("Green");
        assertFalse(player.isJailed());
    }

    @Test
    void checkGetOutOfJailCards(){
        Player player = new Player("Green");
        int amount = 1;
        assertEquals(amount, player.getGetOutJailCards());
    }

    @Test
    void comparePlayersWithName(){
        Player player = new Player("Green");
        Player player2 = new Player("Green");
        assertEquals(player, player2);
    }

    @Test
    void checkToString(){
        Player player = new Player("Green");
        String playerToString = "name -- " + player.getName() + " -- position -- " + player.getPosition();
        assertEquals(player.toString(), playerToString);
    }

    @Test
    void checkProperty(){
        Player player = new Player("Green");
        assertEquals(Collections.emptyList(), player.getProperties());
    }

    @Test
    void checkMoney(){
        Player player = new Player("Green");
        int startMoney = 1500;
        assertEquals(player.getMoney(), startMoney);
    }

    @Test
    void removeMoneyFromPlayer(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");
        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        Property property = new Street(1, "Maes", 60, 30, 2, 2);
        player1.setPosition(1);
        game.buyProperty(player1, property);
        int startMoney = 1500;
        int moneyAfterBuying = startMoney - property.getCost();
        assertEquals(player1.getMoney(), moneyAfterBuying);
    }

    @Test
    void checkIfBankrupt(){
        Player player = new Player("Green");
        assertFalse(player.isBankrupt());
    }

    @Test
    void checkIfPlayerGetsMoneyFromStart(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.setPosition(39);
        player2.setPosition(38);
        assertTrue(game.isDirectSale());
        assertEquals(game.getCurrentPlayer(), player1);
        game.dontBuyProperty();
        assertEquals(game.getCurrentPlayer(), player2);
        game.rollDice(player2.getName());

        assertEquals(1500, player1.getMoney());
        assertEquals(1700, player2.getMoney());
    }

    @Test
    void testPlayerPaysFine(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.setPosition(4);
        player2.setPosition(38);
        game.payTax(player1.getName());
        game.payTax(player2.getName());

        assertEquals(1300, player1.getMoney());
        assertEquals(1300, player2.getMoney());
    }

    @Test
    void testPlayerCantAffordFine() {
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.setPosition(4);
        player2.setPosition(38);
        player1.removeMoney(1500);
        player2.removeMoney(1500);

        assertFalse(game.payTax(player1.getName()));
        assertFalse(game.payTax(player2.getName()));
    }

    @Test
    void didThePlayerPayTheirTaxes(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.setPosition(4);
        player2.setPosition(38);

        game.payTax(player1.getName());
        game.payTax(player2.getName());

        assertTrue(player1.isHasPayedTax());
        assertTrue(player2.isHasPayedTax());
    }

    @Test
    void tryTaxingTwice(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.setPosition(4);
        player2.setPosition(38);

        game.payTax(player1.getName());
        game.payTax(player2.getName());

        assertThrows(IllegalMonopolyActionException.class, () -> game.payTax(player1.getName()));

        assertTrue(player1.isHasPayedTax());
        assertTrue(player2.isHasPayedTax());
    }

    @Test
    void removePayedTaxAfterRolling(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.setPosition(4);
        player2.setPosition(38);

        game.payTax(player1.getName());
        game.payTax(player2.getName());
        game.rollDice(player1.getName());

        assertFalse(player1.isHasPayedTax());
        assertTrue(player2.isHasPayedTax());
    }

    @Test
    void testPlayerNotOnTurn() {
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player2.setPosition(38);
        player2.removeMoney(1500);

        assertThrows(IllegalMonopolyActionException.class, () -> game.payTax(player2.getName()));
    }

    @Test
    void testNotOnTaxTile() {
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        assertThrows(IllegalMonopolyActionException.class, () -> game.payTax(player1.getName()));
    }
}
