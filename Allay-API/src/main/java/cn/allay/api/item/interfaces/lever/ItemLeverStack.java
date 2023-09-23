package cn.allay.api.item.interfaces.lever;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeverStack extends ItemStack {
    ItemType<ItemLeverStack> LEVER_TYPE = ItemTypeBuilder
            .builder(ItemLeverStack.class)
            .vanillaItem(VanillaItemId.LEVER)
            .build();
}
