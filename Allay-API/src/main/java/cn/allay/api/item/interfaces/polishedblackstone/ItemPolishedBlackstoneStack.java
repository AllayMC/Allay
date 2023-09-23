package cn.allay.api.item.interfaces.polishedblackstone;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneStack> POLISHED_BLACKSTONE_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE)
            .build();
}
