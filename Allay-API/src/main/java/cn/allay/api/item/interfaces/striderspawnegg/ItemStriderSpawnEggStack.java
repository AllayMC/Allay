package cn.allay.api.item.interfaces.striderspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStriderSpawnEggStack extends ItemStack {
    ItemType<ItemStriderSpawnEggStack> STRIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemStriderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.STRIDER_SPAWN_EGG)
            .build();
}
