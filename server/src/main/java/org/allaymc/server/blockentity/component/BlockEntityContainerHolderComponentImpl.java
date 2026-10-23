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
 * {@code BlockEntityContainerHolderComponentImpl} owns one block-entity
 * container and connects it to interaction, persistence, comparator, and
 * replacement events. The supplied container remains live and mutable;
 * {@code getContainer} returns it directly, and the generated setter only
 * replaces the reference without moving items, viewers, or listeners.
 *
 * <p>
 * Save events serialize the current container, while load events delegate item
 * application to that container. Replacement handling first removes viewers,
 * drops nonair stacks, and finally clears slots. These steps are incremental:
 * an exception can leave earlier drops completed and contents uncleared.
 * Disabling {@code dropItemOnBreak} skips the entire replacement handler,
 * allowing movement and specialized drop paths to retain inventory state.
 * Callers coordinate mutations with these synchronous operations.
 *
 * @author daoge_cmd
 */
public class BlockEntityContainerHolderComponentImpl implements BlockEntityContainerHolderComponent {
    /**
     * {@code IDENTIFIER} selects the standard block-entity inventory-holder
     * component when component providers resolve dependencies by their
     * registered identifiers.
     */
    @Identifier.Component
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_inventory_holder_component");

    /**
     * {@code baseComponent} supplies the current position for interaction and
     * comparator updates after component injection connects this holder to its
     * owner.
     */
    @Dependency
    protected BlockEntityBaseComponent baseComponent;

    /**
     * {@code container} holds the live inventory exposed by
     * {@code getContainer}. The generated setter replaces this reference
     * without transferring items, viewers, or previously registered listeners
     * to the replacement.
     */
    @Setter
    protected Container container;

    /**
     * {@code dropItemOnBreak} controls the complete replacement handler;
     * disabling it skips viewer removal, item drops, and inventory clearing
     * together.
     */
    protected boolean dropItemOnBreak;
    /**
     * {@code openContainerOnInteract} enables the ordinary interaction handler;
     * subclasses disable it when individual-slot behavior replaces a container
     * window.
     */
    protected boolean openContainerOnInteract;
    /**
     * {@code saveEmptySlots} tells container serialization whether empty slots
     * must be included; bookshelf and shelf holders enable it for complete slot
     * data.
     */
    protected boolean saveEmptySlots;

    /**
     * {@code comparatorUpdateListener} retains the callback installed on each
     * initial container slot; reference replacement does not detach or
     * reinstall it.
     */
    protected Consumer<ItemStack> comparatorUpdateListener;

    /**
     * Creates the holder using the container returned by
     * {@code containerSupplier}. Dropping and ordinary interaction opening
     * start enabled, while saving empty slots starts disabled; the supplier
     * must return a usable container.
     */
    public BlockEntityContainerHolderComponentImpl(Supplier<Container> containerSupplier) {
        this.container = containerSupplier.get();
        this.dropItemOnBreak = true;
        this.openContainerOnInteract = true;
    }

    /**
     * Installs comparator recalculation on every slot of the current container.
     * Dependency injection must already provide {@code baseComponent}; later
     * callbacks resolve its live position rather than retaining the position
     * from initialization.
     */
    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        comparatorUpdateListener = itemStack -> {
            var pos = baseComponent.getPosition();
            pos.dimension().updateComparatorOutputLevel(pos);
        };
        for (int slot = 0; slot < container.getItemStackArray().length; slot++) {
            container.addSlotChangeListener(slot, comparatorUpdateListener);
        }
    }

    /**
     * Returns the stored live container through an unchecked generic cast.
     * Callers share its inventory and must request a compatible subtype; this
     * method does not validate the inferred type or copy the container.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> T getContainer() {
        return (T) container;
    }

    /**
     * Applies a present compound {@code "Items"} list through the current
     * container. An absent list leaves inventory unchanged; the standard
     * container writes supplied slots incrementally without clearing omitted
     * slots or notifying listeners. An invalid saved slot index can fail after
     * earlier entries have already been applied.
     */
    @EventHandler
    protected void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList("Items", NbtType.COMPOUND, items -> container.loadNBT(items));
    }

    /**
     * Writes the current container's item list into the save-event builder.
     * {@code saveEmptySlots} controls whether empty positions are serialized;
     * this handler contributes data to a save and does not itself persist it.
     */
    @EventHandler
    protected void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        builder.putList("Items", NbtType.COMPOUND, container.saveNBT(saveEmptySlots));
    }

    /**
     * Handles ordinary container opening when interaction opening is enabled. A
     * supplied player marks the interaction successful, but only an actual
     * player's controller is added as a viewer; container rejection does not
     * change that success flag.
     */
    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        if (!openContainerOnInteract) {
            return;
        }

        var player = event.getInteractInfo().player();
        if (player == null) {
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

    /**
     * Drops the current inventory and clears its slots when break dropping is
     * enabled. A disabled policy returns before closing viewers or changing
     * contents, leaving those responsibilities to the specialized movement or
     * drop path.
     *
     * <p>
     * Viewer removal precedes item drops, and clearing starts only after every
     * nonair stack has been submitted to the dimension's drop operation. The
     * sequence has no rollback: an exception during dropping can leave earlier
     * world drops present alongside uncleared inventory, while a clearing
     * failure can leave only part of the inventory cleared.
     */
    @EventHandler
    protected void onReplace(CBlockOnReplaceEvent event) {
        if (!this.dropItemOnBreak) {
            return;
        }

        var current = event.getCurrentBlock();
        var pos = current.getPosition();
        var rand = ThreadLocalRandom.current();

        // Close existing viewers before publishing drops, then clear slots only
        // after the drop loop finishes; failures do not compensate earlier
        // world drops.
        container.removeAllViewers();
        for (var itemStack : container.getItemStacks()) {
            if (itemStack == ItemAirStack.AIR_STACK) continue;
            // Scatter drops within the block's central half so all three
            // coordinates stay between one-quarter and three-quarters of its
            // bounds.
            current.getDimension().dropItem(itemStack, new Vector3d(
                    pos.x() + rand.nextDouble(0.5) + 0.25,
                    pos.y() + rand.nextDouble(0.5) + 0.25,
                    pos.z() + rand.nextDouble(0.5) + 0.25
            ));
        }

        container.clearAllSlots();
    }

    /**
     * Returns whether the stored container uses the exact requested type
     * object.
     */
    @Override
    public boolean hasContainer(ContainerType<?> type) {
        return container.getContainerType() == type;
    }

    /**
     * Returns whether the full inventory replacement handler is currently
     * enabled.
     */
    @Override
    public boolean shouldDropItemOnBreak() {
        return dropItemOnBreak;
    }

    /**
     * Enables or disables the holder's inventory replacement handler directly.
     * Disabling it preserves contents during replacement by skipping item
     * drops, viewer removal, and clearing; specialized callers then own any
     * required cleanup.
     */
    @Override
    public void setDropItemOnBreak(boolean drop) {
        this.dropItemOnBreak = drop;
    }
}
