package org.allaymc.server.plugin;

import lombok.SneakyThrows;
import org.allaymc.api.plugin.PluginSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@code DefaultPluginSource} discovers immediate children of the working
 * directory's {@code plugins} folder.
 * Discovery includes directories and unrelated files, leaving format selection
 * to loader factories. The same folder also holds per-plugin data directories,
 * which are created on demand independently of discovery.
 *
 * @author daoge_cmd
 */
public class DefaultPluginSource implements PluginSource {

    /**
     * {@code DEFAULT_PLUGIN_FOLDER} defines the relative directory used for
     * default plugin discovery and
     * per-plugin data folders beneath the server's working directory.
     */
    public static final Path DEFAULT_PLUGIN_FOLDER = Path.of("plugins");

    /**
     * Creates a default source and creates its root directory if absent.
     * Existing paths are retained without checking their type; filesystem
     * failures propagate rather than producing an empty source.
     */
    @SneakyThrows
    public DefaultPluginSource() {
        if (!Files.exists(DEFAULT_PLUGIN_FOLDER)) {
            Files.createDirectory(DEFAULT_PLUGIN_FOLDER);
        }
    }

    /**
     * Resolves a plugin name beneath the default root and creates a missing
     * directory. The root must already exist; names are used as path input
     * without sanitization or confinement checks, and existing paths are returned.
     */
    @SneakyThrows
    public static Path getOrCreateDataFolder(String pluginName) {
        var dataFolder = DEFAULT_PLUGIN_FOLDER.resolve(pluginName);
        if (!Files.exists(dataFolder)) {
            Files.createDirectory(dataFolder);
        }
        return dataFolder;
    }

    /**
     * Returns a new set of the root directory's immediate children.
     * This operation does not recurse or filter entries by plugin format;
     * listing failures propagate to the manager's discovery caller.
     */
    @SneakyThrows
    @Override
    public Set<Path> find() {
        try (var stream = Files.list(DEFAULT_PLUGIN_FOLDER)) {
            return stream.collect(Collectors.toSet());
        }
    }
}
