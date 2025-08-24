package org.allaymc.server.network;

import com.google.common.base.Suppliers;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public final class MultiVersion {

    public static final Supplier<ResourcePackStackPacket> RESOURCES_PACK_STACK_PACKET_1_21_80 = Suppliers.memoize(MultiVersion::encodeResourcesPackStackPacketFor1_21_80);

    public static boolean is1_21_80(EntityPlayer player) {
        return player.getClientSession().getCodec() == Bedrock_v800.CODEC;
    }

    public static void addExperimentsFor1_21_80(List<ExperimentData> experiments) {
        // Enables 2025 Content Drop 2 features
        experiments.add(new ExperimentData("y_2025_drop_2", true));
        // Enables the locator bar for 1.21.80 clients
        experiments.add(new ExperimentData("locator_bar", true));
        // Allows Vibrant Visuals to appear in the settings menu
        experiments.add(new ExperimentData("experimental_graphics", true));
    }

    private static ResourcePackStackPacket encodeResourcesPackStackPacketFor1_21_80() {
        var pk = DeferredData.RESOURCES_PACK_STACK_PACKET.get();
        var packet = new ResourcePackStackPacket();
        packet.setForcedToAccept(pk.isForcedToAccept());
        packet.setGameVersion(pk.getGameVersion());
        packet.getExperiments().addAll(pk.getExperiments());
        addExperimentsFor1_21_80(packet.getExperiments());
        packet.getResourcePacks().addAll(pk.getResourcePacks());
        return packet;
    }
}
