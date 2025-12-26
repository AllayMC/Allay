package org.allaymc.server.player;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.utils.JSONUtils;

import java.io.File;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Miroshka
 */
@Slf4j
public class UserCache {
    private static final String PLAYERS_DIR = "players";
    private static final String USER_CACHE_FILE = "players.json";

    private final Map<UUID, UserCacheEntry> getByUUID = new ConcurrentHashMap<>();
    private final Map<String, UserCacheEntry> getByName = new ConcurrentHashMap<>();
    private final List<UserCacheEntry> entries = Collections.synchronizedList(new ArrayList<>());
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final File userCacheFile;

    public UserCache() {
        Path playersDir = Path.of(PLAYERS_DIR);
        if (!java.nio.file.Files.exists(playersDir)) {
            try {
                java.nio.file.Files.createDirectories(playersDir);
            } catch (Exception e) {
                log.error("Failed to create players directory", e);
            }
        }
        this.userCacheFile = playersDir.resolve(USER_CACHE_FILE).toFile();
        load();
    }

    public void add(Player player) {
        add(player.getOriginName(), player.getLoginData().getUuid());
    }

    public void add(String name, UUID uuid) {
        lock.writeLock().lock();
        try {
            UserCacheEntry entry = new UserCacheEntry(name, uuid,
                    Date.from(Instant.now().plus(java.time.Duration.ofDays(30))));
            UserCacheEntry existing = getByUUID.get(uuid);
            if (existing != null) {
                entries.remove(existing);
                getByName.remove(existing.getName().toLowerCase(Locale.ROOT));
            }
            entries.add(0, entry);
            getByUUID.put(uuid, entry);
            getByName.put(name.toLowerCase(Locale.ROOT), entry);

            if (entries.size() > 1000) {
                UserCacheEntry removed = entries.remove(entries.size() - 1);
                getByUUID.remove(removed.getUuid());
                getByName.remove(removed.getName().toLowerCase(Locale.ROOT));
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String getOfflinePlayerName(UUID uuid) {
        UserCacheEntry entry = getByUUID.get(uuid);
        return entry != null ? entry.getName() : null;
    }

    public UUID getOfflinePlayerUUID(String name) {
        UserCacheEntry entry = getByName.get(name.toLowerCase(Locale.ROOT));
        return entry != null ? entry.getUuid() : null;
    }

    public void load() {
        if (!userCacheFile.exists()) {
            return;
        }

        try {
            List<UserCacheEntry> loaded = JSONUtils.fromList(userCacheFile, UserCacheEntry.class);
            if (loaded == null)
                return;

            lock.writeLock().lock();
            try {
                this.entries.clear();
                this.getByUUID.clear();
                this.getByName.clear();

                for (UserCacheEntry entry : loaded) {
                    this.entries.add(entry);
                    this.getByUUID.put(entry.getUuid(), entry);
                    this.getByName.put(entry.getName().toLowerCase(Locale.ROOT), entry);
                }
            } finally {
                lock.writeLock().unlock();
            }
        } catch (Exception e) {
            log.error("Failed to load user cache", e);
        }
    }

    public void save() {
        lock.readLock().lock();
        try {
            JSONUtils.toFile(userCacheFile.getPath(), new ArrayList<>(entries), (writer) -> {
                try {
                    writer.setIndent("  ");
                } catch (Exception ignored) {
                }
            });
        } catch (Exception e) {
            log.error("Failed to save user cache", e);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Getter
    public static class UserCacheEntry {
        private final String name;
        private final UUID uuid;
        private final Date expiresOn;

        public UserCacheEntry(String name, UUID uuid, Date expiresOn) {
            this.name = name;
            this.uuid = uuid;
            this.expiresOn = expiresOn;
        }
    }
}
