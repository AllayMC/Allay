package cn.allay.api.item.interfaces.grindstone;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrindstoneStack extends ItemStack {
    ItemType<ItemGrindstoneStack> GRINDSTONE_TYPE = ItemTypeBuilder
            .builder(ItemGrindstoneStack.class)
            .vanillaItem(VanillaItemId.GRINDSTONE)
            .build();
}
