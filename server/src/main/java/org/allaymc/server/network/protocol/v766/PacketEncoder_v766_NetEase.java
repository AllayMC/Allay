package org.allaymc.server.network.protocol.v766;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.Skin;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.packet.BiomeDefinitionListPacket;
import org.cloudburstmc.protocol.bedrock.packet.ConfirmSkinPacket;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * {@code PacketEncoder_v766_NetEase} encodes NetEase additions to wire protocol
 * 766 while inheriting the same-version international packet operations.
 * {@code Skin} confirmation includes the player UUID, geometry, copied pixel
 * bytes, and a NetEase UID; entities without a NetEase controller use a
 * deterministic UUID-derived fallback that is not guaranteed unique. This
 * version also emits biome definitions through the legacy NBT definition field.
 */
public class PacketEncoder_v766_NetEase extends PacketEncoder_v766 {
    /**
     * Creates the v766 NetEase encoder using the supplied protocol data. The
     * parent constructor retains that exact bundle and builds definition
     * lookups; callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v766_NetEase(ProtocolData data) {
        super(data);
    }

    /**
     * Builds legacy NBT biome definitions keyed by registered biome
     * identifiers. Climate, particle, tag, and water-color values come from the
     * source registry; each color channel is divided by 256 rather than the
     * modern structured biome representation.
     */
    @Override
    public BiomeDefinitionListPacket encodeBiomeDefinitions() {
        var definitions = NbtMap.builder();
        for (var biomeType : Registries.BIOMES.getContent().m1().values()) {
            var biome = biomeType.getBiomeData();
            var color = biome.mapWaterColor();
            // Legacy water-color fields store floating-point channel values
            // scaled by 256.
            definitions.putCompound(biomeType.getIdentifier().toString(), NbtMap.builder()
                    .putFloat("ash", biome.ashDensity())
                    .putFloat("blue_spores", biome.blueSporeDensity())
                    .putFloat("depth", biome.depth())
                    .putFloat("downfall", biome.downfall())
                    .putFloat("height", biome.scale())
                    .putBoolean("rain", biome.rain())
                    .putFloat("red_spores", biome.redSporeDensity())
                    .putList("tags", NbtType.STRING, biome.tags().stream().map(tag -> tag.name()).toList())
                    .putFloat("temperature", biome.temperature())
                    .putFloat("waterColorA", color.getAlpha() / 256f)
                    .putFloat("waterColorB", color.getBlue() / 256f)
                    .putFloat("waterColorG", color.getGreen() / 256f)
                    .putFloat("waterColorR", color.getRed() / 256f)
                    .putFloat("white_ash", biome.whiteAshDensity())
                    .build());
        }

        var packet = new BiomeDefinitionListPacket();
        packet.setDefinitions(definitions.build());
        return packet;
    }

    /**
     * Creates one skin-confirmation packet for a nonnull player and skin. The
     * entry is marked valid, retains the geometry string and player UUID, and
     * clones the pixel array so later array edits do not change this packet.
     */
    @Override
    public Collection<ConfirmSkinPacket> encodeSkinConfirmation(EntityPlayer player, Skin skin) {
        Objects.requireNonNull(player, "player");
        Objects.requireNonNull(skin, "skin");

        var entry = new ConfirmSkinPacket.SkinEntry();
        entry.setValid(true);
        entry.setUuid(player.getUniqueId());
        entry.setGeoStr(skin.skinGeometry());
        // Isolate the packet pixel array from later source-skin edits while
        // retaining the immutable geometry text and UUID identity.
        entry.setSkinBytes(skin.skinData().data().clone());
        entry.setUidStr(String.valueOf(getNetEaseUid(player)));

        var packet = new ConfirmSkinPacket();
        packet.getEntries().add(entry);
        return List.of(packet);
    }

    /**
     * Returns the login UID for an actual player controlled by a NetEase
     * client. Other entities use the absolute hash of their hyphen-free UUID
     * text; this fallback is deterministic but may collide between distinct
     * entities.
     */
    private static long getNetEaseUid(EntityPlayer player) {
        if (player.isActualPlayer() && player.getController().isNetEasePlayer()) {
            return player.getController().getLoginData().getNetEaseData().uid();
        }

        // Entities without NetEase login data still need a numeric UID field;
        // derive a stable fallback without claiming account identity or
        // uniqueness.
        long uid = player.getUniqueId().toString().replace("-", "").hashCode();
        return Math.abs(uid);
    }
}
