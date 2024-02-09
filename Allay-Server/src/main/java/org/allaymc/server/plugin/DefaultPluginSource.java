package org.allaymc.server.plugin;

import lombok.SneakyThrows;
import org.allaymc.api.plugin.PluginSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public class DefaultPluginSource implements PluginSource {

    public static final Path DEFAULT_PLUGIN_FOLDER = Path.of("plugins");

    @SneakyThrows
    public static Path getOrCreateDataFolder(String pluginName) {
        var dataFolder = DEFAULT_PLUGIN_FOLDER.resolve(pluginName);
        if (!Files.exists(dataFolder)) {
            Files.createDirectory(dataFolder);
        }
        return dataFolder;
    }

    @SneakyThrows
    public DefaultPluginSource() {
        if (!Files.exists(DEFAULT_PLUGIN_FOLDER))
            Files.createDirectory(DEFAULT_PLUGIN_FOLDER);
    }

    @SneakyThrows
    @Override
    public Set<Path> find() {
        try(var stream = Files.list(DEFAULT_PLUGIN_FOLDER)) {
            return stream.collect(Collectors.toSet());
        }
    }
}
