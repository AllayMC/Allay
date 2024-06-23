package org.allaymc.server.utils;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.Allay;
import org.apache.commons.io.file.PathUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@UtilityClass
@SuppressWarnings("unchecked")
public final class ComponentClassCacheUtils {
    public static final Path CACHE_ROOT_PATH = Path.of("caches");

    private static final Object2ObjectOpenHashMap<String, String> CACHE_MAP = new Object2ObjectOpenHashMap<>();

    static {
        var cacheRootDir = CACHE_ROOT_PATH.toFile();
        if (!cacheRootDir.exists() && !cacheRootDir.mkdir()) {
            throw new RuntimeException("Can't create cache directory!");
        }

        try {
            Allay.EXTRA_RESOURCE_CLASS_LOADER.addURL(CACHE_ROOT_PATH.toUri().toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check cache valid,Every time the git commit number changes, the cache will become invalid.
     */
    public static void checkCacheValid() {
        var cacheValid = CACHE_ROOT_PATH.resolve("cache.valid");
        var properties = new Properties();
        try (var input = new InputStreamReader(Objects.requireNonNull(Allay.EXTRA_RESOURCE_CLASS_LOADER.getResourceAsStream("git.properties")))) {
            properties.load(input);
            if (Files.exists(cacheValid) &&
                Files.readString(cacheValid).equals(properties.getProperty("git.commit.id.abbrev")) &&
                CACHE_ROOT_PATH.resolve("mapping.json").toFile().exists()
            ) {
                log.info(I18n.get().tr(TrKeys.A_CACHE_LOAD));
                return;
            }

            var cn = CACHE_ROOT_PATH.resolve("cn");
            if (Files.exists(cn)) PathUtils.deleteDirectory(cn);

            Files.deleteIfExists(CACHE_ROOT_PATH.resolve("mapping.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(CACHE_ROOT_PATH.resolve("cache.valid"));
                Files.writeString(CACHE_ROOT_PATH.resolve("cache.valid"), properties.getProperty("git.commit.id.abbrev"), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            } catch (IOException ignore) {}
        }
    }

    public static void addCacheMapping(String className, String bytebuddyClassName) {
        CACHE_MAP.put(className, bytebuddyClassName);
    }

    public static void saveCacheMapping() {
        try {
            var resolve = CACHE_ROOT_PATH.resolve("mapping.json");
            Files.deleteIfExists(resolve);
            Files.writeString(resolve, JSONUtils.to(CACHE_MAP), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readCacheMapping() {
        var file = CACHE_ROOT_PATH.resolve("mapping.json").toFile();
        if (!file.exists()) return;

        try (var reader = new FileReader(file)) {
            CACHE_MAP.putAll(JSONUtils.from(reader, Map.class));
            CACHE_MAP.trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Class<T> getCacheClass(Class<T> interfaceClass) {
        var s = CACHE_MAP.get(interfaceClass.getSimpleName());
        try {
            if (s == null) return null;
            return (Class<T>) Allay.EXTRA_RESOURCE_CLASS_LOADER.loadClass(s);
        } catch (ClassNotFoundException ignore) {
            CACHE_MAP.remove(s); // remove old cache entry
            return null;
        }
    }
}
