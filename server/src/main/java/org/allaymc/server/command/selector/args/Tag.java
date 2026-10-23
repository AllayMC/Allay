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
 * {@code Tag} filters selector candidates using their current entity tags. A
 * compiled predicate retains string requirements rather than an entity or a
 * copy of its tag collection; each test calls {@code hasTag} on the candidate.
 * Changing an entity's tags can therefore change later results without
 * invalidating the cached predicate.
 *
 * <p>
 * The handler runs at priority four after spatial and count handlers. Tag
 * filtering cannot recover a candidate discarded by an earlier count limit,
 * even if that candidate would satisfy its tag requirements. The inherited
 * argument-set key removes ordering and repeated-value distinctions, matching
 * the compiled sets' semantics. Cache reuse needs no sender or base-position
 * value because neither is captured by this handler.
 *
 * @author daoge_cmd
 */
public class Tag extends CachedSimpleSelectorArgument {
    /**
     * Creates a predicate requiring all positive entity tag values and
     * rejecting every excluded match. A leading {@code !} removes that marker
     * and inserts the remaining text into the exclusion set; other values enter
     * the inclusion set.
     *
     * <p> Every positive tag must be present and every reversed tag must be
     * absent. A tag listed in both sets therefore rejects every entity, while
     * an empty set contributes no requirement of its kind. The compiled sets
     * are retained privately by the returned predicate, which evaluates each
     * candidate's current state when tested. Reordering arguments or repeating
     * an identical argument does not change these set-based requirements.
     */
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

    /**
     * Returns {@code "tag"} as the registration key for this identity-filter
     * handler.
     */
    @Override
    public String getKeyName() {
        return "tag";
    }

    /**
     * Returns priority four so identity filtering follows spatial and count
     * handlers.
     */
    @Override
    public int getPriority() {
        return 4;
    }
}
