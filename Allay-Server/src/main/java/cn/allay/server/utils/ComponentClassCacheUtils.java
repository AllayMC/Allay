package cn.allay.server.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.file.PathUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@SuppressWarnings("unchecked")
public final class ComponentClassCacheUtils {
    public static final Path CACHE_ROOT = Path.of("cache");
    public static final String CACHE_PACKAGE_BLOCK = "cn/allay/api/block/behavior";
    public static final String CACHE_PACKAGE_ITEM = "cn/allay/api/item/impl";
    public static final String CACHE_PACKAGE_ENTITY = "cn/allay/api/entity/impl";
    private static final URLClassLoader LOADER;

    static {
        File file1 = CACHE_ROOT.toFile();
        if (!file1.exists()) {
            file1.mkdir();
        }
        try {
            LOADER = new URLClassLoader(new URL[]{CACHE_ROOT.toUri().toURL()});
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check cache valid.
     */
    public static void checkCacheValid() {
        Path cacheValid = CACHE_ROOT.resolve("cache.valid");
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LOADER.getResourceAsStream("git.properties"))));
            if (Files.exists(cacheValid) && Files.readString(cacheValid).equals(properties.getProperty("git.commit.id.abbrev"))) {
                return;
            }
            String arg = properties.get("git.commit.message.short").toString();
            if (arg.contains("+cb")) {
                PathUtils.deleteDirectory(CACHE_ROOT.resolve(CACHE_PACKAGE_BLOCK));
            } else if (arg.contains("+ci")) {
                PathUtils.deleteDirectory(CACHE_ROOT.resolve(CACHE_PACKAGE_ITEM));
            } else if (arg.contains("+ce")) {
                PathUtils.deleteDirectory(CACHE_ROOT.resolve(CACHE_PACKAGE_ENTITY));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.writeString(CACHE_ROOT.resolve("cache.valid"), properties.getProperty("git.commit.id.abbrev"), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Nullable
    public static <T> Class<T> loadBlockType(Class<T> interfaceClassName) {
        Path path = CACHE_ROOT.resolve(CACHE_PACKAGE_BLOCK);
        File file = path.toFile();
        if (file.exists()) {
            Optional<String> first = Arrays.stream(file.listFiles())
                    .filter(f -> f.getName().contains(interfaceClassName.getSimpleName()))
                    .map(f -> f.getName().replace(".class", ""))
                    .findFirst();
            if (first.isPresent()) {
                String cachePath = CACHE_PACKAGE_BLOCK.replace("/", ".") + "." + first.get();
                try {
                    return (Class<T>) LOADER.loadClass(cachePath);
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                    return null;
                }
            }
        }
        return null;
    }

    @Nullable
    public static <T> Class<T> loadItemType(Class<T> interfaceClassName) {
        Path path = CACHE_ROOT.resolve(CACHE_PACKAGE_ITEM);
        File file = path.toFile();
        if (file.exists()) {
            Optional<String> first = Arrays.stream(file.listFiles())
                    .filter(f -> f.getName().contains(interfaceClassName.getSimpleName()))
                    .map(f -> f.getName().replace(".class", ""))
                    .findFirst();
            if (first.isPresent()) {
                String cachePath = CACHE_PACKAGE_ITEM.replace("/", ".") + "." + first.get();
                try {
                    return (Class<T>) LOADER.loadClass(cachePath);
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                    return null;
                }
            }
        }
        return null;
    }

    @Nullable
    public static <T> Class<T> loadEntityType(Class<T> interfaceClassName) {
        Path path = CACHE_ROOT.resolve(CACHE_PACKAGE_ENTITY);
        File file = path.toFile();
        if (file.exists()) {
            Optional<String> first = Arrays.stream(file.listFiles())
                    .filter(f -> f.getName().contains(interfaceClassName.getSimpleName()))
                    .map(f -> f.getName().replace(".class", ""))
                    .findFirst();
            if (first.isPresent()) {
                String cachePath = CACHE_PACKAGE_ENTITY.replace("/", ".") + "." + first.get();
                try {
                    return (Class<T>) LOADER.loadClass(cachePath);
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                    return null;
                }
            }
        }
        return null;
    }
}
