package org.allaymc.server.client.storage.nbtfile;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.storage.NativeFilePlayerStorage;
import org.allaymc.api.client.storage.PlayerData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Allay Project 2024/1/27
 *
 * @author daoge_cmd
 */
@Slf4j
@Getter
public class AllayNBTFilePlayerStorage implements NativeFilePlayerStorage {

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
        } catch (IOException e) {
            log.error("Error while reading player data " + uuid, e);
            return PlayerData.createEmpty();
        }
    }

    @SneakyThrows
    @Override
    public void savePlayerData(UUID uuid, PlayerData playerData) {
        var path = buildPlayerDataFilePath(uuid);
        Files.deleteIfExists(path);
        try (var writer = NbtUtils.createGZIPWriter(Files.newOutputStream(path))) {
            writer.writeTag(playerData.toNBT());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing player data " + uuid, e);
        }
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
