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
 * Protocol-independent client definition for a custom item.
 * <p>
 * The item type stores this parameter object as-is. Each protocol later combines it with the item type's gameplay
 * data, tags, and components to produce the Bedrock item definition for that protocol.
 *
 * @param texture              texture short name used by the item atlas
 * @param displayName          displayed item name, or {@code null} to use the item identifier
 * @param renderOffsets        first- and third-person render offsets, or {@code null} for client defaults
 * @param foil                 whether the item always displays an enchantment glint
 * @param canDestroyInCreative whether the item can destroy blocks in creative mode
 * @param cooldown             cooldown duration in seconds, or {@code null} for no cooldown
 * @param rawProperties        raw item properties used as an explicit extension point
 * @param rawComponents        raw Bedrock components used as an explicit extension point
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
     * Customizes Lombok's builder default for creative block destruction.
     */
    public static class CustomItemDefinitionBuilder {
        private boolean canDestroyInCreative = true;
    }

    /**
     * Render offsets for custom item display at different viewpoints.
     *
     * @param mainHand offsets for the main hand, or {@code null}
     * @param offHand  offsets for the off hand, or {@code null}
     */
    @Builder
    public record RenderOffsets(Hand mainHand, Hand offHand) {
        /**
         * Creates offsets scaled for a square texture size.
         *
         * @param textureSize texture width in pixels; must be positive and divisible by 16
         * @return the scaled offsets
         */
        public static RenderOffsets textureSize(int textureSize) {
            Preconditions.checkArgument(textureSize > 0 && textureSize % 16 == 0,
                    "Texture size must be positive and divisible by 16");
            return scale(textureSize / 16f);
        }

        /**
         * Creates the conventional Bedrock render offsets for a model scale.
         *
         * @param scale model scale; must be positive
         * @return the scaled offsets
         */
        public static RenderOffsets scale(float scale) {
            Preconditions.checkArgument(scale > 0, "Scale must be greater than 0");
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
         * Hand-specific render offsets.
         *
         * @param firstPerson offset for first-person view, or {@code null}
         * @param thirdPerson offset for third-person view, or {@code null}
         */
        @Builder
        public record Hand(Offset firstPerson, Offset thirdPerson) {
        }

        /**
         * Position, rotation, and scale applied to an item model.
         *
         * @param position position offset in blocks, or {@code null}
         * @param rotation rotation in degrees, or {@code null}
         * @param scale    scale factors, or {@code null}
         */
        @Builder
        public record Offset(Vector3fc position, Vector3fc rotation, Vector3fc scale) {
            public Offset {
                position = copy(position);
                rotation = copy(rotation);
                scale = copy(scale);
            }

            @Override
            public Vector3fc position() {
                return copy(position);
            }

            @Override
            public Vector3fc rotation() {
                return copy(rotation);
            }

            @Override
            public Vector3fc scale() {
                return copy(scale);
            }

            private static Vector3fc copy(Vector3fc value) {
                return value == null ? null : new Vector3f(value);
            }
        }
    }
}
