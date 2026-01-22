package org.allaymc.server.utils;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Builds Molang condition strings for block state permutations.
 * <p>
 * Generates conditions like {@code q.block_state('property') == value} that
 * the client evaluates to determine which permutation to apply.
 * <p>
 * The builder optimizes conditions by:
 * <ul>
 *   <li>Factoring out constant properties that have the same value across all states in a group</li>
 *   <li>Combining variable properties with OR conditions for states that share the same definition</li>
 * </ul>
 * <p>
 * Example outputs:
 * <ul>
 *   <li>Single state: {@code q.block_state('facing') == 'north' && q.block_state('open')}</li>
 *   <li>Multiple states with constant: {@code q.block_state('facing') == 'north' && (!q.block_state('open') || q.block_state('open'))}</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public final class MolangUtils {

    public static final int MOLANG_VERSION = 1;

    private MolangUtils() {
    }

    public static String buildCondition(Set<BlockState> states, BlockType<?> blockType) {
        if (states.isEmpty()) {
            return "1";
        }

        if (states.size() == 1) {
            return buildSingleStateCondition(states.iterator().next(), blockType);
        }

        return buildMultiStateCondition(states, blockType);
    }

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

        return stateConditions.stream()
                .map(c -> "(" + c + ")")
                .collect(Collectors.joining(" || "));
    }

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
