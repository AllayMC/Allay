package org.allaymc.server.block.type;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.server.utils.MolangUtils;
import org.cloudburstmc.nbt.NbtMap;

import java.util.*;

/**
 * Describes the protocol-independent client-side appearance of one custom block state.
 * <p>
 * A block type may use one instance for every state or resolve a different instance for each state while the
 * block type is being built. Protocol implementations serialize this model into their own wire format.
 * <p>
 * Physical properties like collision shape, light emission, and friction are still
 * read from {@link BlockStateData} and are not part of this definition.
 *
 * @param geometry         the geometry component with identifier and advanced properties, {@code null} for default
 * @param materials        material instances for rendering, {@code null} for default
 * @param transformation   transformation applied to the block model, {@code null} for none
 * @param displayName      display name shown in inventory/tooltips, {@code null} to use block identifier
 * @param rawComponents   raw Bedrock components used as an explicit extension point
 * @author daoge_cmd
 */
@Builder(toBuilder = true)
public record CustomBlockStateDefinition(
        Geometry geometry,
        Materials materials,
        Transformation transformation,
        @MayContainTrKey String displayName,
        Map<String, NbtMap> rawComponents
) {
    /**
     * Default definition with no custom properties.
     */
    public static final CustomBlockStateDefinition DEFAULT = CustomBlockStateDefinition.builder().build();

    public CustomBlockStateDefinition {
        if (rawComponents == null) {
            rawComponents = Map.of();
        } else {
            var copy = new LinkedHashMap<String, NbtMap>();
            rawComponents.forEach((name, component) -> copy.put(
                    Objects.requireNonNull(name, "rawComponents contains a null name"),
                    Objects.requireNonNull(component, "rawComponents contains a null component")
            ));
            rawComponents = Collections.unmodifiableMap(copy);
        }
    }

    /**
     * Creates a definition that renders the same opaque texture on every face.
     *
     * @param texture the terrain texture short name
     * @return the custom block state definition
     */
    public static CustomBlockStateDefinition ofTexture(String texture) {
        return builder().materials(Materials.builder().any(texture).build()).build();
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
     * Custom builder extensions for convenience methods.
     * <p>
     * Lombok merges this class with its generated builder, allowing
     * additional overloaded methods alongside the standard ones.
     */
    public static class CustomBlockStateDefinitionBuilder {
        /**
         * Sets the geometry using a string identifier.
         * <p>
         * This is a convenience method equivalent to {@code geometry(Geometry.of(identifier))}.
         *
         * @param identifier the geometry identifier (e.g., "geometry.custom_block")
         * @return this builder
         */
        @Tolerate
        public CustomBlockStateDefinitionBuilder geometry(String identifier) {
            this.geometry = Geometry.of(identifier);
            return this;
        }
    }

    /**
     * Represents a material instance for block face rendering.
     * <p>
     * Material instances define how each face of a block is rendered,
     * including texture, transparency, and lighting effects.
     * @param texture          the texture shortname from terrain_texture.json
     * @param renderMethod     controls transparency and culling behavior
     * @param faceDimming      whether faces using the material instance are dimmed by their direction
     * @param ambientOcclusionIntensity the intensity of ambient occlusion which should be in (0.0, 10.0)
     * @param randomUVRotation whether texture randomly rotates based on world position, corresponds to "isotropic" in Bedrock documentation
     * @param textureVariation whether texture variation is enabled
     * @see RenderMethod
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#material-instances">Material Instances</a>
     */
    @Builder
    public record MaterialInstance(
            String texture,
            RenderMethod renderMethod,
            boolean faceDimming,
            float ambientOcclusionIntensity,
            boolean randomUVRotation,
            boolean textureVariation
    ) {
        public MaterialInstance {
            if (texture == null || texture.isBlank()) {
                throw new IllegalArgumentException("Texture cannot be null or blank");
            }
            if (renderMethod == null) {
                renderMethod = RenderMethod.OPAQUE;
            }
            if (ambientOcclusionIntensity < 0 || ambientOcclusionIntensity > 10) {
                throw new IllegalArgumentException("Ambient occlusion intensity must be between 0 and 10");
            }
        }

        /**
         * Creates an opaque material with the standard lighting defaults.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance of(String texture) {
            return new MaterialInstance(texture, RenderMethod.OPAQUE, true, 1.0f, false, false);
        }

        /**
         * Creates a material with a selected render method and the standard lighting defaults.
         *
         * @param texture      the terrain texture short name
         * @param renderMethod the client render method
         * @return the material instance
         */
        public static MaterialInstance of(String texture, RenderMethod renderMethod) {
            return new MaterialInstance(texture, renderMethod, true, 1.0f, false, false);
        }

        /**
         * Creates an opaque material.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance opaque(String texture) {
            return of(texture, RenderMethod.OPAQUE);
        }

        /**
         * Creates a translucent blended material.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance blend(String texture) {
            return of(texture, RenderMethod.BLEND);
        }

        /**
         * Creates an opaque material rendered from both sides.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance doubleSided(String texture) {
            return of(texture, RenderMethod.DOUBLE_SIDED);
        }

        /**
         * Creates a double-sided alpha-test material.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance alphaTest(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST);
        }

        /**
         * Creates a single-sided alpha-test material.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance alphaTestSingleSided(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST_SINGLE_SIDED);
        }

        /**
         * Creates an alpha-test material that becomes opaque at distance.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance alphaTestToOpaque(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST_TO_OPAQUE);
        }

        /**
         * Creates a single-sided alpha-test material that becomes opaque at distance.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance alphaTestSingleSidedToOpaque(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST_SINGLE_SIDED_TO_OPAQUE);
        }

        /**
         * Creates a blended material that becomes opaque at distance.
         *
         * @param texture the terrain texture short name
         * @return the material instance
         */
        public static MaterialInstance blendToOpaque(String texture) {
            return of(texture, RenderMethod.BLEND_TO_OPAQUE);
        }

        public static class MaterialInstanceBuilder {
            private boolean faceDimming = true;
            private float ambientOcclusionIntensity = 1f;

            /**
             * Enables or disables ambient occlusion using its conventional intensity.
             *
             * @param value whether ambient occlusion is enabled
             * @return this builder
             */
            public MaterialInstanceBuilder ambientOcclusion(boolean value) {
                this.ambientOcclusionIntensity = value ? 1.0f : 0.0f;
                return this;
            }
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
     *     .build()
     * }</pre>
     *
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#material-instances">Material Instances</a>
     */
    public static final class Materials {
        /**
         * Special key for the wildcard face that applies to all unspecified faces.
         */
        public static final String ANY_FACE = "*";

        private final Map<String, MaterialInstance> materials;

        private Materials(Map<String, MaterialInstance> materials) {
            this.materials = Collections.unmodifiableMap(new LinkedHashMap<>(materials));
        }

        /**
         * Creates a mutable builder for an immutable material collection.
         *
         * @return the material builder
         */
        public static MaterialsBuilder builder() {
            return new MaterialsBuilder();
        }

        /**
         * Returns the material instances keyed by face or custom material name.
         *
         * @return the immutable material map
         */
        public Map<String, MaterialInstance> materials() {
            return materials;
        }

        /**
         * Checks whether this definition contains any material instances.
         *
         * @return {@code true} when no material instances are defined
         */
        public boolean isEmpty() {
            return materials.isEmpty();
        }

        /**
         * Builds an immutable collection of material instances.
         */
        public static final class MaterialsBuilder {
            private final Map<String, MaterialInstance> materials = new LinkedHashMap<>();

            /**
             * Sets the material for a specific block face.
             *
             * @param face     the block face
             * @param material the material instance
             * @return this builder
             */
            public MaterialsBuilder face(BlockFace face, MaterialInstance material) {
                materials.put(
                        Objects.requireNonNull(face, "face").name().toLowerCase(Locale.ROOT),
                        Objects.requireNonNull(material, "material")
                );
                return this;
            }

            /**
             * Sets the material for a specific block face with default opaque rendering.
             *
             * @param face    the block face
             * @param texture the texture name
             * @return this builder
             */
            public MaterialsBuilder face(BlockFace face, String texture) {
                return face(face, MaterialInstance.of(texture));
            }

            /**
             * Sets the material for all unspecified faces (wildcard).
             *
             * @param material the material instance
             * @return this builder
             */
            public MaterialsBuilder any(MaterialInstance material) {
                materials.put(ANY_FACE, Objects.requireNonNull(material, "material"));
                return this;
            }

            /**
             * Sets the material for all unspecified faces (wildcard) with default opaque rendering.
             *
             * @param texture the texture name
             * @return this builder
             */
            public MaterialsBuilder any(String texture) {
                return any(MaterialInstance.of(texture));
            }

            /**
             * Sets the same material for all horizontal faces (north, south, east, west).
             *
             * @param material the material instance
             * @return this builder
             */
            public MaterialsBuilder sides(MaterialInstance material) {
                for (var face : BlockFace.getHorizontalBlockFaces()) {
                    face(face, material);
                }
                return this;
            }

            /**
             * Sets the same material for all horizontal faces (north, south, east, west).
             *
             * @param texture the texture name
             * @return this builder
             */
            public MaterialsBuilder sides(String texture) {
                return sides(MaterialInstance.of(texture));
            }

            /**
             * Builds the immutable material collection.
             *
             * @return the material definition
             */
            public Materials build() {
                return new Materials(materials);
            }
        }
    }

    /**
     * Transformation applied to block geometry.
     * <p>
     * Rotation values are expressed in degrees and must be multiples of 90.
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
            validateRotation(rx, "rx");
            validateRotation(ry, "ry");
            validateRotation(rz, "rz");
            if (sx == 0) sx = 1.0f;
            if (sy == 0) sy = 1.0f;
            if (sz == 0) sz = 1.0f;
        }

        private static void validateRotation(int rotation, String axis) {
            if (rotation % 90 != 0) {
                throw new IllegalArgumentException(axis + " rotation must be a multiple of 90 degrees");
            }
        }
    }

    /**
     * Geometry component for custom block models.
     * <p>
     * Defines the 3D model (geometry) used for rendering the block, including
     * bone visibility, culling rules, and UV lock settings.
     * <p>
     * A simple identifier is sufficient for ordinary geometry. Bone visibility,
     * culling, and UV lock settings are available for advanced models.
     * <p>
     * Example usage:
     * <pre>{@code
     * // Simple string form
     * Geometry.of("geometry.custom_block")
     *
     * // Object form with bone visibility
     * Geometry.builder()
     *     .identifier("geometry.door")
     *     .boneVisibility("hinge", false)
     *     .boneVisibility("handle", "q.block_state('open') == 1")
     *     .build()
     *
     * // With culling optimization
     * Geometry.builder()
     *     .identifier("geometry.leaves")
     *     .culling("custom:culling.leaves")
     *     .build()
     * }</pre>
     *
     * @param identifier     geometry identifier (e.g., "geometry.custom_block")
     * @param boneVisibility map of bone names to visibility conditions (Boolean or Molang String)
     * @param culling        culling rules identifier (format: namespace:culling.name)
     * @param cullingLayer   culling layer for optimization
     * @param uvLockBones    list of bone names to lock UVs, or null for no UV lock
     * @param uvLockAll      whether to lock UVs for all bones
     * @see <a href="https://wiki.bedrock.dev/blocks/block-components#geometry">minecraft:geometry</a>
     */
    public record Geometry(
            String identifier,
            Map<String, BoneVisibility> boneVisibility,
            String culling,
            String cullingLayer,
            List<String> uvLockBones,
            boolean uvLockAll
    ) {
        public Geometry {
            if (identifier == null || identifier.isEmpty()) {
                throw new IllegalArgumentException("Geometry identifier cannot be null or empty");
            }
            // Ensure immutability
            if (boneVisibility != null) {
                boneVisibility = Collections.unmodifiableMap(new LinkedHashMap<>(boneVisibility));
            }
            if (uvLockBones != null) {
                uvLockBones = List.copyOf(uvLockBones);
            }
        }

        /**
         * Creates a simple geometry with just an identifier.
         *
         * @param identifier the geometry identifier
         * @return a Geometry with only the identifier set
         */
        public static Geometry of(String identifier) {
            return new Geometry(identifier, null, null, null, null, false);
        }

        /**
         * Creates a builder for configuring geometry with advanced properties.
         *
         * @return a new GeometryBuilder
         */
        public static GeometryBuilder builder() {
            return new GeometryBuilder();
        }

        /**
         * Sealed interface for bone visibility values.
         * Supports static boolean, property-based conditions, and raw Molang expressions.
         */
        public sealed interface BoneVisibility permits BoneVisibility.Bool, BoneVisibility.Property, BoneVisibility.Molang {

            /**
             * Creates a static boolean visibility.
             *
             * @param visible whether the bone is visible
             * @return a Bool visibility
             */
            static BoneVisibility of(boolean visible) {
                return visible ? Bool.TRUE : Bool.FALSE;
            }

            /**
             * Creates a property-based visibility condition.
             * The bone is visible when the property equals the specified value.
             *
             * @param property the block property type
             * @param value    the value to compare against
             * @param <T>      the property value type
             * @return a Property visibility
             */
            static <T> BoneVisibility of(BlockPropertyType<T> property, T value) {
                return new Property(property, value);
            }

            /**
             * Creates a property-based visibility for boolean properties.
             * The bone is visible when the boolean property is true.
             *
             * @param property the boolean property type
             * @return a Property visibility
             */
            static BoneVisibility of(BlockPropertyType<Boolean> property) {
                return new Property(property, true);
            }

            /**
             * Creates a raw Molang expression visibility (for advanced use cases).
             *
             * @param molangExpression the Molang expression
             * @return a Molang visibility
             */
            static BoneVisibility ofMolang(String molangExpression) {
                return new Molang(molangExpression);
            }

            /**
             * Converts this bone visibility condition to a Molang expression string.
             *
             * @return the Molang expression
             */
            String toMolang();

            /**
             * Static boolean visibility.
             *
             * @param visible whether the bone is visible
             */
            record Bool(boolean visible) implements BoneVisibility {
                public static final Bool TRUE = new Bool(true);
                public static final Bool FALSE = new Bool(false);

                @Override
                public String toMolang() {
                    return visible ? "true" : "false";
                }
            }

            /**
             * Property-based visibility condition.
             * The bone is visible when the block property equals the specified value.
             *
             * @param property the block property type
             * @param value    the value to compare against
             */
            record Property(BlockPropertyType<?> property, Object value) implements BoneVisibility {
                public Property {
                    Objects.requireNonNull(property, "property cannot be null");
                    Objects.requireNonNull(value, "value cannot be null");
                }

                /**
                 * Generates the Molang expression for this property condition.
                 *
                 * @return the Molang expression string
                 */
                @Override
                public String toMolang() {
                    return MolangUtils.formatPropertyCondition(property, value);
                }
            }

            /**
             * Raw Molang expression visibility (for advanced use cases).
             *
             * @param expression the Molang expression (e.g., "q.block_state('open') == 1")
             */
            record Molang(String expression) implements BoneVisibility {
                public Molang {
                    if (expression == null || expression.isEmpty()) {
                        throw new IllegalArgumentException("Molang expression cannot be null or empty");
                    }
                }

                @Override
                public String toMolang() {
                    return expression;
                }
            }
        }

        /**
         * Builder for creating Geometry instances with fluent API.
         */
        public static final class GeometryBuilder {
            private String identifier;
            private Map<String, BoneVisibility> boneVisibility;
            private String culling;
            private String cullingLayer;
            private List<String> uvLockBones;
            private boolean uvLockAll;

            private GeometryBuilder() {
            }

            /**
             * Sets the geometry identifier (required).
             *
             * @param identifier the geometry identifier
             * @return this builder
             */
            public GeometryBuilder identifier(String identifier) {
                this.identifier = identifier;
                return this;
            }

            /**
             * Sets bone visibility using a static boolean value.
             *
             * @param boneName the name of the bone
             * @param visible  whether the bone is visible
             * @return this builder
             */
            public GeometryBuilder boneVisibility(String boneName, boolean visible) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.of(visible));
                return this;
            }

            /**
             * Sets bone visibility based on a block property value.
             * The bone is visible when the property equals the specified value.
             * <p>
             * Example:
             * <pre>{@code
             * .boneVisibility("handle", BlockPropertyTypes.OPEN_BIT, true)  // visible when open
             * .boneVisibility("stage_2", BlockPropertyTypes.AGE, 2)         // visible when age == 2
             * }</pre>
             *
             * @param boneName the name of the bone
             * @param property the block property type
             * @param value    the value when the bone should be visible
             * @param <T>      the property value type
             * @return this builder
             */
            public <T> GeometryBuilder boneVisibility(String boneName, BlockPropertyType<T> property, T value) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.of(property, value));
                return this;
            }

            /**
             * Sets bone visibility based on a boolean property being true.
             * <p>
             * Example:
             * <pre>{@code
             * .boneVisibility("indicator", BlockPropertyTypes.POWERED_BIT)  // visible when powered
             * }</pre>
             *
             * @param boneName the name of the bone
             * @param property the boolean property type
             * @return this builder
             */
            public GeometryBuilder boneVisibility(String boneName, BlockPropertyType<Boolean> property) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.of(property));
                return this;
            }

            /**
             * Sets bone visibility using a raw Molang expression.
             * <p>
             * This method is for advanced use cases where property-based methods
             * are not sufficient. Prefer using property-based methods when possible.
             *
             * @param boneName   the name of the bone
             * @param expression the Molang expression
             * @return this builder
             */
            public GeometryBuilder boneVisibilityMolang(String boneName, String expression) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.ofMolang(expression));
                return this;
            }

            /**
             * Sets the culling rules identifier.
             *
             * @param culling the culling identifier (format: namespace:culling.name)
             * @return this builder
             */
            public GeometryBuilder culling(String culling) {
                this.culling = culling;
                return this;
            }

            /**
             * Sets the culling layer.
             *
             * @param cullingLayer the culling layer identifier
             * @return this builder
             */
            public GeometryBuilder cullingLayer(String cullingLayer) {
                this.cullingLayer = cullingLayer;
                return this;
            }

            /**
             * Locks UVs for specific bones.
             *
             * @param boneNames the bones to lock UVs for
             * @return this builder
             */
            public GeometryBuilder uvLock(String... boneNames) {
                this.uvLockBones = List.of(boneNames);
                return this;
            }

            /**
             * Locks UVs for specific bones.
             *
             * @param boneNames the bones to lock UVs for
             * @return this builder
             */
            public GeometryBuilder uvLock(List<String> boneNames) {
                this.uvLockBones = boneNames;
                return this;
            }

            /**
             * Locks UVs for all bones.
             *
             * @return this builder
             */
            public GeometryBuilder uvLockAll() {
                this.uvLockAll = true;
                return this;
            }

            /**
             * Builds the Geometry instance.
             *
             * @return the built Geometry
             */
            public Geometry build() {
                return new Geometry(identifier, boneVisibility, culling, cullingLayer, uvLockBones, uvLockAll);
            }
        }
    }
}
