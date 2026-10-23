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
 * {@code Name} filters selector candidates by their current command-sender
 * names using exact, case-sensitive text comparison. The handler caches
 * compiled string requirements without retaining an entity, sender, or
 * selection origin. Later applications query {@code getCommandSenderName} on
 * each live candidate, so a cached predicate observes a candidate's current
 * name rather than a name snapshot taken during parsing.
 *
 * <p>
 * This handler runs at priority four, after spatial and count handlers. A
 * preceding count filter can already have discarded entities, and name
 * filtering does not restore those candidates when the retained prefix lacks a
 * matching name. The inherited cache treats argument order and identical
 * repetitions as equivalent; the compiled inclusion and exclusion sets use the
 * same equivalence.
 *
 * @author daoge_cmd
 */
public class Name extends CachedSimpleSelectorArgument {
    /**
     * Creates a predicate requiring all positive command-sender name values and
     * rejecting every excluded match. A leading {@code !} removes that marker
     * and inserts the remaining text into the exclusion set; other values enter
     * the inclusion set.
     *
     * <p> Multiple positive values must all equal the same entity name, so
     * distinct required names make the predicate reject every entity. An
     * excluded name rejects its exact match, and empty include or exclude sets
     * impose no requirement of that kind. The compiled sets are retained
     * privately by the returned predicate, which evaluates each candidate's
     * current state when tested. Reordering arguments or repeating an identical
     * argument does not change these set-based requirements.
     */
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

    /**
     * Returns {@code "name"} as the registration key for this identity-filter
     * handler.
     */
    @Override
    public String getKeyName() {
        return "name";
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
