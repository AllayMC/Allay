package cn.allay.api.item.interfaces.observer;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemObserverStack extends ItemStack {
    ItemType<ItemObserverStack> OBSERVER_TYPE = ItemTypeBuilder
            .builder(ItemObserverStack.class)
            .vanillaItem(VanillaItemId.OBSERVER)
            .build();
}
