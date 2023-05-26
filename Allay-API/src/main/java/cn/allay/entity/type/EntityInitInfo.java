package cn.allay.entity.type;

import cn.allay.component.interfaces.ComponentInitInfo;
import cn.allay.math.location.Loc;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public interface EntityInitInfo extends ComponentInitInfo {
    Loc<Float> location();

    record Simple(Loc<Float> location) implements EntityInitInfo {

    }
}
