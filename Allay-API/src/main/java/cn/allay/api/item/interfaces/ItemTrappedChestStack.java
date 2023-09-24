package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrappedChestStack extends ItemStack {
    ItemType<ItemTrappedChestStack> TRAPPED_CHEST_TYPE = ItemTypeBuilder
            .builder(ItemTrappedChestStack.class)
            .vanillaItem(VanillaItemId.TRAPPED_CHEST)
            .build();
}
