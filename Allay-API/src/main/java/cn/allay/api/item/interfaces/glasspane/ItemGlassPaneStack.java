package cn.allay.api.item.interfaces.glasspane;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlassPaneStack extends ItemStack {
    ItemType<ItemGlassPaneStack> GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemGlassPaneStack.class)
            .vanillaItem(VanillaItemId.GLASS_PANE)
            .build();
}
