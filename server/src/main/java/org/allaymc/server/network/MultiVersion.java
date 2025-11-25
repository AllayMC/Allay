package org.allaymc.server.network;

import org.allaymc.api.player.Player;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;

import java.util.List;

/**
 * @author daoge_cmd
 */
public final class MultiVersion {
    public static void adaptExperimentData(Player player, List<ExperimentData> experiments) {
        if (is1_21_80(player)) {
            // Enables 2025 Content Drop 2 features
            experiments.add(new ExperimentData("y_2025_drop_2", true));
            // Enables the locator bar for 1.21.80 clients
            experiments.add(new ExperimentData("locator_bar", true));
            // Allows Vibrant Visuals to appear in the settings menu
            experiments.add(new ExperimentData("experimental_graphics", true));
        }
        if (is1_21_100(player)) {
            experiments.add(new ExperimentData("y_2025_drop_3", true));
        }
    }

    private static boolean is1_21_100(Player player) {
        return getCodec(player) == Bedrock_v827.CODEC;
    }

    private static boolean is1_21_80(Player player) {
        return getCodec(player) == Bedrock_v800.CODEC;
    }

    private static BedrockCodec getCodec(Player player) {
        return ((AllayPlayer) player).getSession().getCodec();
    }
}
