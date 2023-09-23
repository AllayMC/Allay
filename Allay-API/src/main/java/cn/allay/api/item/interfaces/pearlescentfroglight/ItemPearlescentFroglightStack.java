package cn.allay.api.item.interfaces.pearlescentfroglight;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPearlescentFroglightStack extends ItemStack {
    ItemType<ItemPearlescentFroglightStack> PEARLESCENT_FROGLIGHT_TYPE = ItemTypeBuilder
            .builder(ItemPearlescentFroglightStack.class)
            .vanillaItem(VanillaItemId.PEARLESCENT_FROGLIGHT)
            .build();
}
