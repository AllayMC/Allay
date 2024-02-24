package org.allaymc.server.block.initializer.stairs;

import org.allaymc.api.block.interfaces.stairs.BlockSmoothQuartzStairsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.math.voxelshape.CommonShapes;
import org.allaymc.server.block.component.common.BlockAttributeComponentImpl;
import org.allaymc.server.block.component.stairs.BlockStairsBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmoothQuartzStairsBehaviorInitializer {
  static void init() {
    BlockTypes.SMOOTH_QUARTZ_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSmoothQuartzStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .addComponent(BlockAttributeComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
            .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
            .build();
  }
}
