package cn.allay.api.entity.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityInitInfo extends ComponentInitInfo {
    FixedLoc<Float> location();

    record Simple(FixedLoc<Float> location) implements EntityInitInfo {

    }
}
