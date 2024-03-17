package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameMethodsTest {
    @Test
    void checkIsEnded(){
        Game game = new Game(2, "group_02");
        assertFalse(game.isEnded());
    }

    @Test
    void checkForWinner(){
        Game game = new Game(2, "group02");
        assertNull(game.getWinner());
    }

    @Test
    void checkEqualsMethod(){
        Game game = new Game(2, "group02");
        Game game2 = new Game(2, "group02");
        assertEquals(game, game2);
    }

    @Test
    void checkGameId(){
        Game game = new Game(2, "group02");
        assertEquals("group02", game.getGameId());
    }

    @Test
    void checkNumberOfPlayers(){
        Game game = new Game(2, "group_02");

        assertEquals(0, game.getPlayers().size());
    }

    @Test
    void findPlayerInList(){
        Game game = new Game(2, "group02");
        Player player1 = new Player("Green");
        Player player2 = new Player("Black");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        assertEquals(game.getPlayer("Green"), player1);
        assertNull(game.getPlayer("Orange"));
    }

    @Test
    void testGamesListWithOneGame(){
        MonopolyService monopolyService = new MonopolyService();
        List<Game> games = List.of(
                new Game(2, "test_1")
        );

        monopolyService.createGame("test", 2);

        assertEquals(games, monopolyService.getGames());
    }

    @Test
    void testGamesListWithMultipleGames(){
        MonopolyService monopolyService = new MonopolyService();
        List<Game> games = List.of(
                new Game(2, "test_1"),
                new Game(2, "test_2"),
                new Game(3, "test_3"),
                new Game(4, "test_4")
        );

        monopolyService.createGame("test", 2);
        monopolyService.createGame("test", 2);
        monopolyService.createGame("test", 3);
        monopolyService.createGame("test", 4);

        assertEquals(games, monopolyService.getGames());
    }

    @Test
    void testGamesListWithoutGames(){
        MonopolyService monopolyService = new MonopolyService();
        List<Game> games = List.of();

        assertEquals(games, monopolyService.getGames());
    }

    @Test
    void testGetNumberOfPlayers(){
        Game game = new Game(2, "test");
        Player player1 = new Player("Blue");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        assertEquals(2, game.getNumberOfPlayers());
    }

    @Test
    void testCanRollWhenGameIsNotStarted(){
        Game game = new Game(2, "Group_02");
        assertFalse(game.canRoll());
    }

    @Test
    void testIsCurrentPlayer(){
        Game game = new Game(2, "test");
        Player player1 = new Player("Blue");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        assertTrue(game.isCurrentPlayer(player1.getName()));
    }

    @Test
    void testIsNotCurrentPlayer(){
        Game game = new Game(2, "test");
        Player player1 = new Player("Blue");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);

        assertFalse(game.isCurrentPlayer(player2.getName()));
    }

    @Test
    void testRollDiceWithoutFullLobby(){
        Game game = new Game(2, "test");
        Player player1 = new Player("Red");

        game.playerJoinsGame(player1);

        assertThrows(IllegalMonopolyActionException.class, () -> game.rollDice(player1.getName()));
    }

    @Test
    void testRollDiceWhenBankrupt(){
        Game game = new Game(2, "Group_02");
        Player player1 = new Player("Blue");
        Player player2 = new Player("Red");

        game.playerJoinsGame(player1);
        game.playerJoinsGame(player2);
        player1.setPlayerBankrupt();
        Player currentPlayer = game.getCurrentPlayer();
        game.rollDice(player1.getName());
        assertEquals(currentPlayer, game.getCurrentPlayer());
    }
}
