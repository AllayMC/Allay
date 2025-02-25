package org.allaymc.server.block.type;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class TestBlockImpl extends BlockBehaviorImpl implements TestBlock {
    public TestBlockImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
