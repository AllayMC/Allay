package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBricksStack extends ItemStack {
    ItemType<ItemDeepslateBricksStack> DEEPSLATE_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBricksStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICKS)
            .build();
}
