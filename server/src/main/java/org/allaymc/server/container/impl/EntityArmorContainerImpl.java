package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@code EntityArmorContainerImpl} stores four armor stacks and refreshes equipment
 * presentation when an armor slot is notified. The armor listener recomputes the
 * netherite contribution from all four slots, updates owner knockback resistance, and
 * publishes armor equipment to entity viewers.
 *
 * @author daoge_cmd
 */
public class EntityArmorContainerImpl extends BaseContainer implements ArmorContainer {

    /**
     * {@code KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR} is the resistance contribution
     * added for each equipped netherite armor stack when the armor listener recomputes
     * the owner's resistance.
     */
    protected static final float KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR = 0.1f;
    /**
     * {@code entitySupplier} resolves the entity during armor refresh. Capability checks
     * select whether that entity receives physics resistance or equipment-viewer
     * updates.
     */
    protected final Supplier<? extends Entity> entitySupplier;

    /**
     * Creates empty armor storage and registers the armor refresh listener on all four
     * equipment slots. The supplied owner resolver is retained for later refreshes
     * rather than evaluated during construction.
     */
    public EntityArmorContainerImpl(Supplier<? extends Entity> entitySupplier) {
        super(ContainerTypes.ARMOR);
        this.entitySupplier = entitySupplier;
        addSlotChangeListener(0, this::onArmorChange);
        addSlotChangeListener(1, this::onArmorChange);
        addSlotChangeListener(2, this::onArmorChange);
        addSlotChangeListener(3, this::onArmorChange);
    }

    /**
     * Recomputes resistance from the currently equipped netherite armor and publishes
     * armor equipment to viewers. The callback ignores its stack argument because every
     * refresh reads all four stored slots.
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

        var entity = entitySupplier.get();
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            physicsComponent.setKnockbackResistance(knockbackResistance);
        }
        if (entity instanceof EntityContainerHolderComponent) {
            entity.forEachViewers(viewer -> viewer.viewEntityArmors((Entity & EntityContainerHolderComponent) entity));
        }
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
}
