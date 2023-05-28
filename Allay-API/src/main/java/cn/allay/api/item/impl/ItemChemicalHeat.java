package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChemicalHeat extends ItemStack {
    ItemType<ItemChemicalHeat> TYPE = ItemTypeBuilder
            .builder(ItemChemicalHeat.class)
            .vanillaItem(VanillaItemId.CHEMICAL_HEAT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
