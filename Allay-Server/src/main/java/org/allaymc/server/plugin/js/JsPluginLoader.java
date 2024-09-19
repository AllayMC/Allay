package org.allaymc.server.plugin.js;

import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18nLoader;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.api.plugin.PluginException;
import org.allaymc.api.plugin.PluginLoader;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.i18n.AllayI18n;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.allaymc.api.plugin.PluginContainer.createPluginContainer;
import static org.allaymc.server.plugin.DefaultPluginSource.getOrCreateDataFolder;

/**
 * @author daoge_cmd
 */
@Slf4j
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
        descriptor = JSONUtils.from(Files.newBufferedReader(pluginPath.resolve("plugin.json")), JsPluginDescriptor.class);
        PluginDescriptor.checkDescriptorValid(descriptor);
        return descriptor;
    }

    @SneakyThrows
    @Override
    public PluginContainer loadPlugin() {
        // Read entrance js file
        var entrancePath = pluginPath.resolve(descriptor.getEntrance());
        if (!Files.exists(entrancePath)) throw new PluginException("Entrance js file not found: " + entrancePath);
        return createPluginContainer(
                new JsPlugin(),
                descriptor, this,
                getOrCreateDataFolder(descriptor.getName()),
                new AllayI18n(new JsPluginI18nLoader(), Server.SETTINGS.genericSettings().language())
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

    public class JsPluginI18nLoader implements I18nLoader {
        @Override
        public Map<String, String> getLangMap(LangCode langCode) {
            try {
                var str = Files.readString(pluginPath.resolve("lang").resolve(langCode.name() + ".json"));
                TypeToken<HashMap<String, String>> typeToken = new TypeToken<>() {};
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
