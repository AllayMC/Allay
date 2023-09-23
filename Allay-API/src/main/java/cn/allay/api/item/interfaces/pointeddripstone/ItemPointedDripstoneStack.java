package cn.allay.api.item.interfaces.pointeddripstone;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPointedDripstoneStack extends ItemStack {
    ItemType<ItemPointedDripstoneStack> POINTED_DRIPSTONE_TYPE = ItemTypeBuilder
            .builder(ItemPointedDripstoneStack.class)
            .vanillaItem(VanillaItemId.POINTED_DRIPSTONE)
            .build();
}
