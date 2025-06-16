package org.allaymc.api.client.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.allaymc.api.i18n.LangCode;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
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
public class LoginData {
    private static final Gson GSON = new Gson();
    private boolean xboxAuthenticated;
    private String displayName;
    private String xuid;
    private UUID uuid;
    private DeviceInfo deviceInfo;
    private LangCode langCode;
    private String gameVersion;
    private SerializedSkin skin;
    private String identityPublicKey;

    private LoginData(LoginPacket loginPacket) {
        this.decodeChainData(loginPacket.getChain());
        this.decodeSkinData(loginPacket.getExtra());
    }

    public static LoginData decode(LoginPacket loginPacket) {
        return new LoginData(loginPacket);
    }

    private void decodeChainData(List<String> chainData) {
        try {
            this.xboxAuthenticated = EncryptionUtils.validateChain(chainData).signed();
        } catch (Exception e) {
            this.xboxAuthenticated = false;
        }

        for (String chain : chainData) {
            JsonObject chainMap = decodeToken(chain);
            if (chainMap == null) {
                continue;
            }
            if (chainMap.has("extraData")) {
                JsonObject extraData = (JsonObject) chainMap.get("extraData");
                this.displayName = extraData.get("displayName").getAsString();
                this.uuid = UUID.fromString(extraData.get("identity").getAsString());
                this.xuid = extraData.get("XUID").getAsString();
            }
            this.identityPublicKey = chainMap.get("identityPublicKey").getAsString();
        }
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
            this.deviceInfo = new DeviceInfo(deviceModel, deviceId, clientId, Device.getDevice(deviceOS), UIProfile.getById(uiProfile));
        }

        if (skinMap.has("LanguageCode")) {
            this.langCode = LangCode.valueOf(skinMap.get("LanguageCode").getAsString());
        }

        if (skinMap.has("GameVersion")) {
            this.gameVersion = skinMap.get("GameVersion").getAsString();
        }

        var skinBuilder = SerializedSkin.builder();
        if (skinMap.has("SkinId")) {
            skinBuilder.skinId(skinMap.get("SkinId").getAsString());
        }

        if (skinMap.has("SkinResourcePatch")) {
            skinBuilder.skinResourcePatch(new String(Base64.getDecoder().decode(skinMap.get("SkinResourcePatch").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("SkinGeometryData")) {
            skinBuilder.geometryData(new String(Base64.getDecoder().decode(skinMap.get("SkinGeometryData").getAsString()), StandardCharsets.UTF_8));
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
            skinBuilder.premium(skinMap.get("PremiumSkin").getAsBoolean());
        }

        if (skinMap.has("PersonaSkin")) {
            skinBuilder.persona(skinMap.get("PersonaSkin").getAsBoolean());
        }

        if (skinMap.has("CapeOnClassicSkin")) {
            skinBuilder.capeOnClassic(skinMap.get("CapeOnClassicSkin").getAsBoolean());
        }

        if (skinMap.has("AnimatedImageData")) {
            JsonArray array = skinMap.getAsJsonArray("AnimatedImageData");
            var list = new ArrayList<AnimationData>();
            for (JsonElement jsonElement : array) {
                list.add(this.getSkinAnimationData(jsonElement.getAsJsonObject()));
            }
            skinBuilder.animations(list);
        }

        if (skinMap.has("PersonaPieces")) {
            var list = new ArrayList<PersonaPieceData>();
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PersonaPieces")) {
                list.add(this.getPersonaPiece(jsonElement.getAsJsonObject()));
            }
            skinBuilder.personaPieces(list);
        }

        if (skinMap.has("PieceTintColors")) {
            var list = new ArrayList<PersonaPieceTintData>();
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PieceTintColors")) {
                list.add(this.getPersonaPieceTint(jsonElement.getAsJsonObject()));
            }
            skinBuilder.tintColors(list);
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

    private ImageData getImage(JsonObject skinMap, String name) {
        if (skinMap.has(name + "Data")) {
            byte[] skinImage = Base64.getDecoder().decode(skinMap.get(name + "Data").getAsString());
            if (skinMap.has(name + "ImageHeight") && skinMap.has(name + "ImageWidth")) {
                int width = skinMap.get(name + "ImageWidth").getAsInt();
                int height = skinMap.get(name + "ImageHeight").getAsInt();
                return ImageData.of(width, height, skinImage);
            } else {
                return ImageData.of(skinImage);
            }
        }
        return ImageData.EMPTY;
    }

    private AnimationData getSkinAnimationData(JsonObject animationData) {
        byte[] data = Base64.getDecoder().decode(animationData.get("Image").getAsString());
        int width = animationData.get("ImageWidth").getAsInt();
        int height = animationData.get("ImageHeight").getAsInt();
        float frames = animationData.get("Frames").getAsFloat();
        var type = AnimatedTextureType.from(animationData.get("Type").getAsInt());
        var expression = AnimationExpressionType.from(animationData.get("AnimationExpression").getAsInt());
        return new AnimationData(ImageData.of(width, height, data), type, frames, expression);
    }

    private PersonaPieceData getPersonaPiece(JsonObject personaPiece) {
        String pieceId = personaPiece.get("PieceId").getAsString();
        String pieceType = personaPiece.get("PieceType").getAsString();
        String packId = personaPiece.get("PackId").getAsString();
        String productId = personaPiece.get("ProductId").getAsString();
        boolean isDefault = personaPiece.get("IsDefault").getAsBoolean();
        return new PersonaPieceData(pieceId, pieceType, packId, isDefault, productId);
    }

    private PersonaPieceTintData getPersonaPieceTint(JsonObject personaPiceTint) {
        String pieceType = personaPiceTint.get("PieceType").getAsString();
        List<String> colors = new ArrayList<>();
        for (JsonElement element : personaPiceTint.getAsJsonArray("Colors")) {
            colors.add(element.getAsString());
        }
        return new PersonaPieceTintData(pieceType, colors);
    }
}
