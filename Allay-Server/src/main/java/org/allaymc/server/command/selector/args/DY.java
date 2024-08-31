package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

public class DY extends ScopeArgument {
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3f basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var y = basePos.y();
        var dy = Double.parseDouble(arguments[0]);
        return entity -> ParseUtils.checkBetween(y, y + dy, entity.getLocation().y());
    }

    @Override
    public String getKeyName() {
        return "dy";
    }
}
