package org.allaymc.server.block.impl.glass;

import java.util.List;
import org.allaymc.api.block.interfaces.glass.BlockGlassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockGlassBehaviorImpl extends BlockBehaviorImpl implements BlockGlassBehavior {
    public BlockGlassBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
