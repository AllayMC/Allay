package org.allaymc.server.entity.ai;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

/**
 * Entity AI debug options.
 *
 * @author daoge_cmd
 */
public final class EntityAI {

    @Getter
    private static final Set<DebugOption> DEBUG_OPTIONS = EnumSet.noneOf(DebugOption.class);

    private EntityAI() {
    }

    public static boolean isDebugOptionEnabled(DebugOption option) {
        return DEBUG_OPTIONS.contains(option);
    }

    public static void enableDebugOption(DebugOption option) {
        DEBUG_OPTIONS.add(option);
    }

    public static void disableDebugOption(DebugOption option) {
        DEBUG_OPTIONS.remove(option);
    }

    public enum DebugOption {
        BEHAVIOR,
        SENSOR,
        CONTROLLER,
        ROUTE
    }
}
