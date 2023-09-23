package cn.allay.api.item.interfaces.batspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBatSpawnEggStack extends ItemStack {
    ItemType<ItemBatSpawnEggStack> BAT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemBatSpawnEggStack.class)
            .vanillaItem(VanillaItemId.BAT_SPAWN_EGG)
            .build();
}
