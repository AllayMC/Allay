package cn.allay.api.block.interfaces.boneblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBoneBlockBehavior extends BlockBehavior {
    BlockType<BlockBoneBlockBehavior> BONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BONE_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
