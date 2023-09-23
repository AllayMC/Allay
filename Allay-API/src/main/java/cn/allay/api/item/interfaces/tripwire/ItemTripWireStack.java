package cn.allay.api.item.interfaces.tripwire;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTripWireStack extends ItemStack {
    ItemType<ItemTripWireStack> TRIP_WIRE_TYPE = ItemTypeBuilder
            .builder(ItemTripWireStack.class)
            .vanillaItem(VanillaItemId.TRIP_WIRE)
            .build();
}
