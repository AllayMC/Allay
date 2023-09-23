package cn.allay.api.block.interfaces.redstonelamp;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedstoneLampBehavior extends BlockBehavior {
    BlockType<BlockRedstoneLampBehavior> REDSTONE_LAMP_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneLampBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_LAMP)
            .build();
}
