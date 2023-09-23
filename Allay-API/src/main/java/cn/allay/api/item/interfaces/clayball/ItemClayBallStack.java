package cn.allay.api.item.interfaces.clayball;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClayBallStack extends ItemStack {
    ItemType<ItemClayBallStack> CLAY_BALL_TYPE = ItemTypeBuilder
            .builder(ItemClayBallStack.class)
            .vanillaItem(VanillaItemId.CLAY_BALL)
            .build();
}
