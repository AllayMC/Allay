package org.allaymc.server.player;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code AllayLoginDataTest} verifies authenticated and offline persistent identity
 * selection through login UUID resolution.
 */
class AllayLoginDataTest {

    /**
     * Supplies an authenticated UUID and a player name to identity resolution. The
     * UUID must be retained, and an authenticated request without that identity must
     * fail rather than derive another UUID.
     */
    @Test
    void testAuthenticatedIdentityIsPreserved() {
        var identity = UUID.nameUUIDFromBytes(
                "pocket-auth-1-xuid:123456789".getBytes(StandardCharsets.UTF_8)
        );

        assertEquals(identity, AllayLoginData.resolveUuid(true, identity, "Player"));
        assertThrows(
                NullPointerException.class,
                () -> AllayLoginData.resolveUuid(true, null, "Player")
        );
    }

    /**
     * Resolves the same offline name with absent and varying supplied identities.
     * The resulting UUID must depend on the prefixed name bytes and remain separate
     * from the authenticated fixture and another name.
     */
    @Test
    void testOfflineIdentityIsStableAndSeparatedFromOnlineIdentity() {
        var first = AllayLoginData.resolveUuid(false, null, "123456789");
        var second = AllayLoginData.resolveUuid(false, UUID.randomUUID(), "123456789");
        var online = UUID.nameUUIDFromBytes(
                "pocket-auth-1-xuid:123456789".getBytes(StandardCharsets.UTF_8)
        );

        assertEquals(first, second);
        assertEquals(
                UUID.nameUUIDFromBytes("xname:123456789".getBytes(StandardCharsets.UTF_8)),
                first
        );
        assertNotEquals(online, first);
        assertNotEquals(first, AllayLoginData.resolveUuid(false, null, "OtherPlayer"));
    }

    /**
     * Supplies blank and absent names for offline identity resolution. Both requests
     * must fail because neither supplies the name needed to select a persistent
     * offline storage key.
     */
    @Test
    void testOfflineIdentityRejectsBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AllayLoginData.resolveUuid(false, null, " ")
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> AllayLoginData.resolveUuid(false, null, null)
        );
    }
}
