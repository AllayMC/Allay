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
    void testPlayFabIdIsReadFromTheKeyClientsSend() {
        var clientData = new com.google.gson.JsonObject();
        clientData.addProperty("PlayFabId", "f17248eadd1fd0a8");

        assertEquals("f17248eadd1fd0a8", AllayLoginData.readPlayFabId(clientData));
    }

    @Test
    void testPlayFabIdFallsBackToTheLegacyKeyAndIsOptional() {
        var legacy = new com.google.gson.JsonObject();
        legacy.addProperty("PlayFabID", "abc");

        assertEquals("abc", AllayLoginData.readPlayFabId(legacy));
        assertNull(AllayLoginData.readPlayFabId(new com.google.gson.JsonObject()));
    }

    @Test
    void testVerifiedPlayFabIdFromTheTokenWinsOverTheClientValue() {
        assertEquals("F17248EADD1FD0A8", AllayLoginData.resolvePlayFabId(true, "F17248EADD1FD0A8", "client"));
        assertEquals("client", AllayLoginData.resolvePlayFabId(true, " ", "client"));
        assertEquals("client", AllayLoginData.resolvePlayFabId(true, null, "client"));
        // An unverified token's claim is not trusted.
        assertEquals("client", AllayLoginData.resolvePlayFabId(false, "F17248EADD1FD0A8", "client"));
        assertNull(AllayLoginData.resolvePlayFabId(false, null, null));
    }

    @Test
    void testPlatformOnlineIdIsReadFromClientDataAndDefaultsToEmpty() {
        var clientData = new com.google.gson.JsonObject();
        clientData.addProperty("PlatformOnlineId", "12345");
        clientData.addProperty("DeviceModel", "B550M DS3H");

        assertEquals("12345", AllayLoginData.readPlatformOnlineId(clientData));
        assertEquals("", AllayLoginData.readPlatformOnlineId(new com.google.gson.JsonObject()));
    }

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
