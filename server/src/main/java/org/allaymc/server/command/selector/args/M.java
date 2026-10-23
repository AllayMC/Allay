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
 * {@code M} implements the selector's player game-mode argument with optional
 * reversal. The predicate compares each player's current game-mode ordinal with
 * the parsed number. Explicit spectator aliases parse as {@code 6}, but
 * {@code GameMode.SPECTATOR} has ordinal {@code 3}, so positive spectator
 * arguments reject every current player mode. Default-mode aliases resolve when
 * the cached predicate is created and can remain stale after settings change.
 *
 * @author daoge_cmd
 */
public class M extends CachedSimpleSelectorArgument {
    /**
     * Creates a player-only game-mode predicate from one value with an optional
     * leading {@code !}. The parser resolves aliases once and the predicate
     * compares that number with current mode ordinals. Because explicit
     * spectator aliases resolve to {@code 6} rather than ordinal {@code 3},
     * their positive form rejects all modes and their reversed form accepts all
     * player modes.
     */
    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());

        var rawGm = arguments[0];
        var reversed = ParseUtils.checkReversed(rawGm);
        if (reversed) {
            rawGm = rawGm.substring(1);
        }

        var gm = ParseUtils.parseGameMode(rawGm);
        return entity -> entity instanceof EntityPlayer player && (reversed != (player.getGameMode().ordinal() == gm));
    }

    /**
     * Returns {@code "m"} as the registration key for player game-mode
     * filtering.
     */
    @Override
    public String getKeyName() {
        return "m";
    }

    /**
     * Returns priority three so game-mode filtering follows origin and
     * box-range handlers.
     */
    @Override
    public int getPriority() {
        return 3;
    }
}
