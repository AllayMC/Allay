package org.allaymc.server.network.protocol.v827;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v819.PacketEncoder_v819;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code PacketEncoder_v827} adds the {@code "y_2025_drop_3"} experiment to the
 * inherited experiment set for protocol v827. Each request copies the parent
 * list before appending the additional enabled flag, so the parent list is
 * retained without mutation and other packet operations remain inherited.
 */
public class PacketEncoder_v827 extends PacketEncoder_v819 {
    /**
     * Creates the v827 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v827(ProtocolData data) {
        super(data);
    }

    /**
     * Returns a mutable copy of the inherited experiment list with
     * {@code "y_2025_drop_3"} enabled. The parent list is left unchanged, and
     * the additional experiment object is created for this encoding request.
     */
    @Override
    protected List<ExperimentData> createExperiments() {
        var experiments = new ArrayList<>(super.createExperiments());
        // The v844 override restores the baseline set, so this flag belongs to
        // the v827 branch rather than the shared default experiment factory.
        experiments.add(new ExperimentData("y_2025_drop_3", true));
        return experiments;
    }
}
