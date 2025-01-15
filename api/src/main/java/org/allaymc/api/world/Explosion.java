package org.allaymc.api.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBf;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

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
     * The entity that caused the explosion. If keep this field to {@code null},
     * the explosion will be considered as a block explosion.
     * <p>
     * For example if the explosion is caused by a creeper, then this field will be the creeper.
     */
    protected Entity entity;

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
        var explosionPos = new Vector3f(x, y, z);
        var rand = ThreadLocalRandom.current();
        var d = size * 2;
        var aabb = new AABBf(
                (float) Math.floor(x - d - 1) - 2, (float) Math.floor(y - d - 1) - 2, (float) Math.floor(z - d - 1) - 2,
                (float) Math.ceil(x + d + 1) + 2, (float) Math.ceil(y + d + 1) + 2, (float) Math.ceil(z + d + 1) + 2
        );

        var affectedEntities = dimension.getEntityPhysicsService().computeCollidingEntities(aabb);
        for (var affectedEntity : affectedEntities) {
            var pos = affectedEntity.getLocation();
            var dist = pos.sub(x, y, z, new Vector3f()).length();
            if (dist > d || dist == 0) {
                continue;
            }

            var impact = (1 - dist / d) * exposure(dimension, explosionPos, affectedEntity);
            var diff = pos.sub(explosionPos, new Vector3f());
            affectedEntity.knockback(explosionPos, impact, false, diff.y / diff.length() * impact);
            if (affectedEntity instanceof EntityDamageComponent damageComponent) {
                var damage = (float) Math.floor((impact * impact + impact) * 3.5 * size * 2 + 1);
                if (entity == null) {
                    damageComponent.attack(DamageContainer.blockExplosion(damage));
                } else {
                    damageComponent.attack(DamageContainer.entityExplosion(entity, damage));
                }
            }
        }

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

    /**
     * Calculate the exposure of an explosion to an entity, used to calculate the impact of an explosion.
     */
    protected float exposure(Dimension dimension, Vector3fc origin, Entity entity) {
        var box = entity.getOffsetAABB();

        Vector3fc boxMin = new Vector3f(box.minX(), box.minY(), box.minZ());
        Vector3fc boxMax = new Vector3f(box.maxX(), box.maxY(), box.maxZ());
        var diff = boxMax.sub(boxMin, new Vector3f()).mul(2.0f).add(1, 1, 1);

        var step = new Vector3f(1.0f / diff.x(), 1.0f / diff.y(), 1.0f / diff.z());
        if (step.x() < 0.0f || step.y() < 0.0f || step.z() < 0.0f) {
            return 0.0f;
        }

        float xOffset = (1.0f - (float) Math.floor(diff.x()) / diff.x()) / 2.0f;
        float zOffset = (1.0f - (float) Math.floor(diff.z()) / diff.z()) / 2.0f;

        int checks = 0;
        int misses = 0;
        for (var x = 0.0f; x <= 1.0f; x += step.x()) {
            for (var y = 0.0f; y <= 1.0f; y += step.y()) {
                for (var z = 0.0f; z <= 1.0f; z += step.z()) {
                    var point = new Vector3f(
                            lerp(x, boxMin.x(), boxMax.x()) + xOffset,
                            lerp(y, boxMin.y(), boxMax.y()),
                            lerp(z, boxMin.z(), boxMax.z()) + zOffset
                    );

                    final boolean[] collided = new boolean[1];
                    traverseBlocks(origin, point, pos -> {
                        var block = dimension.getBlockState(pos);
                        if (block.getBlockType() != BlockTypes.AIR) {
                            collided[0] = true;
                            return false;
                        }

                        // Keep traversing if is air
                        return true;
                    });

                    if (!collided[0]) {
                        misses++;
                    }
                    checks++;
                }
            }
        }

        return ((float) misses) / ((float) checks);
    }

    /**
     * Return the linear interpolation between a and b at t.
     */
    protected static float lerp(float a, float b, float t) {
        return b + a * (t - b);
    }

    protected static void traverseBlocks(Vector3fc start, Vector3fc end, Predicate<Vector3ic> predicate) {
        var dir = end.sub(start, new Vector3f());
        if (Float.compare(dir.lengthSquared(), 0.0f) == 0) {
            throw new IllegalArgumentException("Start and end points are the same, giving a zero direction vector");
        }

        dir = dir.normalize();

        var b = new Vector3i((int) Math.floor(start.x()), (int) Math.floor(start.y()), (int) Math.floor(start.z()));

        var step = sign(dir);
        var stepX = (int) step.x();
        var stepY = (int) step.y();
        var stepZ = (int) step.z();
        Vector3f max = boundary(start, dir);

        Vector3f delta = safeDivide(step, dir);

        var r = start.sub(end, new Vector3f()).length();
        while (true) {
            if (!predicate.test(b)) {
                return;
            }

            if (max.x < max.y && max.x < max.z) {
                if (max.x > r) {
                    return;
                }

                b.x += stepX;
                max.x += delta.x;
            } else if (max.y < max.z) {
                if (max.y > r) {
                    return;
                }

                b.y += stepY;
                max.y += delta.y;
            } else {
                if (max.z > r) {
                    return;
                }

                b.z += stepZ;
                max.z += delta.z;
            }
        }
    }

    protected static Vector3f safeDivide(Vector3fc dividend, Vector3fc divisor) {
        return new Vector3f(
                safeDivide(dividend.x(), divisor.x()),
                safeDivide(dividend.y(), divisor.y()),
                safeDivide(dividend.z(), divisor.z())
        );
    }

    protected static float safeDivide(float dividend, float divisor) {
        if (divisor == 0.0f) {
            return 0.0f;
        }

        return dividend / divisor;
    }

    protected static Vector3f boundary(Vector3fc v1, Vector3fc v2) {
        return new Vector3f(
                boundary(v1.x(), v2.x()),
                boundary(v1.y(), v2.y()),
                boundary(v1.z(), v2.z())
        );
    }

    /**
     * Return the distance that must be travelled on an axis from the start point with the direction vector
     * component to cross a block boundary.
     */
    protected static float boundary(float start, float dir) {
        if (dir == 0.0f) {
            return Float.POSITIVE_INFINITY;
        }

        if (dir < 0.0f) {
            start = -start;
            dir = -dir;
            if (Math.floor(start) == start) {
                return 0.0f;
            }
        }

        return (1 - (start - (float) Math.floor(start))) / dir;
    }

    protected static Vector3fc sign(Vector3fc vec) {
        return new Vector3f(sign(vec.x()), sign(vec.y()), sign(vec.z()));
    }

    protected static float sign(float f) {
        if (f > 0.0f) {
            return 1.0f;
        } else if (f < 0.0f) {
            return -1.0f;
        } else {
            return 0.0f;
        }
    }
}
