package cn.allay.api.item.interfaces.bambooslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooSlabStack extends ItemStack {
    ItemType<ItemBambooSlabStack> BAMBOO_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBambooSlabStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_SLAB)
            .build();
}
