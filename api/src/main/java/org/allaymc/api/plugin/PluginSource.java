package org.allaymc.api.plugin;

import java.nio.file.Path;
import java.util.Set;

/**
 * {@code PluginSource} discovers paths that plugin loader factories may inspect
 * for plugins.
 * A source can represent a directory or another provider of local paths;
 * returning a path does not establish that it is loadable, because factory
 * matching and descriptor validation happen later.
 *
 * @author daoge_cmd
 */
public interface PluginSource {
    /**
     * Returns candidate paths for the manager's next plugin discovery pass.
     * Sources include every path they want factories to inspect; the result
     * must be non-null and need not contain only valid plugins.
     */
    Set<Path> find();
}
