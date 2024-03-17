package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.InsufficientFundsException;
import be.howest.ti.monopoly.logic.implementation.properties.Street;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollectDebtTest {

    @Test
    void checkIfPlayerCanPayRentTest() {
        Game game = new Game(2, "group_02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Orange");
        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        Property property = new Street(1, "Maes", 60, 30, 2, 2);
        player1.setPosition(1);

        game.buyProperty(player1, property);

        player2.setPosition(1);
        game.collectDebt(player2, player1);

        assertEquals(1498, player2.getMoney());
        assertEquals(1442, player1.getMoney());
    }
}
