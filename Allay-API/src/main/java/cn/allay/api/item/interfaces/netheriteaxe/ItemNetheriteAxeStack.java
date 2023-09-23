package cn.allay.api.item.interfaces.netheriteaxe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteAxeStack extends ItemStack {
    ItemType<ItemNetheriteAxeStack> NETHERITE_AXE_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteAxeStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_AXE)
            .build();
}
