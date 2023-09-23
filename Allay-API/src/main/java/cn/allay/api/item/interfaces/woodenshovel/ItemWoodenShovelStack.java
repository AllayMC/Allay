package cn.allay.api.item.interfaces.woodenshovel;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenShovelStack extends ItemStack {
    ItemType<ItemWoodenShovelStack> WOODEN_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemWoodenShovelStack.class)
            .vanillaItem(VanillaItemId.WOODEN_SHOVEL)
            .build();
}
