package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@code PlayerArmorContainerImpl} stores four player armor stacks and refreshes
 * equipment presentation when an armor slot is notified. The listener reads all slots,
 * updates knockback resistance, and publishes armor to entity viewers. Its owner
 * supplier must resolve a non-{@code null} player during notifications and after NBT
 * loading.
 *
 * @author daoge_cmd
 */
public class PlayerArmorContainerImpl extends AbstractPlayerContainer implements ArmorContainer {

    /**
     * {@code KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR} is the resistance contribution
     * added for each equipped netherite armor stack when the armor listener recomputes
     * the owner's resistance.
     */
    protected static final float KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR = 0.1f;

    /**
     * Creates empty armor storage and registers the refresh listener on all four slots.
     * The retained supplier must resolve a non-{@code null} player before notifications
     * or NBT loading invoke that listener.
     */
    public PlayerArmorContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.ARMOR, playerSupplier);
        addSlotChangeListener(0, this::onArmorChange);
        addSlotChangeListener(1, this::onArmorChange);
        addSlotChangeListener(2, this::onArmorChange);
        addSlotChangeListener(3, this::onArmorChange);
    }

    /**
     * Recomputes netherite resistance from all four stored armor slots and publishes
     * equipment to viewers. The callback ignores its stack argument and requires {@code
     * playerSupplier} to resolve a non-{@code null} player.
     */
    protected void onArmorChange(ItemStack $) {
        var knockbackResistance = 0.0f;
        for (var itemStack : content) {
            if (itemStack == ItemAirStack.AIR_STACK ||
                ItemHelper.getArmorTier(itemStack.getItemType()) != ArmorTier.NETHERITE) {
                continue;
            }

            knockbackResistance += KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR;
        }

        var player = playerSupplier.get();
        player.setKnockbackResistance(knockbackResistance);
        player.forEachViewers(viewer -> viewer.viewEntityArmors(player));
    }

    /**
     * Loads serialized armor slots and then refreshes owner resistance and equipment
     * presentation. The base loader does not send ordinary slot notifications, so this
     * explicit refresh applies the loaded equipment state.
     */
    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        super.loadNBT(nbtList);
        // Loading bypasses slot listeners, so refresh the equipment-derived
        // resistance and viewer state after all supplied armor entries have been
        // installed.
        onArmorChange(null);
    }

    /**
     * Returns the fixed armor inventory ID used for built-in updates.
     */
    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.ARMOR;
    }
}
