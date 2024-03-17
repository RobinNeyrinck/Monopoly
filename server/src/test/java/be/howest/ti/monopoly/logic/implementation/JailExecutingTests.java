package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JailExecutingTests {

    @Test
    public void testOnGoToJailTile() {
        JailExecuting jail = new JailExecuting();
        Player player = new Player("me");

        player.setPosition(30);

        boolean onJail = player.getPosition() == 30;

        assertTrue(onJail);
        jail.sendToJail(player);

        assertTrue(player.isJailed());
        assertEquals(10, player.getPosition());
    }

    @Test
    public void testTripleDoubleRolled() {
        JailExecuting jail = new JailExecuting();
        Player player = new Player("me");

        jail.addDoubleRolled();
        jail.addDoubleRolled();
        jail.addDoubleRolled();

        jail.sendToJail(player);

        assertTrue(jail.checkIfTripleDoubleRolled());
        assertEquals(10, player.getPosition());
        assertTrue(player.isJailed());
    }

    @Test
    public void testResetAmountOfDoubleRolled() {
        JailExecuting jail = new JailExecuting();
        Player player = new Player("me");

        jail.addDoubleRolled();
        assertEquals(1, jail.getAmountOfDoubleRolled());

        jail.resetAmountOfDoubleRolled();
        assertEquals(0, jail.getAmountOfDoubleRolled());
    }
}
