package org.allaymc.server.client.storage;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.storage.PlayerData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayNBTFilePlayerStorage extends AllayPlayerStorage {
    protected Path dataFolderPath;

    @SneakyThrows
    public AllayNBTFilePlayerStorage(Path dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
        if (!Files.exists(dataFolderPath)) Files.createDirectory(dataFolderPath);
    }

    @Override
    public PlayerData readPlayerData(UUID uuid) {
        var path = buildPlayerDataFilePath(uuid);
        if (!Files.exists(path)) return PlayerData.createEmpty();

        try (var reader = NbtUtils.createGZIPReader(Files.newInputStream(path))) {
            return PlayerData.fromNBT((NbtMap) reader.readTag());
        } catch (Throwable e) {
            log.error("Error while reading player data {}", uuid, e);
            return PlayerData.createEmpty();
        }
    }

    @SneakyThrows
    @Override
    public void savePlayerData(UUID uuid, PlayerData playerData) {
        var path = buildPlayerDataFilePath(uuid);

        var oldPath = path.resolveSibling(uuid + "_old.nbt");
        if (Files.exists(oldPath)) {
            // The old file
            log.warn("Undeleted tmp player data file is found, which may caused by incorrect shutdown. File: {}", oldPath);
            Files.delete(oldPath);
        }

        // Rename current file to uuid_old.nbt
        var currentFileExists = Files.exists(path);
        if (currentFileExists) Files.move(path, oldPath);

        try (var writer = NbtUtils.createGZIPWriter(Files.newOutputStream(path))) {
            writer.writeTag(playerData.toNBT());
        } catch (Throwable e) {
            if (currentFileExists) {
                // error, rename uuid_old.nbt file to uuid.nbt
                Files.move(oldPath, path);
            }
            log.error("Error while writing player data {}", uuid, e);
        }

        // delete uuid_old.nbt file
        Files.deleteIfExists(oldPath);
    }

    @SneakyThrows
    @Override
    public boolean removePlayerData(UUID uuid) {
        return Files.deleteIfExists(buildPlayerDataFilePath(uuid));
    }

    @Override
    public boolean hasPlayerData(UUID uuid) {
        return Files.exists(buildPlayerDataFilePath(uuid));
    }

    protected Path buildPlayerDataFilePath(UUID uuid) {
        return dataFolderPath.resolve(uuid.toString() + ".nbt");
    }
}
