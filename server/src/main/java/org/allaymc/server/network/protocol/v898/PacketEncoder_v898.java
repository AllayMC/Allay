package org.allaymc.server.network.protocol.v898;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v860.PacketEncoder_v860;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;

public class PacketEncoder_v898 extends PacketEncoder_v860 {
    public PacketEncoder_v898(ProtocolData data) {
        super(data);
    }

    @Override
    public ResourcePackStackPacket encodeResourcePackStack() {
        var packet = super.encodeResourcePackStack();
        // 1.21.130 stopped serializing behavior packs in the stack packet, so a behavior pack the client
        // downloaded is never activated unless it is listed as a resource pack.
        packet.getResourcePacks().addAll(packet.getBehaviorPacks());
        packet.getBehaviorPacks().clear();
        return packet;
    }
}
