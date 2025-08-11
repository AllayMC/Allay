package org.allaymc.server.item.component.projectile;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.ProjectileLaunchEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3d;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.component.event.CItemClickInAirEvent;
import org.allaymc.server.item.component.event.CItemInteractEntityEvent;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3d;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ItemProjectileComponentImpl implements ItemProjectileComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_projectile_component");

    protected EntityId projectileEntityId;
    protected double throwForce;

    @ComponentObject
    protected ItemStack thisItemStack;

    public ItemProjectileComponentImpl(EntityId projectileEntityId, double throwForce) {
        this.projectileEntityId = projectileEntityId;
        this.throwForce = throwForce;
    }

    @Override
    public EntityType<?> getProjectileEntityType() {
        return this.projectileEntityId.getEntityType();
    }

    @Override
    public double getThrowForce() {
        return this.throwForce;
    }

    @Override
    public boolean shoot(Entity shooter) {
        var location = shooter.getLocation();
        var shootPos = new Vector3d(location.x(), location.y() + shooter.getEyeHeight(), location.z());
        var projectile = createProjectile(shooter, shootPos);
        if (projectile == null) {
            return false;
        }

        var event = new ProjectileLaunchEvent(projectile, shooter, getThrowForce());
        if (!event.call()) {
            return false;
        }

        projectile.setMotion(MathUtils.getDirectionVector(shooter.getLocation()).mul(event.getThrowForce()));
        shooter.getDimension().getEntityService().addEntity(projectile);
        addShootSound(new Position3d(shootPos, location.dimension()));
        if (!(shooter instanceof EntityPlayer player) || player.getGameType() != GameType.CREATIVE) {
            thisItemStack.reduceCount(1);
        }

        return true;
    }

    protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
        var entity = getProjectileEntityType().createEntity(EntityInitInfo.builder().dimension(shooter.getDimension()).pos(shootPos).build());
        if (!(entity instanceof EntityProjectile projectile)) {
            log.error("Entity {} is not a projectile entity", projectileEntityId);
            return null;
        }

        projectile.setShooter(shooter);
        return projectile;
    }

    @EventHandler
    protected void onClickItemInAir(CItemClickInAirEvent event) {
        shoot(event.getPlayer());
    }

    @EventHandler
    protected void onInteractEntity(CItemInteractEntityEvent event) {
        event.setCanBeUsed(shoot(event.getPerformer()));
    }

    protected void addShootSound(Position3d pos) {
        pos.dimension().addLevelSoundEvent(pos.x, pos.y, pos.z, SoundEvent.THROW, -1, "minecraft:player", false, false);
    }
}
