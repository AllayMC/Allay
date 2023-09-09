package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTubeCoralStack extends ItemStack {
    ItemType<ItemTubeCoralStack> TUBE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemTubeCoralStack.class)
            .vanillaItem(VanillaItemId.TUBE_CORAL)
            .build();
}
