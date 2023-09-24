package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherBootsStack extends ItemStack {
    ItemType<ItemLeatherBootsStack> LEATHER_BOOTS_TYPE = ItemTypeBuilder
            .builder(ItemLeatherBootsStack.class)
            .vanillaItem(VanillaItemId.LEATHER_BOOTS)
            .build();
}
