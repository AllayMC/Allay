package org.allaymc.server.blockentity.component;

import lombok.Setter;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.container.impl.BlockContainerImpl;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class BlockEntityContainerHolderComponentImpl implements BlockEntityContainerHolderComponent {
    @Identifier.Component
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_inventory_holder_component");

    @Dependency
    protected BlockEntityBaseComponent baseComponent;

    @Setter
    protected Container container;

    public BlockEntityContainerHolderComponentImpl(Supplier<Container> containerSupplier) {
        this.container = containerSupplier.get();
    }

    @Override
    public <T extends Container> T getContainer() {
        return (T) container;
    }

    @EventHandler
    protected void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList("Items", NbtType.COMPOUND, items -> container.loadNBT(items));
        if (container instanceof BlockContainerImpl blockContainer) {
            blockContainer.setBlockPos(baseComponent.getPosition());
        }
    }

    @EventHandler
    protected void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        builder.putList(
                "Items",
                NbtType.COMPOUND,
                container.saveNBT()
        );
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var player = event.getInteractInfo().player();
        if (player == null || player.isSneaking()) return;

        container.addViewer(player);
        event.setSuccess(true);
    }

    protected boolean dropItemWhenBreak() {
        return true;
    }

    @EventHandler
    protected void onReplace(CBlockOnReplaceEvent event) {
        if (!dropItemWhenBreak()) return;

        var current = event.getCurrentBlock();
        var pos = current.getPosition();
        var rand = ThreadLocalRandom.current();

        container.removeAllViewers();
        for (var itemStack : container.getItemStacks()) {
            if (itemStack == ItemAirStack.AIR_STACK) continue;
            current.getDimension().dropItem(itemStack, new Vector3d(
                    pos.x() + rand.nextDouble(0.5) + 0.25,
                    pos.y() + rand.nextDouble(0.5) + 0.25,
                    pos.z() + rand.nextDouble(0.5) + 0.25
            ));
        }

        container.clearAllSlots();
    }

    @Override
    public boolean hasContainer(ContainerType<?> type) {
        return container.getContainerType() == type;
    }
}
