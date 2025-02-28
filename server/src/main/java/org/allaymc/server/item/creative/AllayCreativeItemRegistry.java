package org.allaymc.server.item.creative;

import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemRegistry;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup;
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
    @Getter
    protected final AllayCreativeItemCategory constructionCategory;
    @Getter
    protected final AllayCreativeItemCategory natureCategory;
    @Getter
    protected final AllayCreativeItemCategory equipmentCategory;
    @Getter
    protected final AllayCreativeItemCategory itemsCategory;

    protected final Map<Integer, ItemStack> items;

    protected int indexCounter = 1;

    public AllayCreativeItemRegistry() {
        this.constructionCategory = new AllayCreativeItemCategory(this);
        this.natureCategory = new AllayCreativeItemCategory(this);
        this.equipmentCategory = new AllayCreativeItemCategory(this);
        this.itemsCategory = new AllayCreativeItemCategory(this);
        this.items = new Int2ObjectOpenHashMap<>();
        this.load();
    }

    @SneakyThrows
    protected void load() {
        log.info(I18n.get().tr(TrKeys.A_CREATIVEITEM_LOADING));

        // Load groups
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("creative_groups.json")))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var group = entry.getAsJsonObject();

                var categoryName = group.get("category").getAsString();
                var category = switch (categoryName) {
                    case "Construction" -> constructionCategory;
                    case "Nature" -> natureCategory;
                    case "Equipment" -> equipmentCategory;
                    case "Items" -> itemsCategory;
                    default -> throw new IllegalArgumentException("Unknown category: " + categoryName);
                };

                ItemStack iconItemStack;
                if (group.has("icon")) {
                    var iconItemTypeName = new Identifier(group.get("icon").getAsString());
                    var iconItemType = Registries.ITEMS.get(iconItemTypeName);
                    Objects.requireNonNull(iconItemType, "Unknown icon item type: " + iconItemTypeName);
                    iconItemStack = iconItemType.createItemStack();
                } else {
                    iconItemStack = ItemTypes.AIR.createItemStack();
                }
                category.registerGroup(group.get("name").getAsString(), iconItemStack);
            });
        }

        // Load items
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource("creative_items.nbt")))) {
            var items = ((NbtMap) reader.readTag()).getList("items", NbtType.COMPOUND);
            for (var item : items) {
                var itemTypeName = new Identifier(item.getString("name"));
                var itemType = Registries.ITEMS.get(itemTypeName);
                Objects.requireNonNull(itemType, "Unknown item type: " + itemTypeName);

                var categoryName = item.getString("category");
                var category = switch (categoryName) {
                    case "Construction" -> constructionCategory;
                    case "Nature" -> natureCategory;
                    case "Equipment" -> equipmentCategory;
                    case "Items" -> itemsCategory;
                    default -> throw new IllegalArgumentException("Unknown category: " + categoryName);
                };

                var itemStack = itemType.createItemStack(
                        ItemStackInitInfo
                                .builder().count(1).meta(item.getShort("damage"))
                                .extraTag(item.getCompound("tag", NbtMap.builder().build()))
                                .autoAssignStackNetworkId(false).build()
                );
                var groupName = item.getString("group");
                var group = category.getGroup(groupName);
                if (group == null) {
                    log.warn("Unknown group {} in category {}!", groupName, categoryName);
                    group = category.getDefaultGroup();
                }
                group.registerItem(itemStack);
            }
        }

        log.info(I18n.get().tr(TrKeys.A_CREATIVEITEM_LOADED));
    }

    @Override
    public ItemStack getItemStackByIndex(int index) {
        return items.get(index);
    }

    @Override
    public @UnmodifiableView Map<Integer, ItemStack> getItems() {
        return Collections.unmodifiableMap(items);
    }

    @Override
    public CreativeContentPacket encodeCreativeContentPacketFor(LangCode langCode) {
        var encodedGroups = new ArrayList<CreativeItemGroup>();
        var encodedItems = new ArrayList<CreativeItemData>();

        encodeGroups(constructionCategory, CreativeItemCategory.CONSTRUCTION, encodedGroups, encodedItems, langCode);
        encodeGroups(natureCategory, CreativeItemCategory.NATURE, encodedGroups, encodedItems, langCode);
        encodeGroups(equipmentCategory, CreativeItemCategory.EQUIPMENT, encodedGroups, encodedItems, langCode);
        encodeGroups(itemsCategory, CreativeItemCategory.ITEMS, encodedGroups, encodedItems, langCode);

        var pk = new CreativeContentPacket();
        pk.getGroups().addAll(encodedGroups);
        pk.getContents().addAll(encodedItems);
        return pk;
    }

    protected void encodeGroups(
            AllayCreativeItemCategory category, CreativeItemCategory encodedType,
            List<CreativeItemGroup> encodedGroups, List<CreativeItemData> encodedItems, LangCode langCode
    ) {
        int groupIdCounter = 1;
        for (var group : category.getGroups().values()) {
            encodedGroups.add(new CreativeItemGroup(encodedType, I18n.get().tr(langCode, group.getName()), group.getIcon().toNetworkItemData()));
            int finalGroupIdCounter = groupIdCounter++;
            group.getItems().forEach((index, itemStack) -> {
                encodedItems.add(new CreativeItemData(itemStack.toNetworkItemData(), index, finalGroupIdCounter));
            });
        }
    }

    int assignIndex(ItemStack itemStack) {
        var assigned = indexCounter++;
        items.put(assigned, itemStack);
        return assigned;
    }
}
