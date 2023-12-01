package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.base.BlockBaseComponentImpl;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCraftingTableBehavior extends BlockBehavior {
  BlockType<BlockCraftingTableBehavior> CRAFTING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockCraftingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.CRAFTING_TABLE).setBlockBaseComponentSupplier(type -> new BlockBaseComponentImpl(type) {
              @Override
              public boolean onInteract(@Nullable EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
                  if (!super.onInteract(player, itemStack, dimension, blockPos, placeBlockPos, clickPos, blockFace) && player != null) {
                      var craftingTableContainer = player.getContainer(FullContainerType.CRAFTING_TABLE);
                      craftingTableContainer.setLastCraftingTablePos(blockPos);
                      craftingTableContainer.addViewer(player);
                      return true;
                  }
                  return true;
              }
          })
          .build();
}
