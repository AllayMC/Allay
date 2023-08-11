package cn.allay.api.block.component.impl.base;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.CanPlaceOn;
import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.blockupdate.*;
import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.Inject;
import org.joml.Vector3i;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends OnNeighborChanged, OnPlace, OnRandomUpdate, OnReplace, OnScheduledUpdate, CanPlaceOn {
    @Inject
    BlockType<? extends BlockBehavior> getBlockType();

    @Override
    @Inject
    void onNeighborChanged(BlockStateWithPos blockState, BlockStateWithPos neighborBlockState, BlockFace blockFace);

    @Override
    @Inject
    void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState);

    @Override
    @Inject
    void onRandomUpdate(BlockStateWithPos blockState);

    @Override
    @Inject
    void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState);

    @Override
    @Inject
    void onScheduledUpdate(BlockStateWithPos blockState);

    @Override
    @Inject
    boolean canPlaceOn(BlockStateWithPos pos);
}
