package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DontBuyProperty {

    @Test
    void dontBuyProperty(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        player1.setPosition(1);
        assertTrue(game.isDirectSale());
        game.dontBuyProperty();
        int totalAmountOfMoney = 1500;
        assertEquals(player1.getMoney(), totalAmountOfMoney);
        assertEquals(game.getCurrentPlayer(), player2);
        assertEquals(Collections.emptyList(), player1.getProperties());
    }
}
