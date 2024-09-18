package org.allaymc.api.plugin;

import java.nio.file.Path;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface PluginSource {
    Set<Path> find();
}
