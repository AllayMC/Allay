package cn.allay.api.item.interfaces.planks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPlanksStack extends ItemStack {
    ItemType<ItemPlanksStack> PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemPlanksStack.class)
            .vanillaItem(VanillaItemId.PLANKS)
            .build();
}
