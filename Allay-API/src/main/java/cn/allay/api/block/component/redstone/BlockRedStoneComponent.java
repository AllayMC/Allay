package cn.allay.api.block.component.redstone;

import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.function.OnRedStoneUpdate;
import cn.allay.api.component.annotation.Inject;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
public interface BlockRedStoneComponent extends OnRedStoneUpdate {
    @Override
    @Inject
    void onRedStoneUpdate(BlockStateWithPos blockState);
}
