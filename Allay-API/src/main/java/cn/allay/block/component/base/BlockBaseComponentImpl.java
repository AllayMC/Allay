package cn.allay.block.component.base;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.type.BlockType;
import cn.allay.component.annotation.Inject;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class BlockBaseComponentImpl implements BlockBaseComponent, BlockComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");
    protected BlockType<? extends Block> blockType;

    public BlockBaseComponentImpl(BlockType<? extends Block> blockType) {
        this.blockType = blockType;
    }

    @Override
    @Inject
    public BlockType<? extends Block> getBlockType() {
        return blockType;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
