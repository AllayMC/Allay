package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleConcreteStack extends ItemStack {
    ItemType<ItemPurpleConcreteStack> PURPLE_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleConcreteStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CONCRETE)
            .build();
}
