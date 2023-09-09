package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStainedGlassPaneStack extends ItemStack {
    ItemType<ItemStainedGlassPaneStack> STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.STAINED_GLASS_PANE)
            .build();
}
