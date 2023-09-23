package cn.allay.api.item.interfaces.polishedblackstonebricks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBricksStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneBricksStack> POLISHED_BLACKSTONE_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBricksStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICKS)
            .build();
}
