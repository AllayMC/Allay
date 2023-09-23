package cn.allay.api.item.interfaces.netheritehoe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteHoeStack extends ItemStack {
    ItemType<ItemNetheriteHoeStack> NETHERITE_HOE_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteHoeStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_HOE)
            .build();
}
