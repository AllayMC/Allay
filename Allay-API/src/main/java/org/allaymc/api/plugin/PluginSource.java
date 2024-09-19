package org.allaymc.api.plugin;

import java.nio.file.Path;
import java.util.Set;

/**
 * PluginSource represents a source that may contain plugins (for example a directory).
 *
 * @author daoge_cmd
 */
public interface PluginSource {
    Set<Path> find();
}
