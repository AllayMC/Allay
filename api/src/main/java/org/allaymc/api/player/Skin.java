package org.allaymc.api.player;

import com.google.gson.JsonParser;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

/**
 * Represents the skin of a player, as sent over the network.
 * <p>
 * The skin holds a texture, a model, and optional animations which may be present when the skin is created
 * using the in-game creator (Persona) or bought from the marketplace.
 *
 * @param skinId                    a unique ID produced for the skin, for example 'c18e65aa-7b21-4637-9b63-8ad63622ef01_Alex' for the default Alex skin
 * @param playFabId                 the PlayFab ID produced for the skin. PlayFab hosts the Marketplace, skins, and other related features. This is the ID used
 *                                  to store the skin within PlayFab
 * @param skinResourcePatch         a JSON encoded object holding fields that point to the skin's geometry. It specifies how the geometry of animations and the
 *                                  default skin are combined
 * @param skinData                  an object containing the dimensions and raw RGBA pixel data for the skin texture
 * @param animations                a list of all animations that the skin has
 * @param capeData                  an object containing the dimensions and raw RGBA pixel data for the cape texture
 * @param skinGeometry              a JSON encoded structure of the skin's geometry data, containing properties such as bones, UVs, pivots, etc.
 * @param animationData             TODO: Find out what value this holds and when it is present
 * @param geometryDataEngineVersion TODO: Find out the usage of this field
 * @param premiumSkin               specifies if this is a skin that was purchased from the marketplace
 * @param personaSkin               specifies if this is a skin that was created using the in-game skin creator (Persona)
 * @param personaCapeOnClassicSkin  specifies if a Persona cape is equipped on a classic skin
 * @param primaryUser               the purpose of this field is unknown
 * @param capeId                    a unique identifier for the cape, which usually contains a UUID
 * @param fullId                    an ID that represents the full skin. The actual functionality is unknown as the client does not appear to send this value
 * @param skinColor                 a hex representation (including #) of the skin's base color, for example '#b37b62'
 * @param armSize                   the size of the player model's arms, which is either 'wide' (generally for male skins) or 'slim' (generally for female skins)
 * @param personaPieces             a list of all Persona pieces that the skin is composed of
 * @param pieceTintColors           a list of specific tint colors for some of the Persona pieces
 * @param overrideAppearance        specifies if the skin should override the player's client-side equipped skin. If false, the client will reject this skin and
 *                                  continue to use its own
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

    public static final String ARM_SIZE_WIDE = "wide";
    public static final String ARM_SIZE_SLIM = "slim";

    public boolean isValid() {
        var isValidSkin = skinId != null && !skinId.trim().isEmpty() && skinData != null && skinData.isValid();
        var isValidResourcePatch = skinResourcePatch != null && validateSkinResourcePatch(skinResourcePatch);
        return isValidSkin && isValidResourcePatch;
    }

    private static boolean validateSkinResourcePatch(String skinResourcePatch) {
        try {
            var object = JsonParser.parseString(skinResourcePatch).getAsJsonObject();
            var geometry = object.get("geometry").getAsJsonObject();
            return geometry.has("default") && geometry.get("default").getAsJsonPrimitive().isString();
        } catch (ClassCastException | NullPointerException | IllegalStateException e) {
            return false;
        }
    }

    /**
     * Holds the dimensions and raw data of an image.
     *
     * @param width  the width of the image in pixels
     * @param height the height of the image in pixels
     * @param data   the raw image data as a byte array, with pixels in RGBA order. The array length should be width * height * 4
     */
    @Builder(toBuilder = true)
    public record ImageData(int width, int height, byte[] data) {
        public static final ImageData EMPTY = new ImageData(0, 0, new byte[0]);

        private static final int PIXEL_SIZE = 4;
        private static final int SINGLE_SKIN_SIZE = 64 * 32 * PIXEL_SIZE;
        private static final int DOUBLE_SKIN_SIZE = 64 * 64 * PIXEL_SIZE;
        private static final int SKIN_128_64_SIZE = 128 * 64 * PIXEL_SIZE;
        private static final int SKIN_128_128_SIZE = 128 * 128 * PIXEL_SIZE;
        private static final int SKIN_PERSONA_SIZE = 256 * 256 * PIXEL_SIZE;

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

        public boolean isValid() {
            return width >= 64 && height >= 32 && data.length >= ImageData.SINGLE_SKIN_SIZE;
        }
    }

    /**
     * Represents an animation that can be added to a skin.
     * <p>
     * The client plays the animation itself, so no server-side intervention is required. The playback rate
     * appears to be client-controlled.
     *
     * @param imageData      the image data for the animation. It contains all frames of the animation concatenated together. The part of the skin this data
     *                       applies to depends on the animationType
     * @param animationType  the type of the animation. This determines how the imageData is applied to the skin
     * @param frameCount     the total number of frames in the animation
     * @param expressionType the type of expression for the animation, such as blinking
     */
    @Builder(toBuilder = true)
    public record AnimationData(
            ImageData imageData, AnimationType animationType,
            float frameCount, ExpressionType expressionType
    ) {
    }

    /**
     * Represents a single piece of a Persona skin. All pieces are sent separately.
     *
     * @param pieceId   a UUID that uniquely identifies this specific piece
     * @param pieceType the type of the piece. Known types include:
     *                  <ul>
     *                    <li>persona_skeleton</li>
     *                    <li>persona_body</li>
     *                    <li>persona_skin</li>
     *                    <li>persona_bottom</li>
     *                    <li>persona_feet</li>
     *                    <li>persona_top</li>
     *                    <li>persona_mouth</li>
     *                    <li>persona_hair</li>
     *                    <li>persona_eyes</li>
     *                    <li>persona_facial_hair</li>
     *                  </ul>
     * @param packId    a UUID that identifies the pack this piece belongs to
     * @param isDefault specifies if this is a default piece (like those on a Steve or Alex skin)
     * @param productId a UUID that identifies the piece for marketplace purposes. This is empty for default pieces
     */
    @Builder(toBuilder = true)
    public record PersonaPieces(
            String pieceId, String pieceType, String packId,
            boolean isDefault, String productId
    ) {
    }

    /**
     * Describes the tint colors for a specific piece of a Persona skin.
     *
     * @param pieceType the type of the Persona piece to which this tint applies. This must correspond to a piece in the personaPieces list. Known tintable
     *                  piece types include:
     *                  <ul>
     *                    <li>persona_mouth</li>
     *                    <li>persona_eyes</li>
     *                    <li>persona_hair</li>
     *                  </ul>
     * @param colors    a list of up to four colors in ARGB hex format. These apply to different parts of the piece. For example, for 'persona_eyes', the
     *                  colors might represent the iris, eyebrows, and sclera
     */
    @Builder(toBuilder = true)
    public record PersonaPieceTintColor(String pieceType, List<String> colors) {
    }

    public enum AnimationType {
        NONE,
        /**
         * Head animation.
         */
        FACE,
        /**
         * Body animation with 32x32 resolution.
         */
        BODY_32X32,
        /**
         * Body animation with 128x128 resolution.
         */
        BODY_128X128;

        private static final AnimationType[] VALUES = values();

        public static AnimationType from(int id) {
            return VALUES[id];
        }
    }

    public enum ExpressionType {
        LINEAR,
        BLINKING;

        private static final ExpressionType[] VALUES = values();

        public static ExpressionType from(int id) {
            return VALUES[id];
        }
    }
}