package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnderChest;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityEnderChestImpl} implements the composed block entity's
 * ender-chest viewer counting capabilities through injected components.
 * Component assembly assigns the delegation fields before completion hooks run,
 * and the inherited base component supplies identity, position, naming, and NBT
 * serialization for the same object.
 *
 * @author IWareQ
 */
public class BlockEntityEnderChestImpl extends BlockEntityImpl implements BlockEntityEnderChest {

    /**
     * {@code enderChestBaseComponent} supplies ender-chest viewer counting
     * operations. Component assembly requires exactly one assignable provider
     * result before generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityEnderChestBaseComponent enderChestBaseComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityEnderChestImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
