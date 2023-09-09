package cn.allay.api.item.interfaces;

import cn.allay.api.block.interfaces.BlockCauldronBehavior;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.CommonUseItemFunctions;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCauldronStack extends ItemStack {
    ItemType<ItemCauldronStack> CAULDRON_TYPE = ItemTypeBuilder
            .builder(ItemCauldronStack.class)
            .vanillaItem(VanillaItemId.CAULDRON)
            .addComponent(
                    ComponentProvider.of(
                            initInfo -> new ItemBaseComponentImpl<>((ItemStackInitInfo<?>) initInfo, CommonUseItemFunctions.createPlaceBlockUseOn(BlockCauldronBehavior.CAULDRON_TYPE.getDefaultState())),
                            ItemBaseComponentImpl.class))
            .build();
}
