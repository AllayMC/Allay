package cn.allay.block.component.base;

import cn.allay.block.component.BlockComponentImpl;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 *
 * This component only contains methods to get BlockType <p/>
 * The getBlockType() method is put into the component so that other components can call this method by relying on this component
 */
public class BlockBaseComponentImpl implements BlockBaseComponent, BlockComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    public BlockBaseComponentImpl() {}

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
