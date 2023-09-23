package cn.allay.api.item.interfaces.coralblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralBlockStack extends ItemStack {
    ItemType<ItemCoralBlockStack> CORAL_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemCoralBlockStack.class)
            .vanillaItem(VanillaItemId.CORAL_BLOCK)
            .build();
}
