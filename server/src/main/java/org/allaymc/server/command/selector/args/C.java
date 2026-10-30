package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * {@code C} implements the selector's signed count argument by ordering
 * surviving entities by distance and returning a prefix view. Positive counts
 * prefer nearer entities, while negative counts reverse the order to prefer
 * farther entities. The filter rejects zero during parsing and does not clamp
 * the requested count to the candidate-list size.
 *
 * <p> The returned function captures {@code basePos}, but the inherited cache
 * key includes only argument values. Reusing a cached function therefore
 * retains the origin from its creating invocation, even when a later selection
 * supplies a different location. Applying the function sorts the supplied list
 * in place and returns a backed {@code subList}; an excessive requested count
 * fails during application rather than returning all available entities.
 *
 * @author daoge_cmd
 */
public class C extends CachedFilterSelectorArgument {
    /**
     * Creates a count filter from one nonreversed, nonzero integer value. The
     * function sorts its input list in place by distance to the captured
     * {@code basePos}; negative values reverse that ordering. Its result is a
     * view backed by the sorted list.
     *
     * <p> The requested prefix length is {@code Math.abs(c)} without a size
     * clamp, so an excessive count or the minimum integer value can fail when
     * the function is applied. The inherited argument-only cache can reuse this
     * function with its original captured location for later selections.
     */
    @Override
    public Function<List<Entity>, List<Entity>> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);

        var c = Integer.parseInt(arguments[0]);
        if (c == 0) {
            throw new SelectorSyntaxException("'c' argument cannot be zero");
        }

        return entities -> {
            // The prefix is selected after sorting current survivors. The captured
            // origin remains the cache-creating invocation's location because the
            // inherited key does not distinguish later selection origins.
            entities.sort(Comparator.comparingDouble(e -> e.getLocation().distanceSquared(basePos)));
            if (c < 0) Collections.reverse(entities);
            return entities.subList(0, Math.abs(c));
        };
    }

    /**
     * Returns {@code "c"} as the registration key for signed count filtering.
     */
    @Override
    public String getKeyName() {
        return "c";
    }

    /**
     * Returns priority three so count filtering follows coordinate and
     * box-range setup.
     */
    @Override
    public int getPriority() {
        return 3;
    }
}
