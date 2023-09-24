package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChemistryTableStack extends ItemStack {
    ItemType<ItemChemistryTableStack> CHEMISTRY_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemChemistryTableStack.class)
            .vanillaItem(VanillaItemId.CHEMISTRY_TABLE)
            .build();
}
