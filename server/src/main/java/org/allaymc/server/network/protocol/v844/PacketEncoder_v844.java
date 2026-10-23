package org.allaymc.server.network.protocol.v844;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v827.PacketEncoder_v827;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

import java.util.List;

/**
 * {@code PacketEncoder_v844} enables single-item and multiple-item swap sounds
 * for protocol v844 and restores the shared baseline experiment set. The
 * restored set omits the v827-specific drop experiment; other sound decisions
 * and packet operations continue through the inherited encoder implementation.
 */
public class PacketEncoder_v844 extends PacketEncoder_v827 {
    /**
     * Creates the v844 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v844(ProtocolData data) {
        super(data);
    }

    /**
     * Returns whether this encoder accepts the requested sound event.
     * {@code SINGLE_ITEM_SWAP} and {@code MULTI_ITEM_SWAP} are enabled here,
     * while the parent predicate decides every other event and retains its
     * earlier compatibility restrictions.
     */
    @Override
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        // The v844 codec assigns both swap events sound IDs, so allow those
        // events while preserving inherited support decisions for other sounds.
        return soundEvent == SoundEvent.SINGLE_ITEM_SWAP
                || soundEvent == SoundEvent.MULTI_ITEM_SWAP
                || super.supportsSoundEvent(soundEvent);
    }

    /**
     * Returns fresh baseline experiment payloads without the preceding
     * version's extra drop flag. Calling the shared baseline factory directly
     * avoids inheriting that flag from the v827 experiment-list override.
     */
    @Override
    protected List<ExperimentData> createExperiments() {
        // Bypass the v827 override so its version-specific drop flag does not
        // remain enabled through ordinary method inheritance.
        return createDefaultExperiments();
    }
}
