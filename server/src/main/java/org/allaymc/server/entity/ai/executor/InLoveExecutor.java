package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.interfaces.EntityIntelligent;


/**
 * {@code InLoveExecutor} maintains a timed breeding-eligibility memory and
 * periodically emits the love entity action. Activation records the current
 * entity tick, while breeding completion can clear the shared eligibility flag
 * early; either elapsed duration or that cleared flag stops execution.
 *
 * @author daoge_cmd
 */
public class InLoveExecutor implements BehaviorExecutor {

    /**
     * Love-mode execution duration in ticks.
     */
    protected final int duration;
    /**
     * Execution ticks since activation.
     */
    protected int tickCounter;

    /**
     * Creates a love-mode executor retaining the supplied execution duration in
     * ticks.
     */
    public InLoveExecutor(int duration) {
        this.duration = duration;
    }

    /**
     * Resets the timer and records love eligibility with the current entity
     * tick.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, true);
        entity.getMemoryStorage().put(MemoryTypes.LAST_IN_LOVE_TIME, entity.getTick());
    }

    /**
     * Maintains love mode until the duration is exceeded or shared eligibility
     * is cleared. Every tenth continuing execution emits the love action; the
     * method does not re-enable a flag cleared by breeding.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;
        if (tickCounter > duration || !entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE)) {
            return false;
        }

        // Space love actions across continuing ticks instead of emitting particles every execution.
        if (tickCounter % 10 == 0) {
            entity.applyAction(SimpleEntityAction.IN_LOVE);
        }

        return true;
    }

    /**
     * Clears the shared love flag while retaining the last activation
     * timestamp.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
    }

    /**
     * Clears the shared love flag while retaining the last activation
     * timestamp.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
    }
}
