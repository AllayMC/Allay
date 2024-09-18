package org.allaymc.api.client.skin;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.Utils;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.JSONValue;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LucGamesYT | daoge_cmd
 */
@Data
@Slf4j
public class Skin {

    public static final int SINGLE_SKIN_SIZE = 8192;
    public static final int DOUBLE_SKIN_SIZE = 16384;
    public static final int SKIN_128_64_SIZE = 32768;
    public static final int SKIN_128_128_SIZE = 65536;

    private String skinId;
    private String resourcePatch = legacyGeometryName("geometry.humanoid.custom");
    private String geometryName = "";
    private String geometryData = "";
    private String animationData = "";
    private String capeId = "";
    private String fullSkinId = UUID.randomUUID().toString();
    private String skinColor = "#0";
    private String armSize = "wide";
    private String playFabId = "";

    private Image skinData = new Image(0, 0, new byte[0]);
    private Image capeData = new Image(0, 0, new byte[0]);

    private boolean premium;
    private boolean persona;
    private boolean capeOnClassic;
    private boolean trusted = false;
    private boolean primaryUser = true;

    private List<SkinAnimation> skinAnimations = new ArrayList<>();
    private List<PersonaPiece> personaPieces = new ArrayList<>();
    private List<PersonaPieceTint> personaPieceTints = new ArrayList<>();

    public static Skin fromNetwork(SerializedSkin serializedSkin) {
        Skin skin = new Skin();
        skin.setSkinId(serializedSkin.getSkinId());
        skin.setPlayFabId(serializedSkin.getPlayFabId());
        skin.setGeometryName(serializedSkin.getGeometryName());
        skin.setResourcePatch(serializedSkin.getSkinResourcePatch());
        skin.setSkinData(new Image(serializedSkin.getSkinData().getWidth(), serializedSkin.getSkinData().getHeight(), serializedSkin.getSkinData().getImage()));
        List<SkinAnimation> skinAnimations = new ArrayList<>();
        for (AnimationData animation : serializedSkin.getAnimations()) {
            Image image = new Image(animation.getImage().getWidth(), animation.getImage().getHeight(), animation.getImage().getImage());
            skinAnimations.add(new SkinAnimation(image, animation.getTextureType().ordinal(), animation.getFrames(), animation.getExpressionType().ordinal()));
        }
        skin.setSkinAnimations(skinAnimations);
        skin.setCapeData(new Image(serializedSkin.getCapeData().getWidth(), serializedSkin.getCapeData().getHeight(), serializedSkin.getCapeData().getImage()));
        skin.setGeometryData(serializedSkin.getGeometryData());
        skin.setAnimationData(serializedSkin.getAnimationData());
        skin.setPremium(serializedSkin.isPremium());
        skin.setPersona(serializedSkin.isPersona());
        skin.setCapeOnClassic(serializedSkin.isCapeOnClassic());
        skin.setCapeId(serializedSkin.getCapeId());
        skin.setFullSkinId(serializedSkin.getFullSkinId());
        skin.setArmSize(serializedSkin.getArmSize());
        skin.setSkinColor(serializedSkin.getSkinColor());
        List<PersonaPiece> personaPieces = new ArrayList<>();
        for (PersonaPieceData personaPiece : serializedSkin.getPersonaPieces()) {
            personaPieces.add(new PersonaPiece(personaPiece.getId(), personaPiece.getType(), personaPiece.getPackId(), personaPiece.getProductId(), personaPiece.isDefault()));
        }
        skin.setPersonaPieces(personaPieces);
        List<PersonaPieceTint> pieceTints = new ArrayList<>();
        for (PersonaPieceTintData tintColor : serializedSkin.getTintColors()) {
            pieceTints.add(new PersonaPieceTint(tintColor.getType(), tintColor.getColors()));
        }
        skin.setPersonaPieceTints(pieceTints);
        return skin;
    }

    public static Skin fromInputStream(InputStream inputStream) {
        Skin skin = new Skin();
        skin.setTrusted(true);
        BufferedImage skinData = null;
        try {
            skinData = ImageIO.read(inputStream);
        } catch (IOException e) {
            log.error("Error while reading skin from input stream", e);
        }
        if (skinData != null) {
            skin.setSkinData(skinData);
        }
        return skin;
    }

    public static Skin fromFile(File file) {
        Skin skin = new Skin();
        skin.setTrusted(true);
        BufferedImage skinData = null;
        try {
            skinData = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (skinData != null) {
            skin.setSkinData(skinData);
        }
        return skin;
    }

    public static String legacyGeometryName(String geometryName) {
        return """
                {
                  "geometry": {
                    "default": "%s"
                  }
                }
                """.formatted(geometryName);
    }

    public static Image parseBufferedImage(BufferedImage image) {
        try (FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Color color = new Color(image.getRGB(x, y), true);
                    outputStream.write(color.getRed());
                    outputStream.write(color.getGreen());
                    outputStream.write(color.getBlue());
                    outputStream.write(color.getAlpha());
                }
            }
            image.flush();
            return new Image(image.getWidth(), image.getHeight(), outputStream.array);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validateSkinResourcePatch(String skinResourcePatch) {
        try {
            JSONObject object = (JSONObject) JSONValue.parseWithException(skinResourcePatch);
            JSONObject geometry = (JSONObject) object.get("geometry");
            return geometry.containsKey("default") && geometry.get("default") instanceof String;
        } catch (Exception e) {
            return false;
        }
    }

    public String getResourcePatch() {
        return this.resourcePatch == null ? "" : this.resourcePatch;
    }

    public String getGeometryData() {
        return this.geometryData == null ? "" : this.geometryData;
    }

    public String getAnimationData() {
        return this.animationData == null ? "" : this.animationData;
    }

    public String getCapeId() {
        return this.capeId == null ? "" : this.capeId;
    }

    public void setSkinData(Image skinData) {
        this.skinData = skinData;
    }

    public void setSkinData(BufferedImage bufferedImage) {
        this.skinData = parseBufferedImage(bufferedImage);
    }

    public Image getCapeData() {
        return this.capeData != null ? this.capeData : new Image(0, 0, new byte[0]);
    }

    public SerializedSkin toNetwork() {
        List<AnimationData> animationDataList = new ArrayList<>();
        for (SkinAnimation animation : this.skinAnimations) {
            animationDataList.add(new AnimationData(ImageData.of(animation.image().width(), animation.image().height(), animation.image().data()), AnimatedTextureType.values()[animation.type()], animation.frames()));
        }
        List<PersonaPieceData> personaPieceDataList = new ArrayList<>();
        for (var p : this.personaPieces) {
            personaPieceDataList.add(new PersonaPieceData(p.pieceId(), p.pieceType(), p.packId(), p.isDefault(), p.productId()));
        }
        List<PersonaPieceTintData> personaPieceTintList = new ArrayList<>();
        for (var per : this.personaPieceTints) {
            personaPieceTintList.add(new PersonaPieceTintData(per.pieceType(), per.colors()));
        }
        if (this.skinId == null) {
            this.skinId = generateSkinId("Custom");
        }
        return SerializedSkin.builder()
                .skinId(this.skinId)
                .playFabId(this.playFabId)
                .geometryName(this.geometryName)
                .skinResourcePatch(this.resourcePatch)
                .skinData(ImageData.of(this.skinData.width(), this.skinData.height(), this.skinData.data()))
                .animations(animationDataList)
                .capeData(ImageData.of(this.capeData.width(), this.capeData.height(), this.capeData.data()))
                .geometryData(this.geometryData)
                .animationData(this.animationData)
                .premium(this.premium)
                .persona(this.persona)
                .capeOnClassic(this.capeOnClassic)
                .capeId(this.capeId)
                .fullSkinId(this.fullSkinId)
                .armSize(this.armSize)
                .skinColor(this.skinColor)
                .personaPieces(personaPieceDataList)
                .tintColors(personaPieceTintList)
                .build();
    }

    public String generateSkinId(String name) {
        byte[] data = Utils.appendBytes(this.skinData.data(), this.resourcePatch.getBytes(StandardCharsets.UTF_8));
        return UUID.nameUUIDFromBytes(data) + "." + name;
    }

    public boolean isValid() {
        return isValidSkin() && isValidResourcePatch();
    }

    private boolean isValidSkin() {
        return skinId != null && !skinId.trim().isEmpty() &&
               skinData != null && skinData.width() >= 64 && skinData.height() >= 32 &&
               skinData.data().length >= SINGLE_SKIN_SIZE;
    }

    private boolean isValidResourcePatch() {
        return resourcePatch != null && validateSkinResourcePatch(resourcePatch);
    }
}