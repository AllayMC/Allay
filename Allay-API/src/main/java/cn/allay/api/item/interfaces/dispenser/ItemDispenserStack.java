package cn.allay.api.item.interfaces.dispenser;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDispenserStack extends ItemStack {
    ItemType<ItemDispenserStack> DISPENSER_TYPE = ItemTypeBuilder
            .builder(ItemDispenserStack.class)
            .vanillaItem(VanillaItemId.DISPENSER)
            .build();
}
