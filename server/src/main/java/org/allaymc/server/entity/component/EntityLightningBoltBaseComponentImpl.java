package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLightningBoltBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockFireBaseComponentImpl;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntityLightningBoltBaseComponentImpl extends EntityBaseComponentImpl implements EntityLightningBoltBaseComponent {

    protected static final float LIGHTNING_DAMAGE = 5.0f;

    @Getter
    @Setter
    protected boolean effect = true;

    protected int state;
    protected int liveTime;

    public EntityLightningBoltBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.persistent = false;
    }

    @OnInitFinish
    @Override
    public void onInitFinish(EntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        this.state = 2;
        this.liveTime = ThreadLocalRandom.current().nextInt(3) + 1;
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickLightning();
    }

    protected void tickLightning() {
        // Play sounds when lightning first appears
        if (state == 2) {
            getDimension().addSound(location, SimpleSound.THUNDER);
            getDimension().addSound(location, SimpleSound.EXPLOSION);
        }

        state--;

        if (state < 0) {
            if (liveTime == 0) {
                this.remove();
                return;
            } else if (state < -ThreadLocalRandom.current().nextInt(10)) {
                liveTime--;
                state = 1;

                // Try to ignite blocks
                if (effect && getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
                    tryIgniteBlock();
                }
            }
        }

        // Deal damage to nearby entities
        if (state >= 0 && effect) {
            damageNearbyEntities();
        }
    }

    protected void tryIgniteBlock() {
        var dimension = getDimension();
        var blockPos = new Position3i((int) Math.floor(location.x()), (int) Math.floor(location.y()), (int) Math.floor(location.z()), dimension);
        var blockState = dimension.getBlockState(blockPos);
        var blockType = blockState.getBlockType();

        // Only ignite if the block at lightning position is air or tall grass
        if (blockType != BlockTypes.AIR && blockType != BlockTypes.SHORT_GRASS && blockType != BlockTypes.TALL_GRASS) {
            return;
        }

        // Check if the block below can support fire
        var downPos = new Position3i(blockPos.x(), blockPos.y() - 1, blockPos.z(), dimension);
        var downBlockState = dimension.getBlockState(downPos);
        if (!BlockFireBaseComponentImpl.canSupportFire(downBlockState)) {
            return;
        }

        // Fire the BlockIgniteEvent
        var event = new BlockIgniteEvent(
                new Block(downBlockState, downPos, 0),
                null,
                (EntityLightningBolt) thisEntity,
                BlockIgniteEvent.BlockIgniteCause.LIGHTNING
        );

        if (event.call()) {
            dimension.setBlockState(blockPos, BlockTypes.FIRE.getDefaultState());
        }
    }

    protected void damageNearbyEntities() {
        var aabb = thisEntity.getOffsetAABB();
        // Grow the AABB to affect nearby entities
        var expandedAABB = new AABBd(
                aabb.minX() - 3, aabb.minY() - 3, aabb.minZ() - 3,
                aabb.maxX() + 3, aabb.maxY() + 3, aabb.maxZ() + 3
        );

        var entities = getDimension().getEntityManager().getPhysicsService().computeCollidingEntities(expandedAABB);
        for (Entity entity : entities) {
            if (entity != thisEntity) {
                onStruckEntity(entity);
            }
        }
    }

    protected void onStruckEntity(Entity entity) {
        if (entity instanceof EntityLightningBolt) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            var damage = DamageContainer.lightning(thisEntity, LIGHTNING_DAMAGE);
            living.attack(damage);

            // Set entity on fire for 8 seconds (160 ticks)
            living.setOnFireTicks(160);
        }
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.1, 0.0, -0.1, 0.1, 2.0, 0.1);
    }
}
