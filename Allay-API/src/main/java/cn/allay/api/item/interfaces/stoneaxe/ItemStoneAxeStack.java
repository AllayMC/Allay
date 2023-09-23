package cn.allay.api.item.interfaces.stoneaxe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneAxeStack extends ItemStack {
    ItemType<ItemStoneAxeStack> STONE_AXE_TYPE = ItemTypeBuilder
            .builder(ItemStoneAxeStack.class)
            .vanillaItem(VanillaItemId.STONE_AXE)
            .build();
}
