package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class Tag extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) {
        Set<String> include = new HashSet<>();
        Set<String> exclude = new HashSet<>();

        for (var rawTag : arguments) {
            if (ParseUtils.checkReversed(rawTag)) {
                exclude.add(rawTag.substring(1));
            } else {
                include.add(rawTag);
            }
        }

        return entity -> include.stream().allMatch(entity::hasTag) && exclude.stream().noneMatch(entity::hasTag);
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
