package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChemicalHeatStack extends ItemStack {
    ItemType<ItemChemicalHeatStack> CHEMICAL_HEAT_TYPE = ItemTypeBuilder
            .builder(ItemChemicalHeatStack.class)
            .vanillaItem(VanillaItemId.CHEMICAL_HEAT)
            .build();
}
