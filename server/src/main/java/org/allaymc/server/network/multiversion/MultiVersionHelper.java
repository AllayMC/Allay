package org.allaymc.server.network.multiversion;

import org.allaymc.api.player.Player;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;

import java.util.LinkedList;
import java.util.List;

/**
 * @author daoge_cmd
 */
@MultiVersion(version = "*")
public final class MultiVersionHelper {

    public static void adaptExperimentData(Player player, List<ExperimentData> experiments) {
        if (is1_21_100(player)) {
            experiments.add(new ExperimentData("y_2025_drop_3", true));
        }
    }

    public static void adaptCustomBlockProperties(Player player, List<BlockPropertyData> properties) {
        if (is1_21_130orHigher(player)) {
            return;
        }

        for (int i = 0; i < properties.size(); i++) {
            var property = properties.get(i);
            var nbt = property.getProperties();
            var builder = nbt.toBuilder();

            builder.putCompound("components", adaptComponents(player, nbt.getCompound("components")));

            var permutations = new LinkedList<NbtMap>();
            nbt.getList("permutations", NbtType.COMPOUND).forEach(permutation -> {
                permutations.add(permutation.toBuilder()
                        .putCompound("components", adaptComponents(player, permutation.getCompound("components")))
                        .build()
                );
            });
            builder.putList("permutations", NbtType.COMPOUND, permutations);

            properties.set(i, new BlockPropertyData(property.getName(), builder.build()));
        }
    }

    private static NbtMap adaptComponents(Player player, NbtMap components) {
        var adaptedComponents = components.toBuilder();

        // For clients < 1.21.130, convert collision_box from boxes format to origin/size format
        var collisionBox = components.getCompound("minecraft:collision_box");
        if (!collisionBox.isEmpty() && collisionBox.containsKey("boxes")) {
            adaptedComponents.putCompound("minecraft:collision_box", adaptCollisionBox(collisionBox));
        }

        // For clients < 1.21.110, convert packed_bools to face_dimming in material instances
        if (!is1_21_110orHigher(player)) {
            var materialInstances = components.getCompound("minecraft:material_instances");
            if (!materialInstances.isEmpty()) {
                adaptedComponents.putCompound("minecraft:material_instances", adaptMaterialInstances(materialInstances));
            }
        }

        return adaptedComponents.build();
    }

    private static NbtMap adaptCollisionBox(NbtMap collisionBox) {
        var boxes = collisionBox.getList("boxes", NbtType.COMPOUND);
        if (boxes.isEmpty()) {
            return NbtMap.builder()
                    .putBoolean("enabled", false)
                    .build();
        }

        // Calculate union of all boxes and convert to origin/size format
        var firstBox = boxes.getFirst();
        float minX = firstBox.getFloat("minX");
        float minY = firstBox.getFloat("minY");
        float minZ = firstBox.getFloat("minZ");
        float maxX = firstBox.getFloat("maxX");
        float maxY = firstBox.getFloat("maxY");
        float maxZ = firstBox.getFloat("maxZ");

        for (int j = 1; j < boxes.size(); j++) {
            var box = boxes.get(j);
            minX = Math.min(minX, box.getFloat("minX"));
            minY = Math.min(minY, box.getFloat("minY"));
            minZ = Math.min(minZ, box.getFloat("minZ"));
            maxX = Math.max(maxX, box.getFloat("maxX"));
            maxY = Math.max(maxY, box.getFloat("maxY"));
            maxZ = Math.max(maxZ, box.getFloat("maxZ"));
        }

        // Convert to origin/size format (origin is relative to block center)
        float originX = minX - 8;
        float originY = minY;
        float originZ = minZ - 8;
        float sizeX = maxX - minX;
        float sizeY = Math.min(maxY - minY, 16); // Cap size Y at 16 for older clients
        float sizeZ = maxZ - minZ;

        return NbtMap.builder()
                .putBoolean("enabled", collisionBox.getBoolean("enabled"))
                .putList("origin", NbtType.FLOAT, List.of(originX, originY, originZ))
                .putList("size", NbtType.FLOAT, List.of(sizeX, sizeY, sizeZ))
                .build();
    }

    private static NbtMap adaptMaterialInstances(NbtMap materialInstances) {
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

        return materialInstances.toBuilder()
                .putCompound("materials", adaptedMaterials.build())
                .build();
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

    private static BedrockCodec getCodec(Player player) {
        return ((AllayPlayer) player).getSession().getCodec();
    }
}
