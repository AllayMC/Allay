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
 * {@code JarPluginLoader} reads a JAR plugin's metadata, constructs its entry
 * instance, and imports its translations. Descriptor loading precedes instance
 * loading; class lookup can
 * fall back to other plugin loaders. The JAR filesystem and class loaders are
 * retained without a closing lifecycle in this loader.
 *
 * @author daoge_cmd
 */
@Slf4j
public class JarPluginLoader implements PluginLoader {

    /** Source JAR path. */
    @Getter
    protected Path pluginPath;
    /**
     * {@code jarFileSystem} supplies the open archive for metadata and language
     * reads. This loader keeps it open and provides no disposal operation.
     */
    protected FileSystem jarFileSystem;
    /**
     * {@code descriptor} holds the most recently parsed metadata, assigned
     * before validation. Callers must complete {@code loadDescriptor} successfully
     * before attempting to construct the plugin.
     */
    protected PluginDescriptor descriptor;

    /**
     * Opens the supplied JAR path as a filesystem for subsequent loading.
     * Opening failures propagate, and a successful filesystem remains open
     * because this loader exposes no automatic or explicit disposal lifecycle.
     */
    @SneakyThrows
    public JarPluginLoader(Path pluginPath) {
        this.pluginPath = pluginPath;
        this.jarFileSystem = FileSystems.newFileSystem(pluginPath);
    }

    /**
     * Parses {@code plugin.json} and validates the resulting descriptor's required data.
     * The parsed value is stored before validation, so a failing call may leave
     * an invalid descriptor installed. The reader is not closed by this path.
     */
    @SneakyThrows
    @Override
    public PluginDescriptor loadDescriptor() {
        descriptor = JSONUtils.from(Files.newBufferedReader(jarFileSystem.getPath("plugin.json")), SimplePluginDescriptor.class);
        Utils.checkDescriptorValid(descriptor);
        return descriptor;
    }

    /**
     * Constructs the descriptor's plugin entry, imports translations, and
     * attaches a container with its data directory. A validated descriptor must
     * already be loaded and the entry must extend {@code Plugin} with a public
     * no-argument constructor. Later failures do not undo earlier class loading
     * or translation imports; lifecycle callbacks are left to the manager.
     */
    @SneakyThrows
    @Override
    public PluginContainer loadPlugin() {
        var mainClass = findMainClass();
        if (!Plugin.class.isAssignableFrom(mainClass)) {
            throw new PluginException(I18n.get().tr(TrKeys.ALLAY_PLUGIN_JAR_ENTRANCE_TYPEINVALID, descriptor.getName()));
        }

        Plugin pluginInstance;
        try {
            pluginInstance = (Plugin) mainClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new PluginException(I18n.get().tr(TrKeys.ALLAY_PLUGIN_CONSTRUCT_INSTANCE_ERROR, descriptor.getName(), e), e);
        }

        // Translations become globally available before container attachment;
        // a later data-directory or attachment failure does not remove those imported messages.
        ((AllayI18n) I18n.get()).applyI18nLoader(new JarPluginI18nLoader());

        return createPluginContainer(
                pluginInstance,
                descriptor, this,
                getOrCreateDataFolder(descriptor.getName())
        );
    }

    /**
     * Resolves the descriptor's entry through a newly registered plugin loader.
     * The class loader remains globally retained even if resolution fails;
     * missing classes and invalid JAR URLs become contextual {@code PluginException}
     * failures, while entry-type validation occurs afterward.
     */
    protected Class<?> findMainClass() {
        try {
            // The registry retains this loader so plugin classes can be resolved
            // later, including through peer fallback after this method has returned.
            // noinspection resource
            var classLoader = new JarPluginClassLoader(new URL[]{pluginPath.toUri().toURL()});
            return classLoader.loadClass(descriptor.getEntrance());
        } catch (ClassNotFoundException e1) {
            throw new PluginException(I18n.get().tr(TrKeys.ALLAY_PLUGIN_ENTRANCE_MISSING, descriptor.getName()));
        } catch (MalformedURLException e2) {
            throw new PluginException("Invalid URL: " + pluginPath.toUri());
        }
    }

    /**
     * {@code Factory} selects regular JAR files and opens loaders for their contents.
     */
    public static class Factory implements PluginLoader.Factory {

        /**
         * {@code PATH_MATCHER} restricts candidate matching to JAR suffixes
         * before a regular-file check. Descriptor presence is validated only
         * after a loader is created.
         */
        protected static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

        /**
         * Returns whether the candidate matches the JAR glob and is a regular file.
         */
        @Override
        public boolean canLoad(Path pluginPath) {
            return PATH_MATCHER.matches(pluginPath) && Files.isRegularFile(pluginPath);
        }

        /**
         * Opens a loader for the supplied candidate without repeating format
         * matching. Filesystem opening failures propagate to discovery, and the
         * created loader retains its archive filesystem for later reads.
         */
        @Override
        public PluginLoader create(Path pluginPath) {
            return new JarPluginLoader(pluginPath);
        }
    }

    /**
     * {@code JarPluginI18nLoader} reads optional language maps from the enclosing
     * plugin's open archive.
     */
    public class JarPluginI18nLoader implements I18nLoader {

        /**
         * Reads the requested language JSON beneath {@code assets/lang} in this JAR.
         * Missing files return an empty map silently; other IO failures are
         * logged and also return empty, while parsing failures propagate. Empty
         * input follows the JSON helper's {@code null} result.
         */
        @Override
        public Map<String, String> getLangMap(LangCode langCode) {
            try {
                var str = Files.readString(jarFileSystem.getPath("assets/lang/" + langCode.name() + ".json"));
                TypeToken<HashMap<String, String>> typeToken = new TypeToken<>() {
                };
                return JSONUtils.fromLenient(str, typeToken);
            } catch (NoSuchFileException e) {
                // Plugins may supply only selected languages; absence contributes
                // no translations while allowing other loaders to provide this language.
                return Collections.emptyMap();
            } catch (IOException e) {
                // An unreadable optional language file does not abort plugin
                // construction; log its failure and contribute no entries for that language.
                log.error("Error while loading plugin language file", e);
                return Collections.emptyMap();
            }
        }
    }
}
