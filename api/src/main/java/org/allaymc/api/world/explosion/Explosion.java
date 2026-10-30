package org.allaymc.api.world.explosion;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.api.world.sound.Sound;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

/**
 * {@code Explosion} configures blast effects and applies them to a dimension
 * at a caller-selected position. Entity impact combines distance with sampled
 * block exposure, and block destruction follows randomized rays whose force
 * decreases through resistant terrain. The mutable settings are reused by each
 * invocation, so callers must avoid changing them while an explosion is
 * running.
 *
 * <p>
 * Entity exposure is computed on the server compute pool and joined before
 * entity effects are applied. Block rays also run in parallel, but block drops
 * and replacements occur afterward on the invoking thread. Drops are computed
 * before replacement because block entities can affect their contents.
 * Failures can leave earlier damage, motion, or block changes applied; this
 * operation provides no rollback.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class Explosion {

    /**
     * {@code RAYS} retains the shared blast directions with a step length of
     * {@code 0.3} blocks. Subclasses must not mutate its entries while
     * explosions read them.
     */
    protected static final Vector3dc[] RAYS = new Vector3dc[1352];

    // The cube surface contains 1352 sample positions, matching RAYS capacity.
    // Sampling the full cube would overrun the fixed direction table before
    // explosion rays could be initialized.
    static {
        int index = 0;
        for (double x = 0; x < 16; x++) {
            for (double y = 0; y < 16; y++) {
                for (double z = 0; z < 16; z++) {
                    if (x != 0 && x != 15 && y != 0 && y != 15 && z != 0 && z != 15) {
                        continue;
                    }
                    // A 0.3-block step matches resistance attenuation during ray tracing.
                    RAYS[index++] = new Vector3d(x / 15.0 * 2 - 1, y / 15.0 * 2 - 1, z / 15.0 * 2 - 1).normalize(0.3);
                }
            }
        }
    }

    /**
     * {@code size} controls blast force and half the entity search distance in
     * blocks. Constructors retain it without rejecting zero, negative, or
     * nonfinite values.
     */
    protected float size;
    /**
     * {@code spawnFire} enables a one-in-three fire attempt for each collected
     * block position. Fire requires air above a block with a full upper
     * collision face.
     */
    protected boolean spawnFire;
    /**
     * {@code itemDropChance} is compared directly with a random value in
     * {@code [0, 1)} for each nonair, non-TNT block. Negative values prevent
     * drops; values at least one always pass.
     */
    protected float itemDropChance;
    /**
     * Sound descriptor emitted after blast mutations.
     */
    protected Sound sound;
    /**
     * Particle descriptor emitted after blast mutations.
     */
    protected Particle particle;
    /**
     * {@code entity} identifies the blast source and is excluded from affected
     * entities. {@code null} selects block-explosion damage attribution
     * instead of entity-explosion attribution.
     */
    protected Entity entity;
    /**
     * {@code destroyBlocks} controls collection and destruction of blast-hit
     * blocks. Disabling it also leaves the fire-placement pass without any
     * collected positions.
     */
    protected boolean destroyBlocks;
    /**
     * {@code affectEntities} controls exposure calculation, motion, and
     * damage. Disabling it skips entity processing while leaving configured
     * block and visual effects available.
     */
    protected boolean affectEntities;
    /**
     * {@code sourceBlockType} supplies block-explosion damage attribution when
     * {@code entity} is {@code null}. Entity-sourced blasts ignore this value
     * when constructing their damage container.
     */
    protected BlockType<?> sourceBlockType;

    /**
     * Creates a size-four explosion with fire disabled and a one-quarter drop
     * chance. The default sound and particle are the ordinary explosion sound
     * and huge-explosion particle, with entity and block effects enabled.
     */
    public Explosion() {
        this(4);
    }

    /**
     * Creates an explosion of the supplied size with fire disabled. The
     * default drop chance is {@code 1 / size}, and the size is retained
     * without range or finiteness checks.
     */
    public Explosion(float size) {
        this(size, false);
    }

    /**
     * Creates an explosion with the supplied size and fire policy. The default
     * drop chance is {@code 1 / size}; division by zero and negative sizes are
     * not rejected during construction.
     */
    public Explosion(float size, boolean spawnFire) {
        this(size, spawnFire, 1.0f / size);
    }

    /**
     * Creates an explosion with explicit force, fire, and drop settings. This
     * overload selects {@code SimpleSound.EXPLOSION} and the huge-explosion
     * particle, while retaining numeric settings without clamping or
     * validation.
     */
    public Explosion(float size, boolean spawnFire, float itemDropChance) {
        this(size, spawnFire, itemDropChance, SimpleSound.EXPLOSION);
    }

    /**
     * Creates an explosion with an explicit sound and the huge-explosion
     * particle. All supplied settings are retained without validation, and
     * entity processing and block destruction both start enabled.
     */
    public Explosion(float size, boolean spawnFire, float itemDropChance, Sound sound) {
        this(size, spawnFire, itemDropChance, sound, SimpleParticle.HUGE_EXPLOSION);
    }

    /**
     * Creates an explosion retaining the supplied numeric and effect settings.
     * The source entity starts as {@code null}, block destruction and entity
     * processing start enabled, and numeric inputs are not validated or
     * clamped.
     */
    public Explosion(float size, boolean spawnFire, float itemDropChance, Sound sound, Particle particle) {
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
     * Returns {@code b + a * (t - b)}, using {@code a} as the interpolation
     * fraction. The helper does not clamp that fraction, so values outside
     * zero through one extrapolate between the supplied endpoints.
     */
    protected static double lerp(double a, double b, double t) {
        return b + a * (t - b);
    }

    /**
     * Visits block cells along the segment from {@code start} through {@code
     * end}, stopping when the predicate rejects a cell. Coincident endpoints
     * throw {@link IllegalArgumentException}, and the predicate receives one
     * reused mutable coordinate that it must copy before retaining.
     *
     * <p>
     * The traversal advances the axis whose next boundary is nearest and
     * checks the segment length before crossing that boundary. Equal boundary
     * times follow the fixed branch order rather than visiting every touching
     * cell simultaneously. Stationary axes use infinite boundary distances so
     * they never win the next-step selection.
     */
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

    /**
     * Returns a new vector containing componentwise guarded division results.
     * Each zero divisor produces zero for its axis, matching the scalar helper
     * used for stationary-axis traversal distances.
     */
    protected static Vector3d safeDivide(Vector3dc dividend, Vector3dc divisor) {
        return new Vector3d(
                safeDivide(dividend.x(), divisor.x()),
                safeDivide(dividend.y(), divisor.y()),
                safeDivide(dividend.z(), divisor.z())
        );
    }

    /**
     * Returns the quotient, or zero when the divisor is zero. Traversal uses
     * this value for stationary-axis increments because their infinite
     * boundary distance already prevents those axes from being selected.
     */
    protected static double safeDivide(double dividend, double divisor) {
        // A stationary axis already has an infinite boundary distance, so its
        // increment remains zero without participating in next-boundary selection.
        if (divisor == 0.0) {
            return 0.0;
        }

        return dividend / divisor;
    }

    /**
     * Returns a new vector of distances to the next block boundary on each
     * axis. Zero direction components yield positive infinity, excluding
     * stationary axes from the traversal's next-boundary choice.
     */
    protected static Vector3d boundary(Vector3dc v1, Vector3dc v2) {
        return new Vector3d(
                boundary(v1.x(), v2.x()),
                boundary(v1.y(), v2.y()),
                boundary(v1.z(), v2.z())
        );
    }

    /**
     * Returns the distance to the next block boundary along an axis. A zero
     * direction yields positive infinity, while negative motion starting on a
     * boundary yields zero so traversal immediately enters the preceding cell.
     */
    protected static double boundary(double start, double dir) {
        // A stationary axis must never beat a moving axis when traversal chooses
        // its nearest boundary; infinity preserves that ordering without division.
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

    /**
     * Returns a new vector holding the sign of each component.
     */
    protected static Vector3dc sign(Vector3dc vec) {
        return new Vector3d(sign(vec.x()), sign(vec.y()), sign(vec.z()));
    }

    /**
     * Returns negative one, zero, or one for the supplied component.
     */
    protected static double sign(double f) {
        if (f > 0.0) {
            return 1.0;
        } else if (f < 0.0) {
            return -1.0;
        } else {
            return 0.0;
        }
    }

    /**
     * Applies the configured explosion at the supplied integer dimension
     * position. This overload reads the vector immediately and delegates all
     * damage, block mutation, effect emission, and failure behavior to the
     * coordinate overload.
     */
    public void explode(Dimension dimension, Vector3ic pos) {
        this.explode(dimension, pos.x(), pos.y(), pos.z());
    }

    /**
     * Applies the configured explosion at the supplied floating-point
     * dimension position. This overload reads the vector immediately and
     * delegates all damage, block mutation, effect emission, and failure
     * behavior to the coordinate overload.
     */
    public void explode(Dimension dimension, Vector3dc pos) {
        this.explode(dimension, pos.x(), pos.y(), pos.z());
    }

    /**
     * Applies entity impact, optional block destruction, fire attempts, and
     * final effects at dimension coordinates. The caller must permit world
     * reads from worker threads and world mutations on the invoking thread,
     * and must keep this explosion's mutable settings stable throughout the
     * call.
     *
     * <p>
     * Entity exposure tasks finish before motion and damage are applied, while
     * randomized block rays finish before drops or replacements begin.
     * Multiple rays may collect the same position, and later passes re-read
     * its current state. Drop calculation precedes air replacement so
     * block-entity-dependent drops remain available. Exceptions propagate
     * without undoing earlier effects, and optional fire is limited to
     * collected positions after block destruction.
     */
    public void explode(Dimension dimension, double x, double y, double z) {
        var explosionPos = new Vector3d(x, y, z);
        var rand = ThreadLocalRandom.current();
        // Entity impact reaches twice the size used for block-ray force.
        var d = size * 2;
        var aabb = new AABBd(
                Math.floor(x - d - 1), Math.floor(y - d - 1), Math.floor(z - d - 1),
                Math.ceil(x + d + 1), Math.ceil(y + d + 1), Math.ceil(z + d + 1)
        ).expand(2);

        if (affectEntities) {
            var affectedEntities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb);
            // Source exclusion prevents a projectile or explosive entity from
            // receiving its own impact through the ordinary affected-entity pass.
            affectedEntities.remove(entity);
            var impactMap = new ConcurrentHashMap<Entity, Double>();
            // Join every exposure task before applying entity effects so the
            // invoking thread consumes completed impact values rather than a
            // partially populated result map.
            Utils.forEachInParallel(affectedEntities, Server.getInstance().getComputeThreadPool(), affectedEntity -> {
                var pos = affectedEntity.getLocation();
                var dist = pos.sub(x, y, z, new Vector3d()).length();
                // The center has no usable radial direction; exclude it before
                // distance normalization rather than creating an undefined motion.
                if (dist > d || dist == 0) {
                    return;
                }

                // Exposure samples collision geometry independently for this entity;
                // the shared map publishes its result to the post-join effect pass.
                impactMap.put(affectedEntity, (1.0 - dist / d) * exposure(dimension, explosionPos, affectedEntity.getOffsetAABB()));
            }).join();

            for (var affectedEntity : affectedEntities) {
                var impact = impactMap.get(affectedEntity);
                if (impact == null || impact == 0) {
                    continue;
                }

                if (affectedEntity instanceof EntityPhysicsComponent physicsComponent) {
                    var kbResistance = physicsComponent.getKnockbackResistance();
                    var direction = affectedEntity.getLocation().sub(explosionPos, new Vector3d());
                    if (direction.lengthSquared() > 0) {
                        physicsComponent.addMotion(direction.normalize().mul(impact * (1.0 - kbResistance)));
                    }
                }
                if (affectedEntity instanceof EntityLiving living) {
                    var m = switch (dimension.getWorld().getWorldData().getDifficulty()) {
                        case PEACEFUL -> 0.0;
                        case EASY -> 3.5f;
                        case NORMAL -> 7.0;
                        case HARD -> 10.5f;
                    };
                    var damage = (impact * impact + impact) * m * size / 2.0 + 1.0;
                    if (entity == null) {
                        living.attack(DamageContainer.blockExplosion(sourceBlockType, (float) damage));
                    } else {
                        living.attack(DamageContainer.entityExplosion(entity, (float) damage));
                    }
                }
            }
        }

        List<Vector3ic> affectedBlocks;
        if (destroyBlocks) {
            // Independent rays collect candidate positions before any block is
            // removed, keeping each ray's resistance reads ahead of destruction.
            affectedBlocks = Arrays.stream(RAYS)
                    .parallel()
                    .flatMap(ray -> {
                        var localList = new ArrayList<Vector3ic>();
                        var lx = x;
                        var ly = y;
                        var lz = z;
                        // Each parallel ray uses its worker's random source so
                        // blast-force sampling does not share a mutable generator.
                        var localRand = ThreadLocalRandom.current();
                        // Randomized initial force and per-step loss bound ray travel
                        // while material resistance supplies additional attenuation.
                        for (var blastForce = size * (0.7f + localRand.nextDouble() * 0.6f); blastForce > 0; blastForce -= 0.225f) {
                            var current = new Vector3i((int) Math.floor(lx), (int) Math.floor(ly), (int) Math.floor(lz));
                            var resistance = 0.0;
                            // Layer-one water must contribute resistance even when
                            // the primary layer is a different block, so inspect that
                            // layer before using primary-block resistance.
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
                            // Resistance that consumes the remaining force stops this
                            // ray before the block enters its destruction candidates;
                            // later rays can still test the same position.
                            if (blastForce < delta) {
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
                // TNT receives a shortened randomized fuse instead of normal drops
                // and air replacement, preserving chain-explosion behavior for it.
                BlockTypes.TNT.getBlockBehavior().prime(new Position3i(pos, dimension), 10 + rand.nextInt(30));
                continue;
            }

            if (itemDropChance > rand.nextDouble()) {
                var centerPos = MathUtils.center(pos);
                for (var item : block.getBehavior().getDrops(new Block(block, new Position3i(pos, dimension)), null, null)) {
                    dimension.dropItem(item, centerPos);
                }
            }

            // Keep the block and its block entity available until getDrops finishes;
            // replacing it first can remove inventory or other drop information.
            // Air replacement follows any successful item-drop calculation.
            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
        }

        if (spawnFire) {
            for (var pos : affectedBlocks) {
                // One third of collected positions reach the support check for fire.
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
        dimension.addSound(x, y, z, sound);
    }

    /**
     * Estimates the fraction of sampled entity-box points whose ray encounters
     * no block collision shape. The estimate affects blast impact rather than
     * selecting entities, and the dimension must support reads from the
     * compute workers used by {@code explode}.
     *
     * <p>
     * Sample spacing depends on box dimensions, with horizontal offsets
     * centering the sampled grid. A negative spacing component returns zero
     * instead of traversing an inverted box. Every sample stops at its first
     * colliding block; coincident sample and origin positions can propagate
     * the traversal helper's zero-direction exception. The result is the
     * unobstructed sample count divided by all checks.
     */
    protected double exposure(Dimension dimension, Vector3dc origin, AABBdc box) {
        Vector3dc boxMin = new Vector3d(box.minX(), box.minY(), box.minZ());
        Vector3dc boxMax = new Vector3d(box.maxX(), box.maxY(), box.maxZ());
        var diff = boxMax.sub(boxMin, new Vector3d()).mul(2.0).add(1, 1, 1);

        var step = new Vector3d(1.0 / diff.x(), 1.0 / diff.y(), 1.0 / diff.z());
        // An inverted box cannot supply a forward sample grid; return no
        // exposure rather than entering loops with negative progress.
        if (step.x() < 0.0 || step.y() < 0.0 || step.z() < 0.0) {
            return 0.0;
        }

        // Center the horizontal sample grid within the entity box.
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

                        // A block without a ray intersection leaves this sample
                        // unobstructed so later cells can still hide the target.
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
}
