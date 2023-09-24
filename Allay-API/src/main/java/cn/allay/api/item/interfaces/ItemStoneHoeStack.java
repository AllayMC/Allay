package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneHoeStack extends ItemStack {
    ItemType<ItemStoneHoeStack> STONE_HOE_TYPE = ItemTypeBuilder
            .builder(ItemStoneHoeStack.class)
            .vanillaItem(VanillaItemId.STONE_HOE)
            .build();
}
