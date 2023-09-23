package cn.allay.api.item.interfaces.quartzbricks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzBricksStack extends ItemStack {
    ItemType<ItemQuartzBricksStack> QUARTZ_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemQuartzBricksStack.class)
            .vanillaItem(VanillaItemId.QUARTZ_BRICKS)
            .build();
}
