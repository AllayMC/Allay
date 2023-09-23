package cn.allay.api.block.interfaces.noteblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNoteblockBehavior extends BlockBehavior {
    BlockType<BlockNoteblockBehavior> NOTEBLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNoteblockBehavior.class)
            .vanillaBlock(VanillaBlockId.NOTEBLOCK)
            .build();
}
