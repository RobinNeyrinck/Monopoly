package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetGameTest {
    @Test
    void getGameId(){
        MonopolyService monopolyService = new MonopolyService();
        monopolyService.createGame("group02", 2);
        assertEquals("group02_1", monopolyService.getGame("group02_1").getGameId());
    }

    @Test
    void tyyGettingGameThatDoesNotExist(){
        MonopolyService monopolyService = new MonopolyService();
        monopolyService.createGame("group02", 2);
        assertThrows(MonopolyResourceNotFoundException.class, () -> monopolyService.getGame("random_game_id"));
    }
}
