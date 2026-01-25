package org.allaymc.server.blockentity.component;

import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
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
    protected boolean dropItemOnBreak;
    protected Consumer<ItemStack> comparatorUpdateListener;

    public BlockEntityContainerHolderComponentImpl(Supplier<Container> containerSupplier) {
        this.container = containerSupplier.get();
        this.dropItemOnBreak = true;
    }

    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        // Register a listener for all slots to update comparators when container contents change
        comparatorUpdateListener = itemStack -> {
            var pos = baseComponent.getPosition();
            pos.dimension().updateComparatorOutputLevel(pos);
        };
        for (int slot = 0; slot < container.getItemStackArray().length; slot++) {
            container.addSlotChangeListener(slot, comparatorUpdateListener);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> T getContainer() {
        return (T) container;
    }

    @EventHandler
    protected void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList("Items", NbtType.COMPOUND, items -> container.loadNBT(items));
    }

    @EventHandler
    protected void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        builder.putList("Items", NbtType.COMPOUND, container.saveNBT());
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var player = event.getInteractInfo().player();
        if (player == null || player.isSneaking()) {
            return;
        }

        if (container instanceof BlockContainer blockContainer) {
            blockContainer.setBlockPos(baseComponent.getPosition());
        }

        if (player.isActualPlayer()) {
            container.addViewer(player.getController());
        }
        event.setSuccess(true);
    }

    @EventHandler
    protected void onReplace(CBlockOnReplaceEvent event) {
        if (!this.dropItemOnBreak) {
            return;
        }

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

    @Override
    public boolean shouldDropItemOnBreak() {
        return dropItemOnBreak;
    }

    @Override
    public void setDropItemOnBreak(boolean drop) {
        this.dropItemOnBreak = drop;
    }
}
