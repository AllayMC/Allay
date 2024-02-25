package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.server.command.selector.ParseUtils;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;

import java.util.function.Predicate;


public class RY extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        final var ry = Double.parseDouble(arguments[0]);
        if (!ParseUtils.checkBetween(-180d, 180d, ry))
            throw new SelectorSyntaxException("RX out of bound (-180 - 180): " + ry);
        //获取到的yaw范围是[0, 360]，而原版规定的范围是[-180, 180]。故减去一个180
        //并还要转换到原版的坐标系(+z为正南 etc...)
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
