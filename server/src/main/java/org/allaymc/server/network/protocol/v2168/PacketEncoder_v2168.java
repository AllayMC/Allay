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
 * {@code PacketEncoder_v2168} adapts inherited player-list and
 * item-stack-response payloads to the layouts used by protocol v2168. The
 * parent encoder still builds player identity, skin, and response content; this
 * layer supplies the extra representation details required by the selected
 * serializers.
 *
 * <p> Player-list actions move from the packet-level selection into every
 * entry, including player-list packets produced while updating a skin. The same
 * operation is applied after parent encoding so no entry loses its add or
 * remove action. Item-stack responses with {@code null} container lists are
 * replaced with equivalent responses containing empty lists, because the
 * serializer inspects container presence even on failed requests. Other
 * response fields and already nonnull container lists retain their original
 * values.
 */
public class PacketEncoder_v2168 extends PacketEncoder_v1001 {

    /**
     * Creates the v2168 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v2168(ProtocolData data) {
        super(data);
    }

    /**
     * Builds the inherited player-list update and copies its action into each
     * entry. The supplied player collection and controlled entities must
     * satisfy the parent encoder's requirements; entry identities and skin data
     * remain those already encoded.
     */
    @Override
    public PlayerListPacket encodePlayerList(Collection<? extends Player> players, boolean add, boolean trustSkins) {
        var packet = super.encodePlayerList(players, add, trustSkins);
        setEntryActions(packet);
        return packet;
    }

    /**
     * Builds the inherited skin-update sequence and adjusts any player-list
     * packets within it. Each such packet's action is copied into its entries,
     * while other packets and their order are retained unchanged.
     */
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

    /**
     * Builds an item-stack response packet whose container lists are safe for
     * the v2168 serializer. The parent validates and copies the response
     * collection before this adaptation examines its entries.
     *
     * <p> The serializer reads container presence even for failed requests, so
     * a {@code null} container list cannot be passed through. Such entries are
     * replaced with responses preserving success, result, and request ID but
     * using an empty list. Entries with existing container lists retain their
     * objects; this method does not modify the original response collection or
     * replace those existing lists.
     */
    @Override
    public ItemStackResponsePacket encodeItemStackResponse(Collection<ItemStackResponse> responses) {
        var packet = super.encodeItemStackResponse(responses);
        // The v2168 serializer inspects containers even for failed responses,
        // so replace null lists before serialization rather than dereferencing
        // them there.
        packet.getEntries().replaceAll(response -> response.containers() == null
                ? new ItemStackResponse(response.success(), response.result(), response.requestId(), List.of())
                : response);
        return packet;
    }

    /**
     * Copies a player-list packet's selected action into each existing entry.
     * The parent encoder places that action on the packet, while the v2168
     * serializer reads it from individual entries.
     *
     * <p> Updating entries after parent encoding preserves add or remove
     * meaning for ordinary player-list updates and the player-list packets
     * embedded in skin changes. The operation mutates those entries without
     * changing membership or order. Later entries added to the packet would
     * require another call, because this helper does not attach a live
     * relationship between the packet and entry fields.
     */
    private static void setEntryActions(PlayerListPacket packet) {
        // The selected serializer reads entry actions, so preserve the parent
        // packet action explicitly on every entry it already created.
        packet.getEntries().forEach(entry -> entry.setAction(packet.getAction()));
    }
}
