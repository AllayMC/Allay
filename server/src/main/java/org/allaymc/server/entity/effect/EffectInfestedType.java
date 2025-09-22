package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.sound.CustomSound;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class EffectInfestedType extends AbstractEffectType {
    public EffectInfestedType() {
        super(35, new Identifier("minecraft:infested"), new Color(0x8c9b8c), true);
    }

    @Override
    public void onEntityDamage(EntityLiving entity, EffectInstance effectInstance, DamageContainer damage) {
        if (ThreadLocalRandom.current().nextFloat() >= 0.1) return;
        var silverFishNumberToSpawn = ThreadLocalRandom.current().nextFloat() < 0.5 ? 1 : 2;
        var dimension = entity.getDimension();
        var location = entity.getLocation();
        var motion = MathUtils.getDirectionVector(location.pitch(), location.yaw());

        for (var i = 0; i < silverFishNumberToSpawn; i++) {
            var randomOffset = ThreadLocalRandom.current().nextFloat(-1.5707964F, 1.5707964F);
            motion.mul(0.3F).mul(1.0F, 1.5F, 1.0F).rotateY(randomOffset);

            var entityInfo = EntityInitInfo.builder()
                    .pos(location)
                    .dimension(dimension)
                    .motion(motion)
                    .build();
            var silverFishEntity = EntityTypes.SILVERFISH.createEntity(entityInfo);
            dimension.getEntityManager().addEntity(silverFishEntity);
            // TODO: replace this with SimpleSound after find out the usage
            dimension.addSound(location, new CustomSound("entity.silverfish.hurt"));
        }
    }
}
