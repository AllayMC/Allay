package org.allaymc.server.plugin.jar;

import lombok.SneakyThrows;
import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginLoader;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.plugin.SimplePluginDescriptor;

import java.nio.file.*;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
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
        return descriptor;
    }

    @Override
    public PluginContainer loadPlugin() {
        // TODO
        return null;
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
