package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceSignStack extends ItemStack {
    ItemType<ItemSpruceSignStack> SPRUCE_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemSpruceSignStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_SIGN)
            .build();
}
