package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.server.command.MockCommandSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class NameSelectorArgumentTest {
    @Test
    void matchesNamesIgnoringCase() throws Exception {
        var entity = Mockito.mock(Entity.class);
        Mockito.when(entity.getCommandSenderName()).thenReturn("QHYPERQ");

        var predicate = new Name().getPredicate(SelectorType.ALL_PLAYERS, new MockCommandSender(), new Location3d(0, 0, 0, null), "qhyperq");

        assertTrue(predicate.test(entity));
    }

    @Test
    void excludesNamesIgnoringCase() throws Exception {
        var entity = Mockito.mock(Entity.class);
        Mockito.when(entity.getCommandSenderName()).thenReturn("QHYPERQ");

        var predicate = new Name().getPredicate(SelectorType.ALL_PLAYERS, new MockCommandSender(), new Location3d(0, 0, 0, null), "!qhyperq");

        assertFalse(predicate.test(entity));
    }
}
