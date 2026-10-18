package org.allaymc.server.player;

import lombok.experimental.UtilityClass;
import org.allaymc.api.player.Skin;
import org.cloudburstmc.protocol.bedrock.data.skin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code SkinConvertor} maps API skin values to protocol skin objects and back. Both
 * representations expose mutable image arrays and nested collection data, so
 * conversion creates new image buffers, animation containers, Persona piece
 * containers, and tint-color lists rather than retaining those mutable sources.
 *
 * <p>
 * Each direction explicitly maps the texture and expression enums and carries the
 * API-represented metadata into the destination. Protocol builder normalization and
 * fields absent from the API still limit round-trip equivalence: the current builder
 * can represent color separately from its legacy string, and trust and profile-hash
 * fields have no API components. Callers must supply non-{@code null} images, lists,
 * and enum values needed by the selected conversion.
 */
@UtilityClass
public final class SkinConvertor {
    /**
     * Creates a protocol skin from the API-represented metadata and copied mutable
     * payloads. Images, animation image bytes, and nested tint lists are copied so
     * later source mutations cannot change those destination buffers.
     *
     * <p>
     * The input must provide its skin and cape images and all three collections;
     * {@code null} collection elements also fail through their accessors. The protocol
     * builder supplies its own defaults and color representation, so this conversion
     * does not promise byte-for-byte metadata round trips or preservation of
     * protocol-only fields.
     */
    public static SerializedSkin toSerializedSkin(Skin skin) {
        // Clone both texture arrays before constructing the destination so mutable pixels are not
        // shared across the API and protocol representations.
        ImageData serializedSkinData = ImageData.of(
                skin.skinData().width(), skin.skinData().height(), skin.skinData().data().clone());

        ImageData serializedCapeData = ImageData.of(
                skin.capeData().width(), skin.capeData().height(), skin.capeData().data().clone());

        // Convert each animation through the helper that clones its image bytes, keeping nested
        // animation payloads independent as well as the outer destination list.
        List<AnimationData> serializedAnimations = skin.animations().stream()
                .map(SkinConvertor::convertAnimationToSerialized)
                .collect(Collectors.toList());


        List<PersonaPieceData> serializedPersonaPieces = skin.personaPieces().stream()
                .map(piece -> new PersonaPieceData(
                        piece.pieceId(),
                        piece.pieceType(),
                        piece.packId(),
                        piece.isDefault(),
                        piece.productId()
                ))
                .collect(Collectors.toList());

        // Copy the nested color lists as well as their outer containers so later tint edits cannot
        // cross the API/protocol conversion boundary.
        List<PersonaPieceTintData> serializedTintColors = skin.pieceTintColors().stream()
                .map(tint -> new PersonaPieceTintData(
                        tint.pieceType(),
                        new ArrayList<>(tint.colors())
                ))
                .collect(Collectors.toList());


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
                .profileHash(skin.profileHash() != null ? skin.profileHash() : "")
                .build();
    }

    /**
     * Creates an API skin from the fields exposed by the protocol object. Images,
     * animation image bytes, and nested tint lists receive new mutable storage
     * rather than sharing their source arrays or lists.
     *
     * <p>
     * Required image and collection values must be present. The conversion reads the
     * protocol's legacy {@code getSkinColor} value directly; a color held only by
     * its separate color component therefore does not reconstruct that text.
     * Protocol-only trust and profile-hash fields have no destination API component
     * and are not preserved.
     */
    public static Skin fromSerializedSkin(SerializedSkin serializedSkin) {
        // Clone both texture arrays before constructing the destination so mutable pixels are not
        // shared across the API and protocol representations.
        Skin.ImageData skinData = new Skin.ImageData(
                serializedSkin.getSkinData().getWidth(),
                serializedSkin.getSkinData().getHeight(),
                serializedSkin.getSkinData().getImage().clone()
        );

        Skin.ImageData capeData = new Skin.ImageData(
                serializedSkin.getCapeData().getWidth(),
                serializedSkin.getCapeData().getHeight(),
                serializedSkin.getCapeData().getImage().clone()
        );

        // Convert each animation through the helper that clones its image bytes, keeping nested
        // animation payloads independent as well as the outer destination list.
        List<Skin.AnimationData> animations = serializedSkin.getAnimations().stream()
                .map(SkinConvertor::convertAnimationFromSerialized)
                .collect(Collectors.toList());


        List<Skin.PersonaPieces> personaPieces = serializedSkin.getPersonaPieces().stream()
                .map(piece -> new Skin.PersonaPieces(
                        piece.id(),
                        piece.type(),
                        piece.packId(),
                        piece.isDefault(),
                        piece.productId()
                ))
                .collect(Collectors.toList());

        // Copy the nested color lists as well as their outer containers so later tint edits cannot
        // cross the API/protocol conversion boundary.
        List<Skin.PersonaPieceTintColor> tintColors = serializedSkin.getTintColors().stream()
                .map(tint -> new Skin.PersonaPieceTintColor(
                        tint.type(),
                        new ArrayList<>(tint.colors())
                ))
                .collect(Collectors.toList());


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
                serializedSkin.isOverridingPlayerAppearance(),
                serializedSkin.getProfileHash()
        );
    }

    /**
     * Creates a protocol animation with a copied pixel array and mapped enum values.
     * The supplied animation, its image, its byte array, and both enum selections
     * must be non-{@code null}.
     */
    private static AnimationData convertAnimationToSerialized(Skin.AnimationData data) {
        // Copy the animation pixel array before mapping the wrapper so nested image mutations cannot
        // leak into the source skin representation.
        return new AnimationData(
                ImageData.of(
                        data.imageData().width(),
                        data.imageData().height(),
                        data.imageData().data().clone()
                ),
                convertAnimationType(data.animationType()),
                data.frameCount(),
                convertExpressionType(data.expressionType())
        );
    }

    /**
     * Creates an API animation with a copied pixel array and mapped enum values. The
     * supplied animation, its image, its byte array, and both enum selections must
     * be non-{@code null}.
     */
    private static Skin.AnimationData convertAnimationFromSerialized(AnimationData data) {
        // Copy the animation pixel array before mapping the wrapper so nested image mutations cannot
        // leak into the source skin representation.
        return new Skin.AnimationData(
                new Skin.ImageData(
                        data.image().getWidth(),
                        data.image().getHeight(),
                        data.image().getImage().clone()
                ),
                convertAnimationType(data.textureType()),
                data.frames(),
                convertExpressionType(data.expressionType())
        );
    }

    /**
     * Maps the animation texture category to the other representation. The explicit
     * face and body categories retain their identity, while the remaining category
     * selects the destination's {@code NONE} value.
     */
    private static AnimatedTextureType convertAnimationType(Skin.AnimationType type) {
        return switch (type) {
            case FACE -> AnimatedTextureType.FACE;
            case BODY_32X32 -> AnimatedTextureType.BODY_32X32;
            case BODY_128X128 -> AnimatedTextureType.BODY_128X128;
            default -> AnimatedTextureType.NONE;
        };
    }

    /**
     * Maps the animation texture category to the other representation. The explicit
     * face and body categories retain their identity, while the remaining category
     * selects the destination's {@code NONE} value.
     */
    private static Skin.AnimationType convertAnimationType(AnimatedTextureType type) {
        return switch (type) {
            case FACE -> Skin.AnimationType.FACE;
            case BODY_32X32 -> Skin.AnimationType.BODY_32X32;
            case BODY_128X128 -> Skin.AnimationType.BODY_128X128;
            default -> Skin.AnimationType.NONE;
        };
    }

    /**
     * Maps the animation expression selection to its corresponding destination enum
     * constant.
     */
    private static AnimationExpressionType convertExpressionType(Skin.ExpressionType type) {
        return switch (type) {
            case LINEAR -> AnimationExpressionType.LINEAR;
            case BLINKING -> AnimationExpressionType.BLINKING;
        };
    }

    /**
     * Maps the animation expression selection to its corresponding destination enum
     * constant.
     */
    private static Skin.ExpressionType convertExpressionType(AnimationExpressionType type) {
        return switch (type) {
            case LINEAR -> Skin.ExpressionType.LINEAR;
            case BLINKING -> Skin.ExpressionType.BLINKING;
        };
    }
}
