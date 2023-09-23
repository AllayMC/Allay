package cn.allay.api.block.interfaces.packedice;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPackedIceBehavior extends BlockBehavior {
    BlockType<BlockPackedIceBehavior> PACKED_ICE_TYPE = BlockTypeBuilder
            .builder(BlockPackedIceBehavior.class)
            .vanillaBlock(VanillaBlockId.PACKED_ICE)
            .build();
}
