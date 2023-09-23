package cn.allay.api.item.interfaces.pinkconcrete;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkConcreteStack extends ItemStack {
    ItemType<ItemPinkConcreteStack> PINK_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemPinkConcreteStack.class)
            .vanillaItem(VanillaItemId.PINK_CONCRETE)
            .build();
}
