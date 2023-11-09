package org.allaymc.server.component.interfaces;

import org.allaymc.api.component.interfaces.Component;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface TestDependencyComponent extends Component{
    Component getNameComponent();

    Component getHealthComponent();

    Component getAttackComponent();
}
