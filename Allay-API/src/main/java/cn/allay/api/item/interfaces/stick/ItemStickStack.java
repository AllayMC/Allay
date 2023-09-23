package cn.allay.api.item.interfaces.stick;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickStack extends ItemStack {
    ItemType<ItemStickStack> STICK_TYPE = ItemTypeBuilder
            .builder(ItemStickStack.class)
            .vanillaItem(VanillaItemId.STICK)
            .build();
}
