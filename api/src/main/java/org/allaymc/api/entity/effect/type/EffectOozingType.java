package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author IWareQ
 */
public class EffectOozingType extends AbstractEffectType {
    EffectOozingType() {
        super(34, new Identifier("minecraft:oozing"), new Color(0x99ffa3), true);
    }

    @Override
    public void onEntityDies(Entity entity, EffectInstance effectInstance) {
        var slimeEntityInitInfo = EntityInitInfo.builder()
                .pos(entity.getLocation())
                .dimension(entity.getDimension())
                .build();

        for (var i = 0; i < 2; i++) {
            var slimeEntity = EntityTypes.SLIME.createEntity(slimeEntityInitInfo);
            entity.getDimension().getEntityManager().addEntity(slimeEntity);
        }
    }
}
