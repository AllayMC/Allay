package cn.allay.api.item.interfaces.skull;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkullStack extends ItemStack {
    ItemType<ItemSkullStack> SKULL_TYPE = ItemTypeBuilder
            .builder(ItemSkullStack.class)
            .vanillaItem(VanillaItemId.SKULL)
            .build();
}
