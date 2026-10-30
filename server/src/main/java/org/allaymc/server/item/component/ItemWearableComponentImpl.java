package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.block.property.enums.CauldronLiquid;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.sound.EquipItemSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.component.event.CItemRightClickInAirEvent;
import org.allaymc.server.item.component.event.CItemUseOnBlockEvent;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.CAULDRON_LIQUID;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.FILL_LEVEL;

/**
 * {@code ItemWearableComponentImpl} equips an item in its configured armor slot
 * during player interactions. The component can disable interaction-based wearing
 * while still exposing the armor type. Dyeable items defer water-cauldron clicks
 * to the block behavior so color interactions can proceed before equipment
 * changes.
 *
 * @author daoge_cmd
 */
public class ItemWearableComponentImpl implements ItemWearableComponent {

    /**
     * {@code IDENTIFIER} names this behavior provider in the item component system,
     * allowing item type builders to select or replace this component by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_wearable_component");

    /**
     * Configured armor equipment slot type.
     */
    @Getter
    protected final ArmorType armorType;
    /**
     * {@code wearWhenInteract} controls whether {@code equipArmor} transfers items.
     * A disabled value leaves inventories and equip sound unchanged when that helper
     * is invoked.
     */
    protected final boolean wearWhenInteract;

    /**
     * Injected component-owning item stack.
     */
    @ComponentObject
    protected ItemStack thisItemStack;

    /**
     * Creates a wearable component with interaction-based equipment enabled.
     * The supplied armor type selects the armor-container slot used by later
     * equipment attempts, while the owning stack is injected separately.
     */
    public ItemWearableComponentImpl(ArmorType armorType) {
        this(armorType, true);
    }

    /**
     * Creates a wearable component with a caller-selected interaction policy.
     * The component retains the armor type and flag without validation; disabling
     * the flag makes its equipment helper leave inventories unchanged.
     */
    public ItemWearableComponentImpl(ArmorType armorType, boolean wearWhenInteract) {
        this.armorType = armorType;
        this.wearWhenInteract = wearWhenInteract;
    }

    /**
     * Handles a block-use event by attempting to equip this item.
     * Missing interaction data and dyeable-item clicks on a filled water cauldron
     * return without marking the event handled; other paths mark it handled even
     * when interaction-based wearing is disabled.
     */
    @EventHandler
    protected void onUseItemOnBlock(CItemUseOnBlockEvent event) {
        var interactInfo = event.getInteractInfo();
        var dimension = event.getDimension();
        if (interactInfo == null) {
            return;
        }

        // A filled water cauldron handles dyeable-item color changes through
        // block interaction, so leave this event unhandled for that path.
        if (thisItemStack instanceof ItemDyeableComponent) {
            var clickedBlock = dimension.getBlockState(interactInfo.clickedBlockPos());
            if (clickedBlock.getBlockType() == BlockTypes.CAULDRON) {
                var fillLevel = clickedBlock.getPropertyValue(FILL_LEVEL);
                var liquid = clickedBlock.getPropertyValue(CAULDRON_LIQUID);
                if (fillLevel > 0 && liquid == CauldronLiquid.WATER) {
                    return;
                }
            }
        }

        equipArmor(interactInfo.player(), thisItemStack);
        event.setCanBeUsed(true);
    }

    /**
     * Attempts interaction-based equipment for the player supplied by the air-click event.
     */
    @EventHandler
    protected void onRightClickItemInAir(CItemRightClickInAirEvent event) {
        equipArmor(event.getPlayer(), thisItemStack);
    }

    /**
     * Moves the supplied stack into the configured armor slot when wearing is enabled.
     * An occupied armor slot becomes the player's held stack; an empty slot clears
     * the hand before assignment. The helper retains stack references and plays equip
     * sound after updating the armor slot.
     */
    protected void equipArmor(EntityPlayer player, ItemStack itemStack) {
        if (!wearWhenInteract) {
            return;
        }

        var armorType = getArmorType();
        var armorContainer = player.getContainer(ContainerTypes.ARMOR);
        if (armorContainer.isEmpty(armorType.ordinal())) {
            player.getContainer(ContainerTypes.INVENTORY).clearItemInHand();
        } else {
            player.getContainer(ContainerTypes.INVENTORY).setItemInHand(armorContainer.getItemStack(armorType.ordinal()));
        }
        armorContainer.setItemStack(getArmorType().ordinal(), itemStack);
        player.getDimension().addSound(player.getLocation(), new EquipItemSound(itemStack.getItemType()));
    }
}
