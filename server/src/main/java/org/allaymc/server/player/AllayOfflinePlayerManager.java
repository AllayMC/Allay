package org.allaymc.server.player;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.server.PlayerNameChangeEvent;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.OfflinePlayer;
import org.allaymc.api.player.OfflinePlayerManager;
import org.allaymc.api.player.PlayerData;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h2>Index Structure</h2>
 * The LevelDB index maintains three types of mappings:
 * <pre>
 * xuid:&lt;xuid&gt;      → &lt;storage-uuid&gt;
 * name_uuid:&lt;uuid&gt; → &lt;storage-uuid&gt;
 * name:&lt;nickname&gt;  → &lt;storage-uuid&gt;
 * </pre>
 *
 * @author IWareQ
 */
@Slf4j
public class AllayOfflinePlayerManager implements OfflinePlayerManager {
    private static final String XUID_PREFIX = "xuid:";
    private static final String NAME_UUID_PREFIX = "name_uuid:";
    private static final String NAME_PREFIX = "name:";

    private final DB database;
    private final AllayPlayerStorage playerStorage;

    private final Map<UUID, AllayOfflinePlayer> cache;

    @SneakyThrows
    public AllayOfflinePlayerManager(Path dbPath, AllayPlayerStorage playerStorage) {
        this.database = Iq80DBFactory.factory.open(dbPath.toFile(), new Options().createIfMissing(true));
        this.playerStorage = playerStorage;
        this.cache = new ConcurrentHashMap<>();

        Files.createDirectories(dbPath);
    }

    /**
     * Handles player login by creating new player data or loading existing data,
     * and updates all identity mappings accordingly.
     * <p>
     * This is the primary entry point for player authentication. It performs
     * identity resolution using XUID (if available) or nickname UUID, handles
     * nickname changes and collisions, and ensures all index mappings are current.
     *
     * @param loginData The login data containing XUID, nickname, and authentication info
     * @return The OfflinePlayer instance for this player (never null)
     */
    public OfflinePlayer handleUpdates(LoginData loginData) {
        var xuid = loginData.getParsedXuid();
        var nickname = loginData.getXname();
        var nameUuid = this.nicknameToUuid(nickname);
        var hasXboxAuth = xuid != null;

        UUID storageUuid;
        AllayOfflinePlayer player;

        if (hasXboxAuth) {
            // Xbox auth: Find by XUID
            var storageUuidStr = this.getMapping(XUID_PREFIX + xuid);
            if (storageUuidStr != null) {
                storageUuid = UUID.fromString(storageUuidStr);
                player = Objects.requireNonNull(this.loadPlayer(storageUuid));
                this.handleNicknameChange(player, nameUuid, nickname);
                return player;
            }
        }

        // Offline mode: Find by nickname UUID
        var storageUuidStr = this.getMapping(NAME_UUID_PREFIX + nameUuid);
        if (storageUuidStr != null) {
            storageUuid = UUID.fromString(storageUuidStr);
            return Objects.requireNonNull(this.loadPlayer(storageUuid));
        }

        return this.createNewPlayer(xuid, nickname, nameUuid, loginData.getUuid());
    }

    private void handleNicknameChange(AllayOfflinePlayer player, UUID newNicknameUuid, String newNickname) {
        var oldNickname = player.getName();
        if (oldNickname.equals(newNickname)) {
            return;
        }

        var storageUuid = player.getStorageUuid();

        var existingStorageUuidStr = this.getMapping(NAME_UUID_PREFIX + newNicknameUuid);
        var nicknameIsOccupied = existingStorageUuidStr != null && !existingStorageUuidStr.equals(storageUuid.toString());
        if (nicknameIsOccupied) {
            var conflictingStorageUuid = UUID.fromString(existingStorageUuidStr);

            var conflictingPlayer = this.loadPlayer(conflictingStorageUuid);
            if (conflictingPlayer != null) {
                var conflictOfflineData = conflictingPlayer.getOfflineNbtData();
                var updated = conflictOfflineData.toBuilder()
                        .putString(OfflinePlayer.TAG_ORIGINAL_NAME, conflictingPlayer.getName())
                        .build();

                conflictingPlayer.setOfflineNbtData(updated);

                var oldConflictNickname = conflictingPlayer.getName();
                var tempNickname = this.generateUniqueNickname(oldConflictNickname);

                this.updateNicknameMappings(conflictingStorageUuid, oldConflictNickname, tempNickname);

                conflictingPlayer.updateName(tempNickname);
                conflictingPlayer.save();

                if (this.cache.containsKey(conflictingStorageUuid)) {
                    this.cache.put(conflictingStorageUuid, conflictingPlayer);
                }
                log.info("Invalidated nickname for conflicting player: {}, renamed to: {}", conflictingStorageUuid, tempNickname);
            }
        }

        this.updateNicknameMappings(storageUuid, oldNickname, newNickname);

        player.updateName(newNickname);
        player.save();

        this.cache.put(storageUuid, player);

        new PlayerNameChangeEvent(player, oldNickname, newNickname).call();

        log.debug("Updated nickname mappings: {} → {}", oldNickname, newNickname);
    }

    private void updateNicknameMappings(UUID storageUuid, String oldNickname, String newNickname) {
        var oldNormalizedNick = oldNickname.toLowerCase(Locale.ROOT);
        var oldNicknameUuid = this.nicknameToUuid(oldNickname);

        this.deleteMapping(NAME_PREFIX + oldNormalizedNick);
        this.deleteMapping(NAME_UUID_PREFIX + oldNicknameUuid);

        var newNormalizedNick = newNickname.toLowerCase(Locale.ROOT);
        var newNicknameUuid = this.nicknameToUuid(newNickname);

        this.setMapping(NAME_PREFIX + newNormalizedNick, storageUuid.toString());
        this.setMapping(NAME_UUID_PREFIX + newNicknameUuid, storageUuid.toString());
    }

    private AllayOfflinePlayer createNewPlayer(Long xuid, String nickname, UUID nameUuid, UUID legacyStorageUuid) {
        var storageUuid = UUID.randomUUID();
        var normalizedNick = nickname.toLowerCase(Locale.ROOT);

        var hasXboxAuth = xuid != null;
        if (hasXboxAuth) {
            this.setMapping(XUID_PREFIX + xuid, storageUuid.toString());
        }
        this.setMapping(NAME_UUID_PREFIX + nameUuid, storageUuid.toString());
        this.setMapping(NAME_PREFIX + normalizedNick, storageUuid.toString());

        var playerData = PlayerData.createEmpty();
        if (this.playerStorage.hasPlayerData(legacyStorageUuid)) { // TODO: remove in 0.13+
            log.info("Migrating legacy player data for {} (legacy storage UUID: {})", nickname, legacyStorageUuid);
            playerData = this.playerStorage.readPlayerData(legacyStorageUuid);
            this.playerStorage.removePlayerData(legacyStorageUuid);
        }

        playerData.setName(nickname);
        if (hasXboxAuth) {
            playerData.setXuid(xuid);
        }

        var player = new AllayOfflinePlayer(playerData, storageUuid);
        player.save();

        this.cache.put(storageUuid, player);
        return player;
    }

    @Override
    public OfflinePlayer getByXboxUserId(long xuid) {
        var storageUuidStr = this.getMapping(XUID_PREFIX + xuid);
        if (storageUuidStr == null) {
            return null;
        }

        var storageUuid = UUID.fromString(storageUuidStr);
        return this.loadPlayer(storageUuid);
    }

    @Override
    public OfflinePlayer getByNameUuid(UUID nameUuid) {
        if (nameUuid == null) {
            return null;
        }

        var storageUuidStr = this.getMapping(NAME_UUID_PREFIX + nameUuid);
        if (storageUuidStr == null) {
            return null;
        }

        var storageUuid = UUID.fromString(storageUuidStr);
        return this.loadPlayer(storageUuid);
    }

    @Override
    public OfflinePlayer getByName(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            return null;
        }

        var normalizedNick = nickname.toLowerCase(Locale.ROOT);
        var storageUuidStr = this.getMapping(NAME_PREFIX + normalizedNick);
        if (storageUuidStr == null) {
            return null;
        }

        var storageUuid = UUID.fromString(storageUuidStr);
        return this.loadPlayer(storageUuid);
    }

    private AllayOfflinePlayer loadPlayer(UUID storageUuid) {
        var cached = this.cache.get(storageUuid);
        if (cached != null) {
            return cached;
        }

        var playerData = this.playerStorage.readPlayerData(storageUuid);
        if (playerData == null || playerData.getName() == null) {
            log.warn("Player data not found for storage UUID: {}", storageUuid);
            return null;
        }

        var player = new AllayOfflinePlayer(playerData, storageUuid);
        cache.put(storageUuid, player);
        return player;
    }

    public void saveAll() {
        for (var player : this.cache.values()) {
            player.save();
        }
    }

    public void shutdown() {
        this.saveAll();
        this.cache.clear();

        try {
            this.database.close();
        } catch (IOException e) {
            log.error("Error closing database", e);
        }
    }

    private UUID nicknameToUuid(String nickname) {
        return UUID.nameUUIDFromBytes(nickname.toLowerCase(Locale.ROOT).getBytes(StandardCharsets.UTF_8));
    }

    private void deleteMapping(String key) {
        this.database.delete(key.getBytes(StandardCharsets.UTF_8));
    }

    private void setMapping(String key, String value) {
        this.database.put(
                key.getBytes(StandardCharsets.UTF_8),
                value.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String getMapping(String key) {
        var value = this.database.get(key.getBytes(StandardCharsets.UTF_8));
        return value != null ? new String(value, StandardCharsets.UTF_8) : null;
    }

    private String generateUniqueNickname(String baseName) {
        for (int i = 0; i < 10; i++) {
            var suffix = UUID.randomUUID().toString().toLowerCase(Locale.ROOT);
            var uniqueName = baseName + "_" + suffix.replaceAll("-", "_");
            var normalizedUnique = uniqueName.toLowerCase(Locale.ROOT);

            var existing = this.getMapping(NAME_PREFIX + normalizedUnique);
            if (existing == null) {
                log.info("Generated unique nickname: {} for base: {}", uniqueName, baseName);
                return uniqueName;
            }
        }

        // ??? reaching this point would indicate a serious system issue
        throw new RuntimeException("Failed to generate unique nickname for: " + baseName);
    }
}
