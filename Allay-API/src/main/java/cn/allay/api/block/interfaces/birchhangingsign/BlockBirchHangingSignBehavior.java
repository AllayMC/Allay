package cn.allay.api.block.interfaces.birchhangingsign;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBirchHangingSignBehavior extends BlockBehavior {
    BlockType<BlockBirchHangingSignBehavior> BIRCH_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBirchHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
}
