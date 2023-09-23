package cn.allay.api.item.interfaces.netheriteboots;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteBootsStack extends ItemStack {
    ItemType<ItemNetheriteBootsStack> NETHERITE_BOOTS_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteBootsStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_BOOTS)
            .build();
}
