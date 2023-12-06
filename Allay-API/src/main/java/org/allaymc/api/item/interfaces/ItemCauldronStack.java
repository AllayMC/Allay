package org.allaymc.api.item.interfaces;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.base.ItemBaseComponentImpl;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.world.Dimension;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCauldronStack extends ItemStack {
  ItemType<ItemCauldronStack> CAULDRON_TYPE = ItemTypeBuilder
          .builder(ItemCauldronStack.class)
          .vanillaItem(VanillaItemId.CAULDRON)
          .addComponent(initInfo -> new ItemBaseComponentImpl<>(initInfo) {
            @Override
            public boolean useItemOn(@Nullable EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
                return tryPlaceBlockState(player, itemStack, dimension, targetBlockPos, placeBlockPos, clickPos, blockFace, BlockCauldronBehavior.CAULDRON_TYPE.getDefaultState());
            }
          }, ItemBaseComponentImpl.class)
          .build();
}
