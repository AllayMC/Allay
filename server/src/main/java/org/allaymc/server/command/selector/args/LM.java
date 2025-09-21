package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class LM extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var lm = Integer.parseInt(arguments[0]);
        return entity -> entity instanceof EntityPlayer player && player.getExperienceLevel() >= lm;
    }

    @Override
    public String getKeyName() {
        return "lm";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
