package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteStainedGlassPaneStack extends ItemStack {
    ItemType<ItemWhiteStainedGlassPaneStack> WHITE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.WHITE_STAINED_GLASS_PANE)
            .build();
}
