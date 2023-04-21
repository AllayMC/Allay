package cn.allay.block.component.position;

import cn.allay.component.annotation.Inject;
import cn.allay.math.position.PosRO;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public interface BlockPositionComponent extends PosRO<Integer> {
    @Inject
    @Override
    Integer getX();

    @Inject
    @Override
    Integer getY();

    @Inject
    @Override
    Integer getZ();
}
