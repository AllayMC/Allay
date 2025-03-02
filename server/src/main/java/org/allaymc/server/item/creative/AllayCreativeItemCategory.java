package org.allaymc.server.item.creative;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.creative.CreativeItemGroup;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.Collections;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class AllayCreativeItemCategory implements CreativeItemCategory {
    protected final AllayCreativeItemRegistry registry;
    @Getter
    protected final org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory type;

    protected Map<Integer, CreativeItemGroup> groups;

    public AllayCreativeItemCategory(AllayCreativeItemRegistry registry, org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory type) {
        this.registry = registry;
        this.type = type;
        this.groups = new Int2ObjectOpenHashMap<>();
    }

    @Override
    public CreativeItemGroup getGroup(int index) {
        return groups.get(index);
    }

    @Override
    public CreativeItemGroup registerGroup(String name, ItemStack icon) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(icon);
        var group = new AllayCreativeItemGroup(registry, this, name, icon);
        groups.put(group.getIndex(), group);
        return group;
    }

    @Override
    public CreativeItemGroup registerUnnamedGroup() {
        var group = new AllayCreativeItemGroup(registry, this, "", ItemAirStack.AIR_STACK);
        groups.put(group.getIndex(), group);
        return group;
    }

    @Override
    public Map<Integer, CreativeItemGroup> getGroups() {
        return Collections.unmodifiableMap(groups);
    }
}
