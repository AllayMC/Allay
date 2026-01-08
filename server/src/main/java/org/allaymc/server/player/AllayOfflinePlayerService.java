package org.allaymc.server.player;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.player.PlayerNicknameChangeEvent;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.OfflinePlayer;
import org.allaymc.api.player.OfflinePlayerService;
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
 * xuid:&lt;xuid&gt;              → &lt;storage-uuid&gt;
 * nickname_uuid:&lt;uuid&gt;     → &lt;storage-uuid&gt;
 * nickname:&lt;nickname&gt;      → &lt;storage-uuid&gt;
 * </pre>
 *
 * @author IWareQ
 */
@Slf4j
public class AllayOfflinePlayerService implements OfflinePlayerService {
    public static final String TAG_ORIGINAL_NICKNAME = "OriginalNickname";

    private static final String XUID_PREFIX = "xuid:";
    private static final String NICKNAME_UUID_PREFIX = "nickname_uuid:";
    private static final String NICKNAME_PREFIX = "nickname:";

    private final DB database;
    private final AllayPlayerStorage playerStorage;

    private final Map<UUID, AllayOfflinePlayer> cache;

    @SneakyThrows
    public AllayOfflinePlayerService(Path dbPath, AllayPlayerStorage playerStorage) {
        this.database = Iq80DBFactory.factory.open(dbPath.toFile(), new Options().createIfMissing(true));
        this.playerStorage = playerStorage;
        this.cache = new ConcurrentHashMap<>();

        Files.createDirectories(dbPath);
    }

    @Override
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
        var storageUuidStr = this.getMapping(NICKNAME_UUID_PREFIX + nameUuid);
        if (storageUuidStr != null) {
            storageUuid = UUID.fromString(storageUuidStr);
            return Objects.requireNonNull(this.loadPlayer(storageUuid));
        }

        // Player not found - create new one
        return this.createNewPlayer(xuid, nickname, nameUuid);
    }

    private void handleNicknameChange(AllayOfflinePlayer player, UUID newNicknameUuid, String newNickname) {
        var oldNickname = player.getNickname();
        if (oldNickname.equals(newNickname)) {
            return;
        }

        var storageUuid = player.getStorageUuid();
        var oldNormalizedNick = oldNickname.toLowerCase(Locale.ROOT);
        var newNormalizedNick = newNickname.toLowerCase(Locale.ROOT);

        var existingStorageUuid = this.getMapping(NICKNAME_UUID_PREFIX + newNicknameUuid);
        var nicknameIsOccupied = existingStorageUuid != null && !existingStorageUuid.equals(storageUuid.toString());
        if (nicknameIsOccupied) {
            var conflictingStorageUuid = UUID.fromString(existingStorageUuid);

            var conflictingPlayer = this.loadPlayer(conflictingStorageUuid);
            if (conflictingPlayer != null) {
                var conflictOfflineData = conflictingPlayer.getOfflineNbtData();
                var updated = conflictOfflineData.toBuilder()
                        .putString(TAG_ORIGINAL_NICKNAME, conflictingPlayer.getNickname())
                        .build();

                conflictingPlayer.setOfflineNbtData(updated);

                var tempNickname = this.generateUniqueNickname(conflictingPlayer.getNickname());
                conflictingPlayer.updateNickname(tempNickname);
                conflictingPlayer.save();

                if (this.cache.containsKey(conflictingStorageUuid)) {
                    this.cache.put(conflictingStorageUuid, conflictingPlayer);
                }
                log.info("Invalidated nickname for conflicting player: {}", conflictingStorageUuid);
            }
        }

        this.deleteMapping(NICKNAME_PREFIX + oldNormalizedNick);
        this.deleteMapping(NICKNAME_UUID_PREFIX + this.nicknameToUuid(oldNickname));

        this.setMapping(NICKNAME_PREFIX + newNormalizedNick, storageUuid.toString());
        this.setMapping(NICKNAME_UUID_PREFIX + newNicknameUuid, storageUuid.toString());

        player.updateNickname(newNickname);
        player.save();

        this.cache.put(storageUuid, player);

        new PlayerNicknameChangeEvent(player, oldNickname, newNickname).call();

        log.debug("Updated nickname mappings: {} → {}", oldNickname, newNickname);
    }

    private AllayOfflinePlayer createNewPlayer(Long xuid, String nickname, UUID nameUuid) {
        var storageUuid = UUID.randomUUID();
        var normalizedNick = nickname.toLowerCase(Locale.ROOT);

        var hasXboxAuth = xuid != null;
        if (hasXboxAuth) {
            this.setMapping(XUID_PREFIX + xuid, storageUuid.toString());
        }
        this.setMapping(NICKNAME_UUID_PREFIX + nameUuid, storageUuid.toString());
        this.setMapping(NICKNAME_PREFIX + normalizedNick, storageUuid.toString());

        var playerData = PlayerData.createEmpty();
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
        // TODO: maybe warn if isXboxAuth == false?
        var storageUuidStr = this.getMapping(XUID_PREFIX + xuid);
        if (storageUuidStr == null) {
            return null;
        }

        var storageUuid = UUID.fromString(storageUuidStr);
        return this.loadPlayer(storageUuid);
    }

    @Override
    public OfflinePlayer getByNicknameUuid(UUID nameUuid) {
        if (nameUuid == null) {
            return null;
        }

        var storageUuidStr = this.getMapping(NICKNAME_UUID_PREFIX + nameUuid);
        if (storageUuidStr == null) {
            return null;
        }

        var storageUuid = UUID.fromString(storageUuidStr);
        return this.loadPlayer(storageUuid);
    }

    @Override
    public OfflinePlayer getByNickname(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            return null;
        }

        var normalizedNick = nickname.toLowerCase(Locale.ROOT);
        var storageUuidStr = this.getMapping(NICKNAME_PREFIX + normalizedNick);
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

    @Override
    public void saveAll() {
        for (var player : this.cache.values()) {
            player.save();
        }
    }

    @Override
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

            var existing = this.getMapping(NICKNAME_PREFIX + normalizedUnique);
            if (existing == null) {
                log.info("Generated unique nickname: {} for base: {}", uniqueName, baseName);
                return uniqueName;
            }
        }

        // ??? reaching this point would indicate a serious system issue
        throw new RuntimeException("Failed to generate unique nickname for: " + baseName);
    }
}
