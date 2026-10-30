package org.allaymc.api.scoreboard;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardAllLinesRemoveEvent;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardLineAddEvent;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardLineRemoveEvent;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.allaymc.api.server.Server;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * {@code Scoreboard} represents a named objective, its scorer-keyed lines, and viewers subscribed
 * in display slots. Each line also has a client identifier, while scorer equality controls
 * replacement in the server map. The client applies the configured sort order.
 *
 * <p>
 * Mutations can dispatch cancellable events when the objective name exists in the global manager.
 * Storage changes precede viewer notifications, so callback failures do not roll back maps or
 * scores. {@code setLines} attempts bulk removal before insertion but does not honor a cancelled
 * removal result, allowing existing lines to survive. Concurrent collections protect individual
 * membership operations, while event checks, related map changes, and notifications remain compound
 * operations requiring caller coordination.
 */
@Getter
public final class Scoreboard {
    /**
     * Stable objective lookup name.
     */
    private final String objectiveName;
    /**
     * Title displayed for the objective.
     */
    private String displayName;
    /**
     * Objective criteria identifier string.
     */
    private String criteriaName;
    /**
     * {@code viewers} maps each display slot to subscribed recipients. Generated accessors and slot
     * lookup expose mutable membership without sending display or hide notifications.
     */
    private final Map<DisplaySlot, Set<ScoreboardViewer>> viewers = new ConcurrentHashMap<>();
    /**
     * {@code lines} maps scorer equality to retained line instances. Its generated getter exposes
     * mutable storage, so direct changes bypass events and viewer notifications.
     */
    private final Map<Scorer, ScoreboardLine> lines = new ConcurrentHashMap<>();
    /**
     * Client-applied score ordering rule.
     */
    private SortOrder sortOrder;

    /**
     * Creates an empty objective with no subscribed viewers in any display slot. This overload uses
     * the objective name as its display name, {@code "dummy"} criteria, {@code ASCENDING} ordering.
     */
    public Scoreboard(String objectiveName) {
        this(objectiveName, objectiveName);
    }

    /**
     * Creates an empty objective with the supplied names and no subscribed viewers in any slot.
     * This overload uses {@code "dummy"} criteria and {@code ASCENDING} ordering without
     * registering the objective in the global manager.
     */
    public Scoreboard(String objectiveName, String displayName) {
        this(objectiveName, displayName, "dummy");
    }

    /**
     * Creates an empty objective with the supplied names and criteria and no subscribed viewers in
     * any slot. This overload selects {@code ASCENDING} ordering without registering the objective
     * in the global manager.
     */
    public Scoreboard(String objectiveName, String displayName, String criteriaName) {
        this(objectiveName, displayName, criteriaName, SortOrder.ASCENDING);
    }

    /**
     * Creates an empty objective with no subscribed viewers in any display slot. The supplied names
     * and sort order are stored without registration in the global manager.
     */
    public Scoreboard(String objectiveName, String displayName, String criteriaName, SortOrder sortOrder) {
        this.objectiveName = objectiveName;
        this.displayName = displayName;
        this.criteriaName = criteriaName;
        this.sortOrder = sortOrder;
        for (var slot : DisplaySlot.values()) {
            viewers.put(slot, Sets.newConcurrentHashSet());
        }
    }

    /**
     * Returns a new mutable set containing viewers from every display slot with duplicates removed.
     * Later subscription changes do not update this set, though concurrent traversal need not
     * observe one atomic membership state.
     */
    public Set<ScoreboardViewer> getAllViewers() {
        var all = new HashSet<ScoreboardViewer>();
        this.viewers.values().forEach(all::addAll);
        return all;
    }

    /**
     * Returns the live mutable viewer set for the supplied display slot. Direct set changes bypass
     * client display and hide notifications; callers normally use {@code addViewer} and
     * {@code removeViewer} to preserve those effects.
     */
    public Set<ScoreboardViewer> getViewers(DisplaySlot slot) {
        return this.viewers.get(slot);
    }

    /**
     * Removes a viewer from one slot and hides that client slot when membership changed. A
     * notification failure leaves the viewer removed, while an absent membership returns
     * {@code false} without hiding anything.
     */
    public boolean removeViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        var removed = this.viewers.get(slot).remove(viewer);
        if (removed) viewer.hideScoreboardSlot(slot);
        return removed;
    }

    /**
     * Prunes invalid viewer memberships, then adds the supplied viewer to one slot and displays the
     * objective when newly added. A display failure leaves membership added; duplicate membership
     * returns {@code false}.
     */
    public boolean addViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        removeInvalidViewers();
        var added = this.viewers.get(slot).add(viewer);
        if (added) viewer.displayScoreboard(this, slot);
        return added;
    }

    /**
     * Removes viewers whose validity predicate returns {@code false} from every slot. This pruning
     * changes server membership without sending hide notifications, and a predicate exception
     * propagates before that slot's removal completes.
     */
    private void removeInvalidViewers() {
        this.viewers.forEach((slot, slotViewers) -> {
            var invalid = slotViewers.stream()
                    .filter(viewer -> !viewer.isScoreboardViewerValid())
                    .collect(Collectors.toSet());
            slotViewers.removeAll(invalid);
        });
    }

    /**
     * Returns whether the supplied viewer is subscribed in the specified display slot.
     */
    public boolean containViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        return this.viewers.get(slot).contains(viewer);
    }

    /**
     * Stores the supplied displayname and resends this objective to its viewers. The value remains
     * changed if a viewer notification fails, and later recipients can retain their previous client
     * display.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        resend();
    }

    /**
     * Stores the supplied criterianame and resends this objective to its viewers. The value remains
     * changed if a viewer notification fails, and later recipients can retain their previous client
     * display.
     */
    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
        resend();
    }

    /**
     * Stores the supplied sortorder and resends this objective to its viewers. The value remains
     * changed if a viewer notification fails, and later recipients can retain their previous client
     * display.
     */
    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        resend();
    }

    /**
     * Returns the retained line for an equal scorer, or {@code null} when absent. The returned line
     * is mutable and remains associated with its originally supplied owning scoreboard.
     */
    public ScoreboardLine getLine(Scorer scorer) {
        return this.lines.get(scorer);
    }

    /**
     * Adds or replaces the scorer's retained line after any enabled add event permits the mutation.
     * Cancellation returns {@code false}; successful storage precedes viewer updates and does not
     * validate the line's owning scoreboard.
     */
    public boolean addLine(ScoreboardLine line) {
        if (wouldCallEvent()) {
            var event = new ScoreboardLineAddEvent(this, line);
            if (!event.call()) {
                return false;
            }
        }
        this.lines.put(line.getScorer(), line);
        updateScore(line);
        return true;
    }

    /**
     * Creates a line for the supplied scorer and score, then attempts its insertion. An enabled
     * cancellable event can reject the addition; otherwise a line with an equal scorer is replaced.
     */
    public boolean addLine(Scorer scorer, int score) {
        return addLine(new ScoreboardLine(this, scorer, score));
    }

    /**
     * Creates a fake scorer from the supplied text and attempts to insert its line. Equal text
     * identifies the same scorer, so successful insertion replaces that scorer's previous line
     * after any enabled event.
     */
    public boolean addLine(String text, int score) {
        var fakeScorer = new FakeScorer(text);
        return addLine(new ScoreboardLine(this, fakeScorer, score));
    }

    /**
     * Removes an existing scorer line after any enabled removal event permits it, then notifies
     * viewers. An absent line or cancelled event returns {@code false}; notification failure
     * leaves the line removed.
     */
    public boolean removeLine(Scorer scorer) {
        if (lines.isEmpty()) return false;

        var removed = lines.get(scorer);
        if (removed == null) return false;

        if (wouldCallEvent()) {
            var event = new ScoreboardLineRemoveEvent(this, removed);
            if (!event.call()) {
                return false;
            }
        }
        this.lines.remove(scorer);
        getAllViewers().forEach(viewer -> viewer.removeScoreboardLine(removed));
        return true;
    }

    /**
     * Attempts to remove all lines after an enabled bulk event permits the operation. An empty
     * scoreboard or cancelled bulk event returns {@code false}. With {@code send} enabled,
     * individual removals can still be cancelled, so a {@code true} result does not guarantee an
     * empty map.
     */
    public boolean removeAllLines(boolean send) {
        if (lines.isEmpty()) return false;
        if (wouldCallEvent()) {
            var event = new ScoreboardAllLinesRemoveEvent(this);
            if (!event.call()) {
                return false;
            }
        }
        if (send) this.lines.keySet().forEach(this::removeLine);
        else this.lines.clear();
        return true;
    }

    /**
     * Returns whether the map currently contains a line for this scorer.
     */
    public boolean containLine(Scorer scorer) {
        return this.lines.containsKey(scorer);
    }

    /**
     * Sends the supplied line to the union of this objective's subscribed viewers. The line need
     * not currently be stored in this objective, and a viewer exception stops later notifications.
     */
    public void updateScore(ScoreboardLine update) {
        getAllViewers().forEach(viewer -> viewer.updateScore(update));
    }

    /**
     * Removes the objective from the current viewer union, then displays it again for each
     * subscribed slot. Client removal can precede a failure in later notifications, and no rollback
     * restores earlier displays.
     */
    public void resend() {
        getAllViewers().forEach(viewer -> viewer.removeScoreboard(this));

        this.viewers.forEach((slot, slotViewers) ->
                slotViewers.forEach(slotViewer ->
                        slotViewer.displayScoreboard(this, slot)
                )
        );
    }

    /**
     * Assigns increasing scores to text entries and resends the objective after inserting
     * fake-scorer lines. Equal text replaces earlier entries for that scorer, while its assigned
     * score still follows input position.
     *
     * <p>
     * The method first attempts silent bulk removal but continues when its event is cancelled.
     * Existing lines can therefore remain alongside the supplied text, and insertion bypasses
     * individual line-add events. A failure during insertion or resend leaves prior mutations
     * intact rather than restoring the old contents.
     */
    public void setLines(List<String> lines) {
        removeAllLines(false);
        AtomicInteger score = new AtomicInteger();
        lines.forEach(str -> {
            var scorer = new FakeScorer(str);
            this.lines.put(scorer, new ScoreboardLine(this, scorer, score.getAndIncrement()));
        });
        resend();
    }

    /**
     * Inserts the supplied lines by scorer and resends this objective after attempting silent bulk
     * removal. Equal scorers replace earlier entries, and the supplied line instances retain their
     * original owners.
     *
     * <p>
     * A cancelled bulk removal does not stop insertion, so previous lines can remain. Direct
     * insertion bypasses individual line-add events and performs no owning-scoreboard validation.
     * Failures leave earlier changes in place; callers must coordinate shared input collections and
     * any required consistency with viewer notifications.
     */
    public void setLines(Collection<ScoreboardLine> lines) {
        removeAllLines(false);
        lines.forEach(line -> this.lines.put(line.getScorer(), line));
        resend();
    }

    /**
     * Returns whether this objective name exists in the global scoreboard manager. The check uses
     * the name rather than object identity, so a different registered instance with that name also
     * enables events here.
     */
    public boolean wouldCallEvent() {
        return Server.getInstance().getScoreboardManager().contain(this);
    }
}
