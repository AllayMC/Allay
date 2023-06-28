package cn.allay.api.world.storage;

import java.nio.file.Path;

/**
 * @author daoge_cmd <br>
 * @date 2023/6/10 <br>
 * Allay Project <br>
 */
public interface NativeFileWorldStorage extends WorldStorage {
    Path getWorldFolderPath();
}
