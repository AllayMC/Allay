package cn.allay.block.type;

import cn.allay.component.interfaces.ComponentInitInfo;
import cn.allay.math.position.Pos;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/21 <br>
 * Allay Project <br>
 */
public interface BlockInitInfo extends ComponentInitInfo {

    Pos<Integer> position();

    record Simple(Pos<Integer> position) implements BlockInitInfo {

    }
}
