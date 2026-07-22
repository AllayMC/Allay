package org.allaymc.server.network.protocol.v827;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v819.PacketEncoder_v819;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;

import java.util.ArrayList;
import java.util.List;

/** International v827 encoder. */
public class PacketEncoder_v827 extends PacketEncoder_v819 {
    public PacketEncoder_v827(ProtocolData data) {
        super(data);
    }

    @Override
    protected List<ExperimentData> createExperiments() {
        var experiments = new ArrayList<>(super.createExperiments());
        experiments.add(new ExperimentData("y_2025_drop_3", true));
        return experiments;
    }
}
