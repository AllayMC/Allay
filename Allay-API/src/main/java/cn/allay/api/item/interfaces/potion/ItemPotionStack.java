package cn.allay.api.item.interfaces.potion;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPotionStack extends ItemStack {
    ItemType<ItemPotionStack> POTION_TYPE = ItemTypeBuilder
            .builder(ItemPotionStack.class)
            .vanillaItem(VanillaItemId.POTION)
            .build();
}
