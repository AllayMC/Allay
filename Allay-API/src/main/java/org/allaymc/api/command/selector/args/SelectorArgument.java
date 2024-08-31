package org.allaymc.api.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
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
     * Returns a specific {@code List<Predicate<Entity>>} based on the given arguments table.
     *
     * @param arguments    the arguments list
     * @param selectorType the selector type
     * @param sender       the command sender
     * @param basePos      if this argument parsing requires relative coordinates, it should be based on this coordinate.
     *                     <p>
     *                     If this parameter needs to modify the reference coordinates (such as x, y, z parameters), it should be modified on this parameter.
     *                     <p>
     *                     Only one Location object will be used on a parsing chain.
     *
     * @return {@code Predicate<Entity>}, can be null
     *
     * @throws SelectorSyntaxException when parsing error occurs
     */
    default Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3f basePos, String... arguments) throws SelectorSyntaxException {
        return null;
    }

    /**
     * This method is called only when {@code isFilter()} method returns true.
     * <p>
     * The entity collection returned by this method will be passed to the next parameter.
     *
     * @param arguments    the arguments list
     * @param selectorType the selector type
     * @param sender       the command sender
     * @param basePos      if this argument parsing requires relative coordinates, it should be based on this coordinate.
     *                     <p>
     *                     If this parameter needs to modify the reference coordinates (such as x, y, z parameters), it should be modified on this parameter.
     *                     <p>
     *                     Only one Location object will be used on a parsing chain.
     *
     * @return entity filter
     *
     * @throws SelectorSyntaxException when parsing error occurs
     */
    default Function<List<Entity>, List<Entity>> getFilter(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) throws SelectorSyntaxException {
        return null;
    }

    /**
     * Gets the name of this argument.
     *
     * @return argument name
     */
    String getKeyName();

    /**
     * Parsing priority defines the parsing order of each parameter.
     * <p>
     * The higher the priority (the smaller the number), the earlier the parameter is parsed, and its parsing result will affect the parsing of the next parameter.
     *
     * @return the parsing priority of this argument
     */
    int getPriority();

    /**
     * Whether filter mode is enabled.
     * <p>
     * If a parameter is in filter mode, the parser will not call {@code getPredicate()} method but {@code filter()} method instead.
     * <p>
     * This means the parameter will check all entities reaching this parameter node instead of relative to a single entity.
     * <p>
     * For some parameters (e.g., "c"), this feature needs to be enabled.
     *
     * @return whether filter mode is enabled
     */
    default boolean isFilter() {
        return false;
    }

    /**
     * If a parameter has a default value (i.e., this method returns a non-null value), it will participate in parsing if this parameter is not present in the given argument table.
     *
     * @param values       the argument map
     * @param selectorType the selector type
     * @param sender       the command executor
     *
     * @return the default value of this argument, can be null
     */
    default String getDefaultValue(Map<String, List<String>> values, SelectorType selectorType, CommandSender sender) {
        return null;
    }

    @Override
    default int compareTo(@NotNull SelectorArgument o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
