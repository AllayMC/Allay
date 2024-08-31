package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

public class DZ extends ScopeArgument {
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3f basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var z = basePos.z();
        var dz = Double.parseDouble(arguments[0]);
        return entity -> ParseUtils.checkBetween(z, z + dz, entity.getLocation().z());
    }

    @Override
    public String getKeyName() {
        return "dz";
    }
}
