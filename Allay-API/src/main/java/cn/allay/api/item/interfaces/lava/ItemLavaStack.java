package cn.allay.api.item.interfaces.lava;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLavaStack extends ItemStack {
    ItemType<ItemLavaStack> LAVA_TYPE = ItemTypeBuilder
            .builder(ItemLavaStack.class)
            .vanillaItem(VanillaItemId.LAVA)
            .build();
}
