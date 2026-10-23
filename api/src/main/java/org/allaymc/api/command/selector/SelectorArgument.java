package org.allaymc.api.command.selector;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * {@code SelectorArgument} defines one selector key's parsing and filtering
 * behavior in priority order. Predicate handlers may adjust the shared
 * selection origin before later handlers run; list filters transform the
 * surviving candidates as a group. The selector implementation chooses one mode
 * through {@code isFilter}.
 */
public interface SelectorArgument extends Comparable<SelectorArgument> {
    /**
     * Creates an entity predicate for this key, or returns {@code null} when no
     * predicate is needed. {@code basePos} is the mutable origin shared with
     * later handlers; coordinate handlers may update it. {@code arguments}
     * contains this key's values or the default selected by the selector
     * implementation.
     */
    default Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        return null;
    }

    /**
     * Creates a transformation for the whole surviving entity list when
     * {@code isFilter} is {@code true}. This default returns {@code null};
     * filter implementations must return a usable function because the selector
     * applies it directly for an explicitly present key.
     */
    default Function<List<Entity>, List<Entity>> getFilter(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        return null;
    }

    /**
     * Returns the exact selector argument key used for registration and lookup.
     */
    String getKeyName();

    /**
     * Returns the ordering priority used when selector handlers are sorted.
     * Smaller values run earlier; handlers with equal values compare as equal,
     * so their registration order determines their relative processing order in
     * the server implementation.
     */
    int getPriority();

    /**
     * Returns whether this handler transforms the entire candidate list instead
     * of creating an entity predicate. The default is {@code false}, selecting
     * {@code getPredicate}; filter implementations override this along with
     * {@code getFilter}.
     */
    default boolean isFilter() {
        return false;
    }

    /**
     * Returns a synthesized value for an absent predicate key, or {@code null}
     * when no default applies. The server does not consult this hook for list
     * filters and may call it twice when a default is present, so
     * implementations should return a stable value.
     */
    default String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        return null;
    }

    /**
     * Compares this handler with another using their ascending numeric parsing
     * priorities.
     */
    @Override
    default int compareTo(@NotNull SelectorArgument o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
