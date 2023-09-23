package cn.allay.api.block.interfaces.reeds;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockReedsBehavior extends BlockBehavior {
    BlockType<BlockReedsBehavior> REEDS_TYPE = BlockTypeBuilder
            .builder(BlockReedsBehavior.class)
            .vanillaBlock(VanillaBlockId.REEDS)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
}
