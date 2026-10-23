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
 * {@code CustomBlockStateDefinition} represents a custom block state's client
 * appearance independently of wire encoding. A type can share one definition across
 * states or resolve definitions during building; protocol implementations later
 * combine this appearance with physical metadata from {@link BlockStateData}.
 *
 * @param geometry optional geometry component; {@code null} leaves geometry
 * unspecified
 * @param materials optional material collection; {@code null} leaves materials
 * unspecified
 * @param transformation optional model transform; {@code null} leaves the transform
 * unspecified
 * @param displayName optional display label; {@code null} leaves the display-name
 * component unspecified
 * @param rawComponents unmodifiable raw component snapshot; {@code null} selects an
 * empty map, while NBT payload references remain shared
 *
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
     * {@code DEFAULT} supplies an appearance definition with every optional
     * component absent and an empty raw component map.
     */
    public static final CustomBlockStateDefinition DEFAULT = CustomBlockStateDefinition.builder().build();

    /**
     * Creates an appearance definition and snapshots its raw component map in
     * insertion order. A {@code null} map becomes empty; supplied names and
     * payloads must be non-{@code null}, while the other component references are
     * retained directly.
     */
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
     * Creates a definition using one opaque wildcard material with standard
     * lighting defaults. The texture must be nonblank and non-{@code null};
     * geometry, transformation, and display name remain unspecified.
     */
    public static CustomBlockStateDefinition ofTexture(String texture) {
        return builder().materials(Materials.builder().any(texture).build()).build();
    }

    /**
     * {@code RenderMethod} identifies the client render-mode token stored in a
     * material instance.
     */
    @Getter
    public enum RenderMethod {
        /**
         * Opaque client render mode.
         */
        OPAQUE("opaque"),
        /**
         * Blended client render mode.
         */
        BLEND("blend"),
        /**
         * Double-sided client render mode.
         */
        DOUBLE_SIDED("double_sided"),
        /**
         * Alpha-test client render mode.
         */
        ALPHA_TEST("alpha_test"),
        /**
         * Single-sided alpha-test render mode.
         */
        ALPHA_TEST_SINGLE_SIDED("alpha_test_single_sided"),
        /**
         * Alpha-test-to-opaque client render mode.
         */
        ALPHA_TEST_TO_OPAQUE("alpha_test_to_opaque"),
        /**
         * Single-sided alpha-test-to-opaque render mode.
         */
        ALPHA_TEST_SINGLE_SIDED_TO_OPAQUE("alpha_test_single_sided_to_opaque"),
        /**
         * Blend-to-opaque client render mode.
         */
        BLEND_TO_OPAQUE("blend_to_opaque");

        /**
         * Client render-mode serialization token.
         */
        private final String id;

        /**
         * Creates a render-method constant retaining its client serialization
         * token.
         */
        RenderMethod(String id) {
            this.id = id;
        }
    }

    /**
     * {@code CustomBlockStateDefinitionBuilder} adds geometry shorthand to the
     * Lombok-generated appearance builder.
     */
    public static class CustomBlockStateDefinitionBuilder {
        /**
         * Replaces geometry with a simple definition using the supplied identifier.
         * The identifier must be nonempty and non-{@code null}; all advanced
         * geometry options remain unspecified in the replacement.
         */
        @Tolerate
        public CustomBlockStateDefinitionBuilder geometry(String identifier) {
            this.geometry = Geometry.of(identifier);
            return this;
        }
    }

    /**
     * {@code MaterialInstance} represents one texture and its client rendering and
     * lighting settings. The constructor validates texture presence and
     * ambient-occlusion bounds, while convenience factories select opaque rendering
     * and conventional lighting settings unless another render mode is supplied.
     *
     * @param texture terrain texture short name
     * @param renderMethod client rendering mode; {@code null} selects {@code
     * OPAQUE}
     * @param faceDimming directional face-dimming flag
     * @param ambientOcclusionIntensity ambient-occlusion intensity, inclusive range
     * {@code 0} to {@code 10}
     * @param randomUVRotation position-dependent texture-rotation flag
     * @param textureVariation texture variation enablement flag
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
        /**
         * Creates a material with a required nonblank texture and an optional
         * render method. A {@code null} method selects {@code OPAQUE}; intensities
         * below {@code 0} or above {@code 10} are rejected, while {@code NaN} is
         * not rejected by those comparisons.
         */
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
         * Creates an opaque material with face dimming and ambient-occlusion
         * intensity {@code 1}. Random UV rotation and texture variation are
         * disabled, and the required texture must be nonblank and non-{@code null}.
         */
        public static MaterialInstance of(String texture) {
            return new MaterialInstance(texture, RenderMethod.OPAQUE, true, 1.0f, false, false);
        }

        /**
         * Creates a material with the selected render mode and standard lighting
         * defaults. A {@code null} mode selects {@code OPAQUE}; face dimming is
         * enabled, ambient-occlusion intensity is {@code 1}, and texture variation
         * and random UV rotation are disabled.
         */
        public static MaterialInstance of(String texture, RenderMethod renderMethod) {
            return new MaterialInstance(texture, renderMethod, true, 1.0f, false, false);
        }

        /**
         * Creates a {@code OPAQUE} material with face dimming and ambient-occlusion
         * intensity {@code 1}. The texture must be nonblank and non-{@code null};
         * random UV rotation and texture variation are disabled.
         */
        public static MaterialInstance opaque(String texture) {
            return of(texture, RenderMethod.OPAQUE);
        }

        /**
         * Creates a {@code BLEND} material with face dimming and ambient-occlusion
         * intensity {@code 1}. The texture must be nonblank and non-{@code null};
         * random UV rotation and texture variation are disabled.
         */
        public static MaterialInstance blend(String texture) {
            return of(texture, RenderMethod.BLEND);
        }

        /**
         * Creates a {@code DOUBLE_SIDED} material with face dimming and
         * ambient-occlusion intensity {@code 1}. The texture must be nonblank and
         * non-{@code null}; random UV rotation and texture variation are disabled.
         */
        public static MaterialInstance doubleSided(String texture) {
            return of(texture, RenderMethod.DOUBLE_SIDED);
        }

        /**
         * Creates a {@code ALPHA_TEST} material with face dimming and
         * ambient-occlusion intensity {@code 1}. The texture must be nonblank and
         * non-{@code null}; random UV rotation and texture variation are disabled.
         */
        public static MaterialInstance alphaTest(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST);
        }

        /**
         * Creates a {@code ALPHA_TEST_SINGLE_SIDED} material with face dimming and
         * ambient-occlusion intensity {@code 1}. The texture must be nonblank and
         * non-{@code null}; random UV rotation and texture variation are disabled.
         */
        public static MaterialInstance alphaTestSingleSided(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST_SINGLE_SIDED);
        }

        /**
         * Creates a {@code ALPHA_TEST_TO_OPAQUE} material with face dimming and
         * ambient-occlusion intensity {@code 1}. The texture must be nonblank and
         * non-{@code null}; random UV rotation and texture variation are disabled.
         */
        public static MaterialInstance alphaTestToOpaque(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST_TO_OPAQUE);
        }

        /**
         * Creates a {@code ALPHA_TEST_SINGLE_SIDED_TO_OPAQUE} material with face
         * dimming and ambient-occlusion intensity {@code 1}. The texture must be
         * nonblank and non-{@code null}; random UV rotation and texture variation
         * are disabled.
         */
        public static MaterialInstance alphaTestSingleSidedToOpaque(String texture) {
            return of(texture, RenderMethod.ALPHA_TEST_SINGLE_SIDED_TO_OPAQUE);
        }

        /**
         * Creates a {@code BLEND_TO_OPAQUE} material with face dimming and
         * ambient-occlusion intensity {@code 1}. The texture must be nonblank and
         * non-{@code null}; random UV rotation and texture variation are disabled.
         */
        public static MaterialInstance blendToOpaque(String texture) {
            return of(texture, RenderMethod.BLEND_TO_OPAQUE);
        }

        /**
         * {@code MaterialInstanceBuilder} supplies lighting defaults and boolean
         * ambient-occlusion shorthand for material construction.
         */
        public static class MaterialInstanceBuilder {
            /**
             * Directional face-dimming builder default.
             */
            private boolean faceDimming = true;
            /**
             * Ambient-occlusion builder intensity default.
             */
            private float ambientOcclusionIntensity = 1f;

            /**
             * Sets ambient-occlusion intensity to {@code 1} when enabled or {@code
             * 0} otherwise, returning this builder.
             */
            public MaterialInstanceBuilder ambientOcclusion(boolean value) {
                this.ambientOcclusionIntensity = value ? 1.0f : 0.0f;
                return this;
            }
        }
    }

    /**
     * {@code Materials} represents a snapshot of materials keyed by face names or
     * the wildcard face token. Its builder can assign one material to all
     * unspecified faces, override individual faces, or assign the same material to
     * the four horizontal faces.
     */
    public static final class Materials {
        /**
         * {@code ANY_FACE} is the wildcard material key used by the builder for
         * faces without an explicit material assignment.
         */
        public static final String ANY_FACE = "*";

        /**
         * {@code materials} holds an unmodifiable insertion-ordered map copied at
         * construction. Its immutable material records remain shared, and later
         * builder changes do not alter the copied entries.
         */
        private final Map<String, MaterialInstance> materials;

        /**
         * Creates a material collection with a shallow insertion-ordered copy of
         * the supplied map. Later structural changes to the source map cannot
         * change this collection, while the immutable material records remain
         * shared values.
         */
        private Materials(Map<String, MaterialInstance> materials) {
            this.materials = Collections.unmodifiableMap(new LinkedHashMap<>(materials));
        }

        /**
         * Creates an empty mutable builder for a material-collection snapshot.
         */
        public static MaterialsBuilder builder() {
            return new MaterialsBuilder();
        }

        /**
         * Returns the unmodifiable material map captured when this collection was
         * built. Callers cannot add or remove entries through this map, and later
         * builder changes do not alter its fixed assignments.
         */
        public Map<String, MaterialInstance> materials() {
            return materials;
        }

        /**
         * Returns whether this collection contains no explicit or wildcard material
         * entries.
         */
        public boolean isEmpty() {
            return materials.isEmpty();
        }

        /**
         * {@code MaterialsBuilder} accumulates face assignments before copying them
         * into a material collection.
         */
        public static final class MaterialsBuilder {
            /**
             * Mutable insertion-ordered face assignments.
             */
            private final Map<String, MaterialInstance> materials = new LinkedHashMap<>();

            /**
             * Sets or replaces the material for the supplied face and returns this
             * builder. Both inputs must be non-{@code null}, and the face name is
             * serialized using locale-independent lowercase spelling.
             */
            public MaterialsBuilder face(BlockFace face, MaterialInstance material) {
                materials.put(
                        Objects.requireNonNull(face, "face").name().toLowerCase(Locale.ROOT),
                        Objects.requireNonNull(material, "material")
                );
                return this;
            }

            /**
             * Sets an opaque material with standard lighting for the supplied face.
             * The face must be non-{@code null}, and the texture must be nonblank
             * and non-{@code null}; any prior face assignment is replaced.
             */
            public MaterialsBuilder face(BlockFace face, String texture) {
                return face(face, MaterialInstance.of(texture));
            }

            /**
             * Sets or replaces the wildcard material used for unspecified faces and
             * returns this builder. The material must be non-{@code null}, and
             * existing explicit face entries remain present in the builder.
             */
            public MaterialsBuilder any(MaterialInstance material) {
                materials.put(ANY_FACE, Objects.requireNonNull(material, "material"));
                return this;
            }

            /**
             * Sets an opaque wildcard material with standard lighting and returns
             * this builder. The texture must be nonblank and non-{@code null};
             * explicit face assignments remain available alongside the wildcard
             * entry.
             */
            public MaterialsBuilder any(String texture) {
                return any(MaterialInstance.of(texture));
            }

            /**
             * Sets the same non-{@code null} material for every horizontal face and
             * returns this builder. Existing horizontal entries are replaced, while
             * up, down, and wildcard assignments retain their current values.
             */
            public MaterialsBuilder sides(MaterialInstance material) {
                for (var face : BlockFace.getHorizontalBlockFaces()) {
                    face(face, material);
                }
                return this;
            }

            /**
             * Sets an opaque material with standard lighting for every horizontal
             * face. The texture must be nonblank and non-{@code null}, and up,
             * down, and wildcard assignments retain their current values.
             */
            public MaterialsBuilder sides(String texture) {
                return sides(MaterialInstance.of(texture));
            }

            /**
             * Builds a material collection with an unmodifiable copy of the current
             * assignments. The builder remains reusable, and later assignment
             * changes do not affect the collection already returned by this call.
             */
            public Materials build() {
                return new Materials(materials);
            }
        }
    }

    /**
     * {@code Transformation} represents model rotation, scale, and translation for
     * protocol encoding. Rotations accept any integer multiple of ninety degrees,
     * including negative values; the compact constructor replaces zero scale
     * components with one so an omitted builder scale produces an unscaled model.
     *
     * @param rx X-axis rotation, degrees
     * @param ry Y-axis rotation, degrees
     * @param rz Z-axis rotation, degrees
     * @param sx X-axis scale factor
     * @param sy Y-axis scale factor
     * @param sz Z-axis scale factor
     * @param tx X-axis translation, blocks
     * @param ty Y-axis translation, blocks
     * @param tz Z-axis translation, blocks
     */
    @Builder
    public record Transformation(
            int rx, int ry, int rz,
            float sx, float sy, float sz,
            float tx, float ty, float tz
    ) {
        /**
         * Creates a transform after requiring every rotation to be a multiple of
         * ninety degrees. Each zero scale component becomes {@code 1}; nonzero
         * scales and translations are retained without additional range or
         * finiteness checks.
         */
        public Transformation {
            validateRotation(rx, "rx");
            validateRotation(ry, "ry");
            validateRotation(rz, "rz");
            if (sx == 0) sx = 1.0f;
            if (sy == 0) sy = 1.0f;
            if (sz == 0) sz = 1.0f;
        }

        /**
         * Validates that one rotation is an integer multiple of ninety degrees.
         * Negative multiples and rotations beyond one turn are accepted, while
         * other values throw {@code IllegalArgumentException} naming the supplied
         * axis.
         */
        private static void validateRotation(int rotation, String axis) {
            if (rotation % 90 != 0) {
                throw new IllegalArgumentException(axis + " rotation must be a multiple of 90 degrees");
            }
        }
    }

    /**
     * {@code Geometry} represents a client model identifier with optional bone
     * visibility, culling, and UV-lock settings. The constructor snapshots supplied
     * collections but retains their element references. Protocol encoding gives
     * {@code uvLockAll} precedence over {@code uvLockBones}; a caller can retain
     * both settings without changing that encoding choice.
     *
     * @param identifier required nonempty geometry identifier
     * @param boneVisibility optional bone-condition snapshot; supplied entries are
     * not checked for {@code null} keys or values
     * @param culling optional culling rules identifier
     * @param cullingLayer optional culling layer identifier
     * @param uvLockBones optional unmodifiable bone-name list; supplied lists must
     * contain no {@code null} elements
     * @param uvLockAll all-bones UV lock; protocol encoding prefers this flag over
     * the bone-name list when {@code true}
     */
    public record Geometry(
            String identifier,
            Map<String, BoneVisibility> boneVisibility,
            String culling,
            String cullingLayer,
            List<String> uvLockBones,
            boolean uvLockAll
    ) {
        /**
         * Creates geometry with a required nonempty identifier and snapshots
         * optional collections. The identifier is not trimmed; bone-map entries
         * remain unvalidated, while copying a UV-lock list rejects {@code null}
         * elements.
         */
        public Geometry {
            if (identifier == null || identifier.isEmpty()) {
                throw new IllegalArgumentException("Geometry identifier cannot be null or empty");
            }
            if (boneVisibility != null) {
                boneVisibility = Collections.unmodifiableMap(new LinkedHashMap<>(boneVisibility));
            }
            if (uvLockBones != null) {
                uvLockBones = List.copyOf(uvLockBones);
            }
        }

        /**
         * Creates simple geometry using only the supplied required identifier. The
         * identifier must be nonempty and non-{@code null}; bone visibility,
         * culling, and bone-list UV locking remain unspecified, and all-bones UV
         * locking is disabled.
         */
        public static Geometry of(String identifier) {
            return new Geometry(identifier, null, null, null, null, false);
        }

        /**
         * Creates an empty mutable geometry builder with every optional setting
         * absent. Callers must select a nonempty, non-{@code null} identifier
         * before building; the builder checks that requirement when it constructs
         * the geometry.
         */
        public static GeometryBuilder builder() {
            return new GeometryBuilder();
        }

        /**
         * {@code BoneVisibility} represents a static, property-based, or raw Molang
         * bone-visibility condition.
         */
        public sealed interface BoneVisibility permits BoneVisibility.Bool, BoneVisibility.Property, BoneVisibility.Molang {

            /**
             * Returns the shared visibility condition for the supplied boolean
             * value.
             */
            static BoneVisibility of(boolean visible) {
                return visible ? Bool.TRUE : Bool.FALSE;
            }

            /**
             * Creates a property-equality visibility condition retaining the
             * supplied domain and raw value. Both inputs must be non-{@code null};
             * formatting later casts the value by property category without
             * checking domain membership.
             */
            static <T> BoneVisibility of(BlockPropertyType<T> property, T value) {
                return new Property(property, value);
            }

            /**
             * Creates a visibility condition requiring the supplied boolean
             * property to equal {@code true}. The property must be non-{@code
             * null}, and conversion to a client expression is deferred until {@code
             * toMolang}.
             */
            static BoneVisibility of(BlockPropertyType<Boolean> property) {
                return new Property(property, true);
            }

            /**
             * Creates a condition retaining the supplied nonempty raw Molang
             * expression. {@code null} and empty strings throw {@code
             * IllegalArgumentException}, while whitespace and expression syntax are
             * not validated by this model.
             */
            static BoneVisibility ofMolang(String molangExpression) {
                return new Molang(molangExpression);
            }

            /**
             * Returns the client Molang expression represented by this visibility
             * condition.
             */
            String toMolang();

            /**
             * {@code Bool} represents a constant truth value used for bone
             * visibility.
             *
             * @param visible constant bone visibility flag
             */
            record Bool(boolean visible) implements BoneVisibility {
                /**
                 * {@code TRUE} is the shared visible condition returned by the
                 * boolean factory instead of allocating repeated {@code true} conditions.
                 */
                public static final Bool TRUE = new Bool(true);
                /**
                 * {@code FALSE} is the shared hidden condition returned by the
                 * boolean factory instead of allocating repeated {@code false} conditions.
                 */
                public static final Bool FALSE = new Bool(false);

                /**
                 * Returns the literal Molang truth token matching the stored
                 * visibility flag.
                 */
                @Override
                public String toMolang() {
                    return visible ? "true" : "false";
                }
            }

            /**
             * {@code Property} represents bone visibility when one property equals
             * a retained raw value.
             *
             * @param property property domain reference
             * @param value required raw comparison value
             */
            record Property(BlockPropertyType<?> property, Object value) implements BoneVisibility {
                /**
                 * Creates a property comparison after rejecting a {@code null}
                 * property or value. The constructor retains both references
                 * without checking domain membership, and formatting later casts
                 * the value according to the property's category.
                 */
                public Property {
                    Objects.requireNonNull(property, "property cannot be null");
                    Objects.requireNonNull(value, "value cannot be null");
                }

                /**
                 * Formats the retained comparison through {@code MolangUtils} using
                 * the property's category. That helper casts the raw value to the
                 * category's Java type without validating membership, so
                 * incompatible runtime types can fail during formatting.
                 */
                @Override
                public String toMolang() {
                    return MolangUtils.formatPropertyCondition(property, value);
                }
            }

            /**
             * {@code Molang} represents an unchecked raw expression used for bone
             * visibility.
             *
             * @param expression nonempty raw expression text
             */
            record Molang(String expression) implements BoneVisibility {
                /**
                 * Creates a raw visibility expression after rejecting {@code null}
                 * or empty text. The constructor retains whitespace and does not
                 * parse the expression, leaving syntax interpretation to the
                 * client.
                 */
                public Molang {
                    if (expression == null || expression.isEmpty()) {
                        throw new IllegalArgumentException("Molang expression cannot be null or empty");
                    }
                }

                /**
                 * Returns the raw expression text retained without parsing or
                 * normalization.
                 */
                @Override
                public String toMolang() {
                    return expression;
                }
            }
        }

        /**
         * {@code GeometryBuilder} accumulates model identity, visibility, culling,
         * and UV-lock configuration for a geometry snapshot.
         */
        public static final class GeometryBuilder {
            /**
             * Requested client geometry identifier.
             */
            private String identifier;
            /**
             * Mutable bone visibility assignments.
             */
            private Map<String, BoneVisibility> boneVisibility;
            /**
             * Optional culling rules identifier.
             */
            private String culling;
            /**
             * Optional culling layer identifier.
             */
            private String cullingLayer;
            /**
             * {@code uvLockBones} retains a supplied list until build copies it. A
             * {@code true} {@code uvLockAll} flag takes precedence during protocol encoding
             * without clearing this list.
             */
            private List<String> uvLockBones;
            /**
             * {@code uvLockAll} requests UV locking for every bone. Once enabled,
             * this builder has no reset method, and protocol encoding prefers it
             * over a bone list.
             */
            private boolean uvLockAll;

            /**
             * Creates an empty geometry builder with every optional setting
             * initially absent.
             */
            private GeometryBuilder() {
            }

            /**
             * Stores the requested geometry identifier for validation during build
             * and returns this builder.
             */
            public GeometryBuilder identifier(String identifier) {
                this.identifier = identifier;
                return this;
            }

            /**
             * Sets or replaces a bone's constant visibility and returns this
             * geometry builder.
             */
            public GeometryBuilder boneVisibility(String boneName, boolean visible) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.of(visible));
                return this;
            }

            /**
             * Sets or replaces a bone's property-equality condition and returns
             * this builder. The property and comparison value must be non-{@code
             * null}; category casts occur during formatting, without validation
             * against the property's value domain.
             */
            public <T> GeometryBuilder boneVisibility(String boneName, BlockPropertyType<T> property, T value) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.of(property, value));
                return this;
            }

            /**
             * Sets or replaces a bone's condition requiring a boolean property to
             * be {@code true}. The property must be non-{@code null}, and the
             * resulting expression is produced later by the protocol consumer.
             */
            public GeometryBuilder boneVisibility(String boneName, BlockPropertyType<Boolean> property) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.of(property));
                return this;
            }

            /**
             * Sets or replaces a bone's raw visibility expression and returns this
             * builder. The expression must be nonempty and non-{@code null}; the
             * model does not check its Molang syntax.
             */
            public GeometryBuilder boneVisibilityMolang(String boneName, String expression) {
                if (this.boneVisibility == null) {
                    this.boneVisibility = new HashMap<>();
                }
                this.boneVisibility.put(boneName, BoneVisibility.ofMolang(expression));
                return this;
            }

            /**
             * Stores the optional culling rules identifier and returns this
             * geometry builder.
             */
            public GeometryBuilder culling(String culling) {
                this.culling = culling;
                return this;
            }

            /**
             * Stores the optional culling layer identifier and returns this
             * geometry builder.
             */
            public GeometryBuilder cullingLayer(String cullingLayer) {
                this.cullingLayer = cullingLayer;
                return this;
            }

            /**
             * Stores an unmodifiable list of the supplied UV-lock bone names. The
             * array and its elements must be non-{@code null}; an already enabled
             * all-bones flag still takes precedence during encoding.
             */
            public GeometryBuilder uvLock(String... boneNames) {
                this.uvLockBones = List.of(boneNames);
                return this;
            }

            /**
             * Retains the supplied UV-lock list until build snapshots it, and
             * returns this builder. A {@code null} list leaves bone-list locking
             * absent; {@code null} elements fail during build, and all-bones locking still
             * takes precedence.
             */
            public GeometryBuilder uvLock(List<String> boneNames) {
                this.uvLockBones = boneNames;
                return this;
            }

            /**
             * Enables UV locking for every bone and returns this builder. Protocol
             * encoding prefers this flag over any stored bone-name list, and this
             * builder exposes no operation to disable the flag.
             */
            public GeometryBuilder uvLockAll() {
                this.uvLockAll = true;
                return this;
            }

            /**
             * Builds geometry after validating the identifier and copying optional
             * collections. The builder remains reusable, and later changes to its
             * bone map or retained UV-lock list do not alter the built collection
             * snapshots.
             */
            public Geometry build() {
                return new Geometry(identifier, boneVisibility, culling, cullingLayer, uvLockBones, uvLockAll);
            }
        }
    }
}
