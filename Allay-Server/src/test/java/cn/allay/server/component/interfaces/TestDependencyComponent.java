package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.component.interfaces.ComponentImpl;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 */
public interface TestDependencyComponent {
    @Inject
    ComponentImpl getNameComponent();

    @Inject
    ComponentImpl getHealthComponent();

    @Inject
    ComponentImpl getAttackComponent();
}
