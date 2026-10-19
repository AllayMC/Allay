package org.allaymc.server.network.protocol.v2168;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v1001.PacketEncoder_v1001;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;

import java.util.Collection;
import java.util.List;

/**
 * Packet encoder for protocol v2168.
 *
 * <p>Player list actions are carried by each entry from this version onward.</p>
 */
public class PacketEncoder_v2168 extends PacketEncoder_v1001 {

    public PacketEncoder_v2168(ProtocolData data) {
        super(data);
    }

    @Override
    public PlayerListPacket encodePlayerList(Collection<? extends Player> players, boolean add, boolean trustSkins) {
        var packet = super.encodePlayerList(players, add, trustSkins);
        setEntryActions(packet);
        return packet;
    }

    @Override
    public Collection<BedrockPacket> encodePlayerSkin(EntityPlayer player, boolean trustSkin) {
        var packets = super.encodePlayerSkin(player, trustSkin);
        for (var packet : packets) {
            if (packet instanceof PlayerListPacket playerList) {
                setEntryActions(playerList);
            }
        }
        return packets;
    }

    @Override
    public ItemStackResponsePacket encodeItemStackResponse(Collection<ItemStackResponse> responses) {
        var packet = super.encodeItemStackResponse(responses);
        // v2168 writes the container-presence flag for failed requests as well.
        packet.getEntries().replaceAll(response -> response.containers() == null
                ? new ItemStackResponse(response.success(), response.result(), response.requestId(), List.of())
                : response);
        return packet;
    }

    private static void setEntryActions(PlayerListPacket packet) {
        packet.getEntries().forEach(entry -> entry.setAction(packet.getAction()));
    }
}
