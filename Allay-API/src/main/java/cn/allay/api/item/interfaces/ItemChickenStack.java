package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChickenStack extends ItemStack {
    ItemType<ItemChickenStack> CHICKEN_TYPE = ItemTypeBuilder
            .builder(ItemChickenStack.class)
            .vanillaItem(VanillaItemId.CHICKEN)
            .build();
}
