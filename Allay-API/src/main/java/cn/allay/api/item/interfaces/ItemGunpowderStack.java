package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGunpowderStack extends ItemStack {
    ItemType<ItemGunpowderStack> GUNPOWDER_TYPE = ItemTypeBuilder
            .builder(ItemGunpowderStack.class)
            .vanillaItem(VanillaItemId.GUNPOWDER)
            .build();
}
