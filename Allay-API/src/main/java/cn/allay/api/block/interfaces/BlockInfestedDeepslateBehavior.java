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
public interface BlockInfestedDeepslateBehavior extends BlockBehavior {
    BlockType<BlockInfestedDeepslateBehavior> INFESTED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockInfestedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.INFESTED_DEEPSLATE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
