package cn.allay.api.block.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.math.position.Pos;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/21 <br>
 * Allay Project <br>
 */
public interface BlockInitInfo extends ComponentInitInfo {

    Pos<Integer> position();

    record Simple(Pos<Integer> position) implements BlockInitInfo {

    }
}
