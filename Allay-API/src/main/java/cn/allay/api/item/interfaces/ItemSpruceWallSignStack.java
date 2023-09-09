package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceWallSignStack extends ItemStack {
    ItemType<ItemSpruceWallSignStack> SPRUCE_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemSpruceWallSignStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_WALL_SIGN)
            .build();
}
