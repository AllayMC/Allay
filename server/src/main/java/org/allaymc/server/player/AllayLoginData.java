package org.allaymc.server.player;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.LangCode;
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
@Slf4j
@ToString
@Getter
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

    /// Decode the given {@link LoginPacket} and return a {@link LoginData}. If there is any error
    /// during the decoding, {@code null} will be returned.
    public static AllayLoginData decode(LoginPacket loginPacket) {
        var loginData = new AllayLoginData();
        try {
            loginData.decodeChainData(EncryptionUtils.validatePayload(loginPacket.getAuthPayload()));
        } catch (Throwable t) {
            log.warn("Failed to decode chain data!", t);
            return null;
        }

        try {
            loginData.decodeSkinData(loginPacket.getClientJwt());
        } catch (Throwable t) {
            log.warn("Failed to decode skin data!", t);
            return null;
        }

        return loginData;
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
        var skinMap = this.decodeToken(skinData);
        if (skinMap.has("DeviceModel") && skinMap.has("DeviceId") &&
            skinMap.has("ClientRandomId") && skinMap.has("DeviceOS") &&
            skinMap.has("GuiScale")) {
            var deviceModel = skinMap.get("DeviceModel").getAsString();
            var deviceId = skinMap.get("DeviceId").getAsString();
            var clientId = skinMap.get("ClientRandomId").getAsLong();
            var deviceOS = skinMap.get("DeviceOS").getAsInt();
            var uiProfile = skinMap.get("UIProfile").getAsInt();
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
        var tokenSplit = token.split("\\.");
        if (tokenSplit.length < 2) {
            throw new IllegalArgumentException("Invalid token length");
        }
        return GSON.fromJson(new String(Base64.getDecoder().decode(tokenSplit[1]), StandardCharsets.UTF_8), JsonObject.class);
    }

    private Skin.ImageData getImage(JsonObject skinMap, String name) {
        var dataKey = name + "Data";
        if (!skinMap.has(dataKey)) {
            return Skin.ImageData.EMPTY;
        }

        var skinImage = Base64.getDecoder().decode(skinMap.get(dataKey).getAsString());
        var widthKey = name + "ImageWidth";
        var heightKey = name + "ImageHeight";
        if (skinMap.has(widthKey) && skinMap.has(heightKey)) {
            var width = skinMap.get(widthKey).getAsInt();
            var height = skinMap.get(heightKey).getAsInt();
            return new Skin.ImageData(width, height, skinImage);
        }

        return Skin.ImageData.from(skinImage);
    }

    private Skin.AnimationData getSkinAnimationData(JsonObject animationData) {
        var data = Base64.getDecoder().decode(animationData.get("Image").getAsString());
        var width = animationData.get("ImageWidth").getAsInt();
        var height = animationData.get("ImageHeight").getAsInt();
        var frames = animationData.get("Frames").getAsFloat();
        var type = Skin.AnimationType.from(animationData.get("Type").getAsInt());
        var expression = Skin.ExpressionType.from(animationData.get("AnimationExpression").getAsInt());
        return new Skin.AnimationData(new Skin.ImageData(width, height, data), type, frames, expression);
    }

    private Skin.PersonaPieces getPersonaPiece(JsonObject personaPiece) {
        var pieceId = personaPiece.get("PieceId").getAsString();
        var pieceType = personaPiece.get("PieceType").getAsString();
        var packId = personaPiece.get("PackId").getAsString();
        var productId = personaPiece.get("ProductId").getAsString();
        var isDefault = personaPiece.get("IsDefault").getAsBoolean();
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
