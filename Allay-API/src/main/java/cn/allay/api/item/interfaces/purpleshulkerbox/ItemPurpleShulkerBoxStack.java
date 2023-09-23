package cn.allay.api.item.interfaces.purpleshulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleShulkerBoxStack extends ItemStack {
    ItemType<ItemPurpleShulkerBoxStack> PURPLE_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemPurpleShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.PURPLE_SHULKER_BOX)
            .build();
}
