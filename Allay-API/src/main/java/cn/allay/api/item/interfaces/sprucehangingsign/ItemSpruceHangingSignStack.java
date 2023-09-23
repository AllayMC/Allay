package cn.allay.api.item.interfaces.sprucehangingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceHangingSignStack extends ItemStack {
    ItemType<ItemSpruceHangingSignStack> SPRUCE_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemSpruceHangingSignStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_HANGING_SIGN)
            .build();
}
