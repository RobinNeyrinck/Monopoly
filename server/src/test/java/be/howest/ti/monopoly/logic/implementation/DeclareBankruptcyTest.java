package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.implementation.properties.Street;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
public class DeclareBankruptcyTest {
    @Test
    void tryDeclarePlayerBankruptException(){
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        assertThrows(IllegalMonopolyActionException.class, () -> game.declarePlayerBankrupt(player2.getName()));
    }

    @Test
    void declarePlayerBankrupt(){
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        game.declarePlayerBankrupt(player.getName());
        assertTrue(player.isBankrupt());
    }

    @Test
    void clearAssetListFromBankruptPlayer(){
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        Property property = new Street(3, "Maes Radler", 60, 30, 2, 4);

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        player.setPosition(3);
        game.buyProperty(player, property);

        assertEquals(1, player.getProperties().size());

        player2.setPosition(3);
        game.dontBuyProperty();

        game.declarePlayerBankrupt(player.getName());
        assertEquals(Collections.emptyList(), player.getProperties());
    }

    @Test
    void checkIfGameIsEnded() {
        Game game = new Game(2, "group_02");
        Player player = new Player("Green");
        Player player2 = new Player("Yellow");

        game.playerJoinsGame(player);
        game.playerJoinsGame(player2);

        game.declarePlayerBankrupt(player.getName());
        assertTrue(game.isEnded());
    }
}
