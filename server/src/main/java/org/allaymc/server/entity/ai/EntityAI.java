package org.allaymc.server.entity.ai;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

/**
 * {@code EntityAI} stores process-wide switches for AI diagnostics. Callers
 * enable or disable individual categories through the helper methods or the
 * generated set getter; the mutable enum set has no synchronization, so
 * concurrent access requires external coordination.
 *
 * @author daoge_cmd
 */
public final class EntityAI {

    /**
     * {@code DEBUG_OPTIONS} holds enabled diagnostic categories. The generated
     * getter exposes this shared mutable set, and callers must coordinate
     * concurrent reads and modifications.
     */
    @Getter
    private static final Set<DebugOption> DEBUG_OPTIONS = EnumSet.noneOf(DebugOption.class);

    /**
     * Prevents instantiation of the process-wide AI diagnostic option holder.
     */
    private EntityAI() {
    }

    /**
     * Returns whether the supplied AI diagnostic category is currently enabled.
     */
    public static boolean isDebugOptionEnabled(DebugOption option) {
        return DEBUG_OPTIONS.contains(option);
    }

    /**
     * Enables the supplied category in the shared AI diagnostic set.
     */
    public static void enableDebugOption(DebugOption option) {
        DEBUG_OPTIONS.add(option);
    }

    /**
     * Disables the supplied category in the shared AI diagnostic set.
     */
    public static void disableDebugOption(DebugOption option) {
        DEBUG_OPTIONS.remove(option);
    }

    /**
     * {@code DebugOption} identifies the AI subsystem covered by a diagnostic
     * switch.
     */
    public enum DebugOption {
        /**
         * Behavior evaluation diagnostic category.
         */
        BEHAVIOR,
        /**
         * Sensor observation diagnostic category.
         */
        SENSOR,
        /**
         * Entity control diagnostic category.
         */
        CONTROLLER,
        /**
         * Route search diagnostic category.
         */
        ROUTE
    }
}
