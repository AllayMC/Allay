package cn.allay.api.item.interfaces.glowberries;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowBerriesStack extends ItemStack {
    ItemType<ItemGlowBerriesStack> GLOW_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemGlowBerriesStack.class)
            .vanillaItem(VanillaItemId.GLOW_BERRIES)
            .build();
}
