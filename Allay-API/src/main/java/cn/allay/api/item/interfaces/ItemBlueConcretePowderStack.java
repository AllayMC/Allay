package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueConcretePowderStack extends ItemStack {
    ItemType<ItemBlueConcretePowderStack> BLUE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemBlueConcretePowderStack.class)
            .vanillaItem(VanillaItemId.BLUE_CONCRETE_POWDER)
            .build();
}
