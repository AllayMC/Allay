package org.allaymc.server.client.storage.nbtfile;

import lombok.Getter;
import lombok.SneakyThrows;
import org.allaymc.api.client.storage.BasePlayerStorage;
import org.allaymc.api.client.storage.NativeFilePlayerStorage;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Allay Project 2024/1/27
 *
 * @author daoge_cmd
 */
@Getter
public class AllayNBTFilePlayerStorage implements NativeFilePlayerStorage {

    protected Path dataFolderPath;

    @SneakyThrows
    public AllayNBTFilePlayerStorage(Path dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
        if (!Files.exists(dataFolderPath)) Files.createDirectory(dataFolderPath);
    }

    @Override
    public void readPlayerData(EntityPlayer player) {

    }

    @Override
    public void writePlayerData(EntityPlayer player) {

    }

    @Override
    public boolean hasPlayerData(UUID uuid) {
        return false;
    }
}
