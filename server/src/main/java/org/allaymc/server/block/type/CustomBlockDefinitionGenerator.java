package org.allaymc.server.block.type;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.data.TintMethod;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.server.block.type.BlockStateDefinition.MaterialInstance;
import org.allaymc.server.block.type.BlockStateDefinition.Materials;
import org.allaymc.server.utils.MolangUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

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
 *         .materials(Materials.builder().any("door_texture"))
 *         .build();
 * });
 *
 * // Crop with different textures for each growth stage
 * CustomBlockDefinitionGenerator.of(state -> {
 *     int age = state.getPropertyValue(BlockPropertyTypes.AGE);
 *     return BlockStateDefinition.builder()
 *         .geometry(Geometry.of("geometry.crop"))
 *         .materials(Materials.builder().any("crop_age_" + age))
 *         .build();
 * });
 *
 * // Block with bone visibility control based on block properties
 * CustomBlockDefinitionGenerator.of(state -> {
 *     return BlockStateDefinition.builder()
 *         .geometry(Geometry.builder()
 *             .identifier("geometry.door")
 *             .boneVisibility("hinge", false)
 *             .boneVisibility("handle", BlockPropertyTypes.OPEN_BIT, true)
 *             .build())
 *         .build();
 * });
 * }</pre>
 *
 * @author daoge_cmd
 * @see BlockStateDefinition
 */
public class CustomBlockDefinitionGenerator implements BlockDefinitionGenerator {

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
                .materials(Materials.builder().any(texture))
                .build());
    }

    @Override
    public BlockDefinition generate(BlockType<?> blockType) {
        // Init global components will fallback values
        var globalComponents = NbtMap.builder()
                // Geometry is default to a unit block
                .putCompound("minecraft:geometry", NbtMap.builder()
                        .putString("identifier", DEFAULT_GEOMETRY)
                        .build())
                // Texture is default to "missing_texture" in all faces
                .putCompound("minecraft:material_instances", NbtMap.builder()
                        .putCompound("mappings", NbtMap.EMPTY)
                        .putCompound("materials", NbtMap.builder()
                                .putCompound("*", MaterialInstance.opaque("missing_texture").toNBT(TintMethod.NONE))
                                .build())
                        .build());

        // Replaceable component (allows block to be replaced when another block is placed)
        if (blockType.hasBlockTag(BlockTags.REPLACEABLE)) {
            globalComponents.putCompound("minecraft:replaceable", NbtMap.EMPTY);
        }

        // Flower pottable component (allows block to be placed in a flower pot)
        if (blockType.hasBlockTag(BlockTags.POTTABLE_PLANT)) {
            globalComponents.putCompound("minecraft:flower_pottable", NbtMap.EMPTY);
        }

        // Custom components
        if (customComponents != null) {
            globalComponents.putAll(customComponents);
        }

        var blockDataBuilder = NbtMap.builder()
                .putCompound("components", globalComponents.build())
                .putCompound("menu_category", defaultMenuCategory())
                .putCompound("vanilla_block_data", NbtMap.builder()
                        .putInt("block_id", CUSTOM_BLOCK_ID.getAndIncrement())
                        .build())
                .putList("properties", NbtType.COMPOUND, buildPropertyDefinitions(blockType))
                .putInt("molangVersion", MolangUtils.MOLANG_VERSION);

        var permutations = new ArrayList<NbtMap>();
        for (var blockState : blockType.getAllStates()) {
            var definition = this.stateDefinitionFunction.apply(blockState);
            if (definition == null) {
                throw new NullPointerException("Definition function returned null for state: " + blockState);
            }

            var blockStateData = blockState.getBlockStateData();
            var components = definition.toComponentsNbt(blockStateData.tintMethod()).toBuilder();

            // Collision and selection box
            components.putCompound("minecraft:collision_box", buildCollisionBoxNBT(blockStateData.collisionShape()));
            components.putCompound("minecraft:selection_box", buildSelectionBoxNBT(blockStateData.shape()));

            // Light emission and dampening
            components.putCompound("minecraft:light_emission", NbtMap.builder()
                    .putByte("emission", (byte) blockStateData.lightEmission())
                    .build());
            components.putCompound("minecraft:light_dampening", NbtMap.builder()
                    .putByte("lightLevel", (byte) blockStateData.lightDampening())
                    .build());

            // Friction
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

            permutations.add(NbtMap.builder()
                    .putString("condition", MolangUtils.buildSingleStateCondition(blockState, blockType))
                    .putCompound("components", components.build())
                    .build()
            );
        }

        blockDataBuilder.putList("permutations", NbtType.COMPOUND, permutations);
        return new BlockDefinition(blockDataBuilder.build());
    }

    /**
     * Builds selection box NBT from VoxelShape.
     * <p>
     * Uses an origin/size format where origin is relative to the block center
     * (range -8 to 8 pixels) and size defines dimensions in pixels (0-16).
     * For VoxelShapes with multiple solids, uses the union AABB.
     *
     * @param voxelShape the selection shape
     * @return NBT with the enabled flag, origin, and size
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

}
