package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAgentSpawnEggStack extends ItemStack {
    ItemType<ItemAgentSpawnEggStack> AGENT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemAgentSpawnEggStack.class)
            .vanillaItem(VanillaItemId.AGENT_SPAWN_EGG)
            .build();
}
