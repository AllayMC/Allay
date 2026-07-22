package org.allaymc.server.network.protocol.v819;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v818.PacketEncoderV818;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/** International v819 encoder. */
public class PacketEncoderV819 extends PacketEncoderV818 {
    public PacketEncoderV819(ProtocolData data) {
        super(data);
    }

    @Override
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        return soundEvent == SoundEvent.RECORD_LAVA_CHICKEN || super.supportsSoundEvent(soundEvent);
    }
}
