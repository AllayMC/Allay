package cn.allay.api.block.interfaces.respawnanchor;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRespawnAnchorBehavior extends BlockBehavior {
    BlockType<BlockRespawnAnchorBehavior> RESPAWN_ANCHOR_TYPE = BlockTypeBuilder
            .builder(BlockRespawnAnchorBehavior.class)
            .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR)
            .setProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
            .build();
}
