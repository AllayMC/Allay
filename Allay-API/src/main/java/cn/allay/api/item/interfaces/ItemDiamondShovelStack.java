package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondShovelStack extends ItemStack {
    ItemType<ItemDiamondShovelStack> DIAMOND_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemDiamondShovelStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_SHOVEL)
            .build();
}
