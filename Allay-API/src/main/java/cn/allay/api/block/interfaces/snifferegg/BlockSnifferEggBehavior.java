package cn.allay.api.block.interfaces.snifferegg;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSnifferEggBehavior extends BlockBehavior {
    BlockType<BlockSnifferEggBehavior> SNIFFER_EGG_TYPE = BlockTypeBuilder
            .builder(BlockSnifferEggBehavior.class)
            .vanillaBlock(VanillaBlockId.SNIFFER_EGG)
            .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
            .build();
}
