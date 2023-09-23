package cn.allay.api.item.interfaces.balloon;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBalloonStack extends ItemStack {
    ItemType<ItemBalloonStack> BALLOON_TYPE = ItemTypeBuilder
            .builder(ItemBalloonStack.class)
            .vanillaItem(VanillaItemId.BALLOON)
            .build();
}
