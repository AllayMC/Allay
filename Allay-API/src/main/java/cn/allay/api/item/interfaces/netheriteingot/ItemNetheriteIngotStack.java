package cn.allay.api.item.interfaces.netheriteingot;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteIngotStack extends ItemStack {
    ItemType<ItemNetheriteIngotStack> NETHERITE_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteIngotStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_INGOT)
            .build();
}
