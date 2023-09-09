package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoisonousPotatoStack extends ItemStack {
    ItemType<ItemPoisonousPotatoStack> POISONOUS_POTATO_TYPE = ItemTypeBuilder
            .builder(ItemPoisonousPotatoStack.class)
            .vanillaItem(VanillaItemId.POISONOUS_POTATO)
            .build();
}
