package cn.allay.api.item.interfaces.sprucetrapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceTrapdoorStack extends ItemStack {
    ItemType<ItemSpruceTrapdoorStack> SPRUCE_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemSpruceTrapdoorStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_TRAPDOOR)
            .build();
}
