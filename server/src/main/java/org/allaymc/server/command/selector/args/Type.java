package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.command.selector.ParseUtils;
import org.allaymc.server.entity.data.EntityId;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * {@code Type} filters selector candidates by entity-type identifiers,
 * accepting either a complete namespaced identifier or its bare path.
 * Comparisons use exact text and consult each candidate's current
 * {@code getEntityType} result. The cached predicate retains only string
 * requirements, so it does not depend on the sender or selection origin of the
 * invocation that created it.
 *
 * <p>
 * This priority-four handler also supplies the absent-type default for random
 * selectors. An explicit type key bypasses that default, so the initial
 * random-selector candidate pool can produce a non-player result when the
 * explicit requirements allow it. Spatial and count handlers run earlier; this
 * handler filters their survivors and never restores an entity discarded before
 * type evaluation.
 *
 * @author daoge_cmd
 */
public class Type extends CachedSimpleSelectorArgument {

    /**
     * Creates a predicate requiring all positive entity-type identifier values
     * and rejecting every excluded match. A leading {@code !} removes that
     * marker and inserts the remaining text into the exclusion set; other
     * values enter the inclusion set.
     *
     * <p> Every positive identifier must match the entity type, and no reversed
     * identifier may match it. Full namespaced identifiers and bare paths are
     * both accepted, so two positive spellings can refer to the same entity
     * type. The compiled sets are retained privately by the returned predicate,
     * which evaluates each candidate's current state when tested. Reordering
     * arguments or repeating an identical argument does not change these
     * set-based requirements.
     */
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

    /**
     * Returns the player identifier for an absent type argument on
     * {@code RANDOM_PLAYER}. Other selector types receive {@code null}; an
     * explicitly supplied type key bypasses this default in the selector
     * implementation.
     */
    @Override
    public String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        return selectorType == SelectorType.RANDOM_PLAYER ? EntityId.PLAYER.getIdentifier().toString() : null;
    }

    /**
     * Returns {@code "type"} as the registration key for this identity-filter
     * handler.
     */
    @Override
    public String getKeyName() {
        return "type";
    }

    /**
     * Returns priority four so identity filtering follows spatial and count
     * handlers.
     */
    @Override
    public int getPriority() {
        return 4;
    }

    /**
     * Returns whether the supplied text equals the entity type's full
     * identifier or bare path. Comparisons are exact and case-sensitive, and
     * the method does not add a namespace or normalize the caller's text.
     */
    protected boolean checkType(Entity entity, String type) {
        var identifier = entity.getEntityType().getIdentifier();
        return identifier.toString().equals(type) || identifier.path().equals(type);
    }
}
