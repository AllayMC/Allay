package cn.allay.api.item.interfaces.firecoral;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireCoralStack extends ItemStack {
    ItemType<ItemFireCoralStack> FIRE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemFireCoralStack.class)
            .vanillaItem(VanillaItemId.FIRE_CORAL)
            .build();
}
