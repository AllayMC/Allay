package cn.allay.api.block.component.blockentity;

import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.identifier.Identifier;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class BlockEntityHolderComponentImpl implements BlockEntityHolderComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_holder_component");

    protected final BlockEntityType<?> blockEntityType;

    public BlockEntityHolderComponentImpl(BlockEntityType<?> blockEntityType) {
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<?> getBlockEntityType() {
        return blockEntityType;
    }
}
