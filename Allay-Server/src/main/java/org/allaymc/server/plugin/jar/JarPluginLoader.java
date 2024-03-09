package org.allaymc.server.plugin.jar;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginException;
import org.allaymc.api.plugin.PluginLoader;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.plugin.SimplePluginDescriptor;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

import static org.allaymc.api.plugin.PluginContainer.createPluginContainer;
import static org.allaymc.server.plugin.DefaultPluginSource.getOrCreateDataFolder;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Slf4j
public class JarPluginLoader implements PluginLoader {

    @Getter
    protected Path pluginPath;
    protected FileSystem jarFileSystem;
    protected PluginDescriptor descriptor;

    @SneakyThrows
    public JarPluginLoader(Path pluginPath) {
        this.pluginPath = pluginPath;
        this.jarFileSystem = FileSystems.newFileSystem(pluginPath);
    }

    @SneakyThrows
    @Override
    public PluginDescriptor loadDescriptor() {
        descriptor = JSONUtils.from(Files.newBufferedReader(jarFileSystem.getPath("plugin.json")), SimplePluginDescriptor.class);
        PluginDescriptor.checkDescriptorValid(descriptor);
        return descriptor;
    }

    @SneakyThrows
    @Override
    public PluginContainer loadPlugin() {
        // Load the main class
        Class<?> mainClass = findMainClass();
        if (!Plugin.class.isAssignableFrom(mainClass)) {
            throw new PluginException(I18n.get().tr(TrKeys.A_PLUGIN_JAR_ENTRANCE_TYPEINVALID, descriptor.getName()));
        }
        // Try to construct plugin instance
        Plugin pluginInstance;
        try {
            pluginInstance = (Plugin) mainClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new PluginException(I18n.get().tr(TrKeys.A_PLUGIN_CONSTRUCT_INSTANCE_ERROR, descriptor.getName()));
        }
        return createPluginContainer(
                pluginInstance,
                descriptor, this,
                getOrCreateDataFolder(descriptor.getName())
        );
    }

    protected Class<?> findMainClass() {
        try {
            // noinspection resource: No need to try-with-resources, as we want to keep the class loader alive until server shutdown
            var classLoader = new JarPluginClassLoader(new URL[]{pluginPath.toUri().toURL()});
            return classLoader.loadClass(descriptor.getEntrance());
        } catch (ClassNotFoundException e1) {
            throw new PluginException(I18n.get().tr(TrKeys.A_PLUGIN_ENTRANCE_MISSING, descriptor.getName()));
        } catch (MalformedURLException e2) {
            throw new PluginException("Invalid URL: " + pluginPath.toUri());
        }
    }

    public static class JarPluginLoaderFactory implements PluginLoaderFactory {

        protected static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

        @Override
        public boolean canLoad(Path pluginPath) {
            return PATH_MATCHER.matches(pluginPath) && Files.isRegularFile(pluginPath);
        }

        @Override
        public PluginLoader create(Path pluginPath) {
            return new JarPluginLoader(pluginPath);
        }
    }
}
