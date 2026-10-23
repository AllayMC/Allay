package org.allaymc.server.network.protocol.v819;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v818.PacketEncoder_v818;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * {@code PacketEncoder_v819} extends v818 packet conversion with support for
 * the Lava Chicken music-disc sound. The inherited sound predicate continues to
 * determine other sound-event support, while packet construction and definition
 * lookup retain the protocol data supplied at encoder creation.
 */
public class PacketEncoder_v819 extends PacketEncoder_v818 {
    /**
     * Creates the v819 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v819(ProtocolData data) {
        super(data);
    }

    /**
     * Returns whether this encoder accepts the requested sound event.
     * {@code RECORD_LAVA_CHICKEN} is enabled here, while the parent predicate
     * decides every other event and retains its earlier compatibility
     * restrictions.
     */
    @Override
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        // The v819 codec assigns RECORD_LAVA_CHICKEN a sound ID, so allow it
        // here while retaining inherited restrictions for other events.
        return soundEvent == SoundEvent.RECORD_LAVA_CHICKEN || super.supportsSoundEvent(soundEvent);
    }
}
