package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneShovelStack extends ItemStack {
    ItemType<ItemStoneShovelStack> STONE_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemStoneShovelStack.class)
            .vanillaItem(VanillaItemId.STONE_SHOVEL)
            .build();
}
