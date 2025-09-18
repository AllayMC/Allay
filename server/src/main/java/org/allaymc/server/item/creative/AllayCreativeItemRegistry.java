package org.allaymc.server.item.creative;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemEntry;
import org.allaymc.api.item.creative.CreativeItemGroup;
import org.allaymc.api.item.creative.CreativeItemRegistry;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.utils.NetworkHelper;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.packet.CreativeContentPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayCreativeItemRegistry implements CreativeItemRegistry {

    protected final Map<CreativeItemCategory, AllayCreativeItemCategory> categories;
    protected final List<CreativeItemEntry> entries;
    protected final List<CreativeItemGroup> groups;
    protected final Map<LangCode, CreativeContentPacket> cachedPackets;

    public AllayCreativeItemRegistry() {
        this.categories = new EnumMap<>(CreativeItemCategory.class);
        this.categories.put(CreativeItemCategory.CONSTRUCTION, new AllayCreativeItemCategory(this, CreativeItemCategory.CONSTRUCTION));
        this.categories.put(CreativeItemCategory.NATURE, new AllayCreativeItemCategory(this, CreativeItemCategory.NATURE));
        this.categories.put(CreativeItemCategory.EQUIPMENT, new AllayCreativeItemCategory(this, CreativeItemCategory.EQUIPMENT));
        this.categories.put(CreativeItemCategory.ITEMS, new AllayCreativeItemCategory(this, CreativeItemCategory.ITEMS));
        this.entries = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.cachedPackets = new EnumMap<>(LangCode.class);
        this.load();
    }

    @SneakyThrows
    protected void load() {
        log.info(I18n.get().tr(TrKeys.ALLAY_CREATIVEITEM_LOADING));

        // Load groups
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("creative_groups.json")))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var group = entry.getAsJsonObject();
                var category = getCategory(CreativeItemCategory.valueOf(group.get("category").getAsString().toUpperCase()));
                var name = group.get("name").getAsString();
                if (name.isEmpty()) {
                    category.registerUnnamedGroup();
                } else {
                    var iconItemTypeName = new Identifier(group.get("icon").getAsString());
                    var iconItemType = Registries.ITEMS.get(iconItemTypeName);
                    Objects.requireNonNull(iconItemType, "Unknown icon item type: " + iconItemTypeName);
                    category.registerGroup(name, iconItemType.createItemStack());
                }
            });
        }

        // Load items
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource("creative_items.nbt")))) {
            var items = ((NbtMap) reader.readTag()).getList("items", NbtType.COMPOUND);
            for (var item : items) {
                var itemTypeName = new Identifier(item.getString("name"));
                var itemType = Registries.ITEMS.get(itemTypeName);
                Objects.requireNonNull(itemType, "Unknown item type: " + itemTypeName);

                var category = getCategory(CreativeItemCategory.valueOf(item.getString("category").toUpperCase()));
                var itemStack = itemType.createItemStack(
                        ItemStackInitInfo
                                .builder().count(1).meta(item.getShort("damage"))
                                .extraTag(item.getCompound("tag", NbtMap.builder().build()))
                                .assignUniqueId(false).build()
                );
                int groupIndex = (int) item.getLong("groupIndex");
                var group = category.getGroup(groupIndex);
                if (group == null) {
                    log.warn("Unknown group index {} for item {} in category {}!", groupIndex, itemTypeName, category.getType());
                    continue;
                }

                group.registerItem(itemStack);
            }
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_CREATIVEITEM_LOADED));
    }

    @Override
    public org.allaymc.api.item.creative.CreativeItemCategory getCategory(CreativeItemCategory type) {
        return categories.get(type);
    }

    @Override
    public CreativeItemEntry getEntryByIndex(int index) {
        return entries.get(index);
    }

    @Override
    public CreativeItemGroup getGroupByIndex(int index) {
        return groups.get(index);
    }

    @Override
    public @UnmodifiableView List<CreativeItemEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    @Override
    public CreativeContentPacket getCreativeContentPacketFor(LangCode langCode) {
        return cachedPackets.computeIfAbsent(langCode, $ -> encodeCreativeContentPacketFor(langCode));
    }

    protected CreativeContentPacket encodeCreativeContentPacketFor(LangCode langCode) {
        var encodedGroups = new ArrayList<org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup>();
        for (var group : groups) {
            encodedGroups.add(new org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup(
                    group.getCategory().getType(), I18n.get().tr(langCode, group.getName()),
                    NetworkHelper.toNetwork(group.getIcon())
            ));
        }

        var encodedItems = new ArrayList<CreativeItemData>();
        for (var entry : entries) {
            // NOTICE: 0 is not indexed by the client for items
            encodedItems.add(new CreativeItemData(NetworkHelper.toNetwork(entry.itemStack()), entry.index() + 1, entry.group().getIndex()));
        }

        var pk = new CreativeContentPacket();
        pk.getGroups().addAll(encodedGroups);
        pk.getContents().addAll(encodedItems);
        return pk;
    }

    CreativeItemEntry assignIndexForEntry(CreativeItemGroup group, ItemStack itemStack) {
        var entry = new CreativeItemEntry(entries.size(), group, itemStack);
        entries.add(entry);
        return entry;
    }

    int assignIndexForGroup(CreativeItemGroup group) {
        groups.add(group);
        return groups.size() - 1;
    }
}
