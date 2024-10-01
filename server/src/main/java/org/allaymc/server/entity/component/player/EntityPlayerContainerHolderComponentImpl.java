package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.impl.*;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentedObject;
import org.allaymc.server.entity.component.EntityContainerHolderComponentImpl;
import org.allaymc.server.item.enchantment.EnchantmentOptionGenerator;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;

/**
 * @author daoge_cmd
 */
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl implements EntityPlayerContainerHolderComponent {

    @ComponentedObject
    private EntityPlayer thisPlayer;

    public EntityPlayerContainerHolderComponentImpl() {
        super(
                new PlayerCreatedOutputContainer(),
                new CraftingGridContainer(),
                new CraftingTableContainer()
        );
        var enchantTableContainer = new EnchantTableContainer();
        enchantTableContainer.addOnSlotChangeListener(EnchantTableContainer.INPUT_SLOT, item -> onEnchantTableContainerInputItemChange(item, new Position3i(enchantTableContainer.getBlockPos(), thisPlayer.getDimension())));
        addContainer(enchantTableContainer);
        // We shouldn't provide thisPlayer object directly
        // because at that time thisPlayer is null
        addContainer(new PlayerArmorContainer(() -> thisPlayer));
        addContainer(new PlayerInventoryContainer(() -> thisPlayer));
        addContainer(new PlayerOffhandContainer(() -> thisPlayer));
        addContainer(new PlayerCursorContainer(() -> thisPlayer));
    }

    protected void onEnchantTableContainerInputItemChange(ItemStack item, Position3ic enchantTablePos) {
        var pk = new PlayerEnchantOptionsPacket();
        if (item != ItemAirStack.AIR_STACK) {
            pk.getOptions().addAll(EnchantmentOptionGenerator.generateEnchantOptions(enchantTablePos, item, thisPlayer.getEnchantmentSeed()));
        }

        thisPlayer.sendPacket(pk);
    }

    @Override
    protected boolean canDropItemInContainers() {
        return !(boolean) thisPlayer.getWorld().getWorldData().getGameRule(GameRule.KEEP_INVENTORY);
    }
}
