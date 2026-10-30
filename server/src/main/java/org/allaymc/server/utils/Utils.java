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
 * {@code Utils} supplies server initialization helpers for reflective type setup, plugin
 * descriptor validation, byte concatenation, and YAML configuration binding. Validation
 * rejects missing descriptor fields, while configuration initialization creates defaults and
 * reloads the bound file with undeclared keys removed.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    /**
     * Invokes the supplied method with a {@code null} receiver and no arguments. Callers provide
     * a compatible static initializer; reflective access or target failures are wrapped in
     * {@code IllegalArgumentException} with the reflection exception retained as cause.
     */
    public static void callInitializer(Method method) {
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Checks required plugin descriptor fields, dependency names, and a coercible plugin version.
     * Missing fields or an uncoercible version fail with {@code NullPointerException}, while a
     * blank dependency name fails with {@code IllegalArgumentException}; ordinary descriptor
     * strings
     * are otherwise not required to be nonblank.
     */
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
     * Copies the first byte array and every following array into one new result in argument order.
     * All arrays must be non-{@code null}, and the method does not modify or retain the input array
     * storage after copying.
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
     * Returns an initializer that binds a YAML configuration to {@code path}. Invocation enables
     * orphan removal, saves defaults for a missing file, and reloads with saving enabled to update
     * declared fields and comments on disk.
     */
    public static OkaeriConfigInitializer createConfigInitializer(Path path) {
        return it -> {
            it.withConfigurer(new YamlSnakeYamlConfigurer());
            it.withBindFile(path);
            it.withRemoveOrphans(true);
            // Save defaults before loading so a missing bound file can be read. The following
            // save-enabled load also writes updated fields and removes undeclared configuration keys.
            it.saveDefaults();
            it.load(true);
        };
    }
}
