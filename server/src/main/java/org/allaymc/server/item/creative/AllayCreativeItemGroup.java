package org.allaymc.server.item.creative;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.creative.CreativeItemEntry;
import org.allaymc.api.item.creative.CreativeItemGroup;

import java.util.Collections;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class AllayCreativeItemGroup implements CreativeItemGroup {
    protected final AllayCreativeItemRegistry registry;
    @Getter
    protected final CreativeItemCategory category;
    @Getter
    protected final String name;
    @Getter
    protected final ItemStack icon;
    @Getter
    protected final int index;

    protected Map<Integer, CreativeItemEntry> items;

    public AllayCreativeItemGroup(AllayCreativeItemRegistry registry, CreativeItemCategory category, String name, ItemStack icon) {
        this.registry = registry;
        this.category = category;
        this.name = name;
        this.icon = icon;
        this.index = registry.assignIndexForGroup(this);
        this.items = new Int2ObjectOpenHashMap<>();
    }

    @Override
    public CreativeItemEntry registerItem(ItemStack itemStack) {
        var entry = registry.assignIndexForEntry(this, itemStack);
        // NOTICE: 0 is not indexed by the client for items
        itemStack.setStackNetworkId(entry.index() + 1);
        this.items.put(entry.index(), entry);
        return entry;
    }

    @Override
    public Map<Integer, CreativeItemEntry> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
