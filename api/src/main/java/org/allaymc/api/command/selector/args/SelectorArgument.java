package org.allaymc.api.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This interface describes a selector argument.
 */
public interface SelectorArgument extends Comparable<SelectorArgument> {
    /**
     * Generates a {@link Predicate} to filter entities based on the provided arguments.
     * <p>
     * The predicate operates on a per-entity basis, filtering entities individually based on the
     * conditions specified in the arguments.
     *
     * @param arguments    the list of arguments to parse
     * @param selectorType the type of entity selector (e.g., {@code @a}, {@code @e})
     * @param sender       the command sender, which may affect the context of the selection
     * @param basePos      the base position used for relative coordinates, if needed. This parameter
     *                     can be modified if the argument adjusts the reference coordinates.
     *
     * @return a {@code Predicate<Entity>} to filter entities, or {@code null} if none is applicable
     *
     * @throws SelectorSyntaxException if an error occurs while parsing the arguments
     */
    default Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        return null;
    }

    /**
     * Applies a filter to a list of entities.
     * <p>
     * This method is called only if {@link #isFilter()} returns {@code true}, and it processes
     * an entire list of entities rather than filtering them individually.
     *
     * @param arguments    the list of arguments to parse
     * @param selectorType the type of entity selector
     * @param sender       the command sender
     * @param basePos      the base position for relative coordinates, if needed
     *
     * @return a function that filters a list of entities, or {@code null} if none is applicable
     *
     * @throws SelectorSyntaxException if an error occurs while parsing the arguments
     */
    default Function<List<Entity>, List<Entity>> getFilter(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        return null;
    }

    /**
     * Return the name or key identifier of the selector argument.
     *
     * @return the key name of this argument
     */
    String getKeyName();

    /**
     * Return the parsing priority of this argument.
     * <p>
     * The smaller the number, the earlier the argument is parsed in relation to other arguments.
     * Arguments with higher priorities can influence the parsing of subsequent arguments.
     *
     * @return the parsing priority
     */
    int getPriority();

    /**
     * Determines whether this argument is in filter mode.
     * <p>
     * If filter mode is enabled, the {@link #getFilter} method is called instead of {@link #getPredicate}.
     * This means that the argument operates on an entire list of entities rather than individual entities.
     *
     * @return {@code true} if this argument operates in filter mode, {@code false} otherwise.
     */
    default boolean isFilter() {
        return false;
    }

    /**
     * Provides a default value for the argument if it is not explicitly present in the argument map.
     *
     * @param values       the map of arguments
     * @param selectorType the type of entity selector
     * @param sender       the command executor or sender
     *
     * @return the default value for this argument, or {@code null} if there is no default
     */
    default String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        return null;
    }

    /**
     * Compares this argument with another based on their parsing priority.
     * <p>
     * This method allows arguments to be sorted by priority when being processed.
     *
     * @param o the other {@code SelectorArgument} to compare to
     *
     * @return a negative integer, zero, or a positive integer if this argument has a lower, equal,
     * or higher priority than the other argument, respectively.
     */
    @Override
    default int compareTo(@NotNull SelectorArgument o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
