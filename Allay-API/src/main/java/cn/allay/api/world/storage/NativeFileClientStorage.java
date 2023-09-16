package cn.allay.api.world.storage;

import java.nio.file.Path;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface NativeFileClientStorage extends ClientStorage{
    Path getClientDBPath();
}
