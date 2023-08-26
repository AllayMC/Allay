package cn.allay.api.world.storage;

import java.nio.file.Path;

/**
 * Allay Project 2023/6/10
 *
 * @author daoge_cmd
 */
public interface NativeFileWorldStorage extends WorldStorage {

    Path getWorldFolderPath();
}
