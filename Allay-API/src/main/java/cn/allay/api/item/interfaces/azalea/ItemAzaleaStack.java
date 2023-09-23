package cn.allay.api.item.interfaces.azalea;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaStack extends ItemStack {
    ItemType<ItemAzaleaStack> AZALEA_TYPE = ItemTypeBuilder
            .builder(ItemAzaleaStack.class)
            .vanillaItem(VanillaItemId.AZALEA)
            .build();
}
