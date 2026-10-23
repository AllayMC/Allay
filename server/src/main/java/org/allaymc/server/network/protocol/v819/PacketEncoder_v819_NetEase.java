package org.allaymc.server.network.protocol.v819;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.Skin;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.packet.ConfirmSkinPacket;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * {@code PacketEncoder_v819_NetEase} encodes NetEase additions to wire protocol
 * 819 while inheriting the same-version international packet operations.
 * {@code Skin} confirmation includes the player UUID, geometry, copied pixel
 * bytes, and a NetEase UID; entities without a NetEase controller use a
 * deterministic UUID-derived fallback that is not guaranteed unique.
 */
public class PacketEncoder_v819_NetEase extends PacketEncoder_v819 {
    /**
     * Creates the v819 NetEase encoder using the supplied protocol data. The
     * parent constructor retains that exact bundle and builds definition
     * lookups; callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v819_NetEase(ProtocolData data) {
        super(data);
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
