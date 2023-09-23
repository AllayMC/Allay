package cn.allay.api.item.interfaces.diamondpickaxe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondPickaxeStack extends ItemStack {
    ItemType<ItemDiamondPickaxeStack> DIAMOND_PICKAXE_TYPE = ItemTypeBuilder
            .builder(ItemDiamondPickaxeStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_PICKAXE)
            .build();
}
