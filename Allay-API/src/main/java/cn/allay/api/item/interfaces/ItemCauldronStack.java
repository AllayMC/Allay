package cn.allay.api.item.interfaces;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.interfaces.BlockCauldronBehavior;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.world.World;
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
            public boolean useItemOn(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
              return tryPlaceBlockState(player, itemStack, world, targetBlockPos, placeBlockPos, clickPos, blockFace, BlockCauldronBehavior.CAULDRON_TYPE.getDefaultState());
            }
          }, ItemBaseComponentImpl.class)
          .build();
}
