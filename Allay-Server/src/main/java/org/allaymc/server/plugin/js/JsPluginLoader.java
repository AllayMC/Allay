package org.allaymc.server.plugin.js;

import lombok.Getter;
import lombok.SneakyThrows;
import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginException;
import org.allaymc.api.plugin.PluginLoader;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.plugin.SimplePluginDescriptor;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.allaymc.api.plugin.PluginContainer.createPluginContainer;
import static org.allaymc.server.plugin.DefaultPluginSource.getOrCreateDataFolder;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class JsPluginLoader implements PluginLoader {

    @Getter
    protected Path pluginPath;
    protected PluginDescriptor descriptor;

    @SneakyThrows
    public JsPluginLoader(Path pluginPath) {
        this.pluginPath = pluginPath;
    }

    @SneakyThrows
    @Override
    public PluginDescriptor loadDescriptor() {
        descriptor = JSONUtils.from(Files.newBufferedReader(pluginPath.resolve("plugin.json")), SimplePluginDescriptor.class);
        PluginDescriptor.checkDescriptorValid(descriptor);
        return descriptor;
    }

    @SneakyThrows
    @Override
    public PluginContainer loadPlugin() {
        // Read entrance js file
        var entrancePath = pluginPath.resolve(descriptor.getEntrance());
        if (!Files.exists(entrancePath)) {
            throw new PluginException("Entrance js file not found: " + entrancePath);
        }
        return createPluginContainer(
                new JsPlugin(entrancePath.toFile()),
                descriptor, this,
                getOrCreateDataFolder(descriptor.getName())
        );
    }

    public static class JsPluginLoaderFactory implements PluginLoaderFactory {

        @Override
        public boolean canLoad(Path pluginPath) {
            return pluginPath.getFileName().toString().startsWith("@") && Files.isDirectory(pluginPath);
        }

        @Override
        public PluginLoader create(Path pluginPath) {
            return new JsPluginLoader(pluginPath);
        }
    }
}
