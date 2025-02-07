package org.allaymc.api.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    protected static final Vector3dc[] RAYS = new Vector3dc[1352];

    static {
        int index = 0;
        for (double x = 0; x < 16; x++) {
            for (double y = 0; y < 16; y++) {
                for (double z = 0; z < 16; z++) {
                    if (x != 0 && x != 15 && y != 0 && y != 15 && z != 0 && z != 15) {
                        continue;
                    }
                    RAYS[index++] = new Vector3d(x / 15.0 * 2 - 1, y / 15.0 * 2 - 1, z / 15.0 * 2 - 1).normalize(0.3);
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
     * Whether this explosion will destroy blocks. Tnt that are underwater won't destroy blocks.
     */
    protected boolean destroyBlocks;
    /**
     * Whether this explosion will affect entities. This including damage and add motion to entities.
     */
    protected boolean affectEntities;

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
        this(size, spawnFire, 1.0f / size);
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
        this.entity = null;
        this.destroyBlocks = true;
        this.affectEntities = true;
    }

    /**
     * @see #explode(Dimension, double, double, double)
     */
    public void explode(Dimension dimension, Vector3ic pos) {
        this.explode(dimension, pos.x(), pos.y(), pos.z());
    }

    /**
     * @see #explode(Dimension, double, double, double)
     */
    public void explode(Dimension dimension, Vector3dc pos) {
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
    public void explode(Dimension dimension, double x, double y, double z) {
        var explosionPos = new Vector3d(x, y, z);
        var rand = ThreadLocalRandom.current();
        var d = size * 2;
        var aabb = new AABBd(
                (double) Math.floor(x - d - 1), (double) Math.floor(y - d - 1), (double) Math.floor(z - d - 1),
                (double) Math.ceil(x + d + 1), (double) Math.ceil(y + d + 1), (double) Math.ceil(z + d + 1)
        );
        MathUtils.grow(aabb, 2);

        if (affectEntities) {
            var affectedEntities = dimension.getEntityPhysicsService().computeCollidingEntities(aabb);
            // Skip the entity that caused the explosion
            affectedEntities.remove(entity);
            var impactMap = affectedEntities.parallelStream()
                    .collect(Collectors.<Entity, Entity, Double>toConcurrentMap(
                            affectedEntity -> affectedEntity,
                            affectedEntity -> {
                                var pos = affectedEntity.getLocation();
                                var dist = pos.sub(x, y, z, new Vector3d()).length();
                                if (dist > d || dist == 0) {
                                    return 0.0;
                                }

                                // Method exposure() is slow, let's calculate it in parallel
                                return (1.0 - dist / d) * exposure(dimension, explosionPos, affectedEntity.getOffsetAABB());
                            }
                    ));
            for (var affectedEntity : affectedEntities) {
                var impact = impactMap.get(affectedEntity);
                if (impact == 0.0) {
                    continue;
                }

                var kbResistance = 0.0;
                if (affectedEntity instanceof EntityAttributeComponent attributeComponent && attributeComponent.supportAttribute(AttributeType.KNOCKBACK_RESISTANCE)) {
                    kbResistance = attributeComponent.getAttributeValue(AttributeType.KNOCKBACK_RESISTANCE);
                }
                var direction = affectedEntity.getLocation().sub(explosionPos, new Vector3d());
                if (direction.lengthSquared() > 0) {
                    affectedEntity.addMotion(direction.normalize().mul(impact * (1.0 - kbResistance)));
                }
                if (affectedEntity instanceof EntityDamageComponent damageComponent) {
                    var m = switch (dimension.getWorld().getWorldData().getDifficulty()) {
                        case PEACEFUL -> 0.0;
                        case EASY -> 3.5f;
                        case NORMAL -> 7.0;
                        case HARD -> 10.5f;
                    };
                    var damage = (impact * impact + impact) * m * size / 2.0 + 1.0;
                    if (entity == null) {
                        damageComponent.attack(DamageContainer.blockExplosion((float) damage));
                    } else {
                        damageComponent.attack(DamageContainer.entityExplosion(entity, (float) damage));
                    }
                }
            }
        }

        List<Vector3ic> affectedBlocks;
        if (destroyBlocks) {
            affectedBlocks = Arrays.stream(RAYS)
                    .parallel()
                    .flatMap(ray -> {
                        var localList = new ArrayList<Vector3ic>();
                        var lx = x;
                        var ly = y;
                        var lz = z;
                        // Get the random instance for each thread
                        var localRand = ThreadLocalRandom.current();
                        for (var blastForce = size * (0.7f + localRand.nextDouble() * 0.6f); blastForce > 0; blastForce -= 0.225f) {
                            var current = new Vector3i((int) Math.floor(lx), (int) Math.floor(ly), (int) Math.floor(lz));
                            var resistance = 0.0;
                            // Do not use getLiquid(), which is much slower. Just get the block in layer 1 and
                            // check if it is water by comparing it with BlockTypes.WATER and BlockTypes.FLOWING_WATER
                            var layer1 = dimension.getBlockState(current, 1).getBlockType();
                            if (layer1 == BlockTypes.WATER || layer1 == BlockTypes.FLOWING_WATER) {
                                resistance = 100;
                            } else {
                                resistance = dimension.getBlockState(current).getBlockStateData().explosionResistance();
                            }
                            lx += ray.x();
                            ly += ray.y();
                            lz += ray.z();

                            var delta = (resistance / 5.0 + 0.3f) * 0.3f;
                            // resistance may be very big if the block is an unbreakable block such as
                            // bedrock, so we should operate it carefully to avoid precision overflow
                            if (blastForce < delta) {
                                // In this case, blastForce - delta will result in a negative value
                                blastForce = 0;
                            } else {
                                blastForce -= delta;
                                localList.add(current);
                            }
                        }

                        return localList.stream();
                    })
                    .toList();
        } else {
            affectedBlocks = Collections.emptyList();
        }

        for (var pos : affectedBlocks) {
            var block = dimension.getBlockState(pos);
            var blockType = block.getBlockType();
            if (blockType == BlockTypes.AIR) {
                continue;
            }
            if (blockType == BlockTypes.TNT) {
                // Explosion can prime tnt around
                BlockTypes.TNT.getBlockBehavior().prime(
                        new BlockStateWithPos(block, new Position3i(pos, dimension)),
                        10 + rand.nextInt(30)
                );
                continue;
            }

            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
            if (itemDropChance > rand.nextDouble()) {
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
    protected double exposure(Dimension dimension, Vector3dc origin, AABBdc box) {
        Vector3dc boxMin = new Vector3d(box.minX(), box.minY(), box.minZ());
        Vector3dc boxMax = new Vector3d(box.maxX(), box.maxY(), box.maxZ());
        var diff = boxMax.sub(boxMin, new Vector3d()).mul(2.0).add(1, 1, 1);

        var step = new Vector3d(1.0 / diff.x(), 1.0 / diff.y(), 1.0 / diff.z());
        if (step.x() < 0.0 || step.y() < 0.0 || step.z() < 0.0) {
            return 0.0;
        }

        double xOffset = (1.0 - Math.floor(diff.x()) / diff.x()) / 2.0;
        double zOffset = (1.0 - Math.floor(diff.z()) / diff.z()) / 2.0;

        double checks = 0;
        double misses = 0;
        for (var x = 0.0; x <= 1.0; x += step.x()) {
            for (var y = 0.0; y <= 1.0; y += step.y()) {
                for (var z = 0.0; z <= 1.0; z += step.z()) {
                    var point = new Vector3d(
                            lerp(x, boxMin.x(), boxMax.x()) + xOffset,
                            lerp(y, boxMin.y(), boxMax.y()),
                            lerp(z, boxMin.z(), boxMax.z()) + zOffset
                    );

                    final boolean[] collided = new boolean[1];
                    traverseBlocks(origin, point, pos -> {
                        var block = dimension.getBlockState(pos);
                        if (block.getBlockStateData().computeOffsetCollisionShape(pos).intersectsRay(origin, point.sub(origin, new Vector3d()))) {
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

        return misses / checks;
    }

    /**
     * Return the linear interpolation between a and b at t.
     */
    protected static double lerp(double a, double b, double t) {
        return b + a * (t - b);
    }

    protected static void traverseBlocks(Vector3dc start, Vector3dc end, Predicate<Vector3ic> predicate) {
        var dir = end.sub(start, new Vector3d());
        if (Double.compare(dir.lengthSquared(), 0.0) == 0) {
            throw new IllegalArgumentException("Start and end points are the same, giving a zero direction vector");
        }

        dir = dir.normalize();

        var b = new Vector3i((int) Math.floor(start.x()), (int) Math.floor(start.y()), (int) Math.floor(start.z()));

        var step = sign(dir);
        var stepX = (int) step.x();
        var stepY = (int) step.y();
        var stepZ = (int) step.z();
        Vector3d max = boundary(start, dir);

        Vector3d delta = safeDivide(step, dir);

        var r = start.sub(end, new Vector3d()).length();
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

    protected static Vector3d safeDivide(Vector3dc dividend, Vector3dc divisor) {
        return new Vector3d(
                safeDivide(dividend.x(), divisor.x()),
                safeDivide(dividend.y(), divisor.y()),
                safeDivide(dividend.z(), divisor.z())
        );
    }

    protected static double safeDivide(double dividend, double divisor) {
        if (divisor == 0.0) {
            return 0.0;
        }

        return dividend / divisor;
    }

    protected static Vector3d boundary(Vector3dc v1, Vector3dc v2) {
        return new Vector3d(
                boundary(v1.x(), v2.x()),
                boundary(v1.y(), v2.y()),
                boundary(v1.z(), v2.z())
        );
    }

    /**
     * Return the distance that must be travelled on an axis from the start point with the direction vector
     * component to cross a block boundary.
     */
    protected static double boundary(double start, double dir) {
        if (dir == 0.0) {
            return Double.POSITIVE_INFINITY;
        }

        if (dir < 0.0) {
            start = -start;
            dir = -dir;
            if (Math.floor(start) == start) {
                return 0.0;
            }
        }

        return (1 - (start - Math.floor(start))) / dir;
    }

    protected static Vector3dc sign(Vector3dc vec) {
        return new Vector3d(sign(vec.x()), sign(vec.y()), sign(vec.z()));
    }

    protected static double sign(double f) {
        if (f > 0.0) {
            return 1.0;
        } else if (f < 0.0) {
            return -1.0;
        } else {
            return 0.0;
        }
    }
}
