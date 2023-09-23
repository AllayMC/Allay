package cn.allay.api.item.interfaces.litredstoneore;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitRedstoneOreStack extends ItemStack {
    ItemType<ItemLitRedstoneOreStack> LIT_REDSTONE_ORE_TYPE = ItemTypeBuilder
            .builder(ItemLitRedstoneOreStack.class)
            .vanillaItem(VanillaItemId.LIT_REDSTONE_ORE)
            .build();
}
