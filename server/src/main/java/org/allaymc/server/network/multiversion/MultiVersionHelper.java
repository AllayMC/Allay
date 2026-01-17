package org.allaymc.server.network.multiversion;

import org.allaymc.api.player.Player;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;

import java.util.List;

/**
 * @author daoge_cmd
 */
@MultiVersion(version = "*")
public final class MultiVersionHelper {
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

    public static void adaptCustomBlockProperties(Player player, List<BlockPropertyData> properties) {
        if (is1_21_110orHigher(player)) {
            return;
        }

        // For older clients, convert packed_bools to face_dimming
        for (int i = 0; i < properties.size(); i++) {
            var property = properties.get(i);
            var nbt = property.getProperties();
            var components = nbt.getCompound("components");
            var materialInstances = components.getCompound("minecraft:material_instances");
            if (materialInstances.isEmpty()) {
                continue;
            }

            var materials = materialInstances.getCompound("materials");
            var adaptedMaterials = NbtMap.builder();
            for (var entry : materials.entrySet()) {
                if (entry.getValue() instanceof NbtMap material) {
                    var adaptedMaterial = NbtMap.builder();
                    for (var materialEntry : material.entrySet()) {
                        if (materialEntry.getKey().equals("packed_bools")) {
                            // Convert "packed_bools" to "face_dimming" for older clients
                            byte packedBools = (Byte) materialEntry.getValue();
                            boolean faceDimming = (packedBools & 0x1) != 0;
                            adaptedMaterial.putBoolean("face_dimming", faceDimming);
                        } else {
                            adaptedMaterial.put(materialEntry.getKey(), materialEntry.getValue());
                        }
                    }
                    adaptedMaterials.putCompound(entry.getKey(), adaptedMaterial.build());
                }
            }

            var adaptedMaterialInstances = materialInstances.toBuilder()
                    .putCompound("materials", adaptedMaterials.build())
                    .build();
            var adaptedComponents = components.toBuilder()
                    .putCompound("minecraft:material_instances", adaptedMaterialInstances)
                    .build();
            var adaptedNbt = nbt.toBuilder()
                    .putCompound("components", adaptedComponents)
                    .build();

            properties.set(i, new BlockPropertyData(property.getName(), adaptedNbt));
        }
    }

    private static boolean is1_21_130orHigher(Player player) {
        return getCodec(player).getProtocolVersion() >= Bedrock_v898.CODEC.getProtocolVersion();
    }

    private static boolean is1_21_110orHigher(Player player) {
        return getCodec(player).getProtocolVersion() >= Bedrock_v844.CODEC.getProtocolVersion();
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
