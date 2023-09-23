package cn.allay.api.item.interfaces.redsandstone;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedSandstoneStack extends ItemStack {
    ItemType<ItemRedSandstoneStack> RED_SANDSTONE_TYPE = ItemTypeBuilder
            .builder(ItemRedSandstoneStack.class)
            .vanillaItem(VanillaItemId.RED_SANDSTONE)
            .build();
}
