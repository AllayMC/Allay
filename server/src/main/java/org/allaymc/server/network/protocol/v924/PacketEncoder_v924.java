package org.allaymc.server.network.protocol.v924;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v898.PacketEncoder_v898;
import org.cloudburstmc.protocol.bedrock.packet.VoxelShapesPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * {@code PacketEncoder_v924} supplies voxel-shape advertisement for protocol
 * v924 using a packet with empty shape and name collections. The custom shape
 * count is zero, but the encoder returns an actual packet rather than the
 * unsupported-operation {@code null} result inherited by earlier versions.
 */
public class PacketEncoder_v924 extends PacketEncoder_v898 {
    /**
     * Creates the v924 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v924(ProtocolData data) {
        super(data);
    }

    /**
     * Creates one voxel-shape packet with empty mutable name and shape
     * collections. The custom shape count is zero; each call returns a fresh
     * packet inside an unmodifiable singleton result list.
     */
    @Override
    public Collection<VoxelShapesPacket> encodeVoxelShapes() {
        var packet = new VoxelShapesPacket();
        packet.setNameMap(new HashMap<>());
        packet.setShapes(new ArrayList<>());
        packet.setCustomShapeCount(0);
        return List.of(packet);
    }
}
