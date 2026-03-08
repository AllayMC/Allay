package org.allaymc.server.block.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.component.BlockStateDataComponent;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockBehaviorImpl extends ComponentClass implements BlockBehavior {

    private static final ComponentInitInfo EMPTY_INIT_INFO = new ComponentInitInfo() {
    };

    @Getter
    @Delegate
    private BlockBaseComponent baseComponent;
    @Getter
    @Delegate
    private BlockStateDataComponent stateDataComponent;

    public BlockBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(EMPTY_INIT_INFO, componentProviders);
    }
}
