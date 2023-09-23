package cn.allay.api.item.interfaces.prismarinebricksstairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrismarineBricksStairsStack extends ItemStack {
    ItemType<ItemPrismarineBricksStairsStack> PRISMARINE_BRICKS_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPrismarineBricksStairsStack.class)
            .vanillaItem(VanillaItemId.PRISMARINE_BRICKS_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
