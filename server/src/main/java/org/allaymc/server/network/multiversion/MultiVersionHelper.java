package org.allaymc.server.network.multiversion;

import org.allaymc.api.player.Player;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.BiomeDefinitionListPacket;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author daoge_cmd
 */
@MultiVersion(version = "*")
public final class MultiVersionHelper {

    public static BiomeDefinitionListPacket adaptBiomeDefinitionListPacket(Player player, BiomeDefinitionListPacket packet) {
        if (!is1_21_50(player)) {
            return packet;
        }

        var builder = NbtMap.builder();
        for (var entry : packet.getBiomes().getDefinitions().entrySet()) {
            var identifier = entry.getKey();
            var definition = entry.getValue();

            builder.putCompound(identifier, NbtMap.builder()
                    .putFloat("ash", definition.getAshDensity())
                    .putFloat("blue_spores", definition.getBlueSporeDensity())
                    .putFloat("depth", definition.getDepth())
                    .putFloat("downfall", definition.getDownfall())
                    .putFloat("height", definition.getScale())
                    .putBoolean("rain", definition.isRain())
                    .putFloat("red_spores", definition.getRedSporeDensity())
                    .putList("tags", NbtType.STRING, definition.getTags())
                    .putFloat("temperature", definition.getTemperature())
                    .putFloat("waterColorA", definition.getMapWaterColor().getAlpha() / 256f)
                    .putFloat("waterColorB", definition.getMapWaterColor().getBlue() / 256f)
                    .putFloat("waterColorG", definition.getMapWaterColor().getGreen() / 256f)
                    .putFloat("waterColorR", definition.getMapWaterColor().getRed() / 256f)
                    .putFloat("white_ash", definition.getWhiteAshDensity())
                    .build()
            );
        }

        packet.setDefinitions(builder.build());
        packet.setBiomes(null);
        return packet;
    }

    public static void adaptItemDefinitions(Player player, List<ItemDefinition> definitions) {
        if (!is1_21_50(player)) {
            return;
        }

        // Remove the items that do not exist in 1.21.50
        definitions.removeIf(def -> {
            var definition = (SimpleItemDefinition) def;
            var identifier = definition.getIdentifier();
            if (!identifier.startsWith(Identifier.DEFAULT_NAMESPACE)){
                // Skip custom items
                return false;
            }

            if (identifier.contains("lightning_rod") &&
                !identifier.equals("minecraft:lightning_rod")) {
                return true;
            }

            if (Stream.of("minecraft:bush", "minecraft:firefly_bush").anyMatch(identifier::equals)) {
                return true;
            }

            // TODO: find out why exclude "wildflowers", "cactus_flower" will crash the client
            return Stream.of(
                    // Copper
                    "copper_bars", "copper_golem", "copper_lantern", "copper_chain",
                    "copper_helmet", "copper_chestplate", "copper_leggings", "copper_boots",
                    "copper_sword", "copper_axe", "copper_pickaxe", "copper_shovel", "copper_hoe",
                    "copper_horse_armor", "copper_torch", "copper_chest", "copper_nugget",
                    // Misc
                    "happy_ghast", "dried_ghast", "netherite_horse_armor", "harness", "nautilus_armor",
                    "nautilus_spawn_egg", "dry_grass", "spear", "_shelf", "iron_chain", "leaf_litter",
                    "brown_egg", "blue_egg", "camel_husk_spawn_egg", "parched_spawn_egg", "music_disc_tears",
                    "music_disc_lava_chicken"
            ).anyMatch(identifier::contains);
        });
    }

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
        if (is1_21_130orHigher(player)) {
            return;
        }

        boolean needMaterialAdapt = !is1_21_110orHigher(player);

        for (int i = 0; i < properties.size(); i++) {
            var property = properties.get(i);
            var nbt = property.getProperties();
            var components = nbt.getCompound("components");
            var adaptedComponents = components.toBuilder();

            // For clients < 1.21.130, convert collision_box from boxes format to origin/size format
            var collisionBox = components.getCompound("minecraft:collision_box");
            if (!collisionBox.isEmpty() && collisionBox.containsKey("boxes")) {
                adaptedComponents.putCompound("minecraft:collision_box", adaptCollisionBox(collisionBox));
            }

            // For clients < 1.21.110, convert packed_bools to face_dimming in material instances
            if (needMaterialAdapt) {
                var materialInstances = components.getCompound("minecraft:material_instances");
                if (!materialInstances.isEmpty()) {
                    adaptedComponents.putCompound("minecraft:material_instances", adaptMaterialInstances(materialInstances));
                }
            }

            var adaptedNbt = nbt.toBuilder()
                    .putCompound("components", adaptedComponents.build())
                    .build();

            properties.set(i, new BlockPropertyData(property.getName(), adaptedNbt));
        }
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

    public static boolean is1_21_50(Player player) {
        return getCodec(player).getProtocolVersion() == Bedrock_v766.CODEC.getProtocolVersion();
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
