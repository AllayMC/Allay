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
public class Name extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) {
        Set<String> include = new HashSet<>();
        Set<String> exclude = new HashSet<>();

        for (var rawName : arguments) {
            if (ParseUtils.checkReversed(rawName)) {
                exclude.add(rawName.substring(1));
            } else {
                include.add(rawName);
            }
        }

        return entity ->
                include.stream().allMatch(name -> entity.getCommandSenderName().equals(name)) &&
                exclude.stream().noneMatch(name -> entity.getCommandSenderName().equals(name));
    }

    @Override
    public String getKeyName() {
        return "name";
    }

    @Override
    public int getPriority() {
        return 4;
    }
}
