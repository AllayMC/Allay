package org.allaymc.server.plugin.js;

import lombok.SneakyThrows;
import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginException;
import org.allaymc.api.plugin.PluginLoader;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.plugin.SimplePluginDescriptor;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

import static org.allaymc.server.plugin.DefaultPluginSource.DEFAULT_PLUGIN_FOLDER;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class JsPluginLoader implements PluginLoader {

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
        var source = Files.readString(entrancePath);
        // Create js plugin instance
        var jsPlugin = new JsPlugin(source);
        // Create data folder for plugin
        var dataFolder = DEFAULT_PLUGIN_FOLDER.resolve(descriptor.getName());
        if (!Files.exists(dataFolder)) {
            Files.createDirectory(dataFolder);
        }
        return new PluginContainer(jsPlugin, descriptor, this, dataFolder);
    }

    public static class JsPluginLoaderFactory implements PluginLoaderFactory {

        protected static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.js");

        @Override
        public boolean canLoad(Path pluginPath) {
            return PATH_MATCHER.matches(pluginPath) && Files.isDirectory(pluginPath);
        }

        @Override
        public PluginLoader create(Path pluginPath) {
            return new JsPluginLoader(pluginPath);
        }
    }
}
