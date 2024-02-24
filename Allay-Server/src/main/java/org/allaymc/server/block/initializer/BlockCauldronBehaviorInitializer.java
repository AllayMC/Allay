package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.math.voxelshape.CommonShapes;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.server.block.component.common.BlockAttributeComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCauldronBehaviorInitializer {
  static void init() {
    BlockTypes.CAULDRON_TYPE = BlockTypeBuilder
            .builder(BlockCauldronBehavior.class)
            .vanillaBlock(VanillaBlockId.CAULDRON)
            .setProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID, VanillaBlockPropertyTypes.FILL_LEVEL)
            .addComponent(BlockAttributeComponentImpl.ofRedefinedAABB(
                    blockState ->
                            VoxelShape.builder()
                                    .solid(0, 0, 0, 1, 1, 1)
                                    .vacancy(0.125f, 0.3125f, 0.125f, 0.875f, 1, 0.875f)
                                    .build()
            ))
            .build();
  }
}
