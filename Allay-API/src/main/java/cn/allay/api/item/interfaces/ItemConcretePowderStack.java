package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemConcretePowderStack extends ItemStack {
    ItemType<ItemConcretePowderStack> CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemConcretePowderStack.class)
            .vanillaItem(VanillaItemId.CONCRETE_POWDER)
            .build();
}
