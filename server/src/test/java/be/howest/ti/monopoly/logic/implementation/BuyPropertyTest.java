package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.implementation.properties.Street;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuyPropertyTest {
    @Test
    void buyProperty(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");
        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        Property property = new Street(1, "Maes", 60, 30, 2, 2);
        player1.setPosition(1);

        assertEquals(game.getCurrentPlayer(), player1);
        assertEquals(player1.getPosition(), property.getPosition());

        game.buyProperty(player1, property);

        assertEquals(1, player1.getProperties().size());

    }

    @Test
    void tryBuyingPropertyWithInsufficientMoney(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");
        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        Property property = new Street(1, "Maes", 999999, 30, 2, 2);
        player1.setPosition(1);

        assertThrows(IllegalMonopolyActionException.class, () -> game.buyProperty(player1, property));
        assertEquals(0, player1.getProperties().size());
    }

    @Test
    void tryBuyingPropertyThatIsAlreadyBought(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");
        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        Property property = new Street(1, "Maes", 60, 30, 2, 2);

        player1.setPosition(1);
        game.buyProperty(player1, property);

        player2.setPosition(1);
        assertFalse(game.isDirectSale());
        assertThrows(IllegalMonopolyActionException.class, () -> game.buyProperty(player2, property));

        assertEquals(1, player1.getProperties().size());
        assertEquals(0, player2.getProperties().size());
    }

    @Test
    void tryBuyingAPropertyWhenNotOnTile(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");
        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        Property property = new Street(1, "Maes", 60, 30, 2, 2);

        player1.setPosition(2);
        assertThrows(IllegalMonopolyActionException.class, () -> game.buyProperty(player1, property));

        assertEquals(0, player1.getProperties().size());
    }


    @Test
    void tryBuyingPropertyWhenNotCurrentPlayer(){
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");
        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        Property property = new Street(1, "Maes", 60, 30, 2, 2);

        player2.setPosition(2);
        assertThrows(IllegalMonopolyActionException.class, () -> game.buyProperty(player2, property));

        assertEquals(0, player2.getProperties().size());
    }

}
