package org.allaymc.server.network.protocol.v818;

import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * {@code PacketEncoder_v818} encodes primitive-shape updates and enables the
 * Tears music-disc sound for protocol v818. Shape packets contain either
 * converted geometry or removal notices, with one packet returned even for an
 * empty input collection; other operations reuse the baseline encoder.
 */
public class PacketEncoder_v818 extends PacketEncoder_v766 {
    /**
     * Creates the v818 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v818(ProtocolData data) {
        super(data);
    }

    /**
     * Returns whether this encoder accepts the requested sound event.
     * {@code RECORD_TEARS} is enabled here, while the parent predicate decides
     * every other event and retains its earlier compatibility restrictions.
     */
    @Override
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        // The v818 codec assigns RECORD_TEARS a sound ID, so allow it here
        // while retaining baseline exclusions for other events.
        return soundEvent == SoundEvent.RECORD_TEARS || super.supportsSoundEvent(soundEvent);
    }

    /**
     * Creates one packet containing each supplied shape converted for the
     * requested dimension. The collection must be nonnull, attachments come
     * from each shape, and an empty collection still returns a packet with no
     * shape entries.
     */
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

    /**
     * Creates one packet containing removal notices for the supplied shapes.
     * The collection must be nonnull, each notice retains the shape identity
     * selected by the conversion helper, and empty input still yields one
     * packet.
     */
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
