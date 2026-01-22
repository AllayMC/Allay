package org.allaymc.server.block.type;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.data.TintMethod;
import org.allaymc.api.message.MayContainTrKey;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * BlockStateDefinition holds the client-side rendering properties for a single block state.
 * <p>
 * This record is used as the return type of the user-provided function in {@link CustomBlockDefinitionGenerator}.
 * Each BlockState can have different rendering properties (geometry, materials, transformation, etc.),
 * and the system will automatically optimize these into global components and state-specific permutations.
 * <p>
 * Physical properties like collision shape, light emission, and friction are still
 * read from {@link BlockStateData} and are not part of this definition.
 *
 * @param geometry       the geometry identifier (e.g., "geometry.custom_block"), null for default
 * @param materials      material instances for rendering, null for default
 * @param transformation transformation applied to the block model, null for none
 * @param displayName    display name shown in inventory/tooltips, null to use block identifier
 * @author daoge_cmd
 * @see CustomBlockDefinitionGenerator
 */
@Builder(toBuilder = true)
public record BlockStateDefinition(
        String geometry,
        Materials materials,
        Transformation transformation,
        @MayContainTrKey String displayName
) {
    /**
     * Default definition with no custom properties.
     */
    public static final BlockStateDefinition DEFAULT = BlockStateDefinition.builder().build();

    /**
     * Computes the difference between this definition and a base definition.
     * Returns a new definition containing only the properties that differ.
     *
     * @param base the base definition to compare against
     * @return a definition containing only the differing properties, or null if identical
     */
    public BlockStateDefinition diff(BlockStateDefinition base) {
        if (base == null || this.equals(base)) {
            return null;
        }

        var builder = BlockStateDefinition.builder();
        boolean hasDiff = false;

        if (!Objects.equals(geometry, base.geometry)) {
            builder.geometry(geometry);
            hasDiff = true;
        }
        if (!Objects.equals(materials, base.materials)) {
            builder.materials(materials);
            hasDiff = true;
        }
        if (!Objects.equals(transformation, base.transformation)) {
            builder.transformation(transformation);
            hasDiff = true;
        }
        if (!Objects.equals(displayName, base.displayName)) {
            builder.displayName(displayName);
            hasDiff = true;
        }

        return hasDiff ? builder.build() : null;
    }

    /**
     * Checks if this definition has any non-null properties.
     *
     * @return true if at least one property is set
     */
    public boolean hasAnyProperty() {
        return geometry != null
                || (materials != null && !materials.isEmpty())
                || transformation != null
                || displayName != null;
    }

    /**
     * Converts this definition to NBT format for permutation components.
     *
     * @param tintMethod the tint method from BlockStateData (can be null)
     * @return the NBT representation containing only non-null components
     */
    public NbtMap toComponentsNbt(TintMethod tintMethod) {
        var builder = NbtMap.builder();

        if (displayName != null) {
            builder.putCompound("minecraft:display_name", NbtMap.builder()
                    .putString("value", displayName)
                    .build());
        }

        if (geometry != null) {
            builder.putCompound("minecraft:geometry", NbtMap.builder()
                    .putString("identifier", geometry)
                    .build());
        }

        if (materials != null && !materials.isEmpty()) {
            var materialsNbt = NbtMap.builder();
            for (var entry : materials.entrySet()) {
                materialsNbt.putCompound(entry.getKey(), entry.getValue().toNBT(tintMethod));
            }
            builder.putCompound("minecraft:material_instances", NbtMap.builder()
                    .putCompound("mappings", NbtMap.EMPTY)
                    .putCompound("materials", materialsNbt.build())
                    .build());
        }

        if (transformation != null) {
            builder.putCompound("minecraft:transformation", transformation.toNBT());
        }

        return builder.build();
    }

    /**
     * Represents a material instance for block face rendering.
     * <p>
     * Material instances define how each face of a block is rendered,
     * including texture, transparency, and lighting effects.
     * <p>
     * The NBT encoding uses {@code packed_bools} (1.21.110+ format) to pack
     * {@code faceDimming}, {@code randomUVRotation}, and {@code textureVariation}
     * into a single byte. For older clients, {@code MultiVersionHelper.adaptMaterialInstances}
     * converts to the legacy format with separate boolean fields.
     *
     * @param texture          the texture shortname from terrain_texture.json
     * @param renderMethod     controls transparency and culling behavior
     * @param faceDimming      whether faces receive directional shading (default: true)
     * @param ambientOcclusion whether smooth lighting is applied (default: true)
     * @param randomUVRotation whether texture randomly rotates based on world position,
     *                         corresponds to "isotropic" in Bedrock documentation (default: false)
     * @param textureVariation whether texture variation is enabled (default: false)
     * @see RenderMethod
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#material-instances">Material Instances</a>
     */
    @Builder
    public record MaterialInstance(
            String texture,
            RenderMethod renderMethod,
            boolean faceDimming,
            boolean ambientOcclusion,
            boolean randomUVRotation,
            boolean textureVariation
    ) {
        public MaterialInstance {
            if (renderMethod == null) {
                renderMethod = RenderMethod.OPAQUE;
            }
        }

        public static MaterialInstance of(String texture) {
            return new MaterialInstance(texture, RenderMethod.OPAQUE, true, true, false, false);
        }

        public static MaterialInstance of(String texture, RenderMethod renderMethod) {
            return new MaterialInstance(texture, renderMethod, true, true, false, false);
        }

        public static MaterialInstance opaque(String texture) {
            return of(texture, RenderMethod.OPAQUE);
        }

        public static MaterialInstance alphaTest(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST);
        }

        public static MaterialInstance alphaTestSingleSided(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST_SINGLE_SIDED);
        }

        public static MaterialInstance blend(String texture) {
            return of(texture, RenderMethod.BLEND);
        }

        public static MaterialInstance doubleSided(String texture) {
            return of(texture, RenderMethod.DOUBLE_SIDED);
        }

        public NbtMap toNBT(TintMethod tintMethod) {
            byte packedBools = 0;
            if (faceDimming) packedBools |= 0x1;
            if (randomUVRotation) packedBools |= 0x2;
            if (textureVariation) packedBools |= 0x4;

            var builder = NbtMap.builder()
                    .putString("texture", texture)
                    .putString("render_method", renderMethod.getId())
                    .putBoolean("ambient_occlusion", ambientOcclusion)
                    .putByte("packed_bools", packedBools);

            if (tintMethod != null && tintMethod != TintMethod.NONE) {
                builder.putString("tint_method", tintMethod.name().toLowerCase(Locale.ROOT));
            }

            return builder.build();
        }
    }

    /**
     * Builder for creating material instances with convenient per-face methods.
     * <p>
     * Material instances can be assigned to specific faces or to a wildcard ({@code *})
     * that applies to all unspecified faces. The client first checks for a face-specific
     * instance (e.g., "up", "north"), then falls back to the wildcard if not found.
     * <p>
     * Named material instances (any string key) can also be used in custom geometry
     * to reference specific materials for different parts of the model.
     * <p>
     * Example usage:
     * <pre>{@code
     * Materials.builder()
     *     .any("default_texture")                           // all faces (wildcard)
     *     .face(BlockFace.UP, "top_texture")                // override top face
     *     .face(BlockFace.DOWN, MaterialInstance.blend("bottom_texture"))
     *     .build();
     * }</pre>
     *
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#material-instances">Material Instances</a>
     */
    public static final class Materials {
        /**
         * Special key for the wildcard face that applies to all unspecified faces.
         */
        public static final String ANY_FACE = "*";

        private final Map<String, MaterialInstance> materials = new HashMap<>();

        private Materials() {}

        public static Materials builder() {
            return new Materials();
        }

        /**
         * Sets the material for a specific block face.
         *
         * @param face     the block face
         * @param material the material instance
         */
        public Materials face(BlockFace face, MaterialInstance material) {
            materials.put(face.name().toLowerCase(Locale.ROOT), material);
            return this;
        }

        /**
         * Sets the material for a specific block face with default opaque rendering.
         *
         * @param face    the block face
         * @param texture the texture name
         */
        public Materials face(BlockFace face, String texture) {
            return face(face, MaterialInstance.of(texture));
        }

        /**
         * Sets the material for all unspecified faces (wildcard).
         *
         * @param material the material instance
         */
        public Materials any(MaterialInstance material) {
            materials.put(ANY_FACE, material);
            return this;
        }

        /**
         * Sets the material for all unspecified faces (wildcard) with default opaque rendering.
         *
         * @param texture the texture name
         */
        public Materials any(String texture) {
            return any(MaterialInstance.of(texture));
        }

        /**
         * Sets the same material for all horizontal faces (north, south, east, west).
         *
         * @param material the material instance
         */
        public Materials sides(MaterialInstance material) {
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                face(face, material);
            }
            return this;
        }

        /**
         * Sets the same material for all horizontal faces (north, south, east, west).
         *
         * @param texture the texture name
         */
        public Materials sides(String texture) {
            return sides(MaterialInstance.of(texture));
        }

        public Set<Map.Entry<String, MaterialInstance>> entrySet() {
            return materials.entrySet();
        }

        public boolean isEmpty() {
            return materials.isEmpty();
        }

        public Materials build() {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Materials other)) return false;
            return materials.equals(other.materials);
        }

        @Override
        public int hashCode() {
            return materials.hashCode();
        }
    }

    /**
     * Render methods for material instances.
     * <p>
     * Each method controls transparency handling, back-face culling, and distance-based rendering.
     *
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#render-methods">Render Methods</a>
     */
    @Getter
    public enum RenderMethod {
        /**
         * Fully opaque rendering with back-face culling. No distance culling.
         * Use for solid blocks like dirt, stone, and planks.
         */
        OPAQUE("opaque"),
        /**
         * Supports full transparency and translucency with back-face culling.
         * Use for semi-transparent blocks like glass, ice, and stained glass.
         */
        BLEND("blend"),
        /**
         * Fully opaque rendering without back-face culling (renders both sides).
         * Use for blocks visible from inside like powder snow.
         */
        DOUBLE_SIDED("double_sided"),
        /**
         * Binary transparency (fully transparent or fully opaque pixels) without back-face culling.
         * Has distance culling. Use for cross-model blocks like flowers, tall grass, and vines.
         */
        ALPHA_TEST("alpha_test"),
        /**
         * Binary transparency with back-face culling. Has distance culling.
         * Use for flat blocks like doors, trapdoors, and saplings.
         */
        ALPHA_TEST_SINGLE_SIDED("alpha_test_single_sided"),
        /**
         * Like {@link #ALPHA_TEST} but switches to opaque rendering at distance.
         * Improves performance while maintaining appearance up close.
         */
        ALPHA_TEST_TO_OPAQUE("alpha_test_to_opaque"),
        /**
         * Like {@link #ALPHA_TEST_SINGLE_SIDED} but switches to opaque rendering at distance.
         * Improves performance while maintaining appearance up close.
         */
        ALPHA_TEST_SINGLE_SIDED_TO_OPAQUE("alpha_test_single_sided_to_opaque"),
        /**
         * Like {@link #BLEND} but switches to opaque rendering at distance.
         * Improves performance while maintaining appearance up close.
         */
        BLEND_TO_OPAQUE("blend_to_opaque");

        private final String id;

        RenderMethod(String id) {
            this.id = id;
        }
    }

    /**
     * Transformation applied to block geometry.
     * <p>
     * Rotation values must be multiples of 90 degrees. The NBT encoding
     * divides rotation by 90 (e.g., 90 degrees becomes RX=1, 180 becomes RX=2).
     * Negative rotations are normalized to positive equivalents.
     *
     * @param rx X-axis rotation in degrees (must be 0, 90, 180, or 270)
     * @param ry Y-axis rotation in degrees (must be 0, 90, 180, or 270)
     * @param rz Z-axis rotation in degrees (must be 0, 90, 180, or 270)
     * @param sx X-axis scale factor (default: 1.0)
     * @param sy Y-axis scale factor (default: 1.0)
     * @param sz Z-axis scale factor (default: 1.0)
     * @param tx X-axis translation in block units
     * @param ty Y-axis translation in block units
     * @param tz Z-axis translation in block units
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#transformation">Transformation</a>
     */
    @Builder
    public record Transformation(
            int rx, int ry, int rz,
            float sx, float sy, float sz,
            float tx, float ty, float tz
    ) {
        public Transformation {
            if (sx == 0) sx = 1.0f;
            if (sy == 0) sy = 1.0f;
            if (sz == 0) sz = 1.0f;
        }

        /**
         * Converts this transformation to NBT format.
         * <p>
         * NBT format uses {@code RX/RY/RZ} as rotation divided by 90 (0-3),
         * {@code SX/SY/SZ} as scale factors, and {@code TX/TY/TZ} as translation.
         *
         * @return the NBT representation
         */
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
