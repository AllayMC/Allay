package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryPlanksStack extends ItemStack {
    ItemType<ItemCherryPlanksStack> CHERRY_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemCherryPlanksStack.class)
            .vanillaItem(VanillaItemId.CHERRY_PLANKS)
            .build();
}
