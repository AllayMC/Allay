package org.allaymc.server.item.type;

import com.google.common.base.Preconditions;
import lombok.Builder;
import org.allaymc.api.message.MayContainTrKey;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * {@code CustomItemDefinition} holds client-definition parameters for a custom
 * item independently of a particular protocol's NBT representation. Protocol
 * initialization combines these parameters with gameplay data and components,
 * then applies raw properties and components after derived defaults so explicit
 * entries can replace them.
 *
 * <p>
 * Construction validates texture and cooldown, copies {@code rawProperties} and
 * {@code rawComponents}, and rejects their {@code null} keys or values. The maps retain their
 * NBT value objects rather than deep-copying payloads. Optional render transforms
 * omit missing hands, perspectives, or vectors independently; vector offsets
 * copy their coordinates both during construction and when returned by accessors.
 *
 * @param texture nonblank item-atlas texture short name
 * @param displayName client display name or translation key; {@code null} makes
 *                    protocol encoding use the item identifier as the name
 * @param renderOffsets optional hand and perspective transforms; {@code null}
 *                      omits render-offset data so the client uses its defaults
 * @param foil always-visible enchantment glint flag
 * @param canDestroyInCreative creative block-destruction permission; the generated
 *                             builder uses {@code true} unless the caller
 *                             explicitly overrides this flag
 * @param cooldown optional client cooldown duration in seconds; {@code null}
 *                 makes protocol encoding omit the client cooldown component
 * @param rawProperties explicit item-property compounds; protocol initialization
 *                      applies these entries after derived values so matching
 *                      property keys are replaced
 * @param rawComponents explicit component compounds; protocol initialization
 *                      applies these entries last, allowing replacement of
 *                      derived components including {@code "item_properties"}
 *
 * @author daoge_cmd
 */
@Builder(toBuilder = true)
public record CustomItemDefinition(
        String texture,
        @MayContainTrKey String displayName,
        RenderOffsets renderOffsets,
        boolean foil,
        boolean canDestroyInCreative,
        Integer cooldown,
        Map<String, NbtMap> rawProperties,
        Map<String, NbtMap> rawComponents
) {
    /**
     * Creates validated client-definition parameters with shallow, unmodifiable raw
     * map copies. Texture must be nonblank, and a present cooldown must be
     * nonnegative. A {@code null} raw map becomes empty; supplied maps must contain
     * non-{@code null} names and NBT values, which remain shared.
     *
     * @throws IllegalArgumentException if {@code texture} is blank or {@code null},
     *                                  or {@code cooldown} is negative
     * @throws NullPointerException if a raw map contains a {@code null} name or value
     */
    public CustomItemDefinition {
        if (texture == null || texture.isBlank()) {
            throw new IllegalArgumentException("Texture cannot be null or blank");
        }
        if (cooldown != null && cooldown < 0) {
            throw new IllegalArgumentException("Cooldown cannot be negative");
        }
        rawProperties = immutableCopy(rawProperties);
        rawComponents = immutableCopy(rawComponents);
    }

    /**
     * Returns an unmodifiable shallow map copy retaining source iteration order.
     * A {@code null} source produces an empty map; non-{@code null} sources must
     * contain non-{@code null} keys and values, and the NBT values themselves are
     * not copied.
     *
     * @throws NullPointerException if {@code values} contains a {@code null} key or value
     */
    private static Map<String, NbtMap> immutableCopy(Map<String, NbtMap> values) {
        if (values == null) {
            return Map.of();
        }
        var copy = new LinkedHashMap<String, NbtMap>();
        values.forEach((name, value) -> copy.put(
                Objects.requireNonNull(name, "Raw definition map contains a null name"),
                Objects.requireNonNull(value, "Raw definition map contains a null value")
        ));
        return Collections.unmodifiableMap(copy);
    }

    /**
     * {@code CustomItemDefinitionBuilder} customizes the generated builder's creative
     * destruction default while Lombok supplies the remaining builder members.
     */
    public static class CustomItemDefinitionBuilder {
        /**
         * {@code canDestroyInCreative} starts at {@code true} for generated builder
         * construction. Direct canonical constructor calls still use the boolean
         * explicitly supplied by their caller.
         */
        private boolean canDestroyInCreative = true;
    }

    /**
     * {@code RenderOffsets} groups optional main-hand and offhand render transforms.
     * Protocol encoding omits {@code mainHand} and {@code offHand} independently
     * when they are {@code null}, so callers can configure one hand without
     * supplying synthetic transforms for the other.
     *
     * @param mainHand main-hand render transform group
     * @param offHand offhand render transform group
     */
    @Builder
    public record RenderOffsets(Hand mainHand, Hand offHand) {
        /**
         * Creates render transforms scaled from a square texture's width in pixels.
         * The width must be positive and divisible by {@code 16}; the relative model
         * scale is the texture width divided by that base size.
         *
         * @throws IllegalArgumentException if {@code textureSize} is nonpositive or not divisible by {@code 16}
         */
        public static RenderOffsets textureSize(int textureSize) {
            Preconditions.checkArgument(textureSize > 0 && textureSize % 16 == 0,
                    "Texture size must be positive and divisible by 16");
            // Sixteen pixels define the base texture scale used by the display
            // ratios, so convert texture width before applying inverse scaling.
            return scale(textureSize / 16f);
        }

        /**
         * Creates the configured hand transforms using inverse model scaling.
         * The scale must be positive; validation imposes no finite upper bound. Larger
         * scales produce smaller display vectors, and the main-hand first-person vector
         * uses an additional reduction relative to the common horizontal factor.
         *
         * @throws IllegalArgumentException if {@code scale} is not greater than {@code 0}
         */
        public static RenderOffsets scale(float scale) {
            Preconditions.checkArgument(scale > 0, "Scale must be greater than 0");
            // Inverse ratios reduce display vectors as texture models grow;
            // main-hand first-person uses the extra 2.4 factor to stay smaller
            // than the other hand and perspective transforms.
            float scale1 = (float) (0.075 / scale);
            float scale2 = (float) (0.125 / scale);
            float scale3 = (float) (0.075 / (scale * 2.4f));
            return builder()
                    .mainHand(Hand.builder()
                            .firstPerson(Offset.builder().scale(new Vector3f(scale3, scale3, scale3)).build())
                            .thirdPerson(Offset.builder().scale(new Vector3f(scale1, scale2, scale1)).build())
                            .build())
                    .offHand(Hand.builder()
                            .firstPerson(Offset.builder().scale(new Vector3f(scale1, scale2, scale1)).build())
                            .thirdPerson(Offset.builder().scale(new Vector3f(scale1, scale2, scale1)).build())
                            .build())
                    .build();
        }

        /**
         * {@code Hand} groups optional first-person and third-person offsets for one hand.
         * Protocol encoding omits {@code firstPerson} and {@code thirdPerson}
         * independently when they are {@code null}, preserving one perspective
         * without requiring a replacement for the absent one.
         *
         * @param firstPerson first-person perspective transform
         * @param thirdPerson third-person perspective transform
         */
        @Builder
        public record Hand(Offset firstPerson, Offset thirdPerson) {
        }

        /**
         * {@code Offset} stores optional {@code position}, {@code rotation}, and {@code scale}
         * vectors for one
         * render perspective. Construction and accessors copy non-{@code null} vectors,
         * so callers cannot change stored coordinates through source vectors or accessor
         * results. Protocol encoding omits {@code null} vectors independently.
         *
         * @param position positional offset in blocks
         * @param rotation rotation angles in degrees
         * @param scale per-axis model scale factors
         */
        @Builder
        public record Offset(Vector3fc position, Vector3fc rotation, Vector3fc scale) {
            /**
             * Creates a render offset with copied optional vector coordinates.
             * Each {@code null} vector remains absent; each non-{@code null} vector is
             * copied into separate storage without validating its coordinate values.
             */
            public Offset {
                position = copy(position);
                rotation = copy(rotation);
                scale = copy(scale);
            }

            /**
             * Returns a copy of the optional position vector.
             * A {@code null} value remains absent; changing a returned mutable vector does
             * not change the coordinates retained by this offset.
             */
            @Override
            public Vector3fc position() {
                return copy(position);
            }

            /**
             * Returns a copy of the optional rotation vector.
             * A {@code null} value remains absent; changing a returned mutable vector does
             * not change the coordinates retained by this offset.
             */
            @Override
            public Vector3fc rotation() {
                return copy(rotation);
            }

            /**
             * Returns a copy of the optional per-axis scale vector.
             * A {@code null} value remains absent; changing a returned mutable vector does
             * not change the coordinates retained by this offset.
             */
            @Override
            public Vector3fc scale() {
                return copy(scale);
            }

            /**
             * Copies non-{@code null} vector coordinates into a new mutable vector.
             * A {@code null} input returns {@code null}, allowing construction and accessors
             * to preserve omission while preventing mutable vector aliases.
             */
            private static Vector3fc copy(Vector3fc value) {
                return value == null ? null : new Vector3f(value);
            }
        }
    }
}
