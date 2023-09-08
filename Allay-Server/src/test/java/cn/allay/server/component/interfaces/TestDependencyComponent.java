package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.component.interfaces.Component;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface TestDependencyComponent {
    @Inject
    Component getNameComponent();

    @Inject
    Component getHealthComponent();

    @Inject
    Component getAttackComponent();
}
