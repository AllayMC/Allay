package cn.allay.block.component.position;

import cn.allay.block.component.BlockComponentImpl;
import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import cn.allay.level.Level;
import cn.allay.math.position.Pos;
import cn.allay.math.position.PosRO;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class BlockPositionComponentImpl implements BlockPositionComponent, BlockComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_position_component");

    protected final Pos<Integer> pos;

    public BlockPositionComponentImpl(PosRO<Integer> pos) {
        this.pos = Pos.of(pos.getX(), pos.getY(), pos.getZ(), pos.getLevel());
    }

    public BlockPositionComponentImpl(int x, int y, int z, Level level) {
        this.pos = Pos.of(x, y, z, level);
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public Level getLevel() {
        return pos.getLevel();
    }

    @Override
    @Impl
    public Integer getX() {
        return pos.getX();
    }

    @Override
    @Impl
    public Integer getY() {
        return pos.getY();
    }

    @Override
    @Impl
    public Integer getZ() {
        return pos.getZ();
    }
}
