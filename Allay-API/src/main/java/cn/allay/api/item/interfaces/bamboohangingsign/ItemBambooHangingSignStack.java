package cn.allay.api.item.interfaces.bamboohangingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooHangingSignStack extends ItemStack {
    ItemType<ItemBambooHangingSignStack> BAMBOO_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBambooHangingSignStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_HANGING_SIGN)
            .build();
}
