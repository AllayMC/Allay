package cn.allay.api.block.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.math.position.FixedPos;
import cn.allay.api.math.position.Pos;

/**
 * Allay Project 2023/4/21
 *
 * @author daoge_cmd
 */
public interface BlockInitInfo extends ComponentInitInfo {

    FixedPos<Integer> position();

    record Simple(FixedPos<Integer> position) implements BlockInitInfo {

    }
}
