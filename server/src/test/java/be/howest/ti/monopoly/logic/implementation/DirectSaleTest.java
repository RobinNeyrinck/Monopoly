package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DirectSaleTest {
    @Test
    void directSaleReturnsTrue(){
        Game game = new Game(2, "group_02");

        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);
        int propertyTilePosition = 1;
        player1.setPosition(propertyTilePosition);
        assertEquals(player1.getPosition(), game.getCurrentPlayer().getPosition());
        assertEquals(game.getCurrentTile(game.getCurrentPlayer()).getPosition(), player1.getPosition());
        assertTrue(game.isDirectSale());
    }

    @Test
    void directSaleReturnsFalse(){
        Game game = new Game(2, "group_02");

        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        int nonPropertyTilePosition = 10;
        player1.setPosition(nonPropertyTilePosition);

        assertEquals(player1.getPosition(), game.getCurrentPlayer().getPosition());
        assertEquals(game.getCurrentTile(game.getCurrentPlayer()).getPosition(), player1.getPosition());
        assertFalse(game.isDirectSale());
    }
}
