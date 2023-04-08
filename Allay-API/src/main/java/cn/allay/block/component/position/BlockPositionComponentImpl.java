package cn.allay.block.component.position;

import cn.allay.block.component.BlockComponentImpl;
import cn.allay.component.annotation.Inject;
import cn.allay.identifier.Identifier;
import cn.allay.level.Level;
import cn.allay.math.position.PosRO;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class BlockPositionComponentImpl implements BlockPositionComponent, BlockComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_position_component");

    protected final PosRO<Integer> pos;

    public BlockPositionComponentImpl(PosRO<Integer> pos) {
        this.pos = pos;
    }

    public BlockPositionComponentImpl(int x, int y, int z, Level level) {
        this.pos = PosRO.of(x, y, z, level);
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @Override
    @Inject
    public Level getLevel() {
        return pos.getLevel();
    }

    @Override
    @Inject
    public Integer getX() {
        return pos.getX();
    }

    @Override
    @Inject
    public Integer getY() {
        return pos.getY();
    }

    @Override
    @Inject
    public Integer getZ() {
        return pos.getZ();
    }
}
