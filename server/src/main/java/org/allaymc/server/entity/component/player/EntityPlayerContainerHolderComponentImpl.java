package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.action.PickedUpAction;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerEnchantOptionsRequestEvent;
import org.allaymc.api.eventbus.event.player.PlayerPickupArrowEvent;
import org.allaymc.api.eventbus.event.player.PlayerPickupItemEvent;
import org.allaymc.api.eventbus.event.player.PlayerPickupTridentEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.container.impl.*;
import org.allaymc.server.entity.component.EntityContainerHolderComponentImpl;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;
import org.allaymc.server.item.enchantment.EnchantmentOptionGenerator;
import org.cloudburstmc.nbt.NbtType;
import org.joml.primitives.AABBd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@code EntityPlayerContainerHolderComponentImpl} owns player inventories,
 * equipment containers, pickup handling, and current enchanting option
 * identifiers. Container suppliers defer access to the instance's injected
 * {@code thisPlayer} until callbacks run. Four compound-list tags persist the
 * offhand, inventory, armor, and ender chest; other work containers remain part
 * of runtime container handling.
 *
 * <p>
 * Pickup transfers mutable stack counts before removing their source entities.
 * Item stacks retain an uninserted remainder when inventory insertion is
 * partial; arrows reconstruct an item with their potion type, and tridents
 * retain their stored item representation. Removal markers and cleared source
 * references allow later scans to skip completed pickups. Enchanting options
 * are registered by the generator and tracked here after event approval, then
 * released on input changes or window close.
 *
 * @author daoge_cmd
 */
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl {

    // TAG_OFFHAND, TAG_INVENTORY, TAG_ARMOR, and TAG_ENDER_ITEMS separate the
    // persisted equipment and storage groups so each list loads into its matching container.
    /**
     * NBT list key for offhand contents.
     */
    protected static final String TAG_OFFHAND = "Offhand";
    /**
     * NBT list key for player inventory.
     */
    protected static final String TAG_INVENTORY = "Inventory";
    /**
     * NBT list key for worn armor.
     */
    protected static final String TAG_ARMOR = "Armor";
    /**
     * NBT list key for ender-chest contents.
     */
    protected static final String TAG_ENDER_ITEMS = "EnderChestInventory";

    /**
     * Player owning the containers and pickup state.
     */
    @ComponentObject
    private EntityPlayer thisPlayer;

    /**
     * {@code enchantOptions} holds globally registered option identifiers
     * accepted for this window. Input changes and window closure remove these
     * identifiers before clearing the list; options from a cancelled request
     * are not added here.
     */
    private final List<Long> enchantOptions;

    /**
     * Creates player work containers and deferred owner-aware equipment
     * containers. The constructor registers enchanting input and close
     * callbacks before component injection; those callbacks must run after
     * {@code thisPlayer} has been assigned by the component initializer.
     */
    public EntityPlayerContainerHolderComponentImpl() {
        super(
                new PlayerCreatedOutputContainerImpl(),
                new CraftingGridContainerImpl(),
                new CraftingTableContainerImpl(),
                new BeaconContainerImpl(),
                new EnderChestContainerImpl(),
                new AnvilContainerImpl(),
                new LoomContainerImpl()
        );
        this.enchantOptions = new ArrayList<>();

        var enchantTableContainer = new EnchantTableContainerImpl();
        enchantTableContainer.addSlotChangeListener(EnchantTableContainerImpl.INPUT_SLOT, item -> {
            var blockPos = enchantTableContainer.getBlockPos();
            if (blockPos != null) {
                onEnchantTableContainerInputItemChange(item, new Position3i(blockPos, thisPlayer.getDimension()));
            }
        });
        enchantTableContainer.addCloseListener(viewer -> clearEnchantOptions());
        addContainer(enchantTableContainer);

        // Owner injection follows construction. Suppliers defer access to thisPlayer
        // until equipment callbacks run rather than capturing the initial null reference.
        addContainer(new PlayerArmorContainerImpl(() -> thisPlayer));
        addContainer(new InventoryContainerImpl(() -> thisPlayer));
        addContainer(new OffhandContainerImpl(() -> thisPlayer));
        addContainer(new PlayerCursorContainerImpl(() -> thisPlayer));
    }

    /**
     * Replaces the current window's enchanting options for an actual player.
     * The method releases previously tracked identifiers first, then asks the
     * generator to register options for a non-air input and the player's
     * enchantment seed. Fake players leave the current tracking list unchanged.
     *
     * <p>
     * An approved request records the generated identifiers and sends the
     * paired options to the controller. Cancellation returns before tracking
     * those new identifiers, so the current implementation does not release the
     * registrations created by that cancelled generation step.
     */
    protected void onEnchantTableContainerInputItemChange(ItemStack item, Position3ic enchantTablePos) {
        if (thisPlayer.isActualPlayer()) {
            clearEnchantOptions();
            if (item != ItemAirStack.AIR_STACK) {
                var enchantOptions = EnchantmentOptionGenerator.generateEnchantOptions(enchantTablePos, item, thisPlayer.getEnchantmentSeed());
                var event = new PlayerEnchantOptionsRequestEvent(thisPlayer, enchantOptions.stream().map(Pair::right).toList());
                // Generation has already registered these options. Cancellation returns
                // before their identifiers enter enchantOptions, so cleanup cannot find them.
                if (!event.call()) {
                    return;
                }

                for (var option : enchantOptions) {
                    this.enchantOptions.add(Long.valueOf(option.left()));
                }

                thisPlayer.getController().viewEnchantOptions(enchantOptions);
            }
        }
    }

    /**
     * Releases every tracked enchanting identifier before clearing the local
     * list. The empty-list path performs no generator calls, and repeated
     * cleanup therefore has no further effect on identifiers already removed by
     * this holder.
     */
    private void clearEnchantOptions() {
        if (!this.enchantOptions.isEmpty()) {
            EnchantmentOptionGenerator.removeEnchantOptions(this.enchantOptions);
            this.enchantOptions.clear();
        }
    }

    /**
     * Returns whether the keep-inventory rule permits this player's container
     * drops. The inherited death-drop handler uses this result when deciding
     * whether to append container items and clear their slots; the player
     * reference is instance state.
     */
    @Override
    protected boolean canDropItemInContainers() {
        return !thisPlayer.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.KEEP_INVENTORY);
    }

    /**
     * Updates elytra wear and nearby pickups while the player is alive. Dead
     * players leave both mechanisms untouched through this handler; the two
     * helpers apply their own cadence, mode, and entity-eligibility checks
     * afterward.
     */
    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (thisPlayer.isAlive()) {
            tickElytraDurability(event);
            tickPickUpEntities();
        }
    }

    /**
     * Attempts one durability increase for equipped elytra every twenty entity
     * ticks. The player must be gliding outside creative and spectator modes,
     * and the armor slot is notified after the durability attempt so viewers
     * receive the current chest item.
     */
    private void tickElytraDurability(CEntityTickEvent event) {
        if (event.getCurrentTick() % 20 != 0) {
            // Elytra wear uses a twenty-tick cadence, leaving intervening ticks
            // available for pickup scans without repeating the durability operation.
            return;
        }

        var gameMode = thisPlayer.getGameMode();
        if (thisPlayer.isGliding() &&
            gameMode != GameMode.CREATIVE &&
            gameMode != GameMode.SPECTATOR) {
            var container = getContainer(ContainerTypes.ARMOR);
            var elytra = container.getChestplate();
            if (elytra.getItemType() == ItemTypes.ELYTRA) {
                elytra.tryIncreaseDamage(1);
                container.notifySlotChange(ArmorContainer.CHESTPLATE_SLOT);
            }
        }
    }

    /**
     * Transfers eligible nearby item, arrow, and trident entities into the
     * inventory. An unloaded current chunk or phantom state skips every pickup
     * category. Each category dispatches its own cancellable event before
     * attempting insertion. Item sources require an elapsed pickup delay and
     * retained stack; arrows and tridents also check their queued-removal flag
     * before processing a candidate.
     *
     * <p>
     * Item insertion mutates the source stack's remaining count. A failed
     * insertion can still merge part of that stack; only a zero remainder
     * triggers pickup feedback, clears the entity's item reference, and
     * schedules removal. This preserves the remaining items for later scans
     * when the inventory has limited capacity.
     *
     * <p>
     * Grounded arrows reconstruct one arrow with the same potion type, while
     * infinite arrows are removed without granting an item. Stationary,
     * non-returning tridents use their retained stack and clear it after
     * successful insertion. These source markers prevent a later scan from
     * reusing a transfer that has already completed; they do not introduce
     * locking around the scan.
     */
    protected void tickPickUpEntities() {
        if (!thisPlayer.isCurrentChunkLoaded() || thisPlayer.isPhantom()) {
            return;
        }

        var location = thisPlayer.getLocation();
        var dimension = location.dimension();
        // The search uses a cube around the player's feet. Its corners reach
        // farther than a sphere with the same half-size on each axis.
        var pickUpArea = new AABBd(
                location.x() - 1.425,
                location.y() - 1.425,
                location.z() - 1.425,
                location.x() + 1.425,
                location.y() + 1.425,
                location.z() + 1.425
        );

        var entityItems = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(EntityItem.class::isInstance)
                .map(EntityItem.class::cast)
                .filter(EntityItemBaseComponent::canBePicked)
                .toList();
        for (var entityItem : entityItems) {
            var item = entityItem.getItemStack();
            if (item == null) {
                // A source without item data cannot supply inventory content. A prior
                // pickup may have cleared it before queued removal leaves the spatial index.
                continue;
            }

            var event = new PlayerPickupItemEvent(thisPlayer, entityItem);
            if (!event.call()) {
                continue;
            }

            var inventory = Objects.requireNonNull(getContainer(ContainerTypes.INVENTORY));
            var slot = inventory.tryAddItem(item);
            if (slot == -1) {
                // Insertion can merge some items before reporting failure. Leave the
                // remaining source count available for a later pickup attempt.
                continue;
            }

            if (item.getCount() == 0) {
                entityItem.applyAction(new PickedUpAction(thisPlayer));
                // Clear the consumed source before scheduling removal so another scan
                // cannot reuse its stack while the entity remains in the spatial index.
                entityItem.setItemStack(null);
                entityItem.remove();
            }
        }

        var entityArrows = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(EntityArrow.class::isInstance)
                .map(EntityArrow.class::cast)
                .filter(EntityArrow::isInGround)
                .toList();
        for (var entityArrow : entityArrows) {
            if (entityArrow.willBeDespawnedLater()) {
                // Queued removal already makes this source unavailable. Skip it
                // while the entity is still present in the spatial index for this scan.
                continue;
            }

            if (entityArrow.isPickUpDisabled()) {
                continue;
            }

            if (entityArrow.isInfinite()) {
                // Infinite arrows provide no reusable ammunition. Remove the entity
                // instead of constructing an inventory stack that would duplicate that source.
                entityArrow.remove();
                continue;
            }

            // Entity and inventory arrows use separate representations. Carry the
            // potion type into the reconstructed stack so pickup retains tipped-arrow contents.
            var arrowItem = ItemTypes.ARROW.createItemStack(1);
            arrowItem.setPotionType(entityArrow.getPotionType());

            var event = new PlayerPickupArrowEvent(thisPlayer, entityArrow, arrowItem);
            if (!event.call()) {
                continue;
            }

            if (thisPlayer.getContainer(ContainerTypes.INVENTORY).tryAddItem(arrowItem) != -1) {
                entityArrow.applyAction(new PickedUpAction(thisPlayer));
                entityArrow.remove();
            }
        }

        var entityTridents = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(EntityThrownTrident.class::isInstance)
                .map(EntityThrownTrident.class::cast)
                // Require stationary, non-returning motion so pickups do not collect
                // a trident still falling after support removal or returning to its owner.
                .filter(trident -> trident.getMotion().lengthSquared() == 0 && !trident.isReturning())
                .toList();
        for (var entityTrident : entityTridents) {
            if (entityTrident.willBeDespawnedLater()) {
                // Queued removal already makes this source unavailable. Skip it
                // while the entity is still present in the spatial index for this scan.
                continue;
            }

            var tridentItem = entityTrident.getTridentItem();
            if (tridentItem == null) {
                // A trident without retained item data cannot produce inventory content.
                // Remove this unusable source so later ticks do not reconsider it.
                entityTrident.remove();
                continue;
            }

            var event = new PlayerPickupTridentEvent(thisPlayer, entityTrident, tridentItem);
            if (!event.call()) {
                continue;
            }

            if (thisPlayer.getContainer(ContainerTypes.INVENTORY).tryAddItem(tridentItem) != -1) {
                entityTrident.applyAction(new PickedUpAction(thisPlayer));
                // Clear the transferred stack before queued removal so later scans
                // cannot grant the same retained trident item a second time.
                entityTrident.setTridentItem(null);
                entityTrident.remove();
            }
        }
    }

    /**
     * Loads the four persistent player container groups from compound-list NBT
     * entries. Missing entries or entries of another type leave their
     * corresponding containers unchanged; accepted lists use each container's
     * own item-loading behavior.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList(TAG_OFFHAND, NbtType.COMPOUND, offhandNbt -> getContainer(ContainerTypes.OFFHAND).loadNBT(offhandNbt));
        nbt.listenForList(TAG_INVENTORY, NbtType.COMPOUND, inventoryNbt -> getContainer(ContainerTypes.INVENTORY).loadNBT(inventoryNbt));
        nbt.listenForList(TAG_ARMOR, NbtType.COMPOUND, armorNbt -> getContainer(ContainerTypes.ARMOR).loadNBT(armorNbt));
        nbt.listenForList(TAG_ENDER_ITEMS, NbtType.COMPOUND, enderItemsNbt -> getContainer(ContainerTypes.ENDER_CHEST).loadNBT(enderItemsNbt));
    }

    /**
     * Writes offhand, inventory, armor, and ender-chest lists into the NBT
     * builder.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt()
                .putList(TAG_OFFHAND, NbtType.COMPOUND, getContainer(ContainerTypes.OFFHAND).saveNBT())
                .putList(TAG_INVENTORY, NbtType.COMPOUND, getContainer(ContainerTypes.INVENTORY).saveNBT())
                .putList(TAG_ARMOR, NbtType.COMPOUND, getContainer(ContainerTypes.ARMOR).saveNBT())
                .putList(TAG_ENDER_ITEMS, NbtType.COMPOUND, getContainer(ContainerTypes.ENDER_CHEST).saveNBT());
    }
}
