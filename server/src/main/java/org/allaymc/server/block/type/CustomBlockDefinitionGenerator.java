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

    /**
     * The Molang version used for custom blocks.
     */
    public static final int MOLANG_VERSION = 9;

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
    public BlockDefinition generate(BlockType<?> blockType, BlockStateData blockStateData) {
        var components = NbtMap.builder();

        // minecraft:display_name
        components.putCompound("minecraft:display_name", NbtMap.builder()
                .putString("value", displayName != null ? displayName : blockType.getIdentifier().toString())
                .build());

        // minecraft:geometry
        if (geometry != null) {
            components.putCompound("minecraft:geometry", NbtMap.builder()
                    .putString("identifier", geometry)
                    .build());
        }

        // minecraft:material_instances
        if (materialInstances != null && !materialInstances.isEmpty()) {
            var materialsNbt = NbtMap.builder();
            for (var entry : materialInstances.entrySet()) {
                materialsNbt.putCompound(entry.getKey(), entry.getValue().toNBT());
            }
            components.putCompound("minecraft:material_instances", NbtMap.builder()
                    .putCompound("mappings", NbtMap.EMPTY)
                    .putCompound("materials", materialsNbt.build())
                    .build());
        }

        // minecraft:collision_box - from BlockStateData.collisionShape
        var collisionBox = boxFromVoxelShape(blockStateData.collisionShape());
        if (collisionBox != null) {
            if (collisionBox.equals(Box.EMPTY)) {
                components.putCompound("minecraft:collision_box", NbtMap.builder()
                        .putBoolean("enabled", false)
                        .build());
            } else {
                components.putCompound("minecraft:collision_box", collisionBox.toCollisionBoxNBT());
            }
        }

        // minecraft:selection_box - from BlockStateData.shape
        var selectionBox = boxFromVoxelShape(blockStateData.shape());
        if (selectionBox != null) {
            if (selectionBox.equals(Box.EMPTY)) {
                components.putCompound("minecraft:selection_box", NbtMap.builder()
                        .putBoolean("enabled", false)
                        .build());
            } else {
                components.putCompound("minecraft:selection_box", selectionBox.toSelectionBoxNBT());
            }
        }

        // minecraft:light_emission - from BlockStateData.lightEmission
        if (blockStateData.lightEmission() > 0) {
            components.putCompound("minecraft:light_emission", NbtMap.builder()
                    .putByte("emission", (byte) blockStateData.lightEmission())
                    .build());
        }

        // minecraft:light_dampening - from BlockStateData.lightDampening
        if (blockStateData.lightDampening() != 15) {
            components.putCompound("minecraft:light_dampening", NbtMap.builder()
                    .putByte("lightLevel", (byte) blockStateData.lightDampening())
                    .build());
        }

        // minecraft:friction - from BlockStateData.friction
        if (Math.abs(blockStateData.friction() - 0.4f) > 0.001f) {
            components.putCompound("minecraft:friction", NbtMap.builder()
                    .putFloat("value", blockStateData.friction())
                    .build());
        }

        // minecraft:transformation
        if (transformation != null) {
            components.putCompound("minecraft:transformation", transformation.toNBT());
        }

        // TODO: check if these components are necessary
//        // minecraft:destructible_by_mining - calculated from BlockStateData.hardness
//        var destroyTime = calculateDestroyTime(blockStateData.hardness());
//        if (destroyTime != null && destroyTime >= 0) {
//            components.putCompound("minecraft:destructible_by_mining", NbtMap.builder()
//                    .putFloat("value", destroyTime)
//                    .build());
//        }
//
//        // minecraft:destructible_by_explosion - from BlockStateData.explosionResistance
//        if (blockStateData.explosionResistance() >= 0) {
//            components.putCompound("minecraft:destructible_by_explosion", NbtMap.builder()
//                    .putFloat("explosion_resistance", blockStateData.explosionResistance())
//                    .build());
//        }
//
//        // minecraft:map_color - from BlockStateData.mapColor
//        components.putCompound("minecraft:map_color", NbtMap.builder()
//                .putString("color", String.format("#%06X", blockStateData.mapColor().getRGB() & 0xFFFFFF))
//                .build());

        // Custom components
        if (customComponents != null) {
            components.putAll(customComponents);
        }

        // Build block data NBT
        var blockData = NbtMap.builder()
                .putCompound("components", components.build())
                .putInt("molangVersion", MOLANG_VERSION)
                .build();

        // Build property definitions from BlockPropertyType
        var properties = buildPropertyDefinitions(blockType);

        return new BlockDefinition(blockData, properties);
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

    /**
     * Calculates destroy time from hardness.
     * In Bedrock, destroy time is approximately hardness * 1.5 for most blocks.
     */
    private Float calculateDestroyTime(float hardness) {
        if (hardness < 0) {
            return null; // Unbreakable
        }
        return hardness * 1.5f;
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

    // ==================== Inner Classes ====================

    /**
     * Represents a material instance for a block face.
     *
     * @param texture          the texture name from the resource pack
     * @param renderMethod     the render method to use
     * @param faceDimming      whether face dimming is enabled (default true)
     * @param ambientOcclusion whether ambient occlusion is enabled (default true)
     */
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

        public static MaterialInstanceBuilder builder() {
            return new MaterialInstanceBuilder();
        }

        public NbtMap toNBT() {
            return NbtMap.builder()
                    .putString("texture", texture)
                    .putString("render_method", renderMethod.getId())
                    .putBoolean("face_dimming", faceDimming)
                    .putBoolean("ambient_occlusion", ambientOcclusion)
                    .build();
        }

        public static class MaterialInstanceBuilder {
            private String texture;
            private RenderMethod renderMethod = RenderMethod.OPAQUE;
            private boolean faceDimming = true;
            private boolean ambientOcclusion = true;

            public MaterialInstanceBuilder texture(String texture) {
                this.texture = texture;
                return this;
            }

            public MaterialInstanceBuilder renderMethod(RenderMethod renderMethod) {
                this.renderMethod = renderMethod;
                return this;
            }

            public MaterialInstanceBuilder faceDimming(boolean faceDimming) {
                this.faceDimming = faceDimming;
                return this;
            }

            public MaterialInstanceBuilder ambientOcclusion(boolean ambientOcclusion) {
                this.ambientOcclusion = ambientOcclusion;
                return this;
            }

            public MaterialInstance build() {
                return new MaterialInstance(texture, renderMethod, faceDimming, ambientOcclusion);
            }
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

        /**
         * Converts to NBT for minecraft:collision_box component.
         * Uses origin/size format plus boxes list for 1.21.130+ compatibility.
         */
        public NbtMap toCollisionBoxNBT() {
            // Calculate min/max coordinates for boxes list
            // origin is already in Bedrock format (X and Z offset by -8)
            float minX = origin.x() + 8;
            float minY = origin.y();
            float minZ = origin.z() + 8;
            float maxX = minX + size.x();
            float maxY = minY + size.y();
            float maxZ = minZ + size.z();

            return NbtMap.builder()
                    .putBoolean("enabled", true)
                    .putList("origin", NbtType.FLOAT, List.of(origin.x(), origin.y(), origin.z()))
                    .putList("size", NbtType.FLOAT, List.of(size.x(), size.y(), size.z()))
                    .putList("boxes", NbtType.COMPOUND, List.of(
                            NbtMap.builder()
                                    .putFloat("minX", minX)
                                    .putFloat("minY", minY)
                                    .putFloat("minZ", minZ)
                                    .putFloat("maxX", maxX)
                                    .putFloat("maxY", maxY)
                                    .putFloat("maxZ", maxZ)
                                    .build()
                    ))
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
