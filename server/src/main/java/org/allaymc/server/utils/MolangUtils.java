package org.allaymc.server.utils;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {@code MolangUtils} builds client Molang conditions that select block-state permutations.
 * Single states become conjunctions of property checks, and groups become disjunctions with
 * shared constant properties factored out. An empty input set or a block type without
 * properties yields the unconditional expression {@code "1"}.
 *
 * <p>
 * Factoring must retain the association between variable values in each state. For states
 * with two varying properties, independently joining each property's accepted values would
 * also accept combinations absent from the supplied set. The optimizer therefore emits a
 * conjunction per state inside the variable disjunction, then combines that result with the
 * conditions common to every state. Boolean values become direct or negated queries, and enum
 * values become lowercase string comparisons.
 *
 * @author daoge_cmd
 */
public final class MolangUtils {

    /**
     * {@code MOLANG_VERSION} identifies the expression version advertised with generated block
     * definitions. Protocol encoding writes this value alongside client-side block permutation
     * conditions.
     */
    public static final int MOLANG_VERSION = 1;

    /**
     * Prevents construction of the stateless block-state Molang condition helper.
     */
    private MolangUtils() {
    }

    /**
     * Builds a condition selecting the supplied states for {@code blockType}. An empty set yields
     * the unconditional expression {@code "1"}; callers supply states from that type so property
     * values match the definitions used for formatting.
     */
    public static String buildCondition(Set<BlockState> states, BlockType<?> blockType) {
        if (states.isEmpty()) {
            return "1";
        }

        if (states.size() == 1) {
            return buildSingleStateCondition(states.iterator().next(), blockType);
        }

        return buildMultiStateCondition(states, blockType);
    }

    /**
     * Builds a conjunction of the supplied state's property checks. A block type with no
     * properties yields {@code "1"}; callers supply a matching state because this operation does
     * not verify type ownership or escape property names.
     */
    public static String buildSingleStateCondition(BlockState state, BlockType<?> blockType) {
        var properties = blockType.getProperties();
        if (properties.isEmpty()) {
            return "1";
        }

        var propertyValues = state.getPropertyValues();
        var conditions = new ArrayList<String>(properties.size());

        for (var entry : propertyValues.entrySet()) {
            conditions.add(formatPropertyCondition(entry.getKey(), entry.getValue().getValue()));
        }

        return String.join(" && ", conditions);
    }

    /**
     * Builds a condition for several states, preferring common-property factoring. Property-free
     * types yield {@code "1"}, and a declined optimization falls back to a parenthesized
     * disjunction of the complete state conditions.
     */
    private static String buildMultiStateCondition(Set<BlockState> states, BlockType<?> blockType) {
        var properties = blockType.getProperties();
        if (properties.isEmpty()) {
            return "1";
        }

        var stateConditions = new ArrayList<String>();
        for (var state : states) {
            stateConditions.add(buildSingleStateCondition(state, blockType));
        }

        var optimized = tryOptimizeConditions(states, blockType);
        if (optimized != null) {
            return optimized;
        }

        // A declined optimization still needs the original state associations. Keep each
        // full conjunction grouped before joining them so the fallback selects only those states.
        return stateConditions.stream()
                .map(c -> "(" + c + ")")
                .collect(Collectors.joining(" || "));
    }

    /**
     * Factors properties with one observed value out of the state disjunction. Property values
     * are collected across the supplied states, and properties with several values remain inside
     * per-state conjunctions rather than separate per-property alternatives.
     *
     * <p>
     * Keeping those conjunctions preserves the original pairings: accepting {@code A=1,B=2} and
     * {@code A=2,B=1} must not also accept {@code A=1,B=1}. The common conditions are combined
     * with that paired disjunction; a property-free type returns {@code null} to decline this
     * optimization path.
     */
    private static String tryOptimizeConditions(Set<BlockState> states, BlockType<?> blockType) {
        var properties = new ArrayList<>(blockType.getProperties().values());
        if (properties.isEmpty()) {
            return null;
        }

        Map<BlockPropertyType<?>, Set<Object>> propertyValueSets = new LinkedHashMap<>();
        for (var prop : properties) {
            propertyValueSets.put(prop, new LinkedHashSet<>());
        }

        for (var state : states) {
            for (var entry : state.getPropertyValues().entrySet()) {
                var valueSet = propertyValueSets.get(entry.getKey());
                if (valueSet != null) {
                    valueSet.add(entry.getValue().getValue());
                }
            }
        }

        var constantConditions = new ArrayList<String>();
        var variableProperties = new ArrayList<BlockPropertyType<?>>();

        for (var entry : propertyValueSets.entrySet()) {
            if (entry.getValue().size() == 1) {
                constantConditions.add(formatPropertyCondition(entry.getKey(), entry.getValue().iterator().next()));
            } else {
                variableProperties.add(entry.getKey());
            }
        }

        if (variableProperties.isEmpty()) {
            return String.join(" && ", constantConditions);
        }

        // Variable values must remain paired by state. Factoring each variable independently
        // would accept cross-combinations that never appeared in the supplied permutation group.
        var orParts = new ArrayList<String>();
        for (var state : states) {
            var stateParts = new ArrayList<String>();
            for (var prop : variableProperties) {
                var value = state.getPropertyValue(prop);
                stateParts.add(formatPropertyCondition(prop, value));
            }
            orParts.add(stateParts.size() == 1 ? stateParts.getFirst() : "(" + String.join(" && ", stateParts) + ")");
        }

        var orCondition = orParts.size() == 1 ? orParts.getFirst() : "(" + String.join(" || ", orParts) + ")";

        if (constantConditions.isEmpty()) {
            return orCondition;
        }

        constantConditions.add(orCondition);
        return String.join(" && ", constantConditions);
    }

    /**
     * Formats one boolean, integer, or enum property value as a Molang check. Values must match
     * the declared property kind; enum names use {@code Locale.ROOT} lowercase, and property names
     * are inserted without string escaping.
     */
    @SuppressWarnings("rawtypes")
    public static String formatPropertyCondition(BlockPropertyType<?> property, Object value) {
        String propertyName = property.getName();

        return switch (property.getType()) {
            case BOOLEAN -> {
                boolean boolValue = (Boolean) value;
                yield boolValue
                        ? "q.block_state('" + propertyName + "')"
                        : "!q.block_state('" + propertyName + "')";
            }
            case INT -> {
                int intValue = (Integer) value;
                yield "q.block_state('" + propertyName + "') == " + intValue;
            }
            case ENUM -> {
                String enumValue = ((Enum) value).name().toLowerCase(Locale.ROOT);
                yield "q.block_state('" + propertyName + "') == '" + enumValue + "'";
            }
        };
    }
}
