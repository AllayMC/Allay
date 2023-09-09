package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherWartStack extends ItemStack {
    ItemType<ItemNetherWartStack> NETHER_WART_TYPE = ItemTypeBuilder
            .builder(ItemNetherWartStack.class)
            .vanillaItem(VanillaItemId.NETHER_WART)
            .build();
}
