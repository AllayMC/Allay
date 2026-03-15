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
 * @author daoge_cmd
 */
public class ItemWearableComponentImpl implements ItemWearableComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_wearable_component");

    @Getter
    protected final ArmorType armorType;
    protected final boolean wearWhenInteract;

    @ComponentObject
    protected ItemStack thisItemStack;

    public ItemWearableComponentImpl(ArmorType armorType) {
        this(armorType, true);
    }

    public ItemWearableComponentImpl(ArmorType armorType, boolean wearWhenInteract) {
        this.armorType = armorType;
        this.wearWhenInteract = wearWhenInteract;
    }

    @EventHandler
    protected void onUseItemOnBlock(CItemUseOnBlockEvent event) {
        var interactInfo = event.getInteractInfo();
        var dimension = event.getDimension();
        if (interactInfo == null) {
            return;
        }

        // If dyeable armor (leather armor) clicks on a water cauldron, let cauldron handle it
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

    @EventHandler
    protected void onRightClickItemInAir(CItemRightClickInAirEvent event) {
        equipArmor(event.getPlayer(), thisItemStack);
    }

    protected void equipArmor(EntityPlayer player, ItemStack itemStack) {
        if (!wearWhenInteract) {
            return;
        }

        var armorType = getArmorType();
        var armorContainer = player.getContainer(ContainerTypes.ARMOR);
        if (armorContainer.isEmpty(armorType.ordinal())) {
            player.getContainer(ContainerTypes.INVENTORY).clearItemInHand();
        } else {
            // Swap armor
            player.getContainer(ContainerTypes.INVENTORY).setItemInHand(armorContainer.getItemStack(armorType.ordinal()));
        }
        armorContainer.setItemStack(getArmorType().ordinal(), itemStack);
        player.getDimension().addSound(player.getLocation(), new EquipItemSound(itemStack.getItemType()));
    }
}
