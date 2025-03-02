package org.allaymc.server.item.creative;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
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
    @Getter
    protected final org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory type;

    protected Map<String, CreativeItemGroup> groups;

    public AllayCreativeItemCategory(AllayCreativeItemRegistry registry, org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory type) {
        this.registry = registry;
        this.type = type;
        this.groups = new Object2ObjectOpenHashMap<>();
    }

    @Override
    public CreativeItemGroup getGroup(String name) {
        return groups.get(name);
    }

    @Override
    public CreativeItemGroup registerGroup(String name, ItemStack icon) {
        var group = new AllayCreativeItemGroup(registry, this, name, icon);
        groups.put(name, group);
        return group;
    }

    @Override
    public Map<String, CreativeItemGroup> getGroups() {
        return Collections.unmodifiableMap(groups);
    }
}
