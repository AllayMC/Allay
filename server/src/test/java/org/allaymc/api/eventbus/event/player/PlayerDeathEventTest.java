package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PlayerDeathEventTest {

    @Test
    void testDeathInfo() {
        EntityPlayer player = mock(EntityPlayer.class);
        var deathInfo = new Pair<>("death.attack.generic", new String[] {"Player"});
        var event = new PlayerDeathEvent(player, deathInfo);
        System.out.println("Death message: " + event.getDeathMessage());
        System.out.println("Arguments: " + Arrays.toString(event.getDeathInfo().right()));
        assertSame(player, event.getPlayer());
        assertSame(deathInfo, event.getDeathInfo());
    }

    @Test
    void testSetDeathInfo() {
        EntityPlayer player = mock(EntityPlayer.class);
        var event = new PlayerDeathEvent(player, new Pair<>("death.attack.generic", new String[0]));
        var newDeathInfo = new Pair<>("death.attack.player", new String[] {"Player", "Killer"});
        event.setDeathInfo(newDeathInfo);
        System.out.println("Death message: " + event.getDeathMessage());
        System.out.println("Arguments: " + Arrays.toString(event.getDeathInfo().right()));
        assertSame(newDeathInfo, event.getDeathInfo());
    }

    @Test
    void testGetDeathMessage() {
        EntityPlayer player = mock(EntityPlayer.class);
        var event = new PlayerDeathEvent(player, new Pair<>("death.attack.player", new String[] {"Player", "Killer"}));
        System.out.println("Death message: " + event.getDeathMessage());
        System.out.println("Arguments: " + Arrays.toString(event.getDeathInfo().right()));
        assertEquals("death.attack.player", event.getDeathMessage());
    }

    @Test
    void testSetDeathMessageWithoutArguments() {
        EntityPlayer player = mock(EntityPlayer.class);
        var event = new PlayerDeathEvent(player, new Pair<>("death.attack.generic", new String[] {"Player"}));
        event.setDeathMessage("test.death.message");
        System.out.println("Death message: " + event.getDeathMessage());
        System.out.println("Arguments: " + Arrays.toString(event.getDeathInfo().right()));
        assertEquals("test.death.message", event.getDeathMessage());
        assertArrayEquals(new String[0], event.getDeathInfo().right());
    }

    @Test
    void testSetDeathMessageWithArguments() {
        EntityPlayer player = mock(EntityPlayer.class);
        var event = new PlayerDeathEvent(player, new Pair<>("death.attack.generic", new String[0]));
        event.setDeathMessage("test.death.message", "LOL", "Zombie");
        System.out.println("Death message: " + event.getDeathMessage());
        System.out.println("Arguments: " + Arrays.toString(event.getDeathInfo().right()));
        assertEquals("test.death.message", event.getDeathMessage());
        assertArrayEquals(new String[] {"LOL", "Zombie"}, event.getDeathInfo().right());
    }
}
