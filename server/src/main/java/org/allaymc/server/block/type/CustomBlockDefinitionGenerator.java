package org.allaymc.server.block.type;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.message.MayContainTrKey;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.primitives.AABBdc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CustomBlockDefinitionGenerator is the default implementation of {@link BlockDefinitionGenerator}.
 * <p>
 * This class follows the Bedrock custom block protocol and generates client-side block definitions.
 * It only requires client-specific information (geometry, materials, transformation), while
 * physical properties (hardness, friction, light emission, collision shape, etc.) are
 * automatically read from {@link BlockStateData}.
 *
 * @author daoge_cmd
 */
@Builder
public class CustomBlockDefinitionGenerator implements BlockDefinitionGenerator {

    private static final int MOLANG_VERSION = 9;
    private static final String DEFAULT_GEOMETRY = "minecraft:geometry.full_block";
    private static final AtomicInteger CUSTOM_BLOCK_ID = new AtomicInteger(10000);

    /**
     * The display name shown in the inventory and tooltips.
     * If {@code null}, the identifier will be used.
     */
    @MayContainTrKey
    protected final String displayName;

    /**
     * The geometry identifier (e.g., "geometry.custom_block").
     * Used to specify a custom geometry model defined in resource packs.
     */
    protected final String geometry;

    /**
     * Material instances for rendering the block.
     * Maps face/material name to MaterialInstance (texture, render method).
     */
    protected final Map<String, MaterialInstance> materialInstances;

    /**
     * Transformation applied to the block model.
     */
    protected final Transformation transformation;

    /**
     * Additional custom components to add to the block definition.
     */
    protected final Map<String, NbtMap> customComponents;

    @Override
    public BlockDefinition generate(BlockType<?> blockType) {
        var blockStateData = blockType.getDefaultState().getBlockStateData();
        var components = NbtMap.builder();

        components.putCompound("minecraft:display_name", NbtMap.builder()
                .putString("value", displayName != null ? displayName : blockType.getIdentifier().toString())
                .build());

        components.putCompound("minecraft:geometry", NbtMap.builder()
                .putString("identifier", geometry != null ? geometry : DEFAULT_GEOMETRY)
                .build());

        var materialsNbt = NbtMap.builder().putCompound("*", new MaterialInstance("missing_texture", RenderMethod.OPAQUE, true, true).toNBT());
        if (materialInstances != null) {
            for (var entry : materialInstances.entrySet()) {
                materialsNbt.putCompound(entry.getKey(), entry.getValue().toNBT());
            }
        }
        components.putCompound("minecraft:material_instances", NbtMap.builder()
                .putCompound("mappings", NbtMap.EMPTY)
                .putCompound("materials", materialsNbt.build())
                .build());

        components.putCompound("minecraft:collision_box", buildCollisionBoxNBT(blockStateData.collisionShape()));

        var selectionBox = boxFromVoxelShape(blockStateData.shape());
        if (selectionBox.equals(Box.EMPTY)) {
            components.putCompound("minecraft:selection_box", NbtMap.builder()
                    .putBoolean("enabled", false)
                    .build());
        } else {
            components.putCompound("minecraft:selection_box", selectionBox.toSelectionBoxNBT());
        }

        components.putCompound("minecraft:light_emission", NbtMap.builder()
                .putByte("emission", (byte) blockStateData.lightEmission())
                .build());

        components.putCompound("minecraft:light_dampening", NbtMap.builder()
                .putByte("lightLevel", (byte) blockStateData.lightDampening())
                .build());

        // See https://learn.microsoft.com/en-us/minecraft/creator/reference/content/blockreference/examples/blockcomponents/minecraftblock_friction
        components.putCompound("minecraft:friction", NbtMap.builder()
                .putFloat("value", Math.clamp(1 - blockStateData.friction(), 0.0f, 0.9f))
                .build());

        // Block breaking is fully server authed, so let just set a extra big value here
        components.putCompound("minecraft:destructible_by_mining", NbtMap.builder()
                .putFloat("value", 99999f)
                .build());

        for (var tag : blockType.getBlockTags()) {
            components.putCompound("tag:" + tag.name(), NbtMap.EMPTY);
        }

        if (transformation != null) {
            components.putCompound("minecraft:transformation", transformation.toNBT());
        }

        if (customComponents != null) {
            components.putAll(customComponents);
        }

        var blockData = NbtMap.builder()
                .putCompound("components", components.build())
                // This is required, or the client will crash, although the creative inventory is already server authed xd
                .putCompound("menu_category", defaultMenuCategory())
                // Integer block id is also required by the client, let's just send an incremental value to the client
                .putCompound("vanilla_block_data", NbtMap.builder().putInt("block_id", CUSTOM_BLOCK_ID.getAndIncrement()).build())
                .putList("properties", NbtType.COMPOUND, buildPropertyDefinitions(blockType))
                .putInt("molangVersion", MOLANG_VERSION)
                .build();

        return new BlockDefinition(blockData);
    }

    /**
     * Converts VoxelShape to Box for Bedrock protocol.
     * Uses the union AABB of all solids in the VoxelShape.
     */
    private Box boxFromVoxelShape(VoxelShape voxelShape) {
        if (voxelShape == null || voxelShape.getSolids().isEmpty()) {
            return Box.EMPTY;
        }

        var aabb = voxelShape.unionAABB();
        return Box.fromAABB(aabb);
    }

    private NbtMap buildCollisionBoxNBT(VoxelShape voxelShape) {
        if (voxelShape == null || voxelShape.getSolids().isEmpty()) {
            return NbtMap.builder()
                    .putBoolean("enabled", false)
                    .build();
        }

        var boxes = new ArrayList<NbtMap>();
        for (var solid : voxelShape.getSolids()) {
            boxes.add(NbtMap.builder()
                    .putFloat("minX", (float) (solid.minX() * 16))
                    .putFloat("minY", (float) (solid.minY() * 16))
                    .putFloat("minZ", (float) (solid.minZ() * 16))
                    .putFloat("maxX", (float) (solid.maxX() * 16))
                    .putFloat("maxY", (float) (solid.maxY() * 16))
                    .putFloat("maxZ", (float) (solid.maxZ() * 16))
                    .build());
        }

        var union = Box.fromAABB(voxelShape.unionAABB());
        return NbtMap.builder()
                .putBoolean("enabled", true)
                .putList("origin", NbtType.FLOAT, List.of(union.origin().x(), union.origin().y(), union.origin().z()))
                .putList("size", NbtType.FLOAT, List.of(union.size().x(), union.size().y(), union.size().z()))
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
            case BOOLEAN -> {
                builder.putList("enum", NbtType.BYTE, List.of((byte) 0, (byte) 1));
            }
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
     * Represents a material instance for a block face.
     *
     * @param texture          the texture name from the resource pack
     * @param renderMethod     the render method to use (default: OPAQUE)
     * @param faceDimming      whether face dimming is enabled (default: true)
     * @param ambientOcclusion whether ambient occlusion is enabled (default: true)
     */
    @Builder
    public record MaterialInstance(
            String texture,
            RenderMethod renderMethod,
            boolean faceDimming,
            boolean ambientOcclusion
    ) {
        public MaterialInstance(String texture, RenderMethod renderMethod) {
            this(texture, renderMethod, true, true);
        }

        public MaterialInstance {
            if (renderMethod == null) {
                renderMethod = RenderMethod.OPAQUE;
            }
        }

        public NbtMap toNBT() {
            byte packedBools = (byte) (faceDimming ? 0x1 : 0x0);
            return NbtMap.builder()
                    .putString("texture", texture)
                    .putString("render_method", renderMethod.getId())
                    .putBoolean("ambient_occlusion", ambientOcclusion)
                    .putByte("packed_bools", packedBools)
                    .build();
        }
    }

    /**
     * Render methods for material instances.
     */
    @Getter
    public enum RenderMethod {
        OPAQUE("opaque"),
        DOUBLE_SIDED("double_sided"),
        BLEND("blend"),
        ALPHA_TEST("alpha_test"),
        ALPHA_TEST_SINGLE_SIDED("alpha_test_single_sided");

        private final String id;

        RenderMethod(String id) {
            this.id = id;
        }
    }

    /**
     * Represents a 3D box for collision or selection.
     * Uses Bedrock format: origin (relative to block center at -8,-8,-8 offset) and size (in pixels 0-16).
     *
     * @param origin the origin of the box (minimum corner), values in pixel units relative to block corner
     * @param size   the size of the box in pixel units
     */
    @Builder
    public record Box(Vector3fc origin, Vector3fc size) {
        /**
         * An empty box that disables collision/selection.
         */
        public static final Box EMPTY = new Box(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

        /**
         * Creates a full block box (16x16x16).
         */
        public static Box fullBlock() {
            return new Box(new Vector3f(-8, 0, -8), new Vector3f(16, 16, 16));
        }

        /**
         * Creates a box from min/max coordinates (in block units 0.0-1.0).
         */
        public static Box of(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            // Convert from block units (0-1) to pixel units (0-16)
            float pMinX = minX * 16;
            float pMinY = minY * 16;
            float pMinZ = minZ * 16;
            float pMaxX = maxX * 16;
            float pMaxY = maxY * 16;
            float pMaxZ = maxZ * 16;

            // Bedrock origin is relative to block center, so offset by -8 on X and Z
            return new Box(
                    new Vector3f(pMinX - 8, pMinY, pMinZ - 8),
                    new Vector3f(pMaxX - pMinX, pMaxY - pMinY, pMaxZ - pMinZ)
            );
        }

        /**
         * Creates a box from an AABB (in block units 0.0-1.0).
         */
        public static Box fromAABB(AABBdc aabb) {
            return of(
                    (float) aabb.minX(), (float) aabb.minY(), (float) aabb.minZ(),
                    (float) aabb.maxX(), (float) aabb.maxY(), (float) aabb.maxZ()
            );
        }

        /**
         * Converts to NBT for minecraft:selection_box component.
         * Uses origin/size format.
         */
        public NbtMap toSelectionBoxNBT() {
            return NbtMap.builder()
                    .putBoolean("enabled", true)
                    .putList("origin", NbtType.FLOAT, List.of(origin.x(), origin.y(), origin.z()))
                    .putList("size", NbtType.FLOAT, List.of(size.x(), size.y(), size.z()))
                    .build();
        }
    }

    /**
     * Transformation applied to the block model.
     * <p>
     * Rotation values are in degrees and must be multiples of 90.
     * They will be converted to quarter-turn units (0-3) internally.
     *
     * @param rx rotation around X axis in degrees (must be multiple of 90)
     * @param ry rotation around Y axis in degrees (must be multiple of 90)
     * @param rz rotation around Z axis in degrees (must be multiple of 90)
     * @param sx scale factor on X axis (default: 1.0)
     * @param sy scale factor on Y axis (default: 1.0)
     * @param sz scale factor on Z axis (default: 1.0)
     * @param tx translation on X axis
     * @param ty translation on Y axis
     * @param tz translation on Z axis
     */
    @Builder
    public record Transformation(
            int rx, int ry, int rz,
            float sx, float sy, float sz,
            float tx, float ty, float tz
    ) {
        public Transformation {
            // Default scale to 1.0 if not specified (0.0 would make block invisible)
            if (sx == 0) sx = 1.0f;
            if (sy == 0) sy = 1.0f;
            if (sz == 0) sz = 1.0f;
        }

        public NbtMap toNBT() {
            return NbtMap.builder()
                    .putInt("RX", (((rx % 360) + 360) % 360) / 90)
                    .putInt("RY", (((ry % 360) + 360) % 360) / 90)
                    .putInt("RZ", (((rz % 360) + 360) % 360) / 90)
                    .putFloat("SX", sx)
                    .putFloat("SY", sy)
                    .putFloat("SZ", sz)
                    .putFloat("TX", tx)
                    .putFloat("TY", ty)
                    .putFloat("TZ", tz)
                    .build();
        }
    }
}
