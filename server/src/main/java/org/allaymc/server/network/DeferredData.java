package org.allaymc.server.network;

import com.google.common.base.Suppliers;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * These data will be build when player join and will only be built once,
 * which allows plugins to register their custom stuff before player join.
 *
 * @author daoge_cmd
 */
public final class DeferredData {

    public static final Supplier<CraftingDataPacket> CRAFTING_DATA_PACKET = Suppliers.memoize(DeferredData::encodeCraftingDataPacket);
    public static final Supplier<List<ItemDefinition>> ITEM_DEFINITIONS = Suppliers.memoize(DeferredData::encodeItemDefinitions);
    public static final Supplier<List<BlockDefinition>> BLOCK_DEFINITIONS = Suppliers.memoize(DeferredData::encodeBlockDefinitions);
    public static final Supplier<AvailableEntityIdentifiersPacket> AVAILABLE_ENTITY_IDENTIFIERS_PACKET = Suppliers.memoize(DeferredData::encodeAvailableEntityIdentifiersPacket);
    public static final Supplier<BiomeDefinitionListPacket> BIOME_DEFINITION_LIST_PACKET = Suppliers.memoize(DeferredData::encodeBiomeDefinitionListPacket);
    public static final Supplier<ResourcePacksInfoPacket> RESOURCE_PACKS_INFO_PACKET = Suppliers.memoize(DeferredData::encodeResourcePacksInfoPacket);
    public static final Supplier<ResourcePackStackPacket> RESOURCES_PACK_STACK_PACKET = Suppliers.memoize(DeferredData::encodeResourcesPackStackPacket);
    public static final Supplier<TrimDataPacket> TRIM_DATA_PACKET = Suppliers.memoize(DeferredData::encodeTrimDataPacket);

    private static CraftingDataPacket encodeCraftingDataPacket() {
        var pk = new CraftingDataPacket();
        pk.getCraftingData().addAll(
                Registries.RECIPES.getContent().values().stream()
                        .map(Recipe::toNetworkData)
                        .toList()
        );
        pk.getCraftingData().addAll(
                Registries.FURNACE_RECIPES.getContent().values().stream()
                        .map(Recipe::toNetworkData)
                        .toList()
        );
        pk.getPotionMixData().addAll(
                Registries.POTION_MIX_RECIPES.getContent().values().stream()
                        .map(Recipe::toNetworkData)
                        .toList()
        );
        // TODO: packet.getContainerMixData().addAll();
        // TODO: packet.getMaterialReducers().addAll();
        pk.setCleanRecipes(true);
        return pk;
    }

    public static List<ItemDefinition> encodeItemDefinitions() {
        var list = new ArrayList<ItemDefinition>();
        for (var itemType : Registries.ITEMS.getContent().values()) {
            list.add(itemType.toNetworkDefinition());
        }
        return list;
    }

    public static List<BlockDefinition> encodeBlockDefinitions() {
        var list = new ArrayList<BlockDefinition>();
        for (var blockType : Registries.BLOCKS.getContent().values()) {
            blockType.getAllStates().forEach(state -> list.add(state.toNetworkBlockDefinition()));
        }
        return list;
    }

    public static AvailableEntityIdentifiersPacket encodeAvailableEntityIdentifiersPacket() {
        // TODO: support custom entity, we just read it from file currently
        try (var stream = NbtUtils.createNetworkReader(Utils.getResource("entity_identifiers.nbt"))) {
            var pk = new AvailableEntityIdentifiersPacket();
            pk.setIdentifiers((NbtMap) stream.readTag());
            return pk;
        } catch (Throwable t) {
            throw new AssertionError("Failed to load entity_identifiers.nbt", t);
        }
    }

    public static BiomeDefinitionListPacket encodeBiomeDefinitionListPacket() {
        // TODO: support custom biome. Same to entity, we just read it from file currently
        try (var stream = Utils.getResource("biome_definitions.nbt")) {
            var pk = new BiomeDefinitionListPacket();
            pk.setDefinitions((NbtMap) NbtUtils.createNetworkReader(stream).readTag());
            return pk;
        } catch (Exception e) {
            throw new AssertionError("Failed to load biome_definitions.nbt", e);
        }
    }

    public static ResourcePacksInfoPacket encodeResourcePacksInfoPacket() {
        var pk = new ResourcePacksInfoPacket();

        pk.setForcedToAccept(Server.SETTINGS.resourcePackSettings().forceResourcePacks());
        pk.setWorldTemplateId(new UUID(0, 0));
        pk.setWorldTemplateVersion("");

        for (var pack : Registries.PACKS.getContent().values()) {
            var type = pack.getType();
            if (type != Pack.Type.RESOURCES) {
                continue;
            }

            pk.getResourcePackInfos().add(pack.toEntryInfo());
        }

        return pk;
    }

    public static ResourcePackStackPacket encodeResourcesPackStackPacket() {
        var pk = new ResourcePackStackPacket();

        pk.setForcedToAccept(
                Server.SETTINGS.resourcePackSettings().forceResourcePacks() &&
                !Server.SETTINGS.resourcePackSettings().allowClientResourcePacks()
        );
        // Just left '*' here. If we put in an exact game version, it is possible that client
        // won't send back ResourcePackClientResponsePacket(packIds=[*], status=COMPLETED)
        pk.setGameVersion("*");

        for (var pack : Registries.PACKS.getContent().values()) {
            var type = pack.getType();
            if (type != Pack.Type.RESOURCES) {
                continue;
            }

            pk.getResourcePacks().add(pack.toEntryStack());
        }

        return pk;
    }

    public static TrimDataPacket encodeTrimDataPacket() {
        var packet = new TrimDataPacket();
        // TODO
        return packet;
    }
}
