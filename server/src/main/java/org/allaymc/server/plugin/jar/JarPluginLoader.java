package org.allaymc.server.plugin.jar;

import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.plugin.*;
import org.allaymc.server.message.AllayI18n;
import org.allaymc.server.message.I18nLoader;
import org.allaymc.server.plugin.SimplePluginDescriptor;
import org.allaymc.server.utils.JSONUtils;
import org.allaymc.server.utils.Utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.allaymc.api.plugin.PluginContainer.createPluginContainer;
import static org.allaymc.server.plugin.DefaultPluginSource.getOrCreateDataFolder;

/**
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
        Utils.checkDescriptorValid(descriptor);
        return descriptor;
    }

    @SneakyThrows
    @Override
    public PluginContainer loadPlugin() {
        // Load the main class
        var mainClass = findMainClass();
        if (!Plugin.class.isAssignableFrom(mainClass)) {
            throw new PluginException(I18n.get().tr(TrKeys.ALLAY_PLUGIN_JAR_ENTRANCE_TYPEINVALID, descriptor.getName()));
        }

        // Try to construct plugin instance
        Plugin pluginInstance;
        try {
            pluginInstance = (Plugin) mainClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new PluginException(I18n.get().tr(TrKeys.ALLAY_PLUGIN_CONSTRUCT_INSTANCE_ERROR, descriptor.getName(), e), e);
        }

        // Load plugin's lang files
        ((AllayI18n) I18n.get()).applyI18nLoader(new JarPluginI18nLoader());

        return createPluginContainer(
                pluginInstance,
                descriptor, this,
                getOrCreateDataFolder(descriptor.getName())
        );
    }

    protected Class<?> findMainClass() {
        try {
            // noinspection resource: no need to try-with-resources, as we want to keep the class loader alive until server shutdown
            var classLoader = new JarPluginClassLoader(new URL[]{pluginPath.toUri().toURL()});
            return classLoader.loadClass(descriptor.getEntrance());
        } catch (ClassNotFoundException e1) {
            throw new PluginException(I18n.get().tr(TrKeys.ALLAY_PLUGIN_ENTRANCE_MISSING, descriptor.getName()));
        } catch (MalformedURLException e2) {
            throw new PluginException("Invalid URL: " + pluginPath.toUri());
        }
    }

    public static class Factory implements PluginLoader.Factory {

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

    public class JarPluginI18nLoader implements I18nLoader {

        @Override
        public Map<String, String> getLangMap(LangCode langCode) {
            try {
                var str = Files.readString(jarFileSystem.getPath("assets/lang/" + langCode.name() + ".json"));
                TypeToken<HashMap<String, String>> typeToken = new TypeToken<>() {
                };
                return JSONUtils.fromLenient(str, typeToken);
            } catch (NoSuchFileException e) {
                return Collections.emptyMap();
            } catch (IOException e) {
                log.error("Error while loading plugin language file", e);
                return Collections.emptyMap();
            }
        }
    }
}
