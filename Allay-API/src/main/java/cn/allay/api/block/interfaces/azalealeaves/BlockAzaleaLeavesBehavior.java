package cn.allay.api.block.interfaces.azalealeaves;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAzaleaLeavesBehavior extends BlockBehavior {
    BlockType<BlockAzaleaLeavesBehavior> AZALEA_LEAVES_TYPE = BlockTypeBuilder
            .builder(BlockAzaleaLeavesBehavior.class)
            .vanillaBlock(VanillaBlockId.AZALEA_LEAVES)
            .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
}
