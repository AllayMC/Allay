package cn.allay.api.item.interfaces.endstone;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndStoneStack extends ItemStack {
    ItemType<ItemEndStoneStack> END_STONE_TYPE = ItemTypeBuilder
            .builder(ItemEndStoneStack.class)
            .vanillaItem(VanillaItemId.END_STONE)
            .build();
}
