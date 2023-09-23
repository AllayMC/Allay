package cn.allay.api.block.interfaces.infoupdate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdateBehavior extends BlockBehavior {
    BlockType<BlockInfoUpdateBehavior> INFO_UPDATE_TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdateBehavior.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE)
            .build();
}
