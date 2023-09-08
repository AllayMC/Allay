package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGlowLichenBehavior extends BlockBehavior {
    BlockType<BlockGlowLichenBehavior> GLOW_LICHEN_TYPE = BlockTypeBuilder
            .builder(BlockGlowLichenBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOW_LICHEN)
            .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
            .build();
}
