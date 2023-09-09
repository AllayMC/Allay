package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherSproutsStack extends ItemStack {
    ItemType<ItemNetherSproutsStack> NETHER_SPROUTS_TYPE = ItemTypeBuilder
            .builder(ItemNetherSproutsStack.class)
            .vanillaItem(VanillaItemId.NETHER_SPROUTS)
            .build();
}
