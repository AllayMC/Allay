package cn.allay.api.item.interfaces.recoverycompass;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRecoveryCompassStack extends ItemStack {
    ItemType<ItemRecoveryCompassStack> RECOVERY_COMPASS_TYPE = ItemTypeBuilder
            .builder(ItemRecoveryCompassStack.class)
            .vanillaItem(VanillaItemId.RECOVERY_COMPASS)
            .build();
}
