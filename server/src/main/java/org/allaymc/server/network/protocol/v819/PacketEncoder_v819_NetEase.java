package org.allaymc.server.network.protocol.v819;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.Skin;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.packet.ConfirmSkinPacket;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/** NetEase-specific v819 encoder branch. */
public class PacketEncoder_v819_NetEase extends PacketEncoder_v819 {
    public PacketEncoder_v819_NetEase(ProtocolData data) {
        super(data);
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
