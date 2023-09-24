package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedDeepslateBricksStack extends ItemStack {
    ItemType<ItemCrackedDeepslateBricksStack> CRACKED_DEEPSLATE_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemCrackedDeepslateBricksStack.class)
            .vanillaItem(VanillaItemId.CRACKED_DEEPSLATE_BRICKS)
            .build();
}
