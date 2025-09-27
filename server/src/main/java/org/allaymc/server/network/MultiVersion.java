package org.allaymc.server.network;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;

import java.util.List;

/**
 * @author daoge_cmd
 */
public final class MultiVersion {
    public static void adaptExperimentData(EntityPlayer player, List<ExperimentData> experiments) {
        if (is1_21_80(player)) {
            // Enables 2025 Content Drop 2 features
            experiments.add(new ExperimentData("y_2025_drop_2", true));
            // Enables the locator bar for 1.21.80 clients
            experiments.add(new ExperimentData("locator_bar", true));
            // Allows Vibrant Visuals to appear in the settings menu
            experiments.add(new ExperimentData("experimental_graphics", true));
        }
        if (!is1_21_100(player)) {
            experiments.removeIf(experimentData -> experimentData.getName().equals("y_2025_drop_3"));
        }
    }

    private static boolean is1_21_100(EntityPlayer player) {
        return getCodec(player) == Bedrock_v827.CODEC;
    }

    private static boolean is1_21_80(EntityPlayer player) {
        return getCodec(player) == Bedrock_v800.CODEC;
    }

    private static BedrockCodec getCodec(EntityPlayer player) {
        return ((EntityPlayerImpl) player).getClientSession().getCodec();
    }
}
