package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardGlassPaneStack extends ItemStack {
    ItemType<ItemHardGlassPaneStack> HARD_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_GLASS_PANE)
            .build();
}
