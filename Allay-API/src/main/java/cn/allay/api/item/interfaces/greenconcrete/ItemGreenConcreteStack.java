package cn.allay.api.item.interfaces.greenconcrete;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenConcreteStack extends ItemStack {
    ItemType<ItemGreenConcreteStack> GREEN_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemGreenConcreteStack.class)
            .vanillaItem(VanillaItemId.GREEN_CONCRETE)
            .build();
}
