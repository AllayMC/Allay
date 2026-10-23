package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectOozingType} represents oozing metadata and spawns two slimes
 * when its owning entity dies. Both slimes use the entity's current location
 * and dimension with default initialization settings; this callback does not
 * vary their number with effect level.
 *
 * @author IWareQ
 */
public class EffectOozingType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful oozing effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectOozingType() {
        super(34, new Identifier("minecraft:oozing"), new Color(0x99ffa3), true);
    }

    /**
     * Creates two slimes from shared initialization data at the dying entity's
     * location. Each slime is added separately to the entity manager, and a
     * later failure does not remove a slime already added.
     */
    @Override
    public void onEntityDies(EntityLiving entity, EffectInstance effectInstance) {
        var slimeEntityInitInfo = EntityInitInfo.builder()
                .pos(entity.getLocation())
                .dimension(entity.getDimension())
                .build();

        // The death callback requests two default slimes regardless of effect strength.
        for (var i = 0; i < 2; i++) {
            var slimeEntity = EntityTypes.SLIME.createEntity(slimeEntityInitInfo);
            entity.getDimension().getEntityManager().addEntity(slimeEntity);
        }
    }
}
