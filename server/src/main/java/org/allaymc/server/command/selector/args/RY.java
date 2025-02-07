package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class RY extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var ry = Double.parseDouble(arguments[0]);
        if (!ParseUtils.checkBetween(-180d, 180d, ry))
            throw new SelectorSyntaxException("RX out of bound (-180 - 180): " + ry);
        // Get the yaw range 0, 360, while the range defined in the original version is -180, 180. Therefore, subtract 180
        // And also need to convert to the original version's coordinate system (+z is positive south, etc.)
        return entity -> ((entity.getLocation().yaw() + 90) % 360 - 180) <= ry;
    }

    @Override
    public String getKeyName() {
        return "ry";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
