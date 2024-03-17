package be.howest.ti.monopoly.logic.implementation;

import org.jcodings.ApplyAllCaseFoldFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoiningGameTests {

    @Test
    void joinGameWithPlayer() {
            Game game = new Game(2, "group_02");
            game.playerJoinsGame(new Player("Green"));
            assertEquals(1, game.getPlayers().size());
    }

    @Test
    void tryJoiningWithFullPlayersList(){
        Game game = new Game(2, "group_02");
        game.playerJoinsGame(new Player("Green"));
        game.playerJoinsGame(new Player("Yellow"));
        game.playerJoinsGame(new Player("Black"));
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void CheckEmptyLobby(){
        Game game = new Game(2, "group_02");
        assertFalse(game.alreadyInLobby(new Player("player")));
    }

    @Test
    void tryJoiningWithSameName(){
        Game game = new Game(2, "group_02");
        game.playerJoinsGame(new Player("Green"));
        game.playerJoinsGame(new Player("Green"));
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    void tryJoiningWithSameNameAndFullLobby(){
        Game game = new Game(2, "group_02");
        game.playerJoinsGame(new Player("Green"));
        game.playerJoinsGame(new Player("Blue"));
        game.playerJoinsGame(new Player("Green"));
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void checkIfGameIsStartedAfterJoining(){
        Game game = new Game(2, "group_02");
        game.playerJoinsGame(new Player("Green"));
        game.playerJoinsGame(new Player("Yellow"));
        assertTrue(game.isStarted());
    }

    @Test
    void checkIsNotStarted(){
        Game game = new Game(2, "group_02");
        assertFalse(game.isStarted());
    }
}
