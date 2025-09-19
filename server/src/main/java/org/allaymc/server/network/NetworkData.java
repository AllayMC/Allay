package org.allaymc.server.network;

import com.google.common.base.Suppliers;
import lombok.experimental.UtilityClass;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.registry.InternalRegistries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitions;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * A utility class that provides memoized suppliers for various encoded data packets and definitions.
 * These suppliers lazily compute and cache the results of their respective encoding operations,
 * simplifying access to precomputed data structures for use in other parts of the application.
 * <p>
 * This class serves purposes such as defining items, blocks, experiments, crafting data,
 * biome definitions, and resource pack information. The encoded data is typically used
 * for network communication with clients.
 * <p>
 * All members of this class are static and are designed for utility purposes.
 * Instances of this class cannot be created.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class NetworkData {

    public static final Supplier<List<ItemDefinition>> ITEM_DEFINITIONS = Suppliers.memoize(NetworkData::encodeItemDefinitions);
    public static final Supplier<List<BlockDefinition>> BLOCK_DEFINITIONS = Suppliers.memoize(NetworkData::encodeBlockDefinitions);
    public static final Supplier<List<ExperimentData>> EXPERIMENT_DATA_LIST = Suppliers.memoize(NetworkData::encodeExperimentDataList);
    public static final Supplier<CraftingDataPacket> CRAFTING_DATA_PACKET = Suppliers.memoize(NetworkData::encodeCraftingDataPacket);
    public static final Supplier<AvailableEntityIdentifiersPacket> AVAILABLE_ENTITY_IDENTIFIERS_PACKET = Suppliers.memoize(NetworkData::encodeAvailableEntityIdentifiersPacket);
    public static final Supplier<BiomeDefinitionListPacket> BIOME_DEFINITION_LIST_PACKET = Suppliers.memoize(NetworkData::encodeBiomeDefinitionListPacket);
    public static final Supplier<ResourcePacksInfoPacket> RESOURCE_PACKS_INFO_PACKET = Suppliers.memoize(NetworkData::encodeResourcePacksInfoPacket);
    public static final Supplier<ResourcePackStackPacket> RESOURCES_PACK_STACK_PACKET = Suppliers.memoize(NetworkData::encodeResourcesPackStackPacket);
    public static final Supplier<TrimDataPacket> TRIM_DATA_PACKET = Suppliers.memoize(NetworkData::encodeTrimDataPacket);

    public static List<ItemDefinition> encodeItemDefinitions() {
        return Registries.ITEMS.getContent().values().stream()
                .map(NetworkHelper::toNetwork)
                .toList();
    }

    public static List<BlockDefinition> encodeBlockDefinitions() {
        return Registries.BLOCKS.getContent().values().stream()
                .flatMap(block -> block.getAllStates().stream())
                .map(blockState -> (BlockDefinition) blockState::blockStateHash)
                .toList();
    }

    public static List<ExperimentData> encodeExperimentDataList() {
        // See https://learn.microsoft.com/en-us/minecraft/creator/documents/experimentalfeaturestoggle for info on each experiment
        return List.of(
                // data_driven_items (Holiday Creator Features) is needed for blocks and items
                new ExperimentData("data_driven_items", true),
                // Needed for block properties for states
                new ExperimentData("upcoming_creator_features", true),
                // Needed for certain molang queries used in blocks and items
                new ExperimentData("experimental_molang_features", true),
                // Copper chests and golem
                new ExperimentData("y_2025_drop_3", true)
        );
    }

    private static CraftingDataPacket encodeCraftingDataPacket() {
        var packet = new CraftingDataPacket();
        packet.getCraftingData().addAll(Registries.RECIPES.getContent().values().stream().map(Recipe::toNetworkData).toList());
        packet.getCraftingData().addAll(Registries.FURNACE_RECIPES.getContent().values().stream().map(Recipe::toNetworkData).toList());
        packet.getPotionMixData().addAll(Registries.POTION_MIX_RECIPES.getContent().values().stream().map(Recipe::toNetworkData).toList());
        // TODO: packet.getContainerMixData().addAll();
        // TODO: packet.getMaterialReducers().addAll();
        packet.setCleanRecipes(true);
        return packet;
    }

    public static AvailableEntityIdentifiersPacket encodeAvailableEntityIdentifiersPacket() {
        // TODO: support custom entity, we just read it from file currently
        try (var stream = NbtUtils.createNetworkReader(Utils.getResource("entity_identifiers.nbt"))) {
            var packet = new AvailableEntityIdentifiersPacket();
            packet.setIdentifiers((NbtMap) stream.readTag());
            return packet;
        } catch (Throwable t) {
            throw new RuntimeException("Failed to load entity_identifiers.nbt", t);
        }
    }

    public static BiomeDefinitionListPacket encodeBiomeDefinitionListPacket() {
        Map<String, BiomeDefinitionData> definitions = new HashMap<>();
        for (var biome : Registries.BIOMES.getContent().m1().values()) {
            definitions.put(biome.getIdentifier().path(), NetworkHelper.toNetwork(biome));
        }
        var packet = new BiomeDefinitionListPacket();
        packet.setBiomes(new BiomeDefinitions(definitions));
        return packet;
    }

    public static ResourcePacksInfoPacket encodeResourcePacksInfoPacket() {
        var settings = Server.SETTINGS.resourcePackSettings();

        var packet = new ResourcePacksInfoPacket();
        packet.setForcedToAccept(settings.forceResourcePacks());
        packet.setWorldTemplateId(new UUID(0, 0));
        packet.setWorldTemplateVersion("");
        packet.setVibrantVisualsForceDisabled(settings.disableVibrantVisuals());

        for (var pack : Registries.PACKS.getContent().values()) {
            var type = pack.getType();
            if (type == Pack.Type.RESOURCES) {
                packet.getResourcePackInfos().add(new ResourcePacksInfoPacket.Entry(
                        pack.getId(), pack.getStringVersion(), pack.getSize(), pack.getContentKey(),
                        "", pack.getId().toString(), pack.getType() == Pack.Type.SCRIPT,
                        pack.getManifest().getCapabilities().contains(PackManifest.Capability.RAYTRACED),
                        false, null
                ));
            }
        }

        return packet;
    }

    public static ResourcePackStackPacket encodeResourcesPackStackPacket() {
        var settings = Server.SETTINGS.resourcePackSettings();

        var packet = new ResourcePackStackPacket();
        packet.setForcedToAccept(settings.forceResourcePacks() && !settings.allowClientResourcePacks());
        // Just left '*' here. If we put in an exact game version, it is possible that client
        // won't send back ResourcePackClientResponsePacket(packIds=[*], status=COMPLETED)
        packet.setGameVersion("*");
        packet.getExperiments().addAll(EXPERIMENT_DATA_LIST.get());

        for (var pack : Registries.PACKS.getContent().values()) {
            var type = pack.getType();
            if (type == Pack.Type.RESOURCES) {
                packet.getResourcePacks().add(new ResourcePackStackPacket.Entry(
                        pack.getId().toString(), pack.getStringVersion(), ""
                ));
            }
        }

        return packet;
    }

    public static TrimDataPacket encodeTrimDataPacket() {
        var packet = new TrimDataPacket();
        packet.getPatterns().addAll(InternalRegistries.TRIM_PATTERNS.getContent());
        packet.getMaterials().addAll(InternalRegistries.TRIM_MATERIALS.getContent());
        return packet;
    }
}
