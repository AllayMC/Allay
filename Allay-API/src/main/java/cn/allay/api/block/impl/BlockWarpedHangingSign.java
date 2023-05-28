package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedHangingSign extends Block {
    BlockType<BlockWarpedHangingSign> TYPE = BlockTypeBuilder
            .builder(BlockWarpedHangingSign.class)
            .vanillaBlock(VanillaBlockId.WARPED_HANGING_SIGN, true)
            .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION,
                    VanillaBlockPropertyTypes.HANGING)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
