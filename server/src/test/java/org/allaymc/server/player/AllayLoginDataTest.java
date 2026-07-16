package org.allaymc.server.player;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AllayLoginDataTest {

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
