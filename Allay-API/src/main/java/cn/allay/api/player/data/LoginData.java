package cn.allay.api.player.data;

import cn.allay.api.player.info.Device;
import cn.allay.api.player.info.DeviceInfo;
import cn.allay.api.player.info.UIProfile;
import cn.allay.api.player.skin.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * Allay Project 2023/6/23
 *
 * @author LucGamesYT | daoge_cmd
 */
@ToString
@Getter
public class LoginData {
    private static final Gson GSON = new Gson();
    private boolean xboxAuthenticated;
    private String displayName;
    private String xuid;
    private UUID uuid;
    private DeviceInfo deviceInfo;
    private String languageCode;
    private String gameVersion;
    private Skin skin;
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
            skinMap.has("ClientRandomId") && skinMap.has("DeviceOS") && skinMap.has("GuiScale")) {
            String deviceModel = skinMap.get("DeviceModel").getAsString();
            String deviceId = skinMap.get("DeviceId").getAsString();
            long clientId = skinMap.get("ClientRandomId").getAsLong();
            int deviceOS = skinMap.get("DeviceOS").getAsInt();
            int uiProfile = skinMap.get("UIProfile").getAsInt();
            this.deviceInfo = new DeviceInfo(deviceModel, deviceId, clientId, Device.getDevice(deviceOS), UIProfile.getById(uiProfile));
        }

        if (skinMap.has("LanguageCode")) {
            this.languageCode = skinMap.get("LanguageCode").getAsString();
        }

        if (skinMap.has("GameVersion")) {
            this.gameVersion = skinMap.get("GameVersion").getAsString();
        }

        this.skin = new Skin();
        if (skinMap.has("SkinId")) {
            this.skin.setSkinId(skinMap.get("SkinId").getAsString());
        }

        if (skinMap.has("SkinResourcePatch")) {
            this.skin.setResourcePatch(new String(Base64.getDecoder().decode(skinMap.get("SkinResourcePatch").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("SkinGeometryData")) {
            this.skin.setGeometryData(new String(Base64.getDecoder().decode(skinMap.get("SkinGeometryData").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("AnimationData")) {
            this.skin.setAnimationData(new String(Base64.getDecoder().decode(skinMap.get("AnimationData").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("CapeId")) {
            this.skin.setCapeId(skinMap.get("CapeId").getAsString());
        }

        if (skinMap.has("SkinColor")) {
            this.skin.setSkinColor(skinMap.get("SkinColor").getAsString());
        }

        if (skinMap.has("ArmSize")) {
            this.skin.setArmSize(skinMap.get("ArmSize").getAsString());
        }

        if (skinMap.has("PlayFabID")) {
            this.skin.setPlayFabId(skinMap.get("PlayFabID").getAsString());
        }

        this.skin.setSkinData(this.getImage(skinMap, "Skin"));
        this.skin.setCapeData(this.getImage(skinMap, "Cape"));

        if (skinMap.has("PremiumSkin")) {
            this.skin.setPremium(skinMap.get("PremiumSkin").getAsBoolean());
        }

        if (skinMap.has("PersonaSkin")) {
            this.skin.setPersona(skinMap.get("PersonaSkin").getAsBoolean());
        }

        if (skinMap.has("CapeOnClassicSkin")) {
            this.skin.setCapeOnClassic(skinMap.get("CapeOnClassicSkin").getAsBoolean());
        }

        if (skinMap.has("AnimatedImageData")) {
            JsonArray array = skinMap.getAsJsonArray("AnimatedImageData");
            for (JsonElement jsonElement : array) {
                this.skin.getSkinAnimations().add(this.getSkinAnimationData(jsonElement.getAsJsonObject()));
            }
        }

        if (skinMap.has("PersonaPieces")) {
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PersonaPieces")) {
                this.skin.getPersonaPieces().add(this.getPersonaPiece(jsonElement.getAsJsonObject()));
            }
        }

        if (skinMap.has("PieceTintColors")) {
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PieceTintColors")) {
                this.skin.getPersonaPieceTints().add(this.getPersonaPieceTint(jsonElement.getAsJsonObject()));
            }
        }
    }

    private JsonObject decodeToken(String token) {
        String[] tokenSplit = token.split("\\.");
        if (tokenSplit.length < 2) {
            throw new IllegalArgumentException("Invalid token length");
        }
        return GSON.fromJson(new String(Base64.getDecoder().decode(tokenSplit[1]), StandardCharsets.UTF_8), JsonObject.class);
    }

    private Image getImage(JsonObject skinMap, String name) {
        if (skinMap.has(name + "Data")) {
            byte[] skinImage = Base64.getDecoder().decode(skinMap.get(name + "Data").getAsString());
            if (skinMap.has(name + "ImageHeight") && skinMap.has(name + "ImageWidth")) {
                int width = skinMap.get(name + "ImageWidth").getAsInt();
                int height = skinMap.get(name + "ImageHeight").getAsInt();
                return new Image(width, height, skinImage);
            } else {
                return Image.getImage(skinImage);
            }
        }
        return new Image(0, 0, new byte[0]);
    }

    private SkinAnimation getSkinAnimationData(JsonObject animationData) {
        byte[] data = Base64.getDecoder().decode(animationData.get("Image").getAsString());
        int width = animationData.get("ImageWidth").getAsInt();
        int height = animationData.get("ImageHeight").getAsInt();
        float frames = animationData.get("Frames").getAsFloat();
        int type = animationData.get("Type").getAsInt();
        int expression = animationData.get("AnimationExpression").getAsInt();
        return new SkinAnimation(new Image(width, height, data), type, frames, expression);
    }

    private PersonaPiece getPersonaPiece(JsonObject personaPiece) {
        String pieceId = personaPiece.get("PieceId").getAsString();
        String pieceType = personaPiece.get("PieceType").getAsString();
        String packId = personaPiece.get("PackId").getAsString();
        String productId = personaPiece.get("ProductId").getAsString();
        boolean isDefault = personaPiece.get("IsDefault").getAsBoolean();
        return new PersonaPiece(pieceId, pieceType, packId, productId, isDefault);
    }

    private PersonaPieceTint getPersonaPieceTint(JsonObject personaPiceTint) {
        String pieceType = personaPiceTint.get("PieceType").getAsString();
        List<String> colors = new ArrayList<>();
        for (JsonElement element : personaPiceTint.getAsJsonArray("Colors")) {
            colors.add(element.getAsString());
        }
        return new PersonaPieceTint(pieceType, colors);
    }

}
