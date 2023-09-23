package cn.allay.api.block.interfaces.frogspawn;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFrogSpawnBehavior extends BlockBehavior {
    BlockType<BlockFrogSpawnBehavior> FROG_SPAWN_TYPE = BlockTypeBuilder
            .builder(BlockFrogSpawnBehavior.class)
            .vanillaBlock(VanillaBlockId.FROG_SPAWN)
            .build();
}
