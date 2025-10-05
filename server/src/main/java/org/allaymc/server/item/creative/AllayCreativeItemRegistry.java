package org.allaymc.server.item.creative;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.creative.CreativeItemCategory.Type;
import org.allaymc.api.item.creative.CreativeItemEntry;
import org.allaymc.api.item.creative.CreativeItemGroup;
import org.allaymc.api.item.creative.CreativeItemRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayCreativeItemRegistry implements CreativeItemRegistry {

    protected final Map<Type, AllayCreativeItemCategory> categories;
    protected final List<CreativeItemEntry> entries;
    protected final List<CreativeItemGroup> groups;

    public AllayCreativeItemRegistry() {
        this.categories = new EnumMap<>(Type.class);
        this.categories.put(Type.CONSTRUCTION, new AllayCreativeItemCategory(this, Type.CONSTRUCTION));
        this.categories.put(Type.NATURE, new AllayCreativeItemCategory(this, Type.NATURE));
        this.categories.put(Type.EQUIPMENT, new AllayCreativeItemCategory(this, Type.EQUIPMENT));
        this.categories.put(Type.ITEMS, new AllayCreativeItemCategory(this, Type.ITEMS));
        this.entries = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.load();
    }

    @SneakyThrows
    protected void load() {
        // Load groups
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("creative_groups.json")))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var group = entry.getAsJsonObject();
                var category = getCategory(Type.valueOf(group.get("category").getAsString().toUpperCase(Locale.ROOT)));
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

                var category = getCategory(Type.valueOf(item.getString("category").toUpperCase(Locale.ROOT)));
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
    }

    @Override
    public CreativeItemCategory getCategory(Type type) {
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
    public List<CreativeItemEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    @Override
    public List<CreativeItemGroup> getGroups() {
        return Collections.unmodifiableList(groups);
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
