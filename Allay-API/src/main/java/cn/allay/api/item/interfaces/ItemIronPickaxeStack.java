package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronPickaxeStack extends ItemStack {
    ItemType<ItemIronPickaxeStack> IRON_PICKAXE_TYPE = ItemTypeBuilder
            .builder(ItemIronPickaxeStack.class)
            .vanillaItem(VanillaItemId.IRON_PICKAXE)
            .build();
}
