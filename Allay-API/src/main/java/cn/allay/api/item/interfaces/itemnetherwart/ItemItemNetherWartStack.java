package cn.allay.api.item.interfaces.itemnetherwart;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemNetherWartStack extends ItemStack {
    ItemType<ItemItemNetherWartStack> ITEM_NETHER_WART_TYPE = ItemTypeBuilder
            .builder(ItemItemNetherWartStack.class)
            .vanillaItem(VanillaItemId.ITEM_NETHER_WART)
            .build();
}
