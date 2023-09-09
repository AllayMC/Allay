package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSlimeBallStack extends ItemStack {
    ItemType<ItemSlimeBallStack> SLIME_BALL_TYPE = ItemTypeBuilder
            .builder(ItemSlimeBallStack.class)
            .vanillaItem(VanillaItemId.SLIME_BALL)
            .build();
}
