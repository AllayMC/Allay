package org.allaymc.server.item.creative;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemGroup;

import java.util.Collections;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class AllayCreativeItemGroup implements CreativeItemGroup {
    protected final AllayCreativeItemRegistry registry;
    @Getter
    protected final String name;
    @Getter
    protected final ItemStack icon;
    protected Map<Integer, ItemStack> items;

    public AllayCreativeItemGroup(AllayCreativeItemRegistry registry, String name, ItemStack icon) {
        this.registry = registry;
        this.name = name;
        this.icon = icon;
        this.items = new Int2ObjectOpenHashMap<>();
    }

    @Override
    public int registerItem(ItemStack itemStack) {
        var assigned = registry.assignIndex(itemStack);
        itemStack.setStackNetworkId(assigned);
        this.items.put(assigned, itemStack);
        return assigned;
    }

    @Override
    public Map<Integer, ItemStack> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
