package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class Tag extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) {
        var have = new ArrayList<String>();
        var dontHave = new ArrayList<String>();
        for (var tag : arguments) {
            var reversed = ParseUtils.checkReversed(tag);
            if (reversed) {
                tag = tag.substring(1);
                dontHave.add(tag);
            } else have.add(tag);
        }
        return entity -> have.stream().allMatch(entity::hasTag) && dontHave.stream().noneMatch(entity::hasTag);
    }

    @Override
    public String getKeyName() {
        return "tag";
    }

    @Override
    public int getPriority() {
        return 4;
    }
}
