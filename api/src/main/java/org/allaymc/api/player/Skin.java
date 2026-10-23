package org.allaymc.api.player;

import com.google.gson.JsonParser;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

/**
 * {@code Skin} represents a player's skin textures, geometry, animations, and
 * Persona metadata. The record retains supplied image arrays and collection
 * references rather than making a deep immutable copy; callers must coordinate
 * mutations while another consumer converts or sends the skin.
 *
 * @param skinId client skin identifier
 * @param playFabId skin Marketplace PlayFab identifier
 * @param skinResourcePatch JSON geometry resource patch
 * @param skinData skin texture and RGBA bytes
 * @param animations {@code animations} retains the supplied animation list. Callers
 * must preserve its elements while another consumer reads this skin.
 * @param capeData cape texture and RGBA bytes
 * @param skinGeometry JSON skin geometry payload
 * @param animationData additional skin animation metadata
 * @param geometryDataEngineVersion geometry data engine version
 * @param premiumSkin Marketplace premium skin flag
 * @param personaSkin Persona character creator flag
 * @param personaCapeOnClassicSkin Persona cape on classic skin flag
 * @param primaryUser primary local user flag
 * @param capeId client cape resource identifier
 * @param fullId combined skin and cape identifier
 * @param skinColor legacy hexadecimal skin color
 * @param armSize arm width token such as {@code "wide"} or {@code "slim"}
 * @param personaPieces {@code personaPieces} retains the supplied Persona piece
 * list. This record does not copy its mutable collection storage.
 * @param pieceTintColors {@code pieceTintColors} retains the supplied tint list and
 * its nested color lists. Callers must coordinate changes to those shared
 * collections.
 * @param overrideAppearance flag requesting override of the locally equipped
 * appearance
 *
 * @author gophertunnel | daoge_cmd
 */
@Builder(toBuilder = true)
public record Skin(
        String skinId, String playFabId, String skinResourcePatch, ImageData skinData, List<AnimationData> animations,
        ImageData capeData, String skinGeometry, String animationData, String geometryDataEngineVersion,
        boolean premiumSkin, boolean personaSkin, boolean personaCapeOnClassicSkin, boolean primaryUser, String capeId,
        String fullId, String skinColor, String armSize, List<PersonaPieces> personaPieces,
        List<PersonaPieceTintColor> pieceTintColors, boolean overrideAppearance
) {

    /**
     * {@code ARM_SIZE_WIDE} names the wide arm model variant used by skin metadata
     * and protocol conversion; it does not change geometry itself.
     */
    public static final String ARM_SIZE_WIDE = "wide";
    /**
     * {@code ARM_SIZE_SLIM} names the slim arm model variant used by skin metadata
     * and protocol conversion; it does not change geometry itself.
     */
    public static final String ARM_SIZE_SLIM = "slim";

    /**
     * Checks the skin identifier, minimum image dimensions and bytes, and
     * resource-patch structure. The check does not validate every record component;
     * malformed JSON syntax or a missing image byte array can still throw instead of
     * returning {@code false}.
     */
    public boolean isValid() {
        var isValidSkin = skinId != null && !skinId.trim().isEmpty() && skinData != null && skinData.isValid();
        var isValidResourcePatch = skinResourcePatch != null && validateSkinResourcePatch(skinResourcePatch);
        return isValidSkin && isValidResourcePatch;
    }

    /**
     * Checks whether the JSON patch contains a string-valued
     * {@code "geometry.default"} entry. Missing or incompatible JSON structure
     * returns {@code false}; JSON parsing exceptions outside the explicitly caught
     * structural failures propagate to the caller.
     */
    private static boolean validateSkinResourcePatch(String skinResourcePatch) {
        try {
            var object = JsonParser.parseString(skinResourcePatch).getAsJsonObject();
            var geometry = object.get("geometry").getAsJsonObject();
            return geometry.has("default") && geometry.get("default").getAsJsonPrimitive().isString();
        } catch (ClassCastException | NullPointerException | IllegalStateException e) {
            // Missing objects or incompatible JSON node shapes mean the patch cannot identify a default
            // geometry; return a failed validation for those structural cases.
            return false;
        }
    }

    /**
     * {@code ImageData} holds image dimensions and a shared RGBA byte array.
     * Construction does not validate consistency between dimensions and byte length,
     * and {@code from} recognizes only the supported canonical skin byte counts.
     *
     * @param width image width in pixels
     * @param height image height in pixels
     * @param data shared RGBA byte array; consumers must preserve its bytes while
     * another reader uses this image
     */
    @Builder(toBuilder = true)
    public record ImageData(int width, int height, byte[] data) {
        /**
         * {@code EMPTY} represents an absent texture with zero dimensions and no
         * pixel bytes. It is shared by zero-length inference results.
         */
        public static final ImageData EMPTY = new ImageData(0, 0, new byte[0]);

        /**
         * {@code PIXEL_SIZE} counts the four RGBA bytes per pixel used when
         * calculating canonical skin image lengths for dimension inference.
         */
        private static final int PIXEL_SIZE = 4;
        /**
         * {@code SINGLE_SKIN_SIZE} identifies the byte count for a {@code 64} by
         * {@code 32} RGBA image used by canonical skin dimension inference.
         */
        private static final int SINGLE_SKIN_SIZE = 64 * 32 * PIXEL_SIZE;
        /**
         * {@code DOUBLE_SKIN_SIZE} identifies the byte count for a {@code 64} by
         * {@code 64} RGBA image used by canonical skin dimension inference.
         */
        private static final int DOUBLE_SKIN_SIZE = 64 * 64 * PIXEL_SIZE;
        /**
         * {@code SKIN_128_64_SIZE} identifies the byte count for a {@code 128} by
         * {@code 64} RGBA image used by canonical skin dimension inference.
         */
        private static final int SKIN_128_64_SIZE = 128 * 64 * PIXEL_SIZE;
        /**
         * {@code SKIN_128_128_SIZE} identifies the byte count for a {@code 128} by
         * {@code 128} RGBA image used by canonical skin dimension inference.
         */
        private static final int SKIN_128_128_SIZE = 128 * 128 * PIXEL_SIZE;
        /**
         * {@code SKIN_PERSONA_SIZE} identifies the byte count for a {@code 256} by
         * {@code 256} RGBA image used by canonical skin dimension inference.
         */
        private static final int SKIN_PERSONA_SIZE = 256 * 256 * PIXEL_SIZE;

        /**
         * Infers canonical skin dimensions from the supplied RGBA byte count.
         * Nonempty arrays are retained without copying, zero bytes return
         * {@code EMPTY}, and an unsupported byte count is rejected rather than
         * approximated.
         *
         * @throws NullPointerException if {@code data} is {@code null}
         * @throws IllegalArgumentException if the byte count has no supported
         * dimension mapping
         */
        public static ImageData from(byte[] data) {
            return switch (Objects.requireNonNull(data).length) {
                case 0 -> EMPTY;
                case SINGLE_SKIN_SIZE -> new ImageData(64, 32, data);
                case DOUBLE_SKIN_SIZE -> new ImageData(64, 64, data);
                case SKIN_128_64_SIZE -> new ImageData(128, 64, data);
                case SKIN_128_128_SIZE -> new ImageData(128, 128, data);
                case SKIN_PERSONA_SIZE -> new ImageData(256, 256, data);
                default -> throw new IllegalArgumentException("Invalid skin length");
            };
        }

        /**
         * Checks the minimum skin width, height, and byte count without verifying
         * their product. An image must reach {@code 64} by {@code 32} and the
         * smallest skin byte count; an evaluated {@code null} byte array throws.
         */
        public boolean isValid() {
            return width >= 64 && height >= 32 && data.length >= ImageData.SINGLE_SKIN_SIZE;
        }
    }

    /**
     * {@code AnimationData} holds the texture, target region, frame count, and
     * expression selection for a client-played skin animation. The record retains
     * its image reference without copying its pixel bytes.
     *
     * @param imageData animation texture image data
     * @param animationType skin region receiving the animation
     * @param frameCount client animation frame count
     * @param expressionType animation expression playback selection
     */
    @Builder(toBuilder = true)
    public record AnimationData(
            ImageData imageData, AnimationType animationType,
            float frameCount, ExpressionType expressionType
    ) {
    }

    /**
     * {@code PersonaPieces} identifies one Persona skin piece and its owning content
     * pack. The record carries identifiers and the default flag to protocol
     * conversion without validating Marketplace ownership or resolving any
     * resources.
     *
     * @param pieceId Persona piece resource identifier
     * @param pieceType Persona piece category token
     * @param packId owning content pack identifier
     * @param isDefault default Persona piece flag
     * @param productId Marketplace piece product identifier
     */
    @Builder(toBuilder = true)
    public record PersonaPieces(
            String pieceId, String pieceType, String packId,
            boolean isDefault, String productId
    ) {
    }

    /**
     * {@code PersonaPieceTintColor} holds color tokens for a Persona piece category.
     * The record retains the supplied color list rather than copying it, so
     * consumers must coordinate mutations while a skin is converted or sent.
     *
     * @param pieceType target Persona piece category
     * @param colors shared client color tokens; the record does not enforce their
     * count or hexadecimal format
     */
    @Builder(toBuilder = true)
    public record PersonaPieceTintColor(String pieceType, List<String> colors) {
    }

    /**
     * {@code AnimationType} identifies the skin region and texture layout used by an
     * animation. The constants map to protocol texture categories, and numeric
     * lookup uses their declaration order directly.
     */
    public enum AnimationType {
        /**
         * No animated texture region.
         */
        NONE,
        /**
         * Head and face animation.
         */
        FACE,
        /**
         * Body animation with a {@code 32} by {@code 32} texture layout.
         */
        BODY_32X32,
        /**
         * Body animation with a {@code 128} by {@code 128} texture layout.
         */
        BODY_128X128;

        /**
         * {@code VALUES} caches the enum's declaration-order constants for protocol
         * lookup. Numeric conversion indexes this array directly and does not
         * validate its argument.
         */
        private static final AnimationType[] VALUES = values();

        /**
         * Returns the animation texture category at the supplied protocol index. The
         * index must identify a declared constant; unsupported negative or oversized
         * indexes throw instead of producing a fallback value.
         *
         * @throws ArrayIndexOutOfBoundsException if {@code id} is outside the
         * declared constants
         */
        public static AnimationType from(int id) {
            return VALUES[id];
        }
    }

    /**
     * {@code ExpressionType} identifies how the client interprets a skin animation's
     * expression. The constants map to protocol expression categories, and numeric
     * lookup uses their declaration order directly.
     */
    public enum ExpressionType {
        /**
         * Linear animation expression selection.
         */
        LINEAR,
        /**
         * Blinking animation expression selection.
         */
        BLINKING;

        /**
         * {@code VALUES} caches the enum's declaration-order constants for protocol
         * lookup. Numeric conversion indexes this array directly and does not
         * validate its argument.
         */
        private static final ExpressionType[] VALUES = values();

        /**
         * Returns the animation expression category at the supplied protocol index.
         * The index must identify a declared constant; unsupported negative or
         * oversized indexes throw instead of producing a fallback value.
         *
         * @throws ArrayIndexOutOfBoundsException if {@code id} is outside the
         * declared constants
         */
        public static ExpressionType from(int id) {
            return VALUES[id];
        }
    }
}