package org.allaymc.server.utils;

import com.google.common.base.Preconditions;
import eu.okaeri.configs.OkaeriConfigInitializer;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import lombok.experimental.UtilityClass;
import org.allaymc.api.plugin.PluginDescriptor;
import org.semver4j.Semver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;

/**
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    public static void callInitializer(Method method) {
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void checkDescriptorValid(PluginDescriptor descriptor) {
        Preconditions.checkNotNull(descriptor.getName(), "Plugin name cannot be null");
        Preconditions.checkNotNull(descriptor.getEntrance(), "Plugin entrance cannot be null");
        Preconditions.checkNotNull(descriptor.getVersion(), "Plugin version cannot be null");
        Preconditions.checkNotNull(descriptor.getAuthors(), "Plugin authors cannot be null");
        Preconditions.checkNotNull(descriptor.getDescription(), "Plugin description cannot be null");
        Preconditions.checkNotNull(descriptor.getAPIVersion(), "Plugin api version cannot be null");
        Preconditions.checkNotNull(descriptor.getDependencies(), "Plugin dependencies cannot be null");
        for (var dependency : descriptor.getDependencies()) {
            Preconditions.checkNotNull(dependency.name(), "Dependency name cannot be null");
            Preconditions.checkArgument(!dependency.name().isBlank(), "Dependency name cannot be blank");
        }
        Preconditions.checkNotNull(descriptor.getWebsite(), "Plugin website cannot be null");
        Preconditions.checkNotNull(Semver.coerce(descriptor.getVersion()), "Plugin version is invalid (check https://semver.org/)");
    }

    /**
     * Merge multiple byte arrays into one byte array.
     *
     * @param bytes1 the first byte array
     * @param bytes2 the other byte arrays
     * @return the merged byte array
     */
    public byte[] appendBytes(byte[] bytes1, byte[]... bytes2) {
        int length = bytes1.length;
        for (byte[] bytes : bytes2) {
            length += bytes.length;
        }

        byte[] appendedBytes = new byte[length];
        System.arraycopy(bytes1, 0, appendedBytes, 0, bytes1.length);
        int index = bytes1.length;

        for (byte[] b : bytes2) {
            System.arraycopy(b, 0, appendedBytes, index, b.length);
            index += b.length;
        }
        return appendedBytes;
    }

    /**
     * Create a YAML config initializer.
     *
     * @param path the path of the config file
     * @return the YAML config initializer
     */
    public static OkaeriConfigInitializer createConfigInitializer(Path path) {
        return it -> {
            // Specify configurer implementation, optionally additional serdes packages
            it.withConfigurer(new YamlSnakeYamlConfigurer());
            // Specify Path, File or pathname
            it.withBindFile(path);
            // Automatic removal of undeclared keys
            it.withRemoveOrphans(true);
            // Save the file if it does not exist
            it.saveDefaults();
            // Load and save to update comments/new fields
            it.load(true);
        };
    }
}
