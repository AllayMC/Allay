package org.allaymc.server.network.protocol.v818;

import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v766.PacketEncoderV766;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/** International v818 encoder. */
public class PacketEncoderV818 extends PacketEncoderV766 {
    public PacketEncoderV818(ProtocolData data) {
        super(data);
    }

    @Override
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        return soundEvent == SoundEvent.RECORD_TEARS || super.supportsSoundEvent(soundEvent);
    }

    @Override
    public Collection<PrimitiveShapesPacket> encodePrimitiveShapes(
            Collection<? extends PrimitiveShape> primitiveShapes,
            int dimensionId
    ) {
        Objects.requireNonNull(primitiveShapes, "primitiveShapes");
        var packet = new PrimitiveShapesPacket();
        primitiveShapes.stream()
                .map(shape -> NetworkHelper.toNetwork(shape, dimensionId, shape.getAttachedEntity()))
                .forEach(packet.getShapes()::add);
        return List.of(packet);
    }

    @Override
    public Collection<PrimitiveShapesPacket> encodePrimitiveShapeRemovals(
            Collection<? extends PrimitiveShape> primitiveShapes
    ) {
        Objects.requireNonNull(primitiveShapes, "primitiveShapes");
        var packet = new PrimitiveShapesPacket();
        primitiveShapes.stream()
                .map(NetworkHelper::toNetworkRemovalNotice)
                .forEach(packet.getShapes()::add);
        return List.of(packet);
    }
}
