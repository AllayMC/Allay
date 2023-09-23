package cn.allay.api.item.interfaces.blueice;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueIceStack extends ItemStack {
    ItemType<ItemBlueIceStack> BLUE_ICE_TYPE = ItemTypeBuilder
            .builder(ItemBlueIceStack.class)
            .vanillaItem(VanillaItemId.BLUE_ICE)
            .build();
}
