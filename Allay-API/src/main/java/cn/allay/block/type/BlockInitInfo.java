package cn.allay.block.type;

import cn.allay.component.interfaces.ComponentInitInfo;
import cn.allay.math.location.Loc;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/21 <br>
 * Allay Project <br>
 */
public interface BlockInitInfo extends ComponentInitInfo {

    Loc<Integer> location();

    record Simple(Loc<Integer> location) implements BlockInitInfo {

    }
}
