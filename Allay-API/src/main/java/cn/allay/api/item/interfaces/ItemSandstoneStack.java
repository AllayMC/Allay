package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSandstoneStack extends ItemStack {
    ItemType<ItemSandstoneStack> SANDSTONE_TYPE = ItemTypeBuilder
            .builder(ItemSandstoneStack.class)
            .vanillaItem(VanillaItemId.SANDSTONE)
            .build();
}
