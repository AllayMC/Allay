package cn.allay.api.player.data;

import cn.allay.api.player.info.Device;
import cn.allay.api.player.info.DeviceInfo;
import cn.allay.api.player.info.UIProfile;
import cn.allay.api.player.skin.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nimbusds.jose.proc.JWSVerifierFactory;
import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.*;

/**
 * @author LucGamesYT | daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
@ToString
@Getter
public class LoginData {

    private static final Gson GSON = new Gson();
    private static final JWSVerifierFactory JWS_VERIFIER_FACTORY = new DefaultJWSVerifierFactory();
    private static final JsonMapper JSON_MAPPER = JsonMapper.builder().build();

    private boolean xboxAuthenticated;
    private String displayName;
    private String xuid;
    private UUID uuid;
    private DeviceInfo deviceInfo;
    private String languageCode;
    private String gameVersion;
    private Skin skin;

    private LoginData(LoginPacket loginPacket) {
        this.decodeChainData(loginPacket.getChain());
        this.decodeSkinData(loginPacket.getExtra());
    }

    public static LoginData decode(LoginPacket loginPacket) {
        return new LoginData(loginPacket);
    }

    private static boolean verify(PublicKey publicKey, JWSObject jwsObject) throws JOSEException {
        return jwsObject.verify(JWS_VERIFIER_FACTORY.createJWSVerifier(jwsObject.getHeader(), publicKey));
    }

    private void decodeChainData(List<SignedJWT> chainData) {
        try {
            this.xboxAuthenticated = verifyChains(chainData);
        } catch (Exception e) {
            this.xboxAuthenticated = false;
        }

        for (SignedJWT chain : chainData) {
            JsonNode chainMap = decodeToken(chain);
            if (chainMap == null) {
                continue;
            }
            if (chainMap.has("extraData")) {
                JsonNode extraData = chainMap.get("extraData");
                this.displayName = extraData.get("displayName").asText();
                this.uuid = UUID.fromString(extraData.get("identity").asText());
                this.xuid = extraData.get("XUID").asText();
            }
        }
    }

    private void decodeSkinData(SignedJWT skinData) {
        JsonNode skinMap = decodeToken(skinData);
        if (skinMap.has("DeviceModel") && skinMap.has("DeviceId") &&
            skinMap.has("ClientRandomId") && skinMap.has("DeviceOS") && skinMap.has("GuiScale")) {
            String deviceModel = skinMap.get("DeviceModel").asText();
            String deviceId = skinMap.get("DeviceId").asText();
            long clientId = skinMap.get("ClientRandomId").asLong();
            int deviceOS = skinMap.get("DeviceOS").asInt();
            int uiProfile = skinMap.get("UIProfile").asInt();
            this.deviceInfo = new DeviceInfo(deviceModel, deviceId, clientId, Device.getDevice(deviceOS), UIProfile.getById(uiProfile));
        }

        if (skinMap.has("LanguageCode")) {
            this.languageCode = skinMap.get("LanguageCode").asText();
        }

        if (skinMap.has("GameVersion")) {
            this.gameVersion = skinMap.get("GameVersion").asText();
        }

        this.skin = new Skin();
        if (skinMap.has("SkinId")) {
            this.skin.setSkinId(skinMap.get("SkinId").asText());
        }

        if (skinMap.has("SkinResourcePatch")) {
            this.skin.setResourcePatch(new String(Base64.getDecoder().decode(skinMap.get("SkinResourcePatch").asText()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("SkinGeometryData")) {
            this.skin.setGeometryData(new String(Base64.getDecoder().decode(skinMap.get("SkinGeometryData").asText()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("AnimationData")) {
            this.skin.setAnimationData(new String(Base64.getDecoder().decode(skinMap.get("AnimationData").asText()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("CapeId")) {
            this.skin.setCapeId(skinMap.get("CapeId").asText());
        }

        if (skinMap.has("SkinColor")) {
            this.skin.setSkinColor(skinMap.get("SkinColor").asText());
        }

        if (skinMap.has("ArmSize")) {
            this.skin.setArmSize(skinMap.get("ArmSize").asText());
        }

        if (skinMap.has("PlayFabID")) {
            this.skin.setPlayFabId(skinMap.get("PlayFabID").asText());
        }

        this.skin.setSkinData(this.getImage(skinMap, "Skin"));
        this.skin.setCapeData(this.getImage(skinMap, "Cape"));

        if (skinMap.has("PremiumSkin")) {
            this.skin.setPremium(skinMap.get("PremiumSkin").asBoolean());
        }

        if (skinMap.has("PersonaSkin")) {
            this.skin.setPersona(skinMap.get("PersonaSkin").asBoolean());
        }

        if (skinMap.has("CapeOnClassicSkin")) {
            this.skin.setCapeOnClassic(skinMap.get("CapeOnClassicSkin").asBoolean());
        }

        if (skinMap.has("AnimatedImageData")) {
            JsonNode array = skinMap.get("AnimatedImageData");
            for (JsonNode jsonElement : array) {
                this.skin.getSkinAnimations().add(this.getSkinAnimationData(jsonElement));
            }
        }

        if (skinMap.has("PersonaPieces")) {
            for (JsonNode jsonElement : skinMap.get("PersonaPieces")) {
                this.skin.getPersonaPieces().add(this.getPersonaPiece(jsonElement));
            }
        }

        if (skinMap.has("PieceTintColors")) {
            for (JsonNode jsonElement : skinMap.get("PieceTintColors")) {
                this.skin.getPersonaPieceTints().add(this.getPersonaPieceTint(jsonElement));
            }
        }
    }

    private boolean verifyChains(List<SignedJWT> chains) throws Exception {
        PublicKey lastKey = null;
        boolean mojangKeyVerified = false;
        for (SignedJWT chain : chains) {
            if (!mojangKeyVerified) {
                mojangKeyVerified = verify(EncryptionUtils.getMojangPublicKey(), chain);
            }
            if (lastKey != null && !verify(lastKey, chain)) {
                throw new JOSEException("Unable to verify key in chain.");
            }
            Map<String, Object> payload = chain.getPayload().toJSONObject();
            String base64key = (String) payload.get("identityPublicKey");
            if (base64key == null) throw new RuntimeException("No key found");
            lastKey = EncryptionUtils.generateKey(base64key);
        }
        return mojangKeyVerified;
    }

    private JsonObject decodeToken(String token) {
        String[] tokenSplit = token.split("\\.");
        if (tokenSplit.length < 2) {
            throw new IllegalArgumentException("Invalid token length");
        }
        return GSON.fromJson(new String(Base64.getDecoder().decode(tokenSplit[1]), StandardCharsets.UTF_8), JsonObject.class);
    }

    private JsonNode decodeToken(SignedJWT token) {
        try {
            return JSON_MAPPER.readTree(token.getPayload().toBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid token JSON", e);
        }
    }

    private Image getImage(JsonNode skinMap, String name) {
        if (skinMap.has(name + "Data")) {
            byte[] skinImage = Base64.getDecoder().decode(skinMap.get(name + "Data").asText());
            if (skinMap.has(name + "ImageHeight") && skinMap.has(name + "ImageWidth")) {
                int width = skinMap.get(name + "ImageWidth").asInt();
                int height = skinMap.get(name + "ImageHeight").asInt();
                return new Image(width, height, skinImage);
            } else {
                return Image.getImage(skinImage);
            }
        }
        return new Image(0, 0, new byte[0]);
    }

    private SkinAnimation getSkinAnimationData(JsonNode animationData) {
        byte[] data = Base64.getDecoder().decode(animationData.get("Image").asText());
        int width = animationData.get("ImageWidth").asInt();
        int height = animationData.get("ImageHeight").asInt();
        float frames = animationData.get("Frames").floatValue();
        int type = animationData.get("Type").asInt();
        int expression = animationData.get("AnimationExpression").asInt();
        return new SkinAnimation(new Image(width, height, data), type, frames, expression);
    }

    private PersonaPiece getPersonaPiece(JsonNode personaPiece) {
        String pieceId = personaPiece.get("PieceId").asText();
        String pieceType = personaPiece.get("PieceType").asText();
        String packId = personaPiece.get("PackId").asText();
        String productId = personaPiece.get("ProductId").asText();
        boolean isDefault = personaPiece.get("IsDefault").asBoolean();
        return new PersonaPiece(pieceId, pieceType, packId, productId, isDefault);
    }

    private PersonaPieceTint getPersonaPieceTint(JsonNode personaPiceTint) {
        String pieceType = personaPiceTint.get("PieceType").asText();
        List<String> colors = new ArrayList<>();
        for (JsonNode element : personaPiceTint.get("Colors")) {
            colors.add(element.textValue());
        }
        return new PersonaPieceTint(pieceType, colors);
    }

}
