package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.non_properties.ChanceCard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChanceTest {

    @Test
    public void testChanceCards() {
        MonopolyService service = new MonopolyService();

        List<ChanceCard> chanceCards = List.of(
                new ChanceCard("Advance to Boardwalk"),
                new ChanceCard("Advance to Go (Collect $200)"),
                new ChanceCard("If you pass Go, collect $200")
        );

        assertEquals(service.getChance(), chanceCards);
    }
}
