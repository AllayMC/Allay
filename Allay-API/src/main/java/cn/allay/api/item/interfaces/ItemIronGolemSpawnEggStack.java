package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronGolemSpawnEggStack extends ItemStack {
    ItemType<ItemIronGolemSpawnEggStack> IRON_GOLEM_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemIronGolemSpawnEggStack.class)
            .vanillaItem(VanillaItemId.IRON_GOLEM_SPAWN_EGG)
            .build();
}
