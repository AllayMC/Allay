package org.allaymc.server.network;

import com.google.common.base.Suppliers;
import lombok.experimental.UtilityClass;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.world.biome.BiomeData;
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
 * These data will be build when player join and will only be built once,
 * which allows plugins to register their custom stuff before player join.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class DeferredData {

    public static final Supplier<CraftingDataPacket> CRAFTING_DATA_PACKET = Suppliers.memoize(DeferredData::encodeCraftingDataPacket);
    public static final Supplier<List<ItemDefinition>> ITEM_DEFINITIONS = Suppliers.memoize(DeferredData::encodeItemDefinitions);
    public static final Supplier<List<BlockDefinition>> BLOCK_DEFINITIONS = Suppliers.memoize(DeferredData::encodeBlockDefinitions);
    public static final Supplier<AvailableEntityIdentifiersPacket> AVAILABLE_ENTITY_IDENTIFIERS_PACKET = Suppliers.memoize(DeferredData::encodeAvailableEntityIdentifiersPacket);
    public static final Supplier<BiomeDefinitionListPacket> BIOME_DEFINITION_LIST_PACKET = Suppliers.memoize(DeferredData::encodeBiomeDefinitionListPacket);
    public static final Supplier<List<ExperimentData>> EXPERIMENT_DATA_LIST = Suppliers.memoize(DeferredData::encodeExperimentDataList);
    public static final Supplier<ResourcePacksInfoPacket> RESOURCE_PACKS_INFO_PACKET = Suppliers.memoize(DeferredData::encodeResourcePacksInfoPacket);
    public static final Supplier<ResourcePackStackPacket> RESOURCES_PACK_STACK_PACKET = Suppliers.memoize(DeferredData::encodeResourcesPackStackPacket);
    public static final Supplier<TrimDataPacket> TRIM_DATA_PACKET = Suppliers.memoize(DeferredData::encodeTrimDataPacket);

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

    public static List<ItemDefinition> encodeItemDefinitions() {
        return Registries.ITEMS.getContent().values().stream()
                .map(ItemType::toNetworkDefinition)
                .toList();
    }

    public static List<BlockDefinition> encodeBlockDefinitions() {
        return Registries.BLOCKS.getContent().values().stream()
                .flatMap(block -> block.getAllStates().stream())
                .map(BlockState::toNetworkBlockDefinition)
                .toList();
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
        for (var biomeId : BiomeId.values()) {
            var biomeData = BiomeData.getBiomeData(biomeId);
            definitions.put(biomeId.getIdentifier().path(), biomeData.toNetworkData());
        }
        var packet = new BiomeDefinitionListPacket();
        packet.setBiomes(new BiomeDefinitions(definitions));
        return packet;
    }

    public static List<ExperimentData> encodeExperimentDataList() {
        // See https://learn.microsoft.com/en-us/minecraft/creator/documents/experimentalfeaturestoggle for info on each experiment
        return List.of(
                // data_driven_items (Holiday Creator Features) is needed for blocks and items
                new ExperimentData("data_driven_items", true),
                // Needed for block properties for states@
                new ExperimentData("upcoming_creator_features", true),
                // Needed for certain molang queries used in blocks and items
                new ExperimentData("experimental_molang_features", true)
        );
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
                packet.getResourcePackInfos().add(pack.toEntryInfo());
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
                packet.getResourcePacks().add(pack.toEntryStack());
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
