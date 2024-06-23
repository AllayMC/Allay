package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

public class RYM extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var rym = Double.parseDouble(arguments[0]);
        if (!ParseUtils.checkBetween(-180d, 180d, rym))
            throw new SelectorSyntaxException("RX out of bound (-180 - 180): " + rym);
        return entity -> ((entity.getLocation().yaw() + 90) % 360 - 180) >= rym;
    }

    @Override
    public String getKeyName() {
        return "rym";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
