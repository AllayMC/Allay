package org.allaymc.server.utils.molang;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.type.BlockStateDefinition;

import java.util.*;
import java.util.function.Function;

/**
 * Optimizes block state definitions into an efficient permutation structure.
 * <p>
 * The optimization process has three steps:
 * <ol>
 *   <li><b>Extract global components</b>: Finds properties that are identical across all states
 *       (geometry, materials, transformation, displayName) and moves them to global components</li>
 *   <li><b>Group states by definition</b>: Groups states that have identical effective definitions
 *       (after removing global components) to reduce permutation count</li>
 *   <li><b>Generate Molang conditions</b>: Creates optimized condition strings for each group
 *       using {@link MolangConditionBuilder}</li>
 * </ol>
 *
 * @author daoge_cmd
 */
public final class PermutationOptimizer {

    private PermutationOptimizer() {
    }

    public static Map<BlockState, BlockStateDefinition> collectDefinitions(
            BlockType<?> blockType,
            Function<BlockState, BlockStateDefinition> definitionFunction
    ) {
        var stateDefinitions = new LinkedHashMap<BlockState, BlockStateDefinition>();

        for (BlockState state : blockType.getAllStates()) {
            BlockStateDefinition definition = definitionFunction.apply(state);
            if (definition == null) {
                throw new NullPointerException("Definition function returned null for state: " + state);
            }
            stateDefinitions.put(state, definition);
        }

        return stateDefinitions;
    }

    public static OptimizedDefinition optimize(
            Map<BlockState, BlockStateDefinition> stateDefinitions,
            BlockType<?> blockType
    ) {
        if (stateDefinitions.isEmpty()) {
            return new OptimizedDefinition(BlockStateDefinition.DEFAULT, List.of());
        }

        if (stateDefinitions.size() == 1) {
            var onlyDefinition = stateDefinitions.values().iterator().next();
            return new OptimizedDefinition(onlyDefinition, List.of());
        }

        var globalComponents = extractGlobalComponents(stateDefinitions.values());
        var groupedStates = groupStatesByDefinition(stateDefinitions, globalComponents);
        var permutations = generatePermutations(groupedStates, blockType);

        return new OptimizedDefinition(globalComponents, permutations);
    }

    private static BlockStateDefinition extractGlobalComponents(Collection<BlockStateDefinition> definitions) {
        if (definitions.isEmpty()) {
            return BlockStateDefinition.DEFAULT;
        }

        var iterator = definitions.iterator();
        var reference = iterator.next();

        BlockStateDefinition.Geometry globalGeometry = reference.geometry();
        BlockStateDefinition.Materials globalMaterials = reference.materials();
        BlockStateDefinition.Transformation globalTransformation = reference.transformation();
        String globalDisplayName = reference.displayName();

        while (iterator.hasNext()) {
            var def = iterator.next();

            if (!Objects.equals(globalGeometry, def.geometry())) {
                globalGeometry = null;
            }
            if (!Objects.equals(globalMaterials, def.materials())) {
                globalMaterials = null;
            }
            if (!Objects.equals(globalTransformation, def.transformation())) {
                globalTransformation = null;
            }
            if (!Objects.equals(globalDisplayName, def.displayName())) {
                globalDisplayName = null;
            }

            if (globalGeometry == null && globalMaterials == null
                    && globalTransformation == null && globalDisplayName == null) {
                break;
            }
        }

        return BlockStateDefinition.builder()
                .geometry(globalGeometry)
                .materials(globalMaterials)
                .transformation(globalTransformation)
                .displayName(globalDisplayName)
                .build();
    }

    private static Map<BlockStateDefinition, Set<BlockState>> groupStatesByDefinition(
            Map<BlockState, BlockStateDefinition> stateDefinitions,
            BlockStateDefinition globalComponents
    ) {
        var groups = new LinkedHashMap<BlockStateDefinition, Set<BlockState>>();

        for (var entry : stateDefinitions.entrySet()) {
            var state = entry.getKey();
            var definition = entry.getValue();

            var effectiveDefinition = definition.diff(globalComponents);
            if (effectiveDefinition == null) {
                effectiveDefinition = BlockStateDefinition.DEFAULT;
            }

            groups.computeIfAbsent(effectiveDefinition, k -> new LinkedHashSet<>()).add(state);
        }

        return groups;
    }

    private static List<MergedPermutation> generatePermutations(
            Map<BlockStateDefinition, Set<BlockState>> groupedStates,
            BlockType<?> blockType
    ) {
        var permutations = new ArrayList<MergedPermutation>();

        for (var entry : groupedStates.entrySet()) {
            var definition = entry.getKey();
            var states = entry.getValue();

            if (!definition.hasAnyProperty()) {
                continue;
            }

            var condition = MolangConditionBuilder.buildCondition(states, blockType);
            permutations.add(new MergedPermutation(condition, definition));
        }

        return permutations;
    }

    public record OptimizedDefinition(
            BlockStateDefinition globalComponents,
            List<MergedPermutation> permutations
    ) {
    }

    public record MergedPermutation(
            String condition,
            BlockStateDefinition components
    ) {
    }
}
