package org.allaymc.server.entity.component.item;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityArmorStandBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.humanlike.EntityHumanLikeBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import static org.allaymc.api.item.data.ItemTags.HEAD;

/**
 * {@code EntityArmorStandBaseComponentImpl} represents armor stand poses and
 * player equipment interactions. Pose changes clamp to the supported range and
 * publish entity state. Equipment routing chooses armor, offhand, or main-hand
 * storage from the supplied item, while an empty hand selects the first
 * occupied slot in a fixed order.
 *
 * <p>
 * Equipment replacement installs a one-item copy before consuming the player
 * stack and returning previous equipment. Survival interactions decrement the
 * original held stack; creative interactions leave it unchanged. Existing
 * equipment goes to the player inventory when possible and falls into the
 * world when insertion fails. The transfer helpers do not roll back earlier
 * container changes after a later failure.
 *
 * @author daoge_cmd
 */
public class EntityArmorStandBaseComponentImpl extends EntityHumanLikeBaseComponentImpl implements EntityArmorStandBaseComponent {

    /**
     * {@code TAG_POSE_INDEX} names the pose integer persisted alongside
     * inherited armor stand entity data.
     */
    protected static final String TAG_POSE_INDEX = "PoseIndex";

    /**
     * Injected armor stand equipment container provider.
     */
    @Dependency
    protected EntityContainerHolderComponent containerHolder;

    /**
     * Current supported armor stand pose index.
     */
    protected int poseIndex = 0;

    /**
     * Creates the armor stand base with the supplied entity initialization
     * context.
     */
    public EntityArmorStandBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns the current pose index stored by this armor stand.
     */
    @Override
    public int getPoseIndex() {
        return poseIndex;
    }

    /**
     * Clamps the requested pose to the supported index range and publishes
     * entity state. Values outside that range select the nearest endpoint
     * instead of causing an invalid pose.
     */
    @Override
    public void setPoseIndex(int poseIndex) {
        // Clamp endpoints to the client-supported pose range before publishing the
        // new state.
        this.poseIndex = Math.max(0, Math.min(poseIndex, MAX_POSE_INDEX));
        thisEntity.broadcastState();
    }

    /**
     * Returns the standing armor stand local bounding box in blocks.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.25, 0.0, -0.25, 0.25, 1.975, 0.25);
    }

    /**
     * Handles pose cycling or equipment interaction for a nonspectator player.
     * Sneaking takes precedence over equipment transfer, and the return value
     * reports whether one of those interactions was accepted.
     */
    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        if (player.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }

        if (player.isSneaking()) {
            // Wrap after the last supported pose so repeated sneaking interactions
            // cycle every pose.
            int newPose = (poseIndex + 1) % (MAX_POSE_INDEX + 1);
            setPoseIndex(newPose);
            return true;
        }

        return tryEquipmentInteraction(player, itemStack);
    }

    /**
     * Routes a supplied wearable, head, shield, or other held item to the
     * matching armor stand slot. A {@code null} or air stack instead takes
     * main-hand, offhand, then armor equipment in order.
     */
    protected boolean tryEquipmentInteraction(EntityPlayer player, ItemStack itemStack) {
        var armorContainer = containerHolder.getContainer(ContainerTypes.ARMOR);
        var handContainer = containerHolder.getContainer(ContainerTypes.ENTITY_HAND);
        var offhandContainer = containerHolder.getContainer(ContainerTypes.OFFHAND);

        if (itemStack != null && itemStack != ItemAirStack.AIR_STACK) {
            if (itemStack instanceof ItemWearableComponent wearableComponent) {
                ArmorType armorType = wearableComponent.getArmorType();
                int slot = armorType.ordinal();
                return trySwapItem(player, itemStack, armorContainer, slot);
            }

            if (itemStack.getItemType().hasItemTag(HEAD)) {
                return trySwapItem(player, itemStack, armorContainer, ArmorContainer.HELMET_SLOT);
            }

            if (itemStack.getItemType() == ItemTypes.SHIELD) {
                return trySwapItem(player, itemStack, offhandContainer, 0);
            }

            return trySwapItem(player, itemStack, handContainer, 0);
        } else {
            if (!handContainer.isEmpty(0)) {
                return tryTakeItem(player, handContainer, 0);
            }
            if (!offhandContainer.isEmpty(0)) {
                return tryTakeItem(player, offhandContainer, 0);
            }
            for (int slot = 0; slot < 4; slot++) {
                if (!armorContainer.isEmpty(slot)) {
                    return tryTakeItem(player, armorContainer, slot);
                }
            }
        }

        return false;
    }

    /**
     * Installs a one-item copy into the selected equipment slot and returns
     * {@code true}. The original player stack is consumed only outside
     * creative mode, after the equipment replacement has already occurred. The
     * caller supplies an existing valid container and slot.
     *
     * <p>
     * Previous equipment is inserted into the player inventory after
     * consumption. Failed insertion drops that equipment at the player
     * location, preserving a world result for the displaced stack. The helper
     * plays the placement sound after these operations and does not roll back
     * the installed equipment if a later operation fails.
     */
    protected boolean trySwapItem(EntityPlayer player, ItemStack playerItem, Container container, int slot) {
        var existingItem = container.getItemStack(slot);
        boolean slotWasEmpty = (existingItem == ItemAirStack.AIR_STACK);

        var itemToPlace = playerItem.copy();
        itemToPlace.setCount(1);
        // Install the replacement before consuming the held stack and returning the
        // displaced equipment; later failures do not roll back this slot.
        container.setItemStack(slot, itemToPlace);

        if (player.getGameMode() != GameMode.CREATIVE) {
            playerItem.setCount(playerItem.getCount() - 1);
            player.getContainer(ContainerTypes.INVENTORY).notifySlotChange(player.getContainer(ContainerTypes.INVENTORY).getHandSlot());
        }

        if (!slotWasEmpty) {
            int addedSlot = player.getContainer(ContainerTypes.INVENTORY).tryAddItem(existingItem);
            if (addedSlot == -1) {
                // Inventory rejection leaves the displaced equipment available as a
                // world drop instead of discarding the stack.
                getDimension().dropItem(existingItem, player.getLocation());
            }
        }

        getDimension().addSound(getLocation(), SimpleSound.ARMOR_STAND_PLACE);
        return true;
    }

    /**
     * Transfers equipment from the selected armor stand slot and returns
     * whether a stack existed. The helper first attempts player inventory
     * insertion and drops the retained stack at the player location if
     * insertion fails. An initially empty slot produces {@code false} without
     * a transfer.
     *
     * <p>
     * The source slot is cleared after insertion or dropping, followed by a
     * placement sound. Callers must use a valid container and slot and must
     * not invoke competing transfers concurrently, because these steps have no
     * rollback or synchronization around the shared item reference.
     */
    protected boolean tryTakeItem(EntityPlayer player, Container container, int slot) {
        if (container.isEmpty(slot)) {
            return false;
        }
        var item = container.getItemStack(slot);

        int addedSlot = player.getContainer(ContainerTypes.INVENTORY).tryAddItem(item);
        if (addedSlot == -1) {
            // Inventory rejection leaves the taken equipment available as a world
            // drop before its source slot is cleared.
            getDimension().dropItem(item, player.getLocation());
        }
        container.clearSlot(slot);

        getDimension().addSound(getLocation(), SimpleSound.ARMOR_STAND_PLACE);
        return true;
    }

    /**
     * Loads inherited entity data and restores a present pose within the
     * supported range. Missing pose data retains the current value, while
     * stored out-of-range values are clamped before assignment.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        if (nbt.containsKey(TAG_POSE_INDEX)) {
            // Clamp persisted pose data as well so loading cannot install an
            // unsupported client pose.
            this.poseIndex = Math.max(0, Math.min(nbt.getInt(TAG_POSE_INDEX), MAX_POSE_INDEX));
        }
    }

    /**
     * Returns inherited entity data with the current armor stand pose
     * persisted.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putInt(TAG_POSE_INDEX, poseIndex)
                .build();
    }
}
