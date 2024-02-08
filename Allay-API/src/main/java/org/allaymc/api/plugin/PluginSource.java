package org.allaymc.api.plugin;

import java.nio.file.Path;
import java.util.Set;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface PluginSource {
    Set<Path> find();
}
