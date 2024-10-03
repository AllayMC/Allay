package org.allaymc.api.utils.explosion;

import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.position.Position3ic;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;

import java.util.Map;
import java.util.Set;

/**
 * @author heyhey123
 */
public class Explosion {
    private final Position3ic sourcePosition;
    private final Entity explosionSourceEntity;
    private final Entity damageSourceEntity;

    private final boolean causeFire;
    private final boolean damageEntity;
    private final BlockInteraction blockInteraction;
    private final double radius;

    private ParticleType smallExplosionParticles;
    private ParticleType largeExplosionParticles;
    private String soundType;
    //TODO: More sound and particle params that describe the explosion to be perfected.

    private Set<BlockStateWithPos> affectedBlocks;
    private Map<EntityPlayer, Position3ic> hitPlayers;
    private final double yield;

    /**
     * Creates an explosion
     *
     * @param explosionSourceEntity entity that caused the explosion
     * @param damageSourceEntity entity that caused the damage of the explosion
     * @param sourcePosition the position of the explosion
     * @param power the power of the explosion
     * @param causeFire if the explosion should cause fire
     * @param damageEntity if the explosion should damage entities
     * @param destructionType what to do with blocks affected by the explosion
     * @param particle the type of particle to spawn
     * @param emitterParticle the type of emitter particle to spawn
     * @param soundType the type of sound to play when the explosion happens
     *
     */
    Explosion(Entity explosionSourceEntity, Entity damageSourceEntity, Position3ic sourcePosition, double power, boolean causeFire, boolean damageEntity, BlockInteraction destructionType, ParticleType particle, ParticleType emitterParticle, String soundType) {
        this.sourcePosition = sourcePosition;
        this.explosionSourceEntity = explosionSourceEntity;
        this.damageSourceEntity = damageSourceEntity;
        this.radius = Math.max(power, 0.0);
        this.blockInteraction = destructionType;
        this.yield = this.blockInteraction == BlockInteraction.DESTROY_WITH_DECAY ? 1.0d / this.radius : 1.0d;
        this.causeFire = causeFire;
        this.damageEntity = damageEntity;
        this.smallExplosionParticles = particle;
        this.largeExplosionParticles = emitterParticle;
        this.soundType = soundType;
    }


    public enum BlockInteraction {
        KEEP,
        DESTROY,
        DESTROY_WITH_DECAY, // When the blast wave hits a block, it will be attenuated by the block's explosion resistance.
        TRIGGER_BLOCK
    }
}
