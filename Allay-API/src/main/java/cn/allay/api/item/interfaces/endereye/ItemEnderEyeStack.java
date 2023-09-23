package cn.allay.api.item.interfaces.endereye;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderEyeStack extends ItemStack {
    ItemType<ItemEnderEyeStack> ENDER_EYE_TYPE = ItemTypeBuilder
            .builder(ItemEnderEyeStack.class)
            .vanillaItem(VanillaItemId.ENDER_EYE)
            .build();
}
