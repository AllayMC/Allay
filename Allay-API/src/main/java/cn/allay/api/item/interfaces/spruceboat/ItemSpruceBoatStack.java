package cn.allay.api.item.interfaces.spruceboat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceBoatStack extends ItemStack {
    ItemType<ItemSpruceBoatStack> SPRUCE_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemSpruceBoatStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_BOAT)
            .build();
}
