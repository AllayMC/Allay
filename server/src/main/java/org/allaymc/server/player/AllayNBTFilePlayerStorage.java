package org.allaymc.server.player;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.PlayerData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayNBTFilePlayerStorage extends AllayPlayerStorage {
    private static final String NBT_EXTENSION = ".nbt";
    private static final String TEMP_EXTENSION = NBT_EXTENSION + ".tmp";

    protected Path dataFolderPath;

    @SneakyThrows
    public AllayNBTFilePlayerStorage(Path dataFolderPath) {
        this.dataFolderPath = dataFolderPath;

        Files.createDirectories(dataFolderPath);

        try (var stream = Files.list(dataFolderPath)) {
            stream.filter(path -> path.toString().endsWith(TEMP_EXTENSION)).forEach(tempFile -> {
                try {
                    Files.deleteIfExists(tempFile);
                    log.warn("Cleaned up orphaned temp file from previous session: {}", tempFile.getFileName());
                } catch (IOException e) {
                    log.error("Failed to delete orphaned temp file: {}", tempFile, e);
                }
            });
        } catch (IOException e) {
            log.error("Failed to cleanup orphaned temp files", e);
        }
    }

    @Override
    public PlayerData readPlayerData(UUID uuid) {
        var path = buildPlayerDataFilePath(uuid);
        if (!Files.exists(path)) {
            return PlayerData.createEmpty();
        }

        try (var reader = NbtUtils.createGZIPReader(Files.newInputStream(path))) {
            return PlayerData.fromNBT((NbtMap) reader.readTag());
        } catch (Throwable e) {
            log.error("Failed to read player data for UUID: {}", uuid, e);
            return PlayerData.createEmpty();
        }
    }

    @SneakyThrows
    @Override
    public void savePlayerData(UUID uuid, PlayerData playerData) {
        var finalPath = this.buildPlayerDataFilePath(uuid);
        var tempPath = this.buildTempPath(uuid);

        try {
            try (var writer = NbtUtils.createGZIPWriter(Files.newOutputStream(tempPath))) {
                writer.writeTag(playerData.toNBT());
            }

            Files.move(tempPath, finalPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            log.error("Failed to save player data for UUID: {}", uuid, e);
            Files.deleteIfExists(tempPath);
        }
    }

    @Override
    public boolean removePlayerData(UUID uuid) {
        try {
            return Files.deleteIfExists(this.buildPlayerDataFilePath(uuid));
        } catch (IOException e) {
            log.error("Failed to remove player data for UUID: {}", uuid, e);
            return false;
        }
    }

    @Override
    public boolean hasPlayerData(UUID uuid) {
        return Files.exists(this.buildPlayerDataFilePath(uuid));
    }

    protected Path buildPlayerDataFilePath(UUID uuid) {
        return this.dataFolderPath.resolve(uuid.toString() + NBT_EXTENSION);
    }

    private Path buildTempPath(UUID uuid) {
        return this.dataFolderPath.resolve(uuid.toString() + TEMP_EXTENSION);
    }
}
