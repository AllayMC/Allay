package cn.allay.api.block.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.math.vector.Pos3i;

/**
 * Allay Project 2023/4/21
 *
 * @author daoge_cmd
 */
public interface BlockInitInfo extends ComponentInitInfo {

    Pos3i position();

    record Simple(Pos3i position) implements BlockInitInfo {

    }
}
