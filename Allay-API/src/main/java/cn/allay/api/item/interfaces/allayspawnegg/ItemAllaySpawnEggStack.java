package cn.allay.api.item.interfaces.allayspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAllaySpawnEggStack extends ItemStack {
    ItemType<ItemAllaySpawnEggStack> ALLAY_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemAllaySpawnEggStack.class)
            .vanillaItem(VanillaItemId.ALLAY_SPAWN_EGG)
            .build();
}
