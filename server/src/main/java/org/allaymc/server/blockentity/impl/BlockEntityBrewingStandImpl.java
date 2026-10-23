package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBrewingStandBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityBrewingStandImpl} implements the composed block entity's
 * brewing inventory, progress, and fuel capabilities through injected
 * components. Component assembly assigns the delegation fields before
 * completion hooks run, and the inherited base component supplies identity,
 * position, naming, and NBT serialization for the same object.
 *
 * @author IWareQ
 */
public class BlockEntityBrewingStandImpl extends BlockEntityImpl implements BlockEntityBrewingStand {

    /**
     * {@code containerHolderComponent} supplies brewing inventory operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    /**
     * {@code brewingStandBaseComponent} supplies brewing progress and fuel
     * operations. Component assembly requires exactly one assignable provider
     * result before generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityBrewingStandBaseComponent brewingStandBaseComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityBrewingStandImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
