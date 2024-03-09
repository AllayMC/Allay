package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.server.command.selector.ParseUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Type extends CachedSimpleSelectorArgument {

    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) {
        final var have = new ArrayList<String>();
        final var dontHave = new ArrayList<String>();
        for (var type : arguments) {
            boolean reversed = ParseUtils.checkReversed(type);
            if (reversed) {
                dontHave.add(type);
            } else have.add(type);
        }
        return entity -> have.stream().allMatch(type -> checkType(entity, type)) && dontHave.stream().noneMatch(type -> checkType(entity, type));
    }

    @Override
    public @Nullable String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
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
        return entity.getEntityType().getIdentifier().toString().equals(type);
    }
}
