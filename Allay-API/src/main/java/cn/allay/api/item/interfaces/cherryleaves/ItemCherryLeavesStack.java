package cn.allay.api.item.interfaces.cherryleaves;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryLeavesStack extends ItemStack {
    ItemType<ItemCherryLeavesStack> CHERRY_LEAVES_TYPE = ItemTypeBuilder
            .builder(ItemCherryLeavesStack.class)
            .vanillaItem(VanillaItemId.CHERRY_LEAVES)
            .build();
}
