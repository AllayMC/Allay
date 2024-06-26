package org.allaymc.api.client.storage;

import java.nio.file.Path;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface NativeFilePlayerStorage extends PlayerStorage {
    Path getDataFolderPath();
}
