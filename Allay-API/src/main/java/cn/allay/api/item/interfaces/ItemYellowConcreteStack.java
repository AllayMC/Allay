package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowConcreteStack extends ItemStack {
    ItemType<ItemYellowConcreteStack> YELLOW_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemYellowConcreteStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CONCRETE)
            .build();
}
