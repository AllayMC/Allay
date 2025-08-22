package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.joml.Vector3d;


@Slf4j
public class ItemBowBaseComponentImpl extends ItemBaseComponentImpl{
    public ItemBowBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        return true;
    }

    @Override
    public void releaseItem(EntityPlayer player, long usedTime) {
        log.info("ItemBowBaseComponentImpl.releaseItem");
        log.info("player use item stage {}", player.isUsingItemInAir());
        log.info("start use time {}", player.getStartUsingItemInAirTime());
        var dim = player.getDimension();
        var location = player.getLocation();
        var arrow = EntityTypes.ARROW.createEntity(
                EntityInitInfo.builder()
                        .dimension(dim)
                        .pos(new Vector3d(location.x(), location.y() + player.getEyeHeight(), location.z()))
                        .rot(location.headYaw(), location.pitch())
                        .build());
        arrow.setShooter(player);

        double yawRad = Math.toRadians(location.headYaw());
        double pitchRad = Math.toRadians(location.pitch());

        double x = -Math.sin(yawRad) * Math.cos(pitchRad);
        double y = -Math.sin(pitchRad);
        double z = Math.cos(yawRad) * Math.cos(pitchRad);
        Vector3d direction = new Vector3d(x, y, z).normalize();
        log.info("usedTime: {}", usedTime);
        log.info("{}", direction.length());
        log.info("level: {}", Math.min(1d, (double) (usedTime * (usedTime + 40)) / 1200));
        log.info("direction: {}", direction.mul(Math.min(1d, (double) (usedTime * (usedTime + 40)) / 1200)*3));
        log.info(":speed: {}", direction.length());
        arrow.setMotion(
                new Vector3d(
                        direction
                )
        );
        dim.getEntityService().addEntity(arrow);


    }
}
