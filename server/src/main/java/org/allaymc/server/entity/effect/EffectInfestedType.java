package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EffectInfestedType} represents infested metadata and can spawn
 * silverfish during a damage callback. A probability gate first decides whether
 * spawning occurs; a second draw chooses one or two silverfish. The effect
 * level and supplied damage container do not alter these choices.
 *
 * <p>
 * Spawn motion starts from the affected entity's facing direction and is scaled
 * and rotated in place for each silverfish. The second iteration therefore
 * transforms the first iteration's vector rather than an untouched direction.
 * The initialization builder snapshots the vector components for each spawn, so
 * later vector mutation does not change motion already encoded for the earlier
 * entity. Each created silverfish also produces its hit sound.
 *
 * @author IWareQ
 */
public class EffectInfestedType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful infested effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectInfestedType() {
        super(35, new Identifier("minecraft:infested"), new Color(0x8c9b8c), true);
    }

    /**
     * Attempts silverfish spawning with a ten-percent gate, then chooses one or
     * two entities with equal probability. The affected entity's dimension,
     * location, and facing direction supply the initial spawn state; damage
     * magnitude and effect level are ignored.
     *
     * <p>
     * The loop mutates one shared motion vector cumulatively, so the second
     * silverfish's launch derives from the already transformed first direction.
     * Each builder call snapshots the current components before creation and
     * insertion into the entity manager. A hit sound is emitted for each
     * spawned entity.
     */
    @Override
    public void onEntityDamage(EntityLiving entity, EffectInstance effectInstance, DamageContainer damage) {
        // Sample the spawn gate once per callback so either possible silverfish
        // shares the same triggering damage observation rather than receiving an independent trigger.
        if (ThreadLocalRandom.current().nextFloat() >= 0.1) {
            return;
        }

        // Both permitted spawn counts receive equal probability after the shared trigger gate.
        var silverFishNumberToSpawn = ThreadLocalRandom.current().nextFloat() < 0.5 ? 1 : 2;
        var dimension = entity.getDimension();
        var location = entity.getLocation();
        var motion = MathUtils.getDirectionVector(location.pitch(), location.yaw());

        for (var i = 0; i < silverFishNumberToSpawn; i++) {
            // The launch rotation spans approximately ninety degrees to either side.
            var randomOffset = ThreadLocalRandom.current().nextFloat(-1.5707964F, 1.5707964F);
            // Reuse the transformed vector across iterations. A second spawn inherits
            // the earlier scaling and rotation before this iteration applies its own transform.
            motion.mul(0.3F).mul(1.0F, 1.5F, 1.0F).rotateY(randomOffset);

            var entityInfo = EntityInitInfo.builder()
                    .pos(location)
                    .dimension(dimension)
                    .motion(motion)
                    .build();
            var silverFishEntity = EntityTypes.SILVERFISH.createEntity(entityInfo);
            dimension.getEntityManager().addEntity(silverFishEntity);
            dimension.addSound(location, new CustomSound(SoundNames.MOB_SILVERFISH_HIT));
        }
    }
}
