package org.allaymc.server.player;

import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlayerNameMatcherTest {
    @Test
    void findOnlinePlayerByNamePrefersExactMatch() {
        var lower = mockPlayer("qhyperq");
        var upper = mockPlayer("QHYPERQ");
        var players = new LinkedHashMap<UUID, Player>();
        players.put(lower.getLoginData().getUuid(), lower);
        players.put(upper.getLoginData().getUuid(), upper);

        assertSame(upper, PlayerNameMatcher.findOnlinePlayerByName(players, "QHYPERQ"));
    }

    @Test
    void findOnlinePlayerByNameUsesUniqueIgnoreCaseMatch() {
        var player = mockPlayer("QHYPERQ");
        var players = new LinkedHashMap<UUID, Player>();
        players.put(player.getLoginData().getUuid(), player);

        assertSame(player, PlayerNameMatcher.findOnlinePlayerByName(players, "qhyperq"));
    }

    @Test
    void findOnlinePlayerByNameRejectsAmbiguousIgnoreCaseMatch() {
        var first = mockPlayer("QHYPERQ");
        var second = mockPlayer("qhyperq");
        var players = new LinkedHashMap<UUID, Player>();
        players.put(first.getLoginData().getUuid(), first);
        players.put(second.getLoginData().getUuid(), second);

        assertNull(PlayerNameMatcher.findOnlinePlayerByName(players, "QhYpErQ"));
    }

    @Test
    void containsStoredIdentityMatchesNameIgnoringCase() {
        assertTrue(PlayerNameMatcher.containsStoredIdentity(Set.of("QHYPERQ"), "qhyperq"));
    }

    @Test
    void resolveStoredIdentityReturnsStoredNameCase() {
        assertEquals("QHYPERQ", PlayerNameMatcher.resolveStoredIdentity(Set.of("QHYPERQ"), "qhyperq"));
    }

    @Test
    void matchesPlayerMatchesUuidAndNameIgnoringCase() {
        var player = mockPlayer("QHYPERQ");

        assertTrue(PlayerNameMatcher.matchesPlayer(player, "qhyperq"));
        assertTrue(PlayerNameMatcher.matchesPlayer(player, player.getLoginData().getUuid().toString()));
        assertFalse(PlayerNameMatcher.matchesPlayer(player, "other"));
    }

    private static Player mockPlayer(String originName) {
        var player = Mockito.mock(Player.class);
        var loginData = Mockito.mock(LoginData.class);
        var uuid = UUID.randomUUID();

        Mockito.when(player.getOriginName()).thenReturn(originName);
        Mockito.when(player.getLoginData()).thenReturn(loginData);
        Mockito.when(loginData.getUuid()).thenReturn(uuid);

        return player;
    }
}
