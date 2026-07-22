package org.allaymc.server.network.protocol.v844;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v827.PacketEncoderV827;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

import java.util.List;

/** International v844 encoder. */
public class PacketEncoderV844 extends PacketEncoderV827 {
    public PacketEncoderV844(ProtocolData data) {
        super(data);
    }

    @Override
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        return soundEvent == SoundEvent.SINGLE_ITEM_SWAP
                || soundEvent == SoundEvent.MULTI_ITEM_SWAP
                || super.supportsSoundEvent(soundEvent);
    }

    @Override
    protected List<ExperimentData> createExperiments() {
        return createDefaultExperiments();
    }
}
