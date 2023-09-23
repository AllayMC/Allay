package cn.allay.api.item.interfaces.drownedspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDrownedSpawnEggStack extends ItemStack {
    ItemType<ItemDrownedSpawnEggStack> DROWNED_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemDrownedSpawnEggStack.class)
            .vanillaItem(VanillaItemId.DROWNED_SPAWN_EGG)
            .build();
}
