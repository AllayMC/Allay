package cn.allay.api.block.component.blockentity;

import cn.allay.api.block.component.BlockComponent;
import cn.allay.api.blockentity.type.BlockEntityType;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityHolderComponent extends BlockComponent {
    BlockEntityType<?> getBlockEntityType();
}
