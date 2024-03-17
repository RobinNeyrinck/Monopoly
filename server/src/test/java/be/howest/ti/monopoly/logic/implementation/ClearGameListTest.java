package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearGameListTest {

    @Test
    void testClearGameList() {
        MonopolyService service = new MonopolyService();

        service.createGame("group02", 2);

        assertEquals(1, service.getGames().size());

        service.clearGameList();

        assertEquals(0, service.getGames().size());
    }
}
