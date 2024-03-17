package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.non_properties.CommunityCard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommunityChestTest {

    @Test
    public void testCommunityChests() {
        MonopolyService service = new MonopolyService();

        List<CommunityCard> communityChests = List.of(
                new CommunityCard("Advance to Boardwalk"),
                new CommunityCard("Bank error in your favor. Collect $200"),
                new CommunityCard("Get Out of Jail Free")
        );

        assertEquals(service.getCommunityChest(), communityChests);
    }
}
