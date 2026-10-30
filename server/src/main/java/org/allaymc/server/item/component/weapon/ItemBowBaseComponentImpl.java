package org.allaymc.server.item.component.weapon;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.entity.EntityShootBowEvent;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemArrowStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.container.impl.OffhandContainerImpl;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3d;

/**
 * {@code ItemBowBaseComponentImpl} launches arrows with draw-dependent speed
 * and bow enchantments. Creative players shoot ordinary arrows without
 * ammunition, while other players select arrows from the offhand before the
 * inventory; Infinity preserves ordinary arrows but still consumes tipped
 * arrows.
 *
 * @author harryxi | daoge_cmd
 */
@Slf4j
public class ItemBowBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * Creates a bow base component from the supplied stack initialization data.
     */
    public ItemBowBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns whether the player can begin drawing this bow.
     * Creative mode always qualifies; other players need an arrow stack in the
     * offhand or inventory even when the bow has Infinity. This check does not
     * consume ammunition.
     */
    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        return player.getGameMode() == GameMode.CREATIVE || hasArrow(player);
    }

    /**
     * Attempts a bow shot after at least three ticks of use.
     * The shot reaches full force at twenty ticks; creative mode uses an ordinary
     * arrow, while other modes select ammunition and copy its potion type.
     *
     * <p>
     * Ammunition consumption occurs before {@code EntityShootBowEvent}, so a
     * cancelled event returns {@code false} without restoring consumed arrows.
     * Accepted shots add the prepared arrow, request one bow damage point outside
     * creative mode, and play the shooting sound.
     */
    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        // Reject brief draws before any ammunition is selected or consumed.
        if (usedTime < 3) {
            return false;
        }

        var creative = player.getGameMode() == GameMode.CREATIVE;
        // Twenty draw ticks reach full force; longer draws retain the same capped
        // speed rather than continuing to accelerate the arrow.
        var force = Math.min(usedTime * (usedTime + 40.0) / 1200.0, 1.0);
        var speed = force * 5;

        var infinity = getEnchantmentLevel(EnchantmentTypes.INFINITY) != 0;
        PotionType potionType = null;
        if (!creative) {
            var arrow = findArrow(player, infinity);
            if (arrow == null) {
                return false;
            }

            potionType = arrow.getPotionType();
        }

        var powerLevel = getEnchantmentLevel(EnchantmentTypes.POWER);
        var punchLevel = getEnchantmentLevel(EnchantmentTypes.PUNCH);
        var flameLevel = getEnchantmentLevel(EnchantmentTypes.FLAME);

        var dimension = player.getDimension();
        var location = player.getLocation();
        // Keep entity creation and the shooting sound at the same eye-relative
        // origin; the small downward offset applies to both effects.
        var shootPos = new Vector3d(location.x(), location.y() + player.getEyeHeight() - 0.1, location.z());
        var playerMotion = new Vector3d(player.getMotion());
        // Arrow velocity inherits player motion. Remove grounded vertical motion
        // so aim and horizontal movement control a grounded shot's trajectory.
        if (player.isOnGround()) {
            playerMotion.setComponent(1, 0);
        }
        var arrow = EntityTypes.ARROW.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(shootPos)
                        .rot(-location.yaw(), -location.pitch())
                        .motion(MathUtils.getDirectionVector(location).mul(speed).add(playerMotion))
                        .build()
        );
        arrow.setShooter(player);
        arrow.setPotionType(potionType);
        if (creative || (infinity && potionType == null)) {
            arrow.setInfinite(true);
        }
        arrow.setPowerLevel(powerLevel);
        arrow.setPunchLevel(punchLevel);
        if (flameLevel != 0) {
            // Convert five seconds of burning into twenty-tick seconds.
            arrow.setOnFireTicks(20 * 5);
        }
        arrow.setCritical(force >= 1.0);

        var event = new EntityShootBowEvent(player, thisItemStack, arrow);
        if (!event.call()) {
            return false;
        }

        dimension.getEntityManager().addEntity(arrow);
        if (!creative) {
            tryIncreaseDamage(1);
        }
        player.getDimension().addSound(shootPos, SimpleSound.BOW_SHOOT);

        return true;
    }

    /**
     * Returns whether the offhand or inventory contains an arrow stack.
     * The offhand is checked first, and the search does not change stack counts
     * or reserve an arrow for a later shot.
     */
    protected boolean hasArrow(EntityPlayer player) {
        Container container = player.getContainer(ContainerTypes.OFFHAND);
        if (container.getItemStack(OffhandContainerImpl.OFFHAND_SLOT) instanceof ItemArrowStack) {
            return true;
        }

        // An absent offhand arrow falls back to the main inventory, which supplies
        // ammunition when the player leaves the offhand available for another item.
        container = player.getContainer(ContainerTypes.INVENTORY);
        for (var itemStack : container.getItemStacks()) {
            if (itemStack instanceof ItemArrowStack) {
                return true;
            }
        }

        return false;
    }

    /**
     * Selects an offhand arrow before the first inventory arrow and applies the
     * requested consumption rule. The method returns the selected stack reference,
     * or {@code null} when none exists; {@code infinity} preserves only arrows
     * whose potion type is {@code null}. The caller decides whether creative
     * mode should bypass this operation.
     */
    protected ItemArrowStack findArrow(EntityPlayer player, boolean infinity) {
        Container container;
        int slot;
        ItemArrowStack arrow = null;

        container = player.getContainer(ContainerTypes.OFFHAND);
        slot = OffhandContainerImpl.OFFHAND_SLOT;
        if (container.getItemStack(slot) instanceof ItemArrowStack a) {
            arrow = a;
        }

        // Fall back to inventory only when the offhand has no arrow, preserving
        // the offhand choice when both locations contain different potion variants.
        if (arrow == null) {
            container = player.getContainer(ContainerTypes.INVENTORY);
            var itemStacks = container.getItemStacks();
            for (slot = 0; slot < itemStacks.size(); slot++) {
                var item = itemStacks.get(slot);
                if (item instanceof ItemArrowStack a) {
                    arrow = a;
                    break;
                }
            }
        }

        if (arrow == null) {
            return null;
        }

        // Remove a one-item slot entirely; otherwise notify the container after
        // changing its shared stack so clients observe the new ammunition count.
        if (!infinity || arrow.getPotionType() != null) {
            if (arrow.getCount() == 1) {
                container.clearSlot(slot);
            } else {
                arrow.reduceCount(1);
                container.notifySlotChange(slot);
            }
        }

        return arrow;
    }

}
