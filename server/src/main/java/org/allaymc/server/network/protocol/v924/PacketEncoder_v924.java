package org.allaymc.server.network.protocol.v924;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v898.PacketEncoder_v898;
import org.cloudburstmc.protocol.bedrock.packet.VoxelShapesPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PacketEncoder_v924 extends PacketEncoder_v898 {
    public PacketEncoder_v924(ProtocolData data) {
        super(data);
    }

    @Override
    public Collection<VoxelShapesPacket> encodeVoxelShapes() {
        var packet = new VoxelShapesPacket();
        packet.setNameMap(new HashMap<>());
        packet.setShapes(new ArrayList<>());
        packet.setCustomShapeCount(0);
        return List.of(packet);
    }
}
