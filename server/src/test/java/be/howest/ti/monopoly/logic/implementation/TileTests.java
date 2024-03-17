package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.non_properties.executing_tiles.Go;
import be.howest.ti.monopoly.logic.implementation.properties.Street;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TileTests {

    @Test
    public void testGetTileById() {
        MonopolyService service = new MonopolyService();

        Tile tile = service.getTile(1);
        Tile meas = new Street(1, "Maes",60, 30, 2, 2);

        assertEquals(tile, meas);
    }

    @Test
    public void testThrowGetTileById(){
        MonopolyService service = new MonopolyService();

        assertThrows(MonopolyResourceNotFoundException.class, () -> service.getTile(41));
    }

    @Test
    public void testGetTileByName() {
        MonopolyService service = new MonopolyService();

        Tile tile = service.getTile("Start");
        Tile go = new Go(0, "Start");

        assertEquals(tile, go);
    }

    @Test
    public void testThrowGetTileByName(){
        MonopolyService service = new MonopolyService();

        assertThrows(MonopolyResourceNotFoundException.class, () -> service.getTile("iDontExist"));
    }
}
