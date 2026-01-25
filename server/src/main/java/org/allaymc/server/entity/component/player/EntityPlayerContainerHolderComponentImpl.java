package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.action.PickedUpAction;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerEnchantOptionsRequestEvent;
import org.allaymc.api.eventbus.event.player.PlayerPickupArrowEvent;
import org.allaymc.api.eventbus.event.player.PlayerPickupItemEvent;
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
import org.allaymc.server.network.NetworkHelper;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;
import org.joml.primitives.AABBd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl {

    protected static final String TAG_OFFHAND = "Offhand";
    protected static final String TAG_INVENTORY = "Inventory";
    protected static final String TAG_ARMOR = "Armor";
    protected static final String TAG_ENDER_ITEMS = "EnderChestInventory";

    @ComponentObject
    private EntityPlayer thisPlayer;

    private final List<Long> enchantOptions;

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

        // We shouldn't provide the player object directly, because at that time 'thisPlayer' is null
        addContainer(new ArmorContainerImpl(() -> thisPlayer));
        addContainer(new InventoryContainerImpl(() -> thisPlayer));
        addContainer(new OffhandContainerImpl(() -> thisPlayer));
        addContainer(new PlayerCursorContainerImpl(() -> thisPlayer));
    }

    protected void onEnchantTableContainerInputItemChange(ItemStack item, Position3ic enchantTablePos) {
        if (thisPlayer.isActualPlayer()) {
            clearEnchantOptions();
            var packet = new PlayerEnchantOptionsPacket();
            if (item != ItemAirStack.AIR_STACK) {
                var enchantOptions = EnchantmentOptionGenerator.generateEnchantOptions(enchantTablePos, item, thisPlayer.getEnchantmentSeed());
                var event = new PlayerEnchantOptionsRequestEvent(thisPlayer, enchantOptions.stream().map(Pair::right).toList());
                if (!event.call()) {
                    return;
                }

                for (var option : enchantOptions) {
                    packet.getOptions().add(NetworkHelper.toNetwork(option));
                    this.enchantOptions.add(Long.valueOf(option.left()));
                }
            }

            thisPlayer.getController().sendPacket(packet);
        }
    }

    private void clearEnchantOptions() {
        if (!this.enchantOptions.isEmpty()) {
            EnchantmentOptionGenerator.removeEnchantOptions(this.enchantOptions);
            this.enchantOptions.clear();
        }
    }

    @Override
    protected boolean canDropItemInContainers() {
        return !thisPlayer.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.KEEP_INVENTORY);
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (thisPlayer.isAlive()) {
            tickElytraDurability(event);
            tickPickUpEntities();
        }
    }

    private void tickElytraDurability(CEntityTickEvent event) {
        if (event.getCurrentTick() % 20 != 0) {
            // Try to increase elytra damage every 20 ticks (1 second)
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

    protected void tickPickUpEntities() {
        if (!thisPlayer.isCurrentChunkLoaded()) {
            return;
        }

        var location = thisPlayer.getLocation();
        var dimension = location.dimension();
        var pickUpArea = new AABBd(
                location.x() - 1.425,
                location.y() - 1.425,
                location.z() - 1.425,
                location.x() + 1.425,
                location.y() + 1.425,
                location.z() + 1.425
        );

        // Pick up items
        var entityItems = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(EntityItem.class::isInstance)
                .map(EntityItem.class::cast)
                .filter(EntityItemBaseComponent::canBePicked)
                .toList();
        for (var entityItem : entityItems) {
            var item = entityItem.getItemStack();
            if (item == null) {
                // Have been picked by others
                continue;
            }

            var event = new PlayerPickupItemEvent(thisPlayer, entityItem);
            if (!event.call()) {
                // Event was cancelled, skip pickup
                continue;
            }

            var inventory = Objects.requireNonNull(getContainer(ContainerTypes.INVENTORY));
            var slot = inventory.tryAddItem(item);
            if (slot == -1) {
                // Player's inventory is full and cannot pick up the item
                continue;
            }

            if (item.getCount() == 0) {
                entityItem.applyAction(new PickedUpAction(thisPlayer));
                // Set the item to null to prevent others from picking this item twice
                entityItem.setItemStack(null);
                entityItem.remove();
            }
        }

        // Pick up arrows
        var entityArrows = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(EntityArrow.class::isInstance)
                .map(EntityArrow.class::cast)
                .filter(arrow -> arrow.getMotion().lengthSquared() == 0)
                .toList();
        for (var entityArrow : entityArrows) {
            if (entityArrow.willBeDespawnedNextTick()) {
                // Have been picked by others
                continue;
            }

            if (entityArrow.isPickUpDisabled()) {
                continue;
            }

            if (entityArrow.isInfinite()) {
                // Arrow shot by bow with infinity enchantment or shot by a creative player can't be picked up
                entityArrow.remove();
                continue;
            }

            var arrowItem = ItemTypes.ARROW.createItemStack(1);
            arrowItem.setPotionType(entityArrow.getPotionType());

            var event = new PlayerPickupArrowEvent(thisPlayer, entityArrow, arrowItem);
            if (!event.call()) {
                // Event was cancelled, skip pickup
                continue;
            }

            if (thisPlayer.getContainer(ContainerTypes.INVENTORY).tryAddItem(arrowItem) != -1) {
                entityArrow.applyAction(new PickedUpAction(thisPlayer));
                entityArrow.remove();
            }
        }
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList(TAG_OFFHAND, NbtType.COMPOUND, offhandNbt -> getContainer(ContainerTypes.OFFHAND).loadNBT(offhandNbt));
        nbt.listenForList(TAG_INVENTORY, NbtType.COMPOUND, inventoryNbt -> getContainer(ContainerTypes.INVENTORY).loadNBT(inventoryNbt));
        nbt.listenForList(TAG_ARMOR, NbtType.COMPOUND, armorNbt -> getContainer(ContainerTypes.ARMOR).loadNBT(armorNbt));
        nbt.listenForList(TAG_ENDER_ITEMS, NbtType.COMPOUND, enderItemsNbt -> getContainer(ContainerTypes.ENDER_CHEST).loadNBT(enderItemsNbt));
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt()
                .putList(TAG_OFFHAND, NbtType.COMPOUND, getContainer(ContainerTypes.OFFHAND).saveNBT())
                .putList(TAG_INVENTORY, NbtType.COMPOUND, getContainer(ContainerTypes.INVENTORY).saveNBT())
                .putList(TAG_ARMOR, NbtType.COMPOUND, getContainer(ContainerTypes.ARMOR).saveNBT())
                .putList(TAG_ENDER_ITEMS, NbtType.COMPOUND, getContainer(ContainerTypes.ENDER_CHEST).saveNBT());
    }
}
