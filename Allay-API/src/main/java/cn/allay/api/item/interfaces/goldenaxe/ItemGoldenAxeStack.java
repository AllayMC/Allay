package cn.allay.api.item.interfaces.goldenaxe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenAxeStack extends ItemStack {
    ItemType<ItemGoldenAxeStack> GOLDEN_AXE_TYPE = ItemTypeBuilder
            .builder(ItemGoldenAxeStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_AXE)
            .build();
}
