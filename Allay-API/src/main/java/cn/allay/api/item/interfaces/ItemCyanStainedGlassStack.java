package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanStainedGlassStack extends ItemStack {
    ItemType<ItemCyanStainedGlassStack> CYAN_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemCyanStainedGlassStack.class)
            .vanillaItem(VanillaItemId.CYAN_STAINED_GLASS)
            .build();
}
