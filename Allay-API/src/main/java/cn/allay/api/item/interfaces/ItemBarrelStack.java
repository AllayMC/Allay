package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBarrelStack extends ItemStack {
    ItemType<ItemBarrelStack> BARREL_TYPE = ItemTypeBuilder
            .builder(ItemBarrelStack.class)
            .vanillaItem(VanillaItemId.BARREL)
            .build();
}
