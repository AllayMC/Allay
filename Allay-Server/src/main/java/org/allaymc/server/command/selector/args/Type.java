package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Type extends CachedSimpleSelectorArgument {

    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) {
        var have = new ArrayList<String>();
        var dontHave = new ArrayList<String>();
        for (var type : arguments) {
            var reversed = ParseUtils.checkReversed(type);
            if (reversed) {
                // Remove '!'
                dontHave.add(type.substring(1));
            } else have.add(type);
        }
        return entity -> have.stream().allMatch(type -> checkType(entity, type)) &&
                         dontHave.stream().noneMatch(type -> checkType(entity, type));
    }

    @Override
    public String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        return selectorType == SelectorType.RANDOM_PLAYER ? "minecraft:player" : null;
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
