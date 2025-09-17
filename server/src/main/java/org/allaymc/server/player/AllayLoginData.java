package org.allaymc.server.player;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.*;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.Skin;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.util.ChainValidationResult;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * @author LucGamesYT | daoge_cmd
 */
@ToString
@Getter
@Builder
@AllArgsConstructor
public class AllayLoginData implements LoginData {
    private static final Gson GSON = new Gson();

    private boolean authed;
    private String xname;
    private String xuid;
    private UUID uuid;
    private DeviceInfo deviceInfo;
    private LangCode langCode;
    private String gameVersion;
    private Skin skin;
    private String identityPublicKey;

    @SneakyThrows
    private AllayLoginData(LoginPacket loginPacket) {
        this.decodeChainData(EncryptionUtils.validatePayload(loginPacket.getAuthPayload()));
        this.decodeSkinData(loginPacket.getClientJwt());
    }

    public static AllayLoginData decode(LoginPacket loginPacket) {
        return new AllayLoginData(loginPacket);
    }

    private void decodeChainData(ChainValidationResult result) {
        this.authed = result.signed();

        var extraData = result.identityClaims().extraData;
        this.xname = extraData.displayName;
        this.uuid = extraData.identity;
        this.xuid = extraData.xuid;
        this.identityPublicKey = result.identityClaims().identityPublicKey;
    }

    private void decodeSkinData(String skinData) {
        JsonObject skinMap = decodeToken(skinData);
        if (skinMap.has("DeviceModel") && skinMap.has("DeviceId") &&
            skinMap.has("ClientRandomId") && skinMap.has("DeviceOS") &&
            skinMap.has("GuiScale")) {
            String deviceModel = skinMap.get("DeviceModel").getAsString();
            String deviceId = skinMap.get("DeviceId").getAsString();
            long clientId = skinMap.get("ClientRandomId").getAsLong();
            int deviceOS = skinMap.get("DeviceOS").getAsInt();
            int uiProfile = skinMap.get("UIProfile").getAsInt();
            this.deviceInfo = new DeviceInfo(deviceModel, deviceId, clientId, Device.from(deviceOS), UIProfile.from(uiProfile));
        }

        if (skinMap.has("LanguageCode")) {
            this.langCode = LangCode.valueOf(skinMap.get("LanguageCode").getAsString());
        }

        if (skinMap.has("GameVersion")) {
            this.gameVersion = skinMap.get("GameVersion").getAsString();
        }

        var skinBuilder = Skin.builder();
        if (skinMap.has("SkinId")) {
            skinBuilder.skinId(skinMap.get("SkinId").getAsString());
        }

        if (skinMap.has("SkinResourcePatch")) {
            skinBuilder.skinResourcePatch(new String(Base64.getDecoder().decode(skinMap.get("SkinResourcePatch").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("SkinGeometryData")) {
            skinBuilder.skinGeometry(new String(Base64.getDecoder().decode(skinMap.get("SkinGeometryData").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("SkinGeometryDataEngineVersion")) {
            skinBuilder.geometryDataEngineVersion(new String(Base64.getDecoder().decode(skinMap.get("SkinGeometryDataEngineVersion").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("AnimationData")) {
            skinBuilder.animationData(new String(Base64.getDecoder().decode(skinMap.get("AnimationData").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("CapeId")) {
            skinBuilder.capeId(skinMap.get("CapeId").getAsString());
        }

        if (skinMap.has("SkinColor")) {
            skinBuilder.skinColor(skinMap.get("SkinColor").getAsString());
        }

        if (skinMap.has("ArmSize")) {
            skinBuilder.armSize(skinMap.get("ArmSize").getAsString());
        }

        if (skinMap.has("PlayFabID")) {
            skinBuilder.playFabId(skinMap.get("PlayFabID").getAsString());
        }

        skinBuilder.skinData(this.getImage(skinMap, "Skin"));
        skinBuilder.capeData(this.getImage(skinMap, "Cape"));

        if (skinMap.has("PremiumSkin")) {
            skinBuilder.premiumSkin(skinMap.get("PremiumSkin").getAsBoolean());
        }

        if (skinMap.has("PersonaSkin")) {
            skinBuilder.personaSkin(skinMap.get("PersonaSkin").getAsBoolean());
        }

        if (skinMap.has("CapeOnClassicSkin")) {
            skinBuilder.personaCapeOnClassicSkin(skinMap.get("CapeOnClassicSkin").getAsBoolean());
        }

        if (skinMap.has("AnimatedImageData")) {
            JsonArray array = skinMap.getAsJsonArray("AnimatedImageData");
            var list = new ArrayList<Skin.AnimationData>();
            for (JsonElement jsonElement : array) {
                list.add(this.getSkinAnimationData(jsonElement.getAsJsonObject()));
            }
            skinBuilder.animations(list);
        }

        if (skinMap.has("PersonaPieces")) {
            var list = new ArrayList<Skin.PersonaPieces>();
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PersonaPieces")) {
                list.add(this.getPersonaPiece(jsonElement.getAsJsonObject()));
            }
            skinBuilder.personaPieces(list);
        }

        if (skinMap.has("PieceTintColors")) {
            var list = new ArrayList<Skin.PersonaPieceTintColor>();
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PieceTintColors")) {
                list.add(this.getPersonaPieceTint(jsonElement.getAsJsonObject()));
            }
            skinBuilder.pieceTintColors(list);
        }

        this.skin = skinBuilder.build();
    }

    private JsonObject decodeToken(String token) {
        String[] tokenSplit = token.split("\\.");
        if (tokenSplit.length < 2) {
            throw new IllegalArgumentException("Invalid token length");
        }
        return GSON.fromJson(new String(Base64.getDecoder().decode(tokenSplit[1]), StandardCharsets.UTF_8), JsonObject.class);
    }

    private Skin.ImageData getImage(JsonObject skinMap, String name) {
        if (skinMap.has(name + "Data")) {
            byte[] skinImage = Base64.getDecoder().decode(skinMap.get(name + "Data").getAsString());
            if (skinMap.has(name + "ImageHeight") && skinMap.has(name + "ImageWidth")) {
                int width = skinMap.get(name + "ImageWidth").getAsInt();
                int height = skinMap.get(name + "ImageHeight").getAsInt();
                return new Skin.ImageData(width, height, skinImage);
            } else {
                return Skin.ImageData.from(skinImage);
            }
        }
        return Skin.ImageData.EMPTY;
    }

    private Skin.AnimationData getSkinAnimationData(JsonObject animationData) {
        byte[] data = Base64.getDecoder().decode(animationData.get("Image").getAsString());
        int width = animationData.get("ImageWidth").getAsInt();
        int height = animationData.get("ImageHeight").getAsInt();
        float frames = animationData.get("Frames").getAsFloat();
        var type = Skin.AnimationType.from(animationData.get("Type").getAsInt());
        var expression = Skin.ExpressionType.from(animationData.get("AnimationExpression").getAsInt());
        return new Skin.AnimationData(new Skin.ImageData(width, height, data), type, frames, expression);
    }

    private Skin.PersonaPieces getPersonaPiece(JsonObject personaPiece) {
        String pieceId = personaPiece.get("PieceId").getAsString();
        String pieceType = personaPiece.get("PieceType").getAsString();
        String packId = personaPiece.get("PackId").getAsString();
        String productId = personaPiece.get("ProductId").getAsString();
        boolean isDefault = personaPiece.get("IsDefault").getAsBoolean();
        return new Skin.PersonaPieces(pieceId, pieceType, packId, isDefault, productId);
    }

    private Skin.PersonaPieceTintColor getPersonaPieceTint(JsonObject personaPieceTint) {
        String pieceType = personaPieceTint.get("PieceType").getAsString();
        List<String> colors = new ArrayList<>();
        for (JsonElement element : personaPieceTint.getAsJsonArray("Colors")) {
            colors.add(element.getAsString());
        }
        return new Skin.PersonaPieceTintColor(pieceType, colors);
    }

}
