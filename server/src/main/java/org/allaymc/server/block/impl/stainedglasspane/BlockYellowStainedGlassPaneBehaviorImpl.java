package org.allaymc.server.block.impl.stainedglasspane;

import java.util.List;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockYellowStainedGlassPaneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockYellowStainedGlassPaneBehaviorImpl extends BlockBehaviorImpl implements BlockYellowStainedGlassPaneBehavior {
    public BlockYellowStainedGlassPaneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
