package cn.allay.api.entity.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.math.vector.Loc3f;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityInitInfo extends ComponentInitInfo {
    Loc3f location();

    record Simple(Loc3f location) implements EntityInitInfo {

    }
}
