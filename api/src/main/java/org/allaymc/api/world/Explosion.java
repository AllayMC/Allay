package org.allaymc.api.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents an explosion.
 * <p>
 * This object contains the information of an explosion, including the size,
 * whether the fire should be spawned, itemDropChance and so on. It then can
 * be performed in a dimension.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class Explosion {

    protected static final Vector3fc[] RAYS = new Vector3fc[1352];

    static {
        int index = 0;
        for (float x = 0; x < 16; x++) {
            for (float y = 0; y < 16; y++) {
                for (float z = 0; z < 16; z++) {
                    if (x != 0 && x != 15 && y != 0 && y != 15 && z != 0 && z != 15) {
                        continue;
                    }
                    RAYS[index++] = new Vector3f(x / 15.0f * 2 - 1, y / 15.0f * 2 - 1, z / 15.0f * 2 - 1).normalize(0.3f);
                }
            }
        }
    }

    /**
     * The size of the explosion, it is effectively the radius which entities/blocks will be affected within.
     */
    protected float size;
    /**
     * Set this field to {@code true} will cause the explosion to randomly start fires in 1/3 of all destroyed air blocks that are
     * above opaque blocks.
     */
    protected boolean spawnFire;
    /**
     * Specifies how item drops should be handled. By default,
     * the item drop chance is {@code 1/size}. If negative, no items will be dropped by
     * the explosion. If set to 1 or higher, all items are dropped.
     */
    protected float itemDropChance;
    /**
     * The sound to play when the explosion is created.
     */
    protected SoundEvent sound;
    /**
     * The particle to spawn when the explosion is created.
     */
    protected ParticleType particle;

    /**
     * @see #Explosion(float, boolean, float, SoundEvent, ParticleType)
     */
    public Explosion() {
        this(4);
    }

    /**
     * @see #Explosion(float, boolean, float, SoundEvent, ParticleType)
     */
    public Explosion(float size) {
        this(size, false);
    }

    /**
     * @see #Explosion(float, boolean, float, SoundEvent, ParticleType)
     */
    public Explosion(float size, boolean spawnFire) {
        this(size, spawnFire, 1 / size);
    }

    /**
     * @see #Explosion(float, boolean, float, SoundEvent, ParticleType)
     */
    public Explosion(float size, boolean spawnFire, float itemDropChance) {
        this(size, spawnFire, itemDropChance, SoundEvent.EXPLODE);
    }

    /**
     * @see #Explosion(float, boolean, float, SoundEvent, ParticleType)
     */
    public Explosion(float size, boolean spawnFire, float itemDropChance, SoundEvent sound) {
        this(size, spawnFire, itemDropChance, sound, ParticleType.HUGE_EXPLOSION);
    }

    /**
     * Create an explosion.
     *
     * @param size           the size of the explosion.
     * @param spawnFire      whether fire should be spawned.
     * @param itemDropChance the item drop chance.
     * @param sound          the sound of the explosion.
     * @param particle       the particle of the explosion.
     */
    public Explosion(float size, boolean spawnFire, float itemDropChance, SoundEvent sound, ParticleType particle) {
        this.size = size;
        this.spawnFire = spawnFire;
        this.itemDropChance = itemDropChance;
        this.sound = sound;
        this.particle = particle;
    }

    /**
     * @see #explode(Dimension, float, float, float)
     */
    public void explode(Dimension dimension, Vector3ic pos) {
        this.explode(dimension, pos.x(), pos.y(), pos.z());
    }

    /**
     * @see #explode(Dimension, float, float, float)
     */
    public void explode(Dimension dimension, Vector3fc pos) {
        this.explode(dimension, pos.x(), pos.y(), pos.z());
    }

    /**
     * Perform the explosion at a pos in a dimension.
     *
     * @param dimension the dimension which the explosion will be performed in.
     * @param x         the x coordinate of the explosion.
     * @param y         the y coordinate of the explosion.
     * @param z         the z coordinate of the explosion.
     */
    public void explode(Dimension dimension, float x, float y, float z) {
        // TODO: logic for ExplodableEntity
        var rand = ThreadLocalRandom.current();

        var affectedBlocks = new ArrayList<Vector3ic>();
        for (var ray : RAYS) {
            var lx = x;
            var ly = y;
            var lz = z;
            for (var blastForce = size * (0.7f + rand.nextFloat() * 0.6f); blastForce > 0; blastForce -= 0.225f) {
                var current = new Vector3i((int) Math.floor(lx), (int) Math.floor(ly), (int) Math.floor(lz));
                var currentBlock = dimension.getBlockState(current);
                var resistance = currentBlock.getBlockStateData().explosionResistance();
                lx += ray.x();
                ly += ray.y();
                lz += ray.z();

                var delta = (resistance / 5.0f + 0.3f) * 0.3f;
                // resistance may be very big if the block is an unbreakable block such as
                // bedrock, so we should operate it carefully to avoid precision overflow
                if (blastForce < delta) {
                    // In this case, blastForce - delta will result in a negative value
                    blastForce = 0;
                } else {
                    blastForce -= delta;
                    affectedBlocks.add(current);
                }
            }
        }

        for (var pos : affectedBlocks) {
            var block = dimension.getBlockState(pos);
            // TODO: logic for Explodable block
            if (block.getBlockType() == BlockTypes.AIR) {
                continue;
            }

            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
            if (itemDropChance > rand.nextFloat()) {
                var centerPos = MathUtils.center(pos);
                for (var item : block.getBehavior().getDrops(new BlockStateWithPos(block, new Position3i(pos, dimension)), null, null)) {
                    dimension.dropItem(item, centerPos);
                }
            }
        }

        if (spawnFire) {
            for (var pos : affectedBlocks) {
                if (rand.nextInt(3) != 0) {
                    continue;
                }

                var block = dimension.getBlockState(pos);
                if (block.getBlockType() == BlockTypes.AIR &&
                    dimension.getBlockState(pos.x(), pos.y() - 1, pos.z()).getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
                    dimension.setBlockState(pos, BlockTypes.FIRE.getDefaultState());
                }
            }
        }

        dimension.addParticle(x, y, z, particle);
        dimension.addLevelSoundEvent(x, y, z, sound);
    }
}
