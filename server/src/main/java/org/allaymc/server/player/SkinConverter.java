package org.allaymc.server.player;

import lombok.experimental.UtilityClass;
import org.allaymc.api.player.Skin;
import org.cloudburstmc.protocol.bedrock.data.skin.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class to convert between the API's Skin object and the protocol's SerializedSkin object.
 */
@UtilityClass
public final class SkinConverter {
    /**
     * Converts an API Skin object into a protocol SerializedSkin object for network transmission.
     *
     * @param skin the API Skin object to convert
     * @return a new SerializedSkin instance containing the data from the Skin object
     */
    public static SerializedSkin toSerializedSkin(Skin skin) {
        // Convert ImageData for skin and cape
        ImageData serializedSkinData = ImageData.of(
                skin.skinData().width(), skin.skinData().height(), skin.skinData().data());

        ImageData serializedCapeData = ImageData.of(
                skin.capeData().width(), skin.capeData().height(), skin.capeData().data());

        // Convert list of animations
        List<AnimationData> serializedAnimations = skin.animations().stream()
                .map(SkinConverter::convertAnimationToSerialized)
                .collect(Collectors.toList());

        // Convert list of persona pieces
        List<PersonaPieceData> serializedPersonaPieces = skin.personaPieces().stream()
                .map(piece -> new PersonaPieceData(
                        piece.pieceId(),
                        piece.pieceType(),
                        piece.packId(),
                        piece.isDefault(),
                        piece.productId()
                ))
                .collect(Collectors.toList());

        // Convert list of persona piece tint colors
        List<PersonaPieceTintData> serializedTintColors = skin.pieceTintColors().stream()
                .map(tint -> new PersonaPieceTintData(
                        tint.pieceType(),
                        tint.colors()
                ))
                .collect(Collectors.toList());

        // Use the SerializedSkin builder to construct the final object
        return SerializedSkin.builder()
                .skinId(skin.skinId())
                .playFabId(skin.playFabId())
                .skinResourcePatch(skin.skinResourcePatch())
                .skinData(serializedSkinData)
                .animations(serializedAnimations)
                .capeData(serializedCapeData)
                .geometryData(skin.skinGeometry())
                .geometryDataEngineVersion(skin.geometryDataEngineVersion())
                .animationData(skin.animationData())
                .premium(skin.premiumSkin())
                .persona(skin.personaSkin())
                .capeOnClassic(skin.personaCapeOnClassicSkin())
                .primaryUser(skin.primaryUser())
                .capeId(skin.capeId())
                .fullSkinId(skin.fullId())
                .armSize(skin.armSize())
                .skinColor(skin.skinColor())
                .personaPieces(serializedPersonaPieces)
                .tintColors(serializedTintColors)
                .overridingPlayerAppearance(skin.overrideAppearance())
                .build();
    }

    /**
     * Converts a protocol SerializedSkin object into an API Skin object.
     *
     * @param serializedSkin the protocol SerializedSkin object to convert
     * @return a new API Skin instance containing the data from the SerializedSkin object
     */
    public static Skin fromSerializedSkin(SerializedSkin serializedSkin) {
        // Convert ImageData for skin and cape
        Skin.ImageData skinData = new Skin.ImageData(
                serializedSkin.getSkinData().getWidth(),
                serializedSkin.getSkinData().getHeight(),
                serializedSkin.getSkinData().getImage()
        );

        Skin.ImageData capeData = new Skin.ImageData(
                serializedSkin.getCapeData().getWidth(),
                serializedSkin.getCapeData().getHeight(),
                serializedSkin.getCapeData().getImage()
        );

        // Convert list of animations
        List<Skin.AnimationData> animations = serializedSkin.getAnimations().stream()
                .map(SkinConverter::convertAnimationFromSerialized)
                .collect(Collectors.toList());

        // Convert list of persona pieces
        List<Skin.PersonaPieces> personaPieces = serializedSkin.getPersonaPieces().stream()
                .map(piece -> new Skin.PersonaPieces(
                        piece.getId(),
                        piece.getType(),
                        piece.getPackId(),
                        piece.isDefault(),
                        piece.getProductId()
                ))
                .collect(Collectors.toList());

        // Convert list of persona piece tint colors
        List<Skin.PersonaPieceTintColor> tintColors = serializedSkin.getTintColors().stream()
                .map(tint -> new Skin.PersonaPieceTintColor(
                        tint.getType(),
                        tint.getColors()
                ))
                .collect(Collectors.toList());

        // Construct the API Skin record
        return new Skin(
                serializedSkin.getSkinId(),
                serializedSkin.getPlayFabId(),
                serializedSkin.getSkinResourcePatch(),
                skinData,
                animations,
                capeData,
                serializedSkin.getGeometryData(),
                serializedSkin.getAnimationData(),
                serializedSkin.getGeometryDataEngineVersion(),
                serializedSkin.isPremium(),
                serializedSkin.isPersona(),
                serializedSkin.isCapeOnClassic(),
                serializedSkin.isPrimaryUser(),
                serializedSkin.getCapeId(),
                serializedSkin.getFullSkinId(),
                serializedSkin.getSkinColor(),
                serializedSkin.getArmSize(),
                personaPieces,
                tintColors,
                serializedSkin.isOverridingPlayerAppearance()
        );
    }

    private static AnimationData convertAnimationToSerialized(Skin.AnimationData data) {
        return new AnimationData(
                ImageData.of(
                        data.imageData().width(),
                        data.imageData().height(),
                        data.imageData().data()
                ),
                convertAnimationType(data.animationType()),
                data.frameCount(),
                convertExpressionType(data.expressionType())
        );
    }

    private static Skin.AnimationData convertAnimationFromSerialized(AnimationData data) {
        return new Skin.AnimationData(
                new Skin.ImageData(
                        data.getImage().getWidth(),
                        data.getImage().getHeight(),
                        data.getImage().getImage()
                ),
                convertAnimationType(data.getTextureType()),
                data.getFrames(),
                convertExpressionType(data.getExpressionType())
        );
    }

    private static AnimatedTextureType convertAnimationType(Skin.AnimationType type) {
        return switch (type) {
            case FACE -> AnimatedTextureType.FACE;
            case BODY_32X32 -> AnimatedTextureType.BODY_32X32;
            case BODY_128X128 -> AnimatedTextureType.BODY_128X128;
            default -> AnimatedTextureType.NONE;
        };
    }

    private static Skin.AnimationType convertAnimationType(AnimatedTextureType type) {
        return switch (type) {
            case FACE -> Skin.AnimationType.FACE;
            case BODY_32X32 -> Skin.AnimationType.BODY_32X32;
            case BODY_128X128 -> Skin.AnimationType.BODY_128X128;
            default -> Skin.AnimationType.NONE;
        };
    }

    private static AnimationExpressionType convertExpressionType(Skin.ExpressionType type) {
        return switch (type) {
            case LINEAR -> AnimationExpressionType.LINEAR;
            case BLINKING -> AnimationExpressionType.BLINKING;
        };
    }

    private static Skin.ExpressionType convertExpressionType(AnimationExpressionType type) {
        return switch (type) {
            case LINEAR -> Skin.ExpressionType.LINEAR;
            case BLINKING -> Skin.ExpressionType.BLINKING;
        };
    }
}
