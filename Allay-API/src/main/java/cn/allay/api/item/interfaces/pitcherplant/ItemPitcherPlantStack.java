package cn.allay.api.item.interfaces.pitcherplant;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPitcherPlantStack extends ItemStack {
    ItemType<ItemPitcherPlantStack> PITCHER_PLANT_TYPE = ItemTypeBuilder
            .builder(ItemPitcherPlantStack.class)
            .vanillaItem(VanillaItemId.PITCHER_PLANT)
            .build();
}
