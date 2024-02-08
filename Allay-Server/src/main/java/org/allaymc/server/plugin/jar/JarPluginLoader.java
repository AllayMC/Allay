package org.allaymc.server.plugin.jar;

import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginLoader;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public class JarPluginLoader implements PluginLoader {

    protected Path pluginPath;

    public JarPluginLoader(Path pluginPath) {
        this.pluginPath = pluginPath;
    }

    @Override
    public PluginDescriptor loadDescriptor() {
        // TODO
        return null;
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
