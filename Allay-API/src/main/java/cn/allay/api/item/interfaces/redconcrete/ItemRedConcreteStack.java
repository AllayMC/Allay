package cn.allay.api.item.interfaces.redconcrete;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedConcreteStack extends ItemStack {
    ItemType<ItemRedConcreteStack> RED_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemRedConcreteStack.class)
            .vanillaItem(VanillaItemId.RED_CONCRETE)
            .build();
}
