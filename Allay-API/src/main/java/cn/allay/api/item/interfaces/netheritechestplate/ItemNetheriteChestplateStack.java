package cn.allay.api.item.interfaces.netheritechestplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteChestplateStack extends ItemStack {
    ItemType<ItemNetheriteChestplateStack> NETHERITE_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteChestplateStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_CHESTPLATE)
            .build();
}
