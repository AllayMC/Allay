package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * {@code RY} implements an inclusive selector yaw bound in degrees. It accepts
 * a single nonreversed threshold between {@code -180} and {@code 180}, then
 * tests whether each entity's current angle is at most that value. The
 * predicate transforms yaw with {@code (yaw + 90) % 360 - 180} before
 * comparison, using Java remainder semantics.
 *
 * @author daoge_cmd
 */
public class RY extends CachedSimpleSelectorArgument {
    /**
     * Creates an inclusive yaw predicate for a single threshold within
     * {@code [-180, 180]} degrees. Out-of-range values and reversal markers are
     * rejected. The predicate transforms yaw with
     * {@code (yaw + 90) % 360 - 180} before comparison, using Java remainder
     * semantics. The threshold is captured once, while candidate angles are
     * read on each test.
     */
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var ry = Double.parseDouble(arguments[0]);
        // Selector yaw thresholds use the closed degree interval [-180, 180].
        if (!ParseUtils.checkBetween(-180d, 180d, ry))
            throw new SelectorSyntaxException("RX out of bound (-180 - 180): " + ry);
        // Apply the selector comparison's orientation shift before the bound test.
        // Java remainder is retained here, so this expression does not normalize
        // every possible negative stored yaw into the nominal selector interval.
        return entity -> ((entity.getLocation().yaw() + 90) % 360 - 180) <= ry;
    }

    /**
     * Returns {@code "ry"} as the registration key for this rotation-bound
     * handler.
     */
    @Override
    public String getKeyName() {
        return "ry";
    }

    /**
     * Returns priority three so rotation bounds follow origin and box-range
     * handlers.
     */
    @Override
    public int getPriority() {
        return 3;
    }
}
