package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class Type extends CachedSimpleSelectorArgument {

    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) {
        Set<String> include = new HashSet<>();
        Set<String> exclude = new HashSet<>();

        for (var rawType : arguments) {
            if (ParseUtils.checkReversed(rawType)) {
                exclude.add(rawType.substring(1));
            } else {
                include.add(rawType);
            }
        }

        return entity ->
                include.stream().allMatch(type -> checkType(entity, type)) &&
                exclude.stream().noneMatch(type -> checkType(entity, type));
    }

    @Override
    public String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        return selectorType == SelectorType.RANDOM_PLAYER ? EntityId.PLAYER.getIdentifier().toString() : null;
    }

    @Override
    public String getKeyName() {
        return "type";
    }

    @Override
    public int getPriority() {
        return 4;
    }

    protected boolean checkType(Entity entity, String type) {
        var identifier = entity.getEntityType().getIdentifier();
        return identifier.toString().equals(type) || identifier.path().equals(type);
    }
}
