package cn.allay.api.item.interfaces.turtleegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTurtleEggStack extends ItemStack {
    ItemType<ItemTurtleEggStack> TURTLE_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTurtleEggStack.class)
            .vanillaItem(VanillaItemId.TURTLE_EGG)
            .build();
}
