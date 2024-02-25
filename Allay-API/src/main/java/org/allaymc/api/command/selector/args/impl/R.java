package org.allaymc.api.command.selector.args.impl;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.ParseUtils;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.ISelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3f;

import java.util.function.Predicate;


public class R implements ISelectorArgument {
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3f basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        final var r = Double.parseDouble(arguments[0]);
        return entity -> entity.getLocation().distanceSquared(basePos) < Math.pow(r, 2);
    }

    @Override
    public String getKeyName() {
        return "r";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
