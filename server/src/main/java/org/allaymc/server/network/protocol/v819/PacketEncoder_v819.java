package org.allaymc.server.network.protocol.v819;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v818.PacketEncoder_v818;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/** International v819 encoder. */
public class PacketEncoder_v819 extends PacketEncoder_v818 {
    public PacketEncoder_v819(ProtocolData data) {
        super(data);
    }

    @Override
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        return soundEvent == SoundEvent.RECORD_LAVA_CHICKEN || super.supportsSoundEvent(soundEvent);
    }
}
