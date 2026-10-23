package org.allaymc.server.entity.ai.behaviorgroup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.allaymc.api.entity.ai.behavior.Behavior;
import org.allaymc.api.entity.ai.behavior.BehaviorState;
import org.allaymc.api.entity.ai.behaviorgroup.BehaviorGroup;
import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.server.Server;
import org.allaymc.server.entity.ai.memory.MemoryStorageImpl;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.*;

/**
 * {@code BehaviorGroupImpl} coordinates sensing, behavior activation,
 * execution, routing, and controllers for one intelligent entity. Core
 * behaviors start independently; ordinary behaviors run together at the highest
 * active priority and yield only to strictly higher eligible priorities. Period
 * counters advance only for behaviors that are not running, so an active
 * behavior stops through execution rather than repeated eligibility checks.
 *
 * <p>
 * AI processing queues sequential world work in {@code syncedActions}, which
 * the entity component drains during its sequential tick. Route searches run in
 * the server's virtual thread pool, while AI ticks consume the published route
 * and advance movement segments before controllers run. {@code routeFinding}
 * limits outstanding submissions, but search results are accepted without
 * checking whether the target changed. Callers must respect the entity tick
 * phases; the route fields and action queue do not provide general concurrent
 * access protection.
 *
 * @author daoge_cmd
 */
@Builder
public class BehaviorGroupImpl implements BehaviorGroup {

    /**
     * {@code ROUTE_UPDATE_CYCLE} sets the active route refresh interval in AI
     * ticks so existing paths can react to changed blocks.
     */
    protected static final int ROUTE_UPDATE_CYCLE = 20;
    /**
     * {@code INACTIVE_ROUTE_MULTIPLIER} lengthens the refresh interval for
     * inactive entities, reducing how often their existing paths request
     * another search.
     */
    protected static final int INACTIVE_ROUTE_MULTIPLIER = 4;

    /**
     * Core behavior configuration set.
     */
    @Singular
    @Getter
    protected final Set<Behavior> coreBehaviors;

    /**
     * Ordinary behavior configuration set.
     */
    @Singular
    @Getter
    protected final Set<Behavior> behaviors;

    /**
     * Observation sensor configuration set.
     */
    @Singular
    @Getter
    protected final Set<Sensor> sensors;

    /**
     * Movement controller configuration set.
     */
    @Singular
    @Getter
    protected final Set<Controller> controllers;

    /**
     * Configured asynchronous route searcher.
     */
    @Getter
    protected final RouteFinder routeFinder;

    /**
     * {@code routeUpdateRequired} requests the next available search.
     * Submission clears the flag; requests raised while a search runs remain
     * pending for a later tick.
     */
    @Getter
    @Setter
    protected boolean routeUpdateRequired;
    /**
     * {@code routeFinding} marks an outstanding search and is cleared in its
     * {@code finally} block. Other route state is not guarded by an equivalent
     * synchronization protocol.
     */
    protected volatile boolean routeFinding;

    // route holds published search nodes, nodeIndex identifies the next node, and
    // routeUpdateTick counts ticks with remaining nodes. Search completion replaces
    // route and resets nodeIndex while AI ticks consume those fields.
    /**
     * Latest published route nodes.
     */
    protected transient List<Node> route;
    /**
     * Next unconsumed route index.
     */
    protected transient int nodeIndex;
    /**
     * Existing route refresh tick counter.
     */
    protected transient int routeUpdateTick;

    /**
     * {@code memoryStorage} shares observations and control inputs across this
     * group's components. The builder creates a fresh {@code MemoryStorageImpl}
     * unless a caller supplies storage explicitly.
     */
    @Builder.Default
    @Getter
    protected MemoryStorage memoryStorage = new MemoryStorageImpl();

    // sensorPeriodCounters, coreBehaviorPeriodCounters, and behaviorPeriodCounters
    // count eligible ticks since the last observation or evaluation. setEntity
    // replaces all three maps and creates empty running sets.
    /**
     * Per-sensor elapsed group ticks.
     */
    protected transient Map<Sensor, Integer> sensorPeriodCounters;
    /**
     * Core and ordinary evaluation tick counters.
     */
    protected transient Map<Behavior, Integer> coreBehaviorPeriodCounters, behaviorPeriodCounters;
    // runningCoreBehaviors ignores priority, while runningBehaviors contains peers
    // at the current ordinary priority. Both sets retain active behaviors until
    // execution finishes; only the ordinary set is replaced by higher priority.
    /**
     * Currently executing core behaviors.
     */
    protected transient Set<Behavior> runningCoreBehaviors;
    /**
     * Currently executing ordinary behaviors.
     */
    protected transient Set<Behavior> runningBehaviors;

    /**
     * {@code entity} identifies this group's tick target. {@code setEntity}
     * replaces it and resets scheduling counters without clearing memories or
     * invoking cleanup on previously running behaviors.
     */
    protected transient EntityIntelligent entity;

    /**
     * {@code syncedActions} retains deferred sequential work in insertion
     * order. Parallel and sequential tick phases must coordinate access because
     * the underlying {@code LinkedList} is not synchronized.
     */
    protected final Queue<Runnable> syncedActions = new LinkedList<>();

    /**
     * Assigns the entity and initializes fresh period counters and running
     * sets. Existing memories, route state, and queued actions remain;
     * previously running behaviors do not receive stop or interruption
     * callbacks.
     */
    public void setEntity(EntityIntelligent entity) {
        this.entity = entity;
        initPeriodCounters();
    }

    /**
     * Queues an action for a later sequential entity tick. Callers must obey
     * the parallel-to-sequential tick handoff because this queue provides no
     * synchronization for unrelated producers or consumers. A queued {@code
     * null} ends the current drain without running later entries during that
     * invocation.
     */
    public void addSyncedAction(Runnable action) {
        syncedActions.offer(action);
    }

    /**
     * Runs queued actions in order during the sequential entity tick. An action
     * is removed before invocation; if it throws, remaining actions stay queued
     * and the failure propagates. A queued {@code null} is removed and ends
     * that drain.
     */
    public void processSyncedActions() {
        Runnable action;
        while ((action = syncedActions.poll()) != null) {
            action.run();
        }
    }

    /**
     * Processes one AI tick for the assigned entity, returning immediately
     * before assignment. Sensors run before behavior evaluation, and newly
     * started behaviors execute during this same tick. Core and ordinary
     * behaviors maintain separate running sets.
     *
     * <p>
     * Execution may replace movement memories before {@code updateRoute}
     * prepares the current segment. Controllers then consume that segment and
     * other memories, so their order follows behavior execution and waypoint
     * advancement. Sequential world actions are queued rather than drained
     * here; the entity component invokes {@code processSyncedActions} in its
     * sequential phase.
     */
    public void tick() {
        if (entity == null) {
            return;
        }

        collectSensorData(entity);
        evaluateCoreBehaviors(entity);
        evaluateBehaviors(entity);
        tickRunningCoreBehaviors(entity);
        tickRunningBehaviors(entity);
        updateRoute(entity);
        applyController(entity);
    }

    /**
     * Resets sensor and behavior timers and creates empty running sets. This
     * operation does not stop prior behavior instances or reset their stored
     * state, so callers must handle any reassignment cleanup separately.
     */
    protected void initPeriodCounters() {
        sensorPeriodCounters = new HashMap<>();
        for (var sensor : sensors) {
            sensorPeriodCounters.put(sensor, 0);
        }
        coreBehaviorPeriodCounters = new HashMap<>();
        for (var behavior : coreBehaviors) {
            coreBehaviorPeriodCounters.put(behavior, 0);
        }
        behaviorPeriodCounters = new HashMap<>();
        for (var behavior : behaviors) {
            behaviorPeriodCounters.put(behavior, 0);
        }
        runningCoreBehaviors = new LinkedHashSet<>();
        runningBehaviors = new LinkedHashSet<>();
    }

    /**
     * Collects observations for sensors whose periods have elapsed.
     * Uninitialized counters skip sensing; each due sensor resets its counter
     * after invocation, while nonpositive periods permit sensing on every group
     * tick.
     */
    protected void collectSensorData(EntityIntelligent entity) {
        if (sensorPeriodCounters == null) return;
        for (var sensor : sensors) {
            int counter = sensorPeriodCounters.getOrDefault(sensor, 0) + 1;
            if (counter >= sensor.getPeriod()) {
                sensor.sense(entity);
                counter = 0;
            }
            sensorPeriodCounters.put(sensor, counter);
        }
    }

    /**
     * Starts due eligible core behaviors without ordinary priority competition.
     * Running core behaviors are skipped without advancing their evaluation
     * counters, and each new activation invokes {@code onStart} before setting
     * {@code ACTIVE} and joining the running set.
     */
    protected void evaluateCoreBehaviors(EntityIntelligent entity) {
        if (coreBehaviorPeriodCounters == null) return;
        for (var entry : coreBehaviorPeriodCounters.entrySet()) {
            var behavior = entry.getKey();
            if (runningCoreBehaviors.contains(behavior)) continue;
            int counter = entry.getValue() + 1;
            coreBehaviorPeriodCounters.put(behavior, counter);
            if (counter < behavior.getPeriod()) continue;
            coreBehaviorPeriodCounters.put(behavior, 0);
            if (behavior.evaluate(entity)) {
                behavior.onStart(entity);
                behavior.setBehaviorState(BehaviorState.ACTIVE);
                runningCoreBehaviors.add(behavior);
            }
        }
    }

    /**
     * Selects newly eligible ordinary behaviors relative to the current running
     * priority. Timers advance only for stopped behaviors; each due evaluation
     * resets its timer even when eligibility fails. The candidate set retains
     * only the largest priority among successful evaluations in this pass.
     *
     * <p>
     * Lower candidates leave the running set unchanged, equal candidates join
     * existing peers, and higher candidates interrupt every running ordinary
     * behavior before activation. The running priority comes from its first
     * member, relying on prior selection to keep all members at the same
     * priority. The group does not use behavior weights in this selection.
     */
    protected void evaluateBehaviors(EntityIntelligent entity) {
        if (behaviorPeriodCounters == null) return;
        var evalSucceed = new HashSet<Behavior>();
        int highestPriority = Integer.MIN_VALUE;
        for (var entry : behaviorPeriodCounters.entrySet()) {
            var behavior = entry.getKey();
            if (runningBehaviors.contains(behavior)) continue;
            int counter = entry.getValue() + 1;
            behaviorPeriodCounters.put(behavior, counter);
            if (counter < behavior.getPeriod()) continue;
            behaviorPeriodCounters.put(behavior, 0);
            if (behavior.evaluate(entity)) {
                if (behavior.getPriority() > highestPriority) {
                    evalSucceed.clear();
                    highestPriority = behavior.getPriority();
                } else if (behavior.getPriority() < highestPriority) {
                    continue;
                }
                evalSucceed.add(behavior);
            }
        }
        if (evalSucceed.isEmpty()) return;

        var first = runningBehaviors.isEmpty() ? null : runningBehaviors.iterator().next();
        int runningPriority = first != null ? first.getPriority() : Integer.MIN_VALUE;

        if (highestPriority < runningPriority) {
            // Lower candidates leave the current peers running because only a strictly
            // higher priority may replace behavior work that has already started.
        } else if (highestPriority > runningPriority) {
            // Finish interruption callbacks before activating replacements so their cleanup
            // can release memories that the new higher-priority behaviors will populate.
            interruptRunningBehaviors(entity);
            startBehaviors(entity, evalSucceed);
        } else {
            startBehaviors(entity, evalSucceed);
        }
    }

    /**
     * Interrupts all ordinary behaviors before clearing their running set. Each
     * callback runs before its behavior changes to {@code STOP}; callback
     * failures propagate and can leave the transition partially completed.
     */
    protected void interruptRunningBehaviors(EntityIntelligent entity) {
        for (var behavior : runningBehaviors) {
            behavior.onInterrupt(entity);
            behavior.setBehaviorState(BehaviorState.STOP);
        }
        runningBehaviors.clear();
    }

    /**
     * Starts each supplied ordinary behavior and adds it to the running set.
     * {@code onStart} runs before the state changes to {@code ACTIVE}; callers
     * select compatible priorities before invoking this helper.
     */
    protected void startBehaviors(EntityIntelligent entity, Set<Behavior> toStart) {
        for (var behavior : toStart) {
            behavior.onStart(entity);
            behavior.setBehaviorState(BehaviorState.ACTIVE);
            runningBehaviors.add(behavior);
        }
    }

    /**
     * Executes each running core behavior and removes normal completions. A
     * {@code false} execution result invokes {@code onStop} before the state
     * changes to {@code STOP} and iterator removal completes.
     */
    protected void tickRunningCoreBehaviors(EntityIntelligent entity) {
        if (runningCoreBehaviors == null) return;
        var it = runningCoreBehaviors.iterator();
        while (it.hasNext()) {
            var behavior = it.next();
            if (!behavior.execute(entity)) {
                behavior.onStop(entity);
                behavior.setBehaviorState(BehaviorState.STOP);
                it.remove();
            }
        }
    }

    /**
     * Executes each running ordinary behavior and removes normal completions. A
     * {@code false} execution result invokes {@code onStop} before the state
     * changes to {@code STOP} and iterator removal completes.
     */
    protected void tickRunningBehaviors(EntityIntelligent entity) {
        if (runningBehaviors == null) return;
        var it = runningBehaviors.iterator();
        while (it.hasNext()) {
            var behavior = it.next();
            if (!behavior.execute(entity)) {
                behavior.onStop(entity);
                behavior.setBehaviorState(BehaviorState.STOP);
                it.remove();
            }
        }
    }

    /**
     * Refreshes route searches and prepares movement segments for the
     * controllers. An absent route finder leaves directions untouched; an
     * absent move target clears directions but keeps the cached route. Existing
     * route nodes trigger periodic refreshes, with a longer cycle for inactive
     * entities.
     *
     * <p>
     * A pending refresh or exhausted route submits a search only when {@code
     * routeFinding} is clear. Completion replaces {@code route} and resets
     * {@code nodeIndex}, even if the entity or target changed meanwhile. The
     * tick can continue following existing nodes while that search runs. If
     * submission throws before the task starts, {@code routeFinding} remains
     * set because the task's cleanup never runs.
     *
     * <p>
     * Before controllers run, proximity to the current endpoint requests the
     * next node. An exhausted route clears directions only when advancement was
     * requested; each consumed node otherwise becomes the next segment
     * endpoint.
     */
    protected void updateRoute(EntityIntelligent entity) {
        if (routeFinder == null) return;

        Vector3dc moveTarget = memoryStorage.get(MemoryTypes.MOVE_TARGET);
        if (moveTarget == null) {
            entity.setMoveDirectionStart(null);
            entity.setMoveDirectionEnd(null);
            return;
        }

        // Existing nodes can outlive block changes along their path. Refresh them
        // periodically so controllers do not keep following the same stale search indefinitely.
        if (hasNextNode()) {
            routeUpdateTick++;
            int cycle = entity.isActive() ? ROUTE_UPDATE_CYCLE : ROUTE_UPDATE_CYCLE * INACTIVE_ROUTE_MULTIPLIER;
            if (routeUpdateTick >= cycle) {
                routeUpdateTick = 0;
                routeUpdateRequired = true;
            }
        }

        if ((routeUpdateRequired || !hasNextNode()) && !routeFinding) {
            routeUpdateRequired = false;
            routeFinding = true;
            // Keep following the current route while this search runs. The completion
            // publishes its result without checking whether moveTarget is still current.
            Server.getInstance().getVirtualThreadPool().submit(() -> {
                try {
                    this.route = routeFinder.search(entity, moveTarget);
                    this.nodeIndex = 0;
                } finally {
                    routeFinding = false;
                }
            });
        }

        // Advance before controllers read the segment, allowing WalkController to
        // steer toward the next node in the same tick when this endpoint is reached.
        if (!entity.shouldUpdateMoveDirection() && entity.hasMoveDirection()) {
            var end = entity.getMoveDirectionEnd();
            if (end != null) {
                var loc = entity.getLocation();
                double dx = end.x() - loc.x();
                double dz = end.z() - loc.z();
                double horizontalDistSq = dx * dx + dz * dz;
                // Entity width scales the horizontal arrival tolerance for waypoint advancement.
                var aabb = entity.getAABB();
                double bbWidth = aabb.maxX() - aabb.minX();
                double maxDist = bbWidth > 0.75 ? bbWidth / 2.0 : 0.75 - bbWidth / 2.0;
                if (horizontalDistSq < maxDist * maxDist) {
                    entity.setShouldUpdateMoveDirection(true);
                }
            }
        }

        if (entity.shouldUpdateMoveDirection() || !entity.hasMoveDirection()) {
            if (hasNextNode()) {
                updateMoveDirection(entity);
                entity.setShouldUpdateMoveDirection(false);
            } else if (entity.shouldUpdateMoveDirection()) {
                entity.setMoveDirectionStart(null);
                entity.setMoveDirectionEnd(null);
                entity.setShouldUpdateMoveDirection(false);
            }
        }
    }

    /**
     * Returns whether the cached route contains another unconsumed waypoint
     * node.
     */
    protected boolean hasNextNode() {
        return route != null && nodeIndex < route.size();
    }

    /**
     * Returns the next route node and advances the index. An absent or
     * exhausted route returns {@code null} without advancing; callers must
     * coordinate consumption with asynchronous route replacement.
     */
    protected Node nextNode() {
        if (!hasNextNode()) return null;
        return route.get(nodeIndex++);
    }

    /**
     * Consumes a route node and sets the next movement segment. The previous
     * endpoint becomes its origin, or the entity's current position supplies
     * the origin when no endpoint exists.
     */
    protected void updateMoveDirection(EntityIntelligent entity) {
        var end = entity.getMoveDirectionEnd();
        var next = nextNode();
        if (next != null) {
            entity.setMoveDirectionStart(end != null ? end : new Vector3d(entity.getLocation().x(), entity.getLocation().y(), entity.getLocation().z()));
            entity.setMoveDirectionEnd(next.getVector());
        }
    }

    /**
     * Invokes every configured controller using the current entity and
     * memories.
     */
    protected void applyController(EntityIntelligent entity) {
        for (var controller : controllers) {
            controller.control(entity);
        }
    }
}
