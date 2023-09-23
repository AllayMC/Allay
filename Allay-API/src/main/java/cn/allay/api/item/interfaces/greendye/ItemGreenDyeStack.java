package cn.allay.api.item.interfaces.greendye;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenDyeStack extends ItemStack {
    ItemType<ItemGreenDyeStack> GREEN_DYE_TYPE = ItemTypeBuilder
            .builder(ItemGreenDyeStack.class)
            .vanillaItem(VanillaItemId.GREEN_DYE)
            .build();
}
