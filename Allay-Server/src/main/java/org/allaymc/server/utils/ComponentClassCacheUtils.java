package org.allaymc.server.utils;

import org.allaymc.server.Allay;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.file.PathUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@UtilityClass
@SuppressWarnings("unchecked")
public final class ComponentClassCacheUtils {
    public static final Path CACHE_ROOT_PATH = Path.of("caches");
    private static final Gson GSON = new GsonBuilder().create();
    private static Map<String, String> LAST_CACHE_MAP;
    private static Map<String, String> CACHE_MAP = new LinkedHashMap<>();

    static {
        File file1 = CACHE_ROOT_PATH.toFile();
        if (!file1.exists()) {
            file1.mkdir();
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

    @Nullable
    public static <T> Class<T> loadBlockType(Class<T> interfaceClassName) {
        return getCacheClass(interfaceClassName.getSimpleName());
    }

    public static <T> Class<T> loadBlockEntityType(Class<T> interfaceClassName) {
        return getCacheClass(interfaceClassName.getSimpleName());
    }

    @Nullable
    public static <T> Class<T> loadItemType(Class<T> interfaceClassName) {
        return getCacheClass(interfaceClassName.getSimpleName());
    }

    @Nullable
    public static <T> Class<T> loadEntityType(Class<T> interfaceClassName) {
        return getCacheClass(interfaceClassName.getSimpleName());
    }

    public static void addCacheMapping(String className, String bytebuddyClassName) {
        CACHE_MAP.put(className, bytebuddyClassName);
    }

    public static void saveCacheMapping() {
        if (LAST_CACHE_MAP != null) {
            LAST_CACHE_MAP = null;
        }
        if (!CACHE_MAP.isEmpty()) {
            try {
                Files.writeString(CACHE_ROOT_PATH.resolve("mapping.json"), GSON.toJson(CACHE_MAP), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        CACHE_MAP = null;
    }

    public static void readCacheMapping() {
        File file = CACHE_ROOT_PATH.resolve("mapping.json").toFile();
        if (file.exists()) {
            try {
                LAST_CACHE_MAP = GSON.fromJson(new FileReader(file), Map.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static @Nullable <T> Class<T> getCacheClass(String simpleName) {
        if (LAST_CACHE_MAP == null) return null;
        try {
            return (Class<T>) Allay.EXTRA_RESOURCE_CLASS_LOADER.loadClass(LAST_CACHE_MAP.get(simpleName));
        } catch (ClassNotFoundException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
