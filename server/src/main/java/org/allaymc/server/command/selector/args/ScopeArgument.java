package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorArgument;
import org.allaymc.api.command.selector.SelectorType;

import java.util.List;
import java.util.Map;

/**
 * {@code ScopeArgument} supplies shared ordering and missing-axis defaults for
 * the selector's three axis-range handlers. Coordinate handlers first adjust
 * the selection origin at priority one; scope predicates run at priority two
 * and compare entity coordinates with that origin and their axis extents. Box
 * selection therefore depends on whether any extent key is configured, rather
 * than treating every absent axis identically.
 *
 * <p>
 * With no extent keys, absent axes impose no range predicate. Once any of
 * {@code dx}, {@code dy}, or {@code dz} is present, the other axes receive zero
 * extents. For example, {@code dx=5} also constrains the other coordinates to
 * their adjusted origin values, so a partially specified box does not leave
 * those dimensions unrestricted.
 */
public abstract class ScopeArgument implements SelectorArgument {
    /**
     * Returns priority two so box ranges follow coordinate-origin adjustments.
     */
    @Override
    public int getPriority() {
        return 2;
    }

    /**
     * Returns {@code "0"} for an absent extent when any axis-range key is
     * configured. With no {@code dx}, {@code dy}, or {@code dz} key, the method
     * returns {@code null}, and the selector skips the absent axis predicate.
     *
     * <p>
     * The decision checks key presence rather than parsing supplied extents. A
     * partial configuration consequently supplies zero-width constraints on its
     * other axes; malformed explicit values can still fail in their own
     * handlers. When a handler is considered, an explicit key bypasses this
     * absent-key default. Earlier filters can empty the candidate list and stop
     * processing before that handler runs.
     */
    @Override
    public String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        if (values.containsKey("dx") || values.containsKey("dy") || values.containsKey("dz")) {
            return "0";
        }

        return null;
    }
}
