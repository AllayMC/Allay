package cn.allay.api.block.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.math.position.Pos;

/**
 * Allay Project 2023/4/21
 *
 * @author daoge_cmd
 */
public interface BlockInitInfo extends ComponentInitInfo {

    Pos<Integer> position();

    record Simple(Pos<Integer> position) implements BlockInitInfo {

    }
}
