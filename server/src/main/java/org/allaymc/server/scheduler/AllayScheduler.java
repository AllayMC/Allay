package org.allaymc.server.scheduler;

import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.Task;
import org.allaymc.api.scheduler.TaskCreator;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@code AllayScheduler} dispatches tasks from a due-tick queue and tracks
 * their cancellation lifecycle. The owner advances the clock from one ticking thread; synchronous
 * callbacks execute there, while asynchronous callbacks run on the supplied
 * executor. A repeating registration leaves the queue during execution and is
 * reinserted after completion, using the then-current tick to calculate its
 * next due time.
 *
 * <p>
 * Queue operations and individual status values support cross-thread access,
 * but the lifecycle spans several operations without a common lock. In
 * particular, {@code cancelTask} checks cancellation separately from changes
 * to {@code taskCount}, while {@code shutdown} only visits entries in
 * {@code queue}. Owners must serialize ticking and coordinate
 * shutdown with submissions if they need a quiescent scheduler; this class does
 * not await running work or make cancellation callbacks globally atomic.
 *
 * @author daoge_cmd
 */
public class AllayScheduler implements Scheduler {

    /**
     * {@code COMPARATOR} orders queue entries by due tick. Registrations sharing
     * a tick have no additional insertion-order guarantee within this ordering.
     */
    protected static final Comparator<RunningTaskInfo> COMPARATOR = Comparator.comparing(RunningTaskInfo::getNextRunTick);
    /**
     * {@code INITIAL_CAPACITY} sets the queue's initial allocation size without
     * imposing a limit on the number of scheduled tasks.
     */
    protected static final int INITIAL_CAPACITY = 11;

    /**
     * {@code asyncTaskExecutor} executes asynchronous callbacks. The scheduler
     * borrows this executor from its caller, who retains responsibility for
     * closing it.
     */
    protected final ExecutorService asyncTaskExecutor;
    /**
     * {@code queue} holds waiting entries. Executing asynchronous tasks are
     * absent until their completion path offers them again for a later due tick.
     */
    protected final PriorityBlockingQueue<RunningTaskInfo> queue = new PriorityBlockingQueue<>(INITIAL_CAPACITY, COMPARATOR);
    /**
     * {@code tickCounter} counts completed clock increments on the ticking
     * thread. Asynchronous completion reads this plain field without a separate
     * visibility guarantee.
     */
    protected long tickCounter;
    /**
     * {@code taskCount} counts registrations until cancellation bookkeeping
     * completes. It includes executing callbacks and does not represent the
     * physical size of {@code queue}.
     */
    protected AtomicInteger taskCount = new AtomicInteger(0);

    /**
     * Creates an empty scheduler with a zero tick counter and the supplied
     * asynchronous executor. The caller owns the executor and must provide one
     * that accepts submissions whenever asynchronous tasks become due.
     */
    public AllayScheduler(ExecutorService asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    /**
     * Advances the clock and drains entries already due at the resulting tick.
     * The owner must serialize calls because queue inspection, removal, and the
     * clock increment form no atomic operation. Creator validity is checked
     * before dispatch, so invalidation after asynchronous submission does not
     * retract that callback. Work queued by a synchronous callback can be
     * drained during this same call when its due tick is already reached.
     *
     * <p>
     * Executor rejection, validity-check failures, and cancellation-hook
     * failures in the pre-dispatch rejection path propagate to the ticking
     * caller; the removed entry is not restored. Once execution starts,
     * {@link #runTask(RunningTaskInfo)} applies its own exception handling.
     */
    @Override
    public void tick() {
        tickCounter++;
        while (!queue.isEmpty() && queue.peek().getNextRunTick() <= tickCounter) {
            var taskInfo = queue.poll();
            // A cancelled repeating entry can remain queued after completion.
            // Check it before dispatch so that reinsertion cannot invoke it again.
            if (taskInfo.isCancelled() || !taskInfo.getCreator().isValid()) {
                cancelTask(taskInfo);
                continue;
            }

            // Remove the entry before submitting its callback; only completion
            // reinserts repeating work, so one registration is not dispatched again here.
            if (taskInfo.isAsync()) {
                asyncTaskExecutor.submit(() -> runTask(taskInfo));
            } else {
                runTask(taskInfo);
            }
        }
    }

    /**
     * Registers one invocation with a due tick calculated from the current
     * counter and delay. Nonpositive delays remain eligible for the next queue
     * scan; the registration is cancelled after its callback finishes.
     */
    @Override
    public void scheduleDelayed(TaskCreator creator, Task task, int delay, boolean async) {
        addTask(new RunningTaskInfo(creator, task, delay, 0, async));
    }

    /**
     * Registers repetition whose first due tick includes the positive period.
     * A nonpositive period is classified as one-shot work with zero delay, so
     * it does not repeatedly execute within a single tick.
     */
    @Override
    public void scheduleRepeating(TaskCreator creator, Task task, int period, boolean async) {
        addTask(new RunningTaskInfo(creator, task, 0, period, async));
    }

    /**
     * Returns the counter most recently advanced by the ticking thread.
     */
    @Override
    public long getTicks() {
        return tickCounter;
    }

    /**
     * Returns the current registration count maintained by cancellation
     * bookkeeping. Executing tasks contribute to this value, and a cancelled
     * repeating entry can remain queued after it no longer contributes.
     */
    @Override
    public int getRunningTaskCount() {
        return taskCount.get();
    }

    /**
     * Cancels entries visible in the waiting queue and then clears that queue.
     * Already submitted callbacks are absent from the queue and continue
     * independently; their completion can enqueue repeating work after this
     * method returns. Callers needing complete termination must coordinate those
     * callbacks and further submissions themselves. Cancellation hook failures
     * propagate and can prevent the final clear, while the caller-owned executor
     * remains open regardless of this scheduler's queue contents.
     */
    @Override
    public void shutdown() {
        // Running asynchronous entries are outside this queue, so this pass
        // only cancels waiting work and does not establish an executor completion barrier.
        queue.forEach(this::cancelTask);
        queue.clear();
    }

    /**
     * Invokes a dispatched callback and resolves its next lifecycle step.
     * Returning {@code false} requests cancellation; caught exceptions are
     * reported through the task's error hook before cancellation is attempted.
     * Both hooks must return normally for their following bookkeeping to run.
     *
     * <p>
     * The finalization path always clears the running flag. One-shot entries
     * are cancelled there, whereas repeating entries are requeued even when
     * already cancelled; the tick-side validity check subsequently discards
     * those entries. An uncaught error still reaches this finalization path.
     */
    protected void runTask(RunningTaskInfo info) {
        var task = info.getTask();
        try {
            info.setRunning(true);
            if (!task.onRun()) {
                cancelTask(info);
            }
        } catch (Exception exception) {
            // Give the task its failure before cancellation; an error hook that
            // throws prevents this path's later cancellation request from being reached.
            task.onError(exception);
            cancelTask(info);
        } finally {
            info.setRunning(false);
            // Requeue all repeating entries after completion, including cancelled
            // ones; tick checks cancellation before dispatching a queued entry again.
            if (!info.isRepeating()) {
                cancelTask(info);
            } else {
                addTask(info, false);
            }
        }
    }

    /**
     * Marks an uncancelled entry and notifies its task before reducing the
     * registration count. A previously visible cancellation flag suppresses
     * repeated calls along sequential lifecycle paths. The check and update
     * are separate operations, so concurrent callers are not guaranteed to
     * notify or decrement exactly once. The flag is set before the hook to
     * suppress reentrant cancellation; if that hook throws, the count decrement
     * is skipped and the cancellation flag remains set.
     */
    protected void cancelTask(RunningTaskInfo info) {
        if (info.isCancelled()) {
            return;
        }

        // Publish cancellation before the user hook so reentrant cancellation
        // observes it; bookkeeping still depends on that hook returning normally.
        info.setCancelled(true);
        info.getTask().onCancel();
        taskCount.decrementAndGet();
    }

    /**
     * Enqueues a newly created registration and adds it to the live count.
     * Callers use this overload only for first admission; repeating callbacks reinsert
     * their existing registration through the overload that skips counting.
     */
    protected void addTask(RunningTaskInfo taskInfo) {
        addTask(taskInfo, true);
    }

    /**
     * Calculates an entry's next due tick and offers it to the waiting queue.
     * Repeating entries use their period; other entries use their delay. The
     * calculation uses the current scheduler counter, so asynchronous completion
     * schedules from completion time rather than from its previous deadline.
     *
     * <p>
     * Queue publication precedes the optional first-registration count increase;
     * these separate operations do not provide an atomic admission snapshot to
     * a concurrent ticking thread or count observer.
     */
    protected void addTask(RunningTaskInfo taskInfo, boolean firstTime) {
        // Complete the queue key before publication because the priority queue
        // does not reorder entries when their due-tick field changes after insertion.
        taskInfo.setNextRunTick(tickCounter + (taskInfo.isRepeating() ? taskInfo.getPeriod() : taskInfo.getDelay()));
        queue.offer(taskInfo);
        if (firstTime) {
            taskCount.incrementAndGet();
        }
    }
}
