package org.allaymc.server.component.interfaces;

import org.allaymc.api.component.interfaces.Component;

/**
 * @author daoge_cmd
 */
public interface TestDependencyComponent extends Component {
    Component getNameComponent();

    Component getHealthComponent();

    Component getAttackComponent();
}
