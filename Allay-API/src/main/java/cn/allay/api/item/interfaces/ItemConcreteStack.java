package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemConcreteStack extends ItemStack {
    ItemType<ItemConcreteStack> CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemConcreteStack.class)
            .vanillaItem(VanillaItemId.CONCRETE)
            .build();
}
