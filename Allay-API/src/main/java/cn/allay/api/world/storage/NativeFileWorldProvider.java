package cn.allay.api.world.storage;

import java.nio.file.Path;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/6/10 <br>
 * Allay Project <br>
 */
public interface NativeFileWorldProvider extends WorldProvider {
    Path getWorldFolderPath();
}
