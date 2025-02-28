package org.allaymc.server.item.creative;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.creative.CreativeItemGroup;

import java.util.Collections;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class AllayCreativeItemCategory implements CreativeItemCategory {
    protected final AllayCreativeItemRegistry registry;
    protected Map<String, CreativeItemGroup> groups;

    public AllayCreativeItemCategory(AllayCreativeItemRegistry registry) {
        this.registry = registry;
        this.groups = new Object2ObjectOpenHashMap<>();
    }

    @Override
    public CreativeItemGroup getGroup(String name) {
        return groups.get(name);
    }

    @Override
    public CreativeItemGroup registerGroup(String name, ItemStack icon) {
        var group = new AllayCreativeItemGroup(registry, name, icon);
        groups.put(name, group);
        return group;
    }

    @Override
    public Map<String, CreativeItemGroup> getGroups() {
        return Collections.unmodifiableMap(groups);
    }
}
