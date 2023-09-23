package cn.allay.api.item.interfaces.grayconcrete;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayConcreteStack extends ItemStack {
    ItemType<ItemGrayConcreteStack> GRAY_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemGrayConcreteStack.class)
            .vanillaItem(VanillaItemId.GRAY_CONCRETE)
            .build();
}
