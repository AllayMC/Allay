package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedFilterSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @author daoge_cmd
 */
public class C extends CachedFilterSelectorArgument {
    @Override
    public Function<List<Entity>, List<Entity>> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var c = Integer.parseInt(arguments[0]);
        if (c == 0) {
            throw new SelectorSyntaxException("'c' argument cannot be zero");
        }

        return entities -> {
            entities.sort(Comparator.comparingDouble(e -> e.getLocation().distanceSquared(basePos)));
            if (c < 0) Collections.reverse(entities);
            return entities.subList(0, Math.abs(c));
        };
    }

    @Override
    public String getKeyName() {
        return "c";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
