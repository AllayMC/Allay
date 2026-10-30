package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * {@code L} implements the selector's {@code l} experience-level bound. The
 * cached predicate accepts only player entities whose current experience level
 * is at most the supplied integer, including the boundary. The parsed bound is
 * reusable because it does not capture the sender or selection origin.
 *
 * @author daoge_cmd
 */
public class L extends CachedSimpleSelectorArgument {
    /**
     * Creates a player-only predicate whose current experience level is at most
     * one parsed integer. Multiple values and the reversal prefix are rejected;
     * numeric parsing failures propagate to selector-level error wrapping.
     */
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var l = Integer.parseInt(arguments[0]);
        return entity -> entity instanceof EntityPlayer player && player.getExperienceLevel() <= l;
    }

    /**
     * Returns {@code "l"} as the registration key for this experience-level
     * bound.
     */
    @Override
    public String getKeyName() {
        return "l";
    }

    /**
     * Returns priority three so experience filtering follows origin and
     * box-range handlers.
     */
    @Override
    public int getPriority() {
        return 3;
    }
}
