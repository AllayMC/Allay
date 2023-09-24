package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTurtleHelmetStack extends ItemStack {
    ItemType<ItemTurtleHelmetStack> TURTLE_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemTurtleHelmetStack.class)
            .vanillaItem(VanillaItemId.TURTLE_HELMET)
            .build();
}
