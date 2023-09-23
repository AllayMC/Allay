package cn.allay.api.item.interfaces.trident;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTridentStack extends ItemStack {
    ItemType<ItemTridentStack> TRIDENT_TYPE = ItemTypeBuilder
            .builder(ItemTridentStack.class)
            .vanillaItem(VanillaItemId.TRIDENT)
            .build();
}
