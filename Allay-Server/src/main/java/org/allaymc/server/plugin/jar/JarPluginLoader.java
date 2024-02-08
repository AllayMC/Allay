package org.allaymc.server.plugin.jar;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.*;
import org.allaymc.api.utils.JSONUtils;

import java.net.URL;
import java.nio.file.*;

import static org.allaymc.server.plugin.DefaultPluginSource.DEFAULT_PLUGIN_FOLDER;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Slf4j
public class JarPluginLoader implements PluginLoader {

    protected Path pluginPath;
    protected FileSystem jarFileSystem;
    protected JarPluginDescriptor descriptor;

    @SneakyThrows
    public JarPluginLoader(Path pluginPath) {
        this.pluginPath = pluginPath;
        this.jarFileSystem = FileSystems.newFileSystem(pluginPath);
    }

    @SneakyThrows
    @Override
    public PluginDescriptor loadDescriptor() {
        descriptor = JSONUtils.from(Files.newBufferedReader(jarFileSystem.getPath("plugin.json")), JarPluginDescriptor.class);
        JarPluginDescriptor.checkDescriptorValid(descriptor);
        return descriptor;
    }

    @SneakyThrows
    @Override
    public PluginContainer loadPlugin() {
        // Load main class
        JarPluginClassLoader classLoader = new JarPluginClassLoader(new URL[]{pluginPath.toUri().toURL()});
        var mainClass = classLoader.loadClass(descriptor.getMain());
        if (!Plugin.class.isAssignableFrom(mainClass)) {
            throw new PluginException("Main class must implement interface Plugin: " + descriptor.getMain());
        }
        // Try to construct plugin instance
        Plugin pluginInstance = null;
        try {
            pluginInstance = (Plugin) mainClass.getConstructor().newInstance();
        } catch (Exception e) {
            log.error("Error while constructing plugin instance! Plugin: " + descriptor.getName(), e);
        }
        // Create data folder for plugin
        var dataFolder = DEFAULT_PLUGIN_FOLDER.resolve(descriptor.getName());
        if (!Files.exists(dataFolder)) {
            Files.createDirectory(dataFolder);
        }
        return new PluginContainer(pluginInstance, descriptor, this, dataFolder);
    }

    public static class JarPluginLoaderFactory implements PluginLoaderFactory {

        protected static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

        @Override
        public boolean canLoad(Path pluginPath) {
            return PATH_MATCHER.matches(pluginPath);
        }

        @Override
        public PluginLoader create(Path pluginPath) {
            return new JarPluginLoader(pluginPath);
        }
    }
}
