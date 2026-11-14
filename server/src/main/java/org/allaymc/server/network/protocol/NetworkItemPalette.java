package org.allaymc.server.network.protocol;

import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.updater.item.ItemStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The items the clients of a protocol know.
 * <p>
 * The current palette sends every item as it is. An older palette registers each current item under the name the
 * version knows it by, upgraded with the item state updater (e.g. {@code minecraft:chain} for
 * {@code minecraft:iron_chain}), with that version's definition data. Items the version lacks are sent as a similar
 * item it knows: the one from {@code item_fallbacks.json}, or for block items the item of the fallback block from
 * {@code block_fallbacks.json}. Custom items are sent as they are. Runtime IDs are the current ones, since the item
 * registry packet assigns them.
 * <p>
 * The palettes in {@code item_palettes/} combine {@code runtime_item_states.json} and {@code item_components.nbt} of
 * CloudburstMC/Data (Apache-2.0): 818 (a899625), 819 (61d0fcb), 827 (6b0c502), 844 (10d55f7), 859 (32ea82f),
 * 898 (282f39c), 924 (ecee374), 944 (0fb7745), 975 (eb77f9f), 1001 (4634cd1) and 2168 (619483e).
 */
@Slf4j
public final class NetworkItemPalette {
    private static final NetworkItemPalette CURRENT = new NetworkItemPalette("current", null, null);
    private static final Map<String, NetworkItemPalette> OLD_PALETTES = new ConcurrentHashMap<>();
    private static final int MAX_FALLBACK_DEPTH = 8;

    private final String name;
    private final Map<ItemType<?>, ItemType<?>> networkTypes;
    private final Map<ItemType<?>, Definition> definitions;

    /**
     * How a version defines an item.
     *
     * @param name           the name the version knows the item by
     * @param version        the item version
     * @param componentBased whether the item is component based
     * @param components     the component data, empty for items of version {@link ItemVersion#NONE}
     */
    public record Definition(String name, ItemVersion version, boolean componentBased, NbtMap components) {
    }

    private NetworkItemPalette(String name, Map<ItemType<?>, ItemType<?>> networkTypes, Map<ItemType<?>, Definition> definitions) {
        this.name = name;
        this.networkTypes = networkTypes;
        this.definitions = definitions;
    }

    /**
     * Returns the current item palette, which sends every item as it is.
     *
     * @return the current palette
     */
    public static NetworkItemPalette current() {
        return CURRENT;
    }

    /**
     * Returns an older item palette from {@code item_palettes/<name>.nbt}, loading it on first use.
     *
     * @param name the palette name, e.g. {@code 2168}
     * @return the palette
     */
    public static NetworkItemPalette of(String name) {
        return OLD_PALETTES.computeIfAbsent(name, NetworkItemPalette::load);
    }

    /**
     * Returns the name of this palette, {@code current} for the current one.
     *
     * @return the name
     */
    public String name() {
        return name;
    }

    /**
     * Returns whether this is the current palette.
     *
     * @return {@code true} for the current palette
     */
    public boolean isCurrent() {
        return networkTypes == null;
    }

    /**
     * Returns the item a client of this palette is sent instead of the given one: the item itself if the client knows
     * it, otherwise a similar item it knows, or air if there is none.
     *
     * @param itemType the item type
     * @return the item type to send
     */
    public ItemType<?> toNetwork(ItemType<?> itemType) {
        if (networkTypes == null) {
            return itemType;
        }
        return networkTypes.getOrDefault(itemType, itemType);
    }

    /**
     * Returns whether a client of this palette knows the given item.
     *
     * @param itemType the item type
     * @return {@code true} if the item is sent as it is
     */
    public boolean isKnown(ItemType<?> itemType) {
        return toNetwork(itemType) == itemType;
    }

    /**
     * Returns how this palette's version defines a vanilla item.
     *
     * @param itemType the item type
     * @return the definition, or {@code null} for the current palette, custom items and items the version lacks
     */
    public Definition definition(ItemType<?> itemType) {
        return definitions == null ? null : definitions.get(itemType);
    }

    @Override
    public String toString() {
        return "NetworkItemPalette[" + name + "]";
    }

    private static NetworkItemPalette load(String name) {
        NbtMap oldItems;
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource("item_palettes/" + name + ".nbt")))) {
            oldItems = (NbtMap) reader.readTag();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read item palette " + name, e);
        }

        var definitions = new IdentityHashMap<ItemType<?>, Definition>();
        oldItems.forEach((oldName, value) -> {
            var data = (NbtMap) value;
            var itemType = currentItemType(oldName);
            if (itemType == null) {
                return;
            }
            var definition = new Definition(
                    oldName,
                    ItemVersion.from(data.getInt("version")),
                    data.getBoolean("isComponentBased"),
                    data.getCompound("components").isEmpty() ? NbtMap.EMPTY
                            : NbtMap.builder().putCompound("components", data.getCompound("components")).build()
            );
            // An item the version knows by its current name wins over a renamed one.
            if (oldName.equals(itemType.getIdentifier().toString())) {
                definitions.put(itemType, definition);
            } else {
                definitions.putIfAbsent(itemType, definition);
            }
        });

        var itemFallbacks = readFallbacks("item_fallbacks.json");
        var blockFallbacks = readFallbacks("block_fallbacks.json");
        var networkTypes = new IdentityHashMap<ItemType<?>, ItemType<?>>();
        int unknownItems = 0;
        for (var itemType : Registries.ITEMS.getContent().values()) {
            if (isCustom(itemType) || definitions.containsKey(itemType)) {
                networkTypes.put(itemType, itemType);
                continue;
            }
            var networkType = resolve(itemType, definitions, itemFallbacks, blockFallbacks, 0);
            if (networkType == ItemTypes.AIR && itemType != ItemTypes.AIR) {
                unknownItems++;
            }
            networkTypes.put(itemType, networkType);
        }
        if (unknownItems > 0) {
            log.warn("{} items have no counterpart in item palette {} and are sent as air", unknownItems, name);
        }
        return new NetworkItemPalette(name, Map.copyOf(networkTypes), Map.copyOf(definitions));
    }

    private static ItemType<?> resolve(
            ItemType<?> itemType,
            Map<ItemType<?>, Definition> definitions,
            Map<Identifier, Identifier> itemFallbacks,
            Map<Identifier, Identifier> blockFallbacks,
            int depth
    ) {
        if (definitions.containsKey(itemType) || itemType == ItemTypes.AIR) {
            return itemType;
        }
        if (depth >= MAX_FALLBACK_DEPTH) {
            return ItemTypes.AIR;
        }

        var fallback = itemFallbacks.get(itemType.getIdentifier());
        if (fallback != null) {
            var fallbackType = Registries.ITEMS.get(fallback);
            if (fallbackType != null) {
                return resolve(fallbackType, definitions, itemFallbacks, blockFallbacks, depth + 1);
            }
        }

        var blockType = itemType.getBlockType();
        var blockFallback = blockType == null ? null : blockFallbacks.get(blockType.getIdentifier());
        var fallbackBlockType = blockFallback == null ? null : Registries.BLOCKS.get(blockFallback);
        if (fallbackBlockType != null) {
            return resolve(fallbackBlockType.getItemType(), definitions, itemFallbacks, blockFallbacks, depth + 1);
        }
        return ItemTypes.AIR;
    }

    private static ItemType<?> currentItemType(String oldName) {
        var itemType = Registries.ITEMS.get(new Identifier(oldName));
        if (itemType != null) {
            return itemType;
        }
        // Renamed since, e.g. minecraft:chain -> minecraft:iron_chain.
        var upgraded = ItemStateUpdaters.updateItemState(
                NbtMap.builder().putString("Name", oldName).putShort("Damage", (short) 0).build(),
                ProtocolInfo.ITEM_STATE_UPDATER.getVersion()
        );
        return Registries.ITEMS.get(new Identifier(upgraded.getString("Name")));
    }

    private static boolean isCustom(ItemType<?> itemType) {
        return itemType instanceof AllayItemType<?> allayItemType && allayItemType.isCustomItem();
    }

    private static Map<Identifier, Identifier> readFallbacks(String resource) {
        var fallbacks = new HashMap<Identifier, Identifier>();
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource(resource)))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry ->
                    fallbacks.put(new Identifier(entry.getKey()), new Identifier(entry.getValue().getAsString())));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read " + resource, e);
        }
        return fallbacks;
    }
}
