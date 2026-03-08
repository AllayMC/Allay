package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Cow base behavior: bucket milking, breeding interaction and loot table.
 */
public class EntityCowBaseComponentImpl extends EntityAnimalBaseComponentImpl {

    public EntityCowBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.45, 0.0, -0.45, 0.45, 1.3, 0.45);
    }

    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        if (player == null) {
            return false;
        }

        if (itemStack.getItemType() == ItemTypes.BUCKET && !babyComponent.isBaby()) {
            player.tryConsumeItemInHand();
            player.tryAddItem(ItemTypes.MILK_BUCKET.createItemStack(1));
            player.getDimension().addSound(this.location, SimpleSound.MILKING);
            return true;
        }

        return super.onInteract(player, itemStack);
    }

    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (!canDrop()) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        int leatherCount = rand.nextInt(3) + rand.nextInt(event.getLootingLevel() + 1);
        if (leatherCount > 0) {
            event.getDrops().add(ItemTypes.LEATHER.createItemStack(leatherCount));
        }

        int beefCount = rand.nextInt(1, 3) + rand.nextInt(event.getLootingLevel() + 1);
        var beefType = livingComponent.isOnFire() ? ItemTypes.COOKED_BEEF : ItemTypes.BEEF;
        event.getDrops().add(beefType.createItemStack(beefCount));
    }
}
