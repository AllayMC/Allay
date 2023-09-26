package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackStainedGlassPaneStack extends ItemStack {
    ItemType<ItemBlackStainedGlassPaneStack> BLACK_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemBlackStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.BLACK_STAINED_GLASS_PANE)
            .build();
}
