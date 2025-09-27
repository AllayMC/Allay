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
 * @author daoge_cmd
 */
public class RM implements SelectorArgument {
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var rm = Double.parseDouble(arguments[0]);
        return entity -> entity.getLocation().distanceSquared(basePos) > Math.pow(rm, 2);
    }

    @Override
    public String getKeyName() {
        return "rm";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
