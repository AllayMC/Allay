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

public class PacketEncoder_v766_NetEase extends PacketEncoder_v766 {
    public PacketEncoder_v766_NetEase(ProtocolData data) {
        super(data);
    }

    @Override
    public BiomeDefinitionListPacket encodeBiomeDefinitions() {
        var definitions = NbtMap.builder();
        for (var biomeType : Registries.BIOMES.getContent().m1().values()) {
            var biome = biomeType.getBiomeData();
            var color = biome.mapWaterColor();
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

    @Override
    public Collection<ConfirmSkinPacket> encodeSkinConfirmation(EntityPlayer player, Skin skin) {
        Objects.requireNonNull(player, "player");
        Objects.requireNonNull(skin, "skin");

        var entry = new ConfirmSkinPacket.SkinEntry();
        entry.setValid(true);
        entry.setUuid(player.getUniqueId());
        entry.setGeoStr(skin.skinGeometry());
        entry.setSkinBytes(skin.skinData().data().clone());
        entry.setUidStr(String.valueOf(getNetEaseUid(player)));

        var packet = new ConfirmSkinPacket();
        packet.getEntries().add(entry);
        return List.of(packet);
    }

    private static long getNetEaseUid(EntityPlayer player) {
        if (player.isActualPlayer() && player.getController().isNetEasePlayer()) {
            return player.getController().getLoginData().getNetEaseData().uid();
        }

        long uid = player.getUniqueId().toString().replace("-", "").hashCode();
        return Math.abs(uid);
    }
}
