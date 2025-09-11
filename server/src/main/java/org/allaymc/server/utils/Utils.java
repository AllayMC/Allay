package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.plugin.PluginDescriptor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.semver4j.Semver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public static GameType toGameType(GameMode gameMode) {
        return switch (gameMode) {
            case SURVIVAL -> GameType.SURVIVAL;
            case CREATIVE -> GameType.CREATIVE;
            case ADVENTURE -> GameType.ADVENTURE;
            case SPECTATOR -> GameType.SPECTATOR;
        };
    }

    public static GameMode toGameMode(GameType gameType) {
        return switch (gameType) {
            case SURVIVAL -> GameMode.SURVIVAL;
            case CREATIVE -> GameMode.CREATIVE;
            case ADVENTURE -> GameMode.ADVENTURE;
            case SPECTATOR -> GameMode.SPECTATOR;
            default -> null;
        };
    }

    @SuppressWarnings("DataFlowIssue")
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
        Preconditions.checkNotNull(Semver.coerce(descriptor.getVersion()), "Plugin version cannot be coerced (Use https://semver.org/)");
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
}
