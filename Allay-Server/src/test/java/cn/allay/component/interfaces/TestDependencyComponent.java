package cn.allay.component.interfaces;

import cn.allay.component.annotation.Inject;
import cn.allay.component.interfaces.ComponentImpl;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
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
