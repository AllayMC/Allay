package cn.allay.api.item.interfaces.limeconcrete;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeConcreteStack extends ItemStack {
    ItemType<ItemLimeConcreteStack> LIME_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemLimeConcreteStack.class)
            .vanillaItem(VanillaItemId.LIME_CONCRETE)
            .build();
}
