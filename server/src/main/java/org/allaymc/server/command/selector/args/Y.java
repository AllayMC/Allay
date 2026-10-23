package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * {@code Y} implements the selector's {@code y} origin argument. It updates the
 * shared selection location with an absolute value or tilde offset before
 * spatial handlers run, and it returns no entity predicate because changing the
 * origin is its entire effect.
 *
 * @author daoge_cmd
 */
public class Y extends CoordinateArgument {
    /**
     * Updates {@code basePos.y} from the key's single nonreversed value and
     * returns {@code null}. Absolute input replaces the coordinate; tilde input
     * offsets its current value, which later range and distance handlers
     * observe.
     */
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        basePos.y = ParseUtils.parseOffsetDouble(arguments[0], basePos.y);
        return null;
    }

    /**
     * Returns {@code "y"} as the registration key for this origin-coordinate
     * handler.
     */
    @Override
    public String getKeyName() {
        return "y";
    }
}
