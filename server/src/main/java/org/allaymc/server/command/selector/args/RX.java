package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * {@code RX} implements an inclusive selector pitch bound in degrees. It
 * accepts a single nonreversed threshold between {@code -90} and {@code 90},
 * then tests whether each entity's current angle is at most that value. The
 * predicate compares the location's pitch directly.
 *
 * @author daoge_cmd
 */
public class RX extends CachedSimpleSelectorArgument {
    /**
     * Creates an inclusive pitch predicate for a single threshold within
     * {@code [-90, 90]} degrees. Out-of-range values and reversal markers are
     * rejected. The predicate compares the location's pitch directly. The
     * threshold is captured once, while candidate angles are read on each test.
     */
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var rx = Double.parseDouble(arguments[0]);
        // Selector pitch thresholds use the closed degree interval [-90, 90].
        if (!ParseUtils.checkBetween(-90d, 90d, rx))
            throw new SelectorSyntaxException("RX out of bound (-90 - 90): " + rx);
        return entity -> entity.getLocation().pitch() <= rx;
    }

    /**
     * Returns {@code "rx"} as the registration key for this rotation-bound
     * handler.
     */
    @Override
    public String getKeyName() {
        return "rx";
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
