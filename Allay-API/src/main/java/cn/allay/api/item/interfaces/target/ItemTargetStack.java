package cn.allay.api.item.interfaces.target;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTargetStack extends ItemStack {
    ItemType<ItemTargetStack> TARGET_TYPE = ItemTypeBuilder
            .builder(ItemTargetStack.class)
            .vanillaItem(VanillaItemId.TARGET)
            .build();
}
