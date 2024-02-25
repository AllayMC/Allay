package org.allaymc.server.command.selector.args;

//import org.allaymc.api.command.CommandSender;
//import org.allaymc.server.command.selector.ParseUtils;
//import org.allaymc.api.command.selector.SelectorType;
//import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
//import org.allaymc.api.entity.Entity;
//import org.allaymc.api.math.location.Location3fc;
//
//import java.util.ArrayList;
//import java.util.function.Predicate;

// TODO
public class Tag /*extends CachedSimpleSelectorArgument*/ {
//    @Override
//    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) {
//        final var have = new ArrayList<String>();
//        final var dontHave = new ArrayList<String>();
//        for (var tag : arguments) {
//            boolean reversed = ParseUtils.checkReversed(tag);
//            if (reversed) {
//                tag = tag.substring(1);
//                dontHave.add(tag);
//            } else have.add(tag);
//        }
//        return entity -> have.stream().allMatch(entity::containTag) && dontHave.stream().noneMatch(entity::containTag);
//    }
//
//    @Override
//    public String getKeyName() {
//        return "tag";
//    }
//
//    @Override
//    public int getPriority() {
//        return 4;
//    }
}
