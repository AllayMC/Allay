package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickSlabStack extends ItemStack {
    ItemType<ItemMudBrickSlabStack> MUD_BRICK_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemMudBrickSlabStack.class)
            .vanillaItem(VanillaItemId.MUD_BRICK_SLAB)
            .build();
}
