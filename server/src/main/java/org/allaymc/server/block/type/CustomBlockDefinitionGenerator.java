package org.allaymc.server.block.type;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.allaymc.server.block.type.BlockStateDefinition.Geometry;
import org.allaymc.server.block.type.BlockStateDefinition.MaterialInstance;
import org.allaymc.server.block.type.BlockStateDefinition.Materials;
import org.allaymc.server.block.type.BlockStateDefinition.Transformation;

/**
 * CustomBlockDefinitionGenerator generates client-side block definitions following the Bedrock protocol.
 * <p>
 * This generator uses a function-based approach where users provide a {@link Function} that maps each
 * {@link BlockState} to its corresponding {@link BlockStateDefinition}. The generator automatically:
 * <ul>
 *   <li>Iterates all possible block state combinations</li>
 *   <li>Collects rendering properties for each state</li>
 *   <li>Optimizes by extracting common properties to global components</li>
 *   <li>Merges states with identical properties into single permutations with combined conditions</li>
 * </ul>
 * <p>
 * Physical properties (collision shape, light emission, friction, etc.) are automatically read from
 * {@link BlockStateData} and do not need to be specified in the definition function.
 * <p>
 * Example usage:
 * <pre>{@code
 * // Simple door block with different geometry based on open state
 * CustomBlockDefinitionGenerator.of(state -> {
 *     boolean isOpen = state.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
 *     return BlockStateDefinition.builder()
 *         .geometry(Geometry.of(isOpen ? "geometry.door_open" : "geometry.door_closed"))
 *         .materials(Materials.builder().any("door_texture").build())
 *         .build();
 * });
 *
 * // Crop with different textures for each growth stage
 * CustomBlockDefinitionGenerator.of(state -> {
 *     int age = state.getPropertyValue(BlockPropertyTypes.AGE);
 *     return BlockStateDefinition.builder()
 *         .geometry(Geometry.of("geometry.crop"))
 *         .materials(Materials.builder().any("crop_age_" + age).build())
 *         .build();
 * });
 *
 * // Block with bone visibility control
 * CustomBlockDefinitionGenerator.of(state -> {
 *     boolean isOpen = state.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
 *     return BlockStateDefinition.builder()
 *         .geometry(Geometry.builder()
 *             .identifier("geometry.door")
 *             .boneVisibility("hinge", false)
 *             .boneVisibility("handle", "q.block_state('minecraft:open_bit') == 1")
 *             .build())
 *         .build();
 * });
 * }</pre>
 *
 * @author daoge_cmd
 * @see BlockStateDefinition
 */
public class CustomBlockDefinitionGenerator implements BlockDefinitionGenerator {

    private static final int MOLANG_VERSION = 9;
    private static final String DEFAULT_GEOMETRY = "minecraft:geometry.full_block";
    private static final AtomicInteger CUSTOM_BLOCK_ID = new AtomicInteger(10000);

    /**
     * Function that generates a BlockStateDefinition for each BlockState.
     */
    private final Function<BlockState, BlockStateDefinition> stateDefinitionFunction;

    /**
     * Additional custom components to add to the block definition.
     * These are merged into the global components section.
     */
    private final Map<String, NbtMap> customComponents;

    /**
     * Creates a new generator with the specified state definition function.
     *
     * @param stateDefinitionFunction function that maps each BlockState to its rendering properties
     */
    public CustomBlockDefinitionGenerator(Function<BlockState, BlockStateDefinition> stateDefinitionFunction) {
        this(stateDefinitionFunction, null);
    }

    /**
     * Creates a new generator with the specified state definition function and custom components.
     *
     * @param stateDefinitionFunction function that maps each BlockState to its rendering properties
     * @param customComponents        additional custom NBT components to add to the definition
     */
    public CustomBlockDefinitionGenerator(
            Function<BlockState, BlockStateDefinition> stateDefinitionFunction,
            Map<String, NbtMap> customComponents
    ) {
        this.stateDefinitionFunction = Objects.requireNonNull(stateDefinitionFunction, "stateDefinitionFunction cannot be null");
        this.customComponents = customComponents;
    }

    /**
     * Creates a new generator with the specified state definition function.
     * <p>
     * This is a convenience factory method equivalent to the constructor.
     *
     * @param stateDefinitionFunction function that maps each BlockState to its rendering properties
     * @return a new CustomBlockDefinitionGenerator
     */
    public static CustomBlockDefinitionGenerator of(Function<BlockState, BlockStateDefinition> stateDefinitionFunction) {
        return new CustomBlockDefinitionGenerator(stateDefinitionFunction);
    }

    /**
     * Creates a new generator with the specified state definition function and custom components.
     *
     * @param stateDefinitionFunction function that maps each BlockState to its rendering properties
     * @param customComponents        additional custom NBT components to add to the definition
     * @return a new CustomBlockDefinitionGenerator
     */
    public static CustomBlockDefinitionGenerator of(
            Function<BlockState, BlockStateDefinition> stateDefinitionFunction,
            Map<String, NbtMap> customComponents
    ) {
        return new CustomBlockDefinitionGenerator(stateDefinitionFunction, customComponents);
    }

    /**
     * Creates a simple generator for blocks without state-dependent rendering.
     * <p>
     * Use this when all block states should have the same visual appearance.
     *
     * @param definition the definition to use for all states
     * @return a new CustomBlockDefinitionGenerator
     */
    public static CustomBlockDefinitionGenerator ofConstant(BlockStateDefinition definition) {
        return new CustomBlockDefinitionGenerator(state -> definition);
    }

    /**
     * Creates a simple generator for blocks with only a texture.
     *
     * @param texture the texture name to use for all faces
     * @return a new CustomBlockDefinitionGenerator
     */
    public static CustomBlockDefinitionGenerator ofTexture(String texture) {
        return ofConstant(BlockStateDefinition.builder()
                .materials(Materials.builder().any(texture).build())
                .build());
    }

    @Override
    public BlockDefinition generate(BlockType<?> blockType) {
        // Collect definitions for all states
        var stateDefinitions = PermutationOptimizer.collectDefinitions(blockType, stateDefinitionFunction);

        // Optimize into global components and permutations
        var optimized = PermutationOptimizer.optimize(stateDefinitions, blockType);

        // Build the final block definition
        return buildBlockDefinition(blockType, optimized);
    }

    /**
     * Builds the final BlockDefinition from optimized components.
     */
    private BlockDefinition buildBlockDefinition(BlockType<?> blockType, PermutationOptimizer.OptimizedDefinition optimized) {
        var blockStateData = blockType.getDefaultState().getBlockStateData();
        var tintMethod = blockStateData.tintMethod();
        var globalDef = optimized.globalComponents();

        // Build base components from global definition and physical properties
        var components = NbtMap.builder();

        // Display name (from global definition or block identifier)
        String displayName = globalDef.displayName();
        components.putCompound("minecraft:display_name", NbtMap.builder()
                .putString("value", displayName != null ? displayName : blockType.getIdentifier().toString())
                .build());

        // Geometry (from global definition or default)
        Geometry geometry = globalDef.geometry();
        if (geometry != null) {
            components.putCompound("minecraft:geometry", geometry.toNBT());
        } else {
            components.putCompound("minecraft:geometry", NbtMap.builder()
                    .putString("identifier", DEFAULT_GEOMETRY)
                    .build());
        }

        // Materials (from global definition or default)
        var materialsNbt = NbtMap.builder()
                .putCompound("*", MaterialInstance.opaque("missing_texture").toNBT(tintMethod));
        Materials materials = globalDef.materials();
        if (materials != null) {
            for (var entry : materials.entrySet()) {
                materialsNbt.putCompound(entry.getKey(), entry.getValue().toNBT(tintMethod));
            }
        }
        components.putCompound("minecraft:material_instances", NbtMap.builder()
                .putCompound("mappings", NbtMap.EMPTY)
                .putCompound("materials", materialsNbt.build())
                .build());

        // Physical properties from BlockStateData
        components.putCompound("minecraft:collision_box", buildCollisionBoxNBT(blockStateData.collisionShape()));

        components.putCompound("minecraft:selection_box", buildSelectionBoxNBT(blockStateData.shape()));

        components.putCompound("minecraft:light_emission", NbtMap.builder()
                .putByte("emission", (byte) blockStateData.lightEmission())
                .build());

        components.putCompound("minecraft:light_dampening", NbtMap.builder()
                .putByte("lightLevel", (byte) blockStateData.lightDampening())
                .build());

        components.putCompound("minecraft:friction", NbtMap.builder()
                .putFloat("value", Math.clamp(1 - blockStateData.friction(), 0.0f, 0.9f))
                .build());

        // Block breaking is fully server-authoritative
        components.putCompound("minecraft:destructible_by_mining", NbtMap.builder()
                .putFloat("value", Float.MAX_VALUE)
                .build());

        // Block tags
        for (var tag : blockType.getBlockTags()) {
            components.putCompound("tag:" + tag.name(), NbtMap.EMPTY);
        }

        // Replaceable component (allows block to be replaced when another block is placed)
        if (blockType.hasBlockTag(BlockTags.REPLACEABLE)) {
            components.putCompound("minecraft:replaceable", NbtMap.EMPTY);
        }

        // Flower pottable component (allows block to be placed in a flower pot)
        if (blockType.hasBlockTag(BlockTags.POTTABLE_PLANT)) {
            components.putCompound("minecraft:flower_pottable", NbtMap.EMPTY);
        }

        // Transformation from global definition
        Transformation transformation = globalDef.transformation();
        if (transformation != null) {
            components.putCompound("minecraft:transformation", transformation.toNBT());
        }

        // Custom components
        if (customComponents != null) {
            components.putAll(customComponents);
        }

        // Build the final block data
        var blockDataBuilder = NbtMap.builder()
                .putCompound("components", components.build())
                .putCompound("menu_category", defaultMenuCategory())
                .putCompound("vanilla_block_data", NbtMap.builder()
                        .putInt("block_id", CUSTOM_BLOCK_ID.getAndIncrement())
                        .build())
                .putList("properties", NbtType.COMPOUND, buildPropertyDefinitions(blockType))
                .putInt("molangVersion", MOLANG_VERSION);

        // Add permutations if any
        var permutations = optimized.permutations();
        if (!permutations.isEmpty()) {
            var permutationNbtList = permutations.stream()
                    .map(p -> NbtMap.builder()
                            .putString("condition", p.condition())
                            .putCompound("components", p.components().toComponentsNbt(tintMethod))
                            .build())
                    .toList();
            blockDataBuilder.putList("permutations", NbtType.COMPOUND, permutationNbtList);
        }

        return new BlockDefinition(blockDataBuilder.build());
    }

    /**
     * Builds selection box NBT from VoxelShape.
     * <p>
     * Uses origin/size format where origin is relative to block center
     * (range -8 to 8 pixels) and size defines dimensions in pixels (0-16).
     * For VoxelShapes with multiple solids, uses the union AABB.
     *
     * @param voxelShape the selection shape
     * @return NBT with enabled flag, origin, and size
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#selection-box">Selection Box</a>
     */
    private static NbtMap buildSelectionBoxNBT(VoxelShape voxelShape) {
        if (voxelShape == null || voxelShape.getSolids().isEmpty()) {
            return NbtMap.builder()
                    .putBoolean("enabled", false)
                    .build();
        }

        var aabb = voxelShape.unionAABB();
        float originX = (float) (aabb.minX() * 16) - 8;
        float originY = (float) (aabb.minY() * 16);
        float originZ = (float) (aabb.minZ() * 16) - 8;
        float sizeX = (float) ((aabb.maxX() - aabb.minX()) * 16);
        float sizeY = (float) ((aabb.maxY() - aabb.minY()) * 16);
        float sizeZ = (float) ((aabb.maxZ() - aabb.minZ()) * 16);

        return NbtMap.builder()
                .putBoolean("enabled", true)
                .putList("origin", NbtType.FLOAT, List.of(originX, originY, originZ))
                .putList("size", NbtType.FLOAT, List.of(sizeX, sizeY, sizeZ))
                .build();
    }

    /**
     * Builds collision box NBT from VoxelShape.
     * <p>
     * Uses the 1.21.130+ "boxes" format which supports multiple collision boxes.
     * Each box is defined by minX/minY/minZ/maxX/maxY/maxZ in pixels (0-16).
     * For older clients, {@code MultiVersionHelper.adaptCollisionBox} converts
     * to the legacy origin/size format (which only supports a single box).
     *
     * @param voxelShape the collision shape (can contain multiple AABBs)
     * @return NBT with enabled flag and boxes list
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#collision-box">Collision Box</a>
     */
    static NbtMap buildCollisionBoxNBT(VoxelShape voxelShape) {
        if (voxelShape == null || voxelShape.getSolids().isEmpty()) {
            return NbtMap.builder()
                    .putBoolean("enabled", false)
                    .build();
        }

        var boxes = new ArrayList<NbtMap>();
        for (var solid : voxelShape.getSolids()) {
            float minX = (float) (solid.minX() * 16);
            float minY = (float) (solid.minY() * 16);
            float minZ = (float) (solid.minZ() * 16);
            float maxX = (float) (solid.maxX() * 16);
            float maxY = (float) (solid.maxY() * 16);
            float maxZ = (float) (solid.maxZ() * 16);
            boxes.add(NbtMap.builder()
                    .putFloat("minX", minX)
                    .putFloat("minY", minY)
                    .putFloat("minZ", minZ)
                    .putFloat("maxX", maxX)
                    .putFloat("maxY", maxY)
                    .putFloat("maxZ", maxZ)
                    .build());
        }

        return NbtMap.builder()
                .putBoolean("enabled", true)
                .putList("boxes", NbtType.COMPOUND, boxes)
                .build();
    }

    private static NbtMap defaultMenuCategory() {
        return NbtMap.builder()
                .putString("category", "construction")
                .putString("group", "")
                .putByte("is_hidden_in_commands", (byte) 0)
                .build();
    }

    /**
     * Builds property definitions from the block type's properties.
     */
    private List<NbtMap> buildPropertyDefinitions(BlockType<?> blockType) {
        var properties = new ArrayList<NbtMap>();
        for (var property : blockType.getProperties().values()) {
            properties.add(buildPropertyDefinition(property));
        }
        return properties;
    }

    /**
     * Builds a single property definition NBT.
     */
    private NbtMap buildPropertyDefinition(BlockPropertyType<?> property) {
        var builder = NbtMap.builder();
        builder.putString("name", property.getName());

        switch (property.getType()) {
            case BOOLEAN -> builder.putList("enum", NbtType.BYTE, List.of((byte) 0, (byte) 1));
            case INT -> {
                var intProp = (IntPropertyType) property;
                var values = new ArrayList<Integer>();
                for (int i = intProp.getMin(); i <= intProp.getMax(); i++) {
                    values.add(i);
                }
                builder.putList("enum", NbtType.INT, values);
            }
            case ENUM -> {
                var enumValues = property.getValidValues().stream()
                        .map(v -> ((Enum<?>) v).name().toLowerCase(Locale.ROOT))
                        .toList();
                builder.putList("enum", NbtType.STRING, enumValues);
            }
        }

        return builder.build();
    }

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
     * <p>
     * Internal class - not exposed to users, but package-private for testing.
     */
    static final class PermutationOptimizer {

        private PermutationOptimizer() {
        }

        static Map<BlockState, BlockStateDefinition> collectDefinitions(
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

        static OptimizedDefinition optimize(
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

            Geometry globalGeometry = reference.geometry();
            Materials globalMaterials = reference.materials();
            Transformation globalTransformation = reference.transformation();
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

        record OptimizedDefinition(
                BlockStateDefinition globalComponents,
                List<MergedPermutation> permutations
        ) {
        }

        record MergedPermutation(
                String condition,
                BlockStateDefinition components
        ) {
        }
    }

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
     *   <li>Single state: {@code q.block_state('facing') == 'north' && q.block_state('open') == 1}</li>
     *   <li>Multiple states with constant: {@code q.block_state('facing') == 'north' && (q.block_state('open') == 0 || q.block_state('open') == 1)}</li>
     * </ul>
     * <p>
     * Internal class - not exposed to users, but package-private for testing.
     */
    static final class MolangConditionBuilder {

        private MolangConditionBuilder() {
        }

        static String buildCondition(Set<BlockState> states, BlockType<?> blockType) {
            if (states.isEmpty()) {
                return "1";
            }

            if (states.size() == 1) {
                return buildSingleStateCondition(states.iterator().next(), blockType);
            }

            return buildMultiStateCondition(states, blockType);
        }

        private static String buildSingleStateCondition(BlockState state, BlockType<?> blockType) {
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
        private static String formatPropertyCondition(BlockPropertyType<?> property, Object value) {
            String propertyName = property.getName();

            return switch (property.getType()) {
                case BOOLEAN -> {
                    boolean boolValue = (Boolean) value;
                    yield "q.block_state('" + propertyName + "') == " + (boolValue ? 1 : 0);
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
}
