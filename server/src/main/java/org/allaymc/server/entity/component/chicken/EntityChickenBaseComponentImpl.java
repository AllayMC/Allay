package org.allaymc.server.entity.component.chicken;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.allaymc.server.entity.component.animal.EntityAnimalBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Chicken base behavior: breeding interaction, egg laying and loot table.
 */
public class EntityChickenBaseComponentImpl extends EntityAnimalBaseComponentImpl {

    protected static final int MIN_EGG_LAY_TICKS = 6000;
    protected static final int MAX_EGG_LAY_TICKS = 12000;
    protected static final String TAG_EGG_LAY_TIME = "EggLayTime";

    protected int eggLayTime = nextEggLayTime();

    public EntityChickenBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.3, 0.0, -0.3, 0.3, 0.8, 0.3);
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (!thisEntity.isAlive() || babyComponent.isBaby()) return;

        eggLayTime--;
        if (eggLayTime > 0) return;

        var loc = thisEntity.getLocation();
        var pos = new Vector3d(loc.x(), loc.y(), loc.z());
        thisEntity.getDimension().dropItem(ItemTypes.EGG.createItemStack(1), pos);
        thisEntity.getDimension().addSound(pos, new CustomSound(SoundNames.MOB_CHICKEN_PLOP));
        if (aiComponent != null) {
            aiComponent.getMemoryStorage().put(MemoryTypes.LAST_EGG_SPAWN_TIME, thisEntity.getTick());
        }

        eggLayTime = nextEggLayTime();
    }

    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (!canDrop()) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        int chickenCount = 1 + rand.nextInt(event.getLootingLevel() + 1);
        var chickenType = livingComponent.isOnFire() ? ItemTypes.COOKED_CHICKEN : ItemTypes.CHICKEN;
        event.getDrops().add(chickenType.createItemStack(chickenCount));

        int featherCount = rand.nextInt(3) + rand.nextInt(event.getLootingLevel() + 1);
        if (featherCount > 0) {
            event.getDrops().add(ItemTypes.FEATHER.createItemStack(featherCount));
        }
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putInt(TAG_EGG_LAY_TIME, eggLayTime);
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForInt(TAG_EGG_LAY_TIME, value -> this.eggLayTime = Math.max(1, value));
    }

    protected static int nextEggLayTime() {
        return ThreadLocalRandom.current().nextInt(MIN_EGG_LAY_TICKS, MAX_EGG_LAY_TICKS + 1);
    }
}
