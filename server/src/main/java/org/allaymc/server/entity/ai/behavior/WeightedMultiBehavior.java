package org.allaymc.server.entity.ai.behavior;

import lombok.Getter;
import org.allaymc.api.entity.ai.behavior.Behavior;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code WeightedMultiBehavior} selects one eligible child by weight within the
 * highest child priority and delegates active work to that child. Its own
 * priority, weight, and period govern its position in the surrounding group
 * independently of the corresponding child values. The supplied child set is
 * exposed through an unmodifiable view, so the original owner can still change
 * the available children.
 *
 * <p>
 * Selection evaluates children again while finding weights and locating the
 * winner. Evaluators that change state or use randomness can therefore disagree
 * between passes, producing no selection or an invalid total weight. Callers
 * must provide suitable weights and account for repeated evaluation. Successful
 * evaluation replaces {@code activeBehavior}; failed evaluation leaves any
 * previous selection intact until a stop or interruption callback clears it.
 *
 * @author daoge_cmd
 */
@Getter
public class WeightedMultiBehavior extends AbstractBehavior {

    /**
     * {@code behaviors} is an unmodifiable view of the supplied child set.
     * Mutations through the original set remain visible to selection and the
     * generated getter.
     */
    protected final Set<Behavior> behaviors;
    /**
     * Outer behavior scheduling priority.
     */
    protected final int priority;
    /**
     * Outer composite selection weight.
     */
    protected final int weight;
    /**
     * Outer evaluation interval in ticks.
     */
    protected final int period;

    /**
     * {@code activeBehavior} retains the latest selected child. Failed
     * evaluation leaves it unchanged; normal stop and interruption clear it
     * after forwarding their callbacks.
     */
    protected Behavior activeBehavior;

    /**
     * Creates a composite retaining an unmodifiable view of the supplied
     * children. The constructor does not validate weights or priorities;
     * child-set owners must coordinate changes with evaluation and execution.
     */
    public WeightedMultiBehavior(Set<Behavior> behaviors, int priority, int weight, int period) {
        this.behaviors = Collections.unmodifiableSet(behaviors);
        this.priority = priority;
        this.weight = weight;
        this.period = period;
    }

    /**
     * Returns a fresh builder for collecting weighted behavior children.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Selects a child from the highest priority whose evaluations succeed. The
     * first pass finds that priority, the second computes its total weight, and
     * the final pass locates a random weighted interval. Each pass evaluates
     * children again, so the observations can change between passes.
     *
     * <p>
     * The total passed to random selection must be positive; otherwise random
     * selection throws {@code IllegalArgumentException}. No eligible priority
     * returns {@code false}, including the {@code Integer.MIN_VALUE} sentinel
     * case. A successful selection replaces {@code activeBehavior}, while an
     * unsuccessful pass leaves its prior value unchanged.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity) {
        // Child priority is independent of the outer composite priority. Later passes
        // repeat evaluation rather than retaining this pass's eligibility results.
        int highestPriority = Integer.MIN_VALUE;
        for (var behavior : behaviors) {
            if (behavior.evaluate(entity) && behavior.getPriority() > highestPriority) {
                highestPriority = behavior.getPriority();
            }
        }
        if (highestPriority == Integer.MIN_VALUE) {
            return false;
        }

        // Repeated evaluation can change eligibility after priority selection, so the
        // total reflects only this pass and must remain positive for nextInt.
        int totalWeight = 0;
        for (var behavior : behaviors) {
            if (behavior.getPriority() == highestPriority && behavior.evaluate(entity)) {
                totalWeight += behavior.getWeight();
            }
        }

        // The third pass can disagree with the weight pass for stateful evaluators,
        // leaving the previous active child when no interval receives the draw.
        int randomWeight = ThreadLocalRandom.current().nextInt(totalWeight);
        int currentWeight = 0;
        for (var behavior : behaviors) {
            if (behavior.getPriority() == highestPriority && behavior.evaluate(entity)) {
                currentWeight += behavior.getWeight();
                if (randomWeight < currentWeight) {
                    activeBehavior = behavior;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Runs the latest selected child and returns its continuation result. This
     * method returns {@code false} before any selection or after cleanup clears
     * the child; execution alone does not clear the selection.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        if (activeBehavior != null) {
            return activeBehavior.execute(entity);
        }
        return false;
    }

    /**
     * Forwards interruption to the selected child, then clears the selection.
     * An absent child requires no cleanup; if the child callback throws, the
     * retained selection is not cleared.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        if (activeBehavior != null) {
            activeBehavior.onInterrupt(entity);
            activeBehavior = null;
        }
    }

    /**
     * Forwards activation to the selected child when one exists. This method
     * does not select a child or alter its stored behavior state, so evaluation
     * must establish the selection first.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        if (activeBehavior != null) {
            activeBehavior.onStart(entity);
        }
    }

    /**
     * Forwards normal completion to the selected child, then clears the
     * selection. An absent child requires no cleanup; if the child callback
     * throws, the retained selection is not cleared.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        if (activeBehavior != null) {
            activeBehavior.onStop(entity);
            activeBehavior = null;
        }
    }

    /**
     * {@code Builder} collects child behaviors and outer scheduling metadata
     * for a weighted composite.
     */
    public static class Builder {
        /**
         * {@code behaviors} preserves child insertion order. Built composites
         * retain views of this set, so adding children later also changes
         * composites already built.
         */
        private final Set<Behavior> behaviors = new LinkedHashSet<>();
        /**
         * Selected outer scheduling priority.
         */
        private int priority;
        /**
         * Selected outer selection weight.
         */
        private int weight = 1;
        /**
         * Selected outer evaluation interval.
         */
        private int period = 1;

        /**
         * Adds a child and returns this mutable weighted behavior builder.
         */
        public Builder behavior(Behavior behavior) {
            this.behaviors.add(behavior);
            return this;
        }

        /**
         * Stores outer scheduling priority and returns this mutable composite
         * builder.
         */
        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        /**
         * Stores outer selection weight and returns this mutable composite
         * builder.
         */
        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        /**
         * Stores outer evaluation interval and returns this mutable composite
         * builder.
         */
        public Builder period(int period) {
            this.period = period;
            return this;
        }

        /**
         * Creates a composite with a live view of this builder's children.
         * Priority defaults to zero, outer weight and period default to one,
         * and no child-weight validation occurs.
         */
        public WeightedMultiBehavior build() {
            return new WeightedMultiBehavior(behaviors, priority, weight, period);
        }
    }
}
