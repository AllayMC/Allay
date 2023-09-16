package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.FullContainerType;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class BarrelContainer extends BaseContainer {

    protected Vector3ic blockPos;

    public BarrelContainer(Vector3ic blockPos) {
        super(FullContainerType.BARREL);
        this.blockPos = blockPos;
    }

    public BarrelContainer(Supplier<Vector3ic> blockPosGenerator) {
        this(blockPosGenerator.get());
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }

    @Override
    public Vector3ic getBlockPos() {
        return blockPos;
    }
}
