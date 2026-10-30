package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorArgument;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * {@code RM} implements a strict inner radius bound around the adjusted
 * selection origin. Its predicate compares squared distance with the squared
 * supplied radius, excluding the boundary itself. Radius signs are not
 * validated, so negative and positive radii of equal magnitude produce the same
 * bound.
 *
 * @author daoge_cmd
 */
public class RM implements SelectorArgument {
    /**
     * Creates a predicate accepting squared distances greater than the squared
     * radius from one nonreversed value. The predicate retains {@code basePos},
     * and entities exactly on the radius boundary are rejected.
     */
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var rm = Double.parseDouble(arguments[0]);
        // Squared distance avoids a square root while keeping the strict radius
        // boundary; squaring also makes opposite-signed radii impose the same bound.
        return entity -> entity.getLocation().distanceSquared(basePos) > Math.pow(rm, 2);
    }

    /**
     * Returns {@code "rm"} as the registration key for this radius-bound
     * handler.
     */
    @Override
    public String getKeyName() {
        return "rm";
    }

    /**
     * Returns priority three so radius bounds follow origin and box-range
     * handlers.
     */
    @Override
    public int getPriority() {
        return 3;
    }
}
