package org.allaymc.server.utils;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.Allay;
import org.apache.commons.io.file.PathUtils;

import java.io.File;
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
        File cacheRootDir = CACHE_ROOT_PATH.toFile();
        if (!cacheRootDir.exists()) {
            if (!cacheRootDir.mkdir()) {
                throw new RuntimeException("Can't create cache directory!");
            }
        }
        try {
            Allay.EXTRA_RESOURCE_CLASS_LOADER.addURL(CACHE_ROOT_PATH.toUri().toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check cache valid.
     */
    public static void checkCacheValid() {
        Path cacheValid = CACHE_ROOT_PATH.resolve("cache.valid");
        Properties properties = new Properties();
        try (var input = new InputStreamReader(Objects.requireNonNull(Allay.EXTRA_RESOURCE_CLASS_LOADER.getResourceAsStream("git.properties")))) {
            properties.load(input);
            if (Files.exists(cacheValid) &&
                    Files.readString(cacheValid).equals(properties.getProperty("git.commit.id.abbrev")) &&
                    CACHE_ROOT_PATH.resolve("mapping.json").toFile().exists()
            ) {
                return;
            }
            var cn = CACHE_ROOT_PATH.resolve("cn");
            if (Files.exists(cn)) {
                PathUtils.deleteDirectory(cn);
            }
            Files.deleteIfExists(CACHE_ROOT_PATH.resolve("mapping.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(CACHE_ROOT_PATH.resolve("cache.valid"));
                Files.writeString(CACHE_ROOT_PATH.resolve("cache.valid"), properties.getProperty("git.commit.id.abbrev"), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            } catch (IOException ignore) {
            }
        }
    }

    public static void addCacheMapping(String className, String bytebuddyClassName) {
        CACHE_MAP.put(className, bytebuddyClassName);
    }

    public static void saveCacheMapping() {
        try {
            Files.writeString(CACHE_ROOT_PATH.resolve("mapping.json"), JSONUtils.to(CACHE_MAP), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readCacheMapping() {
        File file = CACHE_ROOT_PATH.resolve("mapping.json").toFile();
        if (file.exists()) {
            try {
                CACHE_MAP.putAll(JSONUtils.from(new FileReader(file), Map.class));
                CACHE_MAP.trim();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> Class<T> getCacheClass(Class<T> interfaceClass) {
        try {
            String s = CACHE_MAP.get(interfaceClass.getSimpleName());
            if (s == null) return null;
            return (Class<T>) Allay.EXTRA_RESOURCE_CLASS_LOADER.loadClass(s);
        } catch (ClassNotFoundException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
