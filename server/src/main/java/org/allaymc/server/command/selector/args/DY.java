package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * {@code DY} implements the selector's {@code dy} axis-range argument. Its
 * predicate includes entities between the adjusted origin and that origin plus
 * the supplied extent, accepting either extent direction and both endpoints.
 * The shared scope default supplies zero when another axis enables box
 * selection.
 *
 * @author daoge_cmd
 */
public class DY extends ScopeArgument {
    /**
     * Creates an inclusive {@code y}-coordinate predicate from one nonreversed
     * numeric extent. The origin coordinate is captured when this predicate is
     * created; a negative extent reverses endpoint order without changing the
     * inclusive comparison.
     */
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var y = basePos.y();
        var dy = Double.parseDouble(arguments[0]);
        return entity -> ParseUtils.checkBetween(y, y + dy, entity.getLocation().y());
    }

    /**
     * Returns {@code "dy"} as the registration key for this axis-range handler.
     */
    @Override
    public String getKeyName() {
        return "dy";
    }
}
