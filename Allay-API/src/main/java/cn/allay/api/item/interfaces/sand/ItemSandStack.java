package cn.allay.api.item.interfaces.sand;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSandStack extends ItemStack {
    ItemType<ItemSandStack> SAND_TYPE = ItemTypeBuilder
            .builder(ItemSandStack.class)
            .vanillaItem(VanillaItemId.SAND)
            .build();
}
