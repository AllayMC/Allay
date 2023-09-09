package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenShovelStack extends ItemStack {
    ItemType<ItemGoldenShovelStack> GOLDEN_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemGoldenShovelStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_SHOVEL)
            .build();
}
