package org.allaymc.api.scoreboard;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.allaymc.api.server.Server;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
public final class Scoreboard {
    /**
     * The identifier name of this scoreboard
     */
    private final String objectiveName;
    /**
     * The display name of this scoreboard
     */
    private final String displayName;
    /**
     * The "criteria" of this scoreboard (e.g., dummy)
     */
    private final String criteriaName;
    private final Map<DisplaySlot, Set<ScoreboardViewer>> viewers = new ConcurrentHashMap<>();
    private final Map<Scorer, ScoreboardLine> lines = new ConcurrentHashMap<>();
    /**
     * The sorting rule of this scoreboard
     * <p>
     * The sorting action is performed by the client, so the server does not need to handle sorting
     */
    @Setter
    private SortOrder sortOrder;

    {
        for (var slot : DisplaySlot.values()) {
            viewers.put(slot, Sets.newConcurrentHashSet());
        }
    }

    public Scoreboard(String objectiveName) {
        this(objectiveName, objectiveName);
    }

    public Scoreboard(String objectiveName, String displayName) {
        this(objectiveName, displayName, "dummy");
    }

    public Scoreboard(String objectiveName, String displayName, String criteriaName) {
        this(objectiveName, displayName, criteriaName, SortOrder.ASCENDING);
    }

    public Scoreboard(String objectiveName, String displayName, String criteriaName, SortOrder sortOrder) {
        this.objectiveName = objectiveName;
        this.displayName = displayName;
        this.criteriaName = criteriaName;
        this.sortOrder = sortOrder;
    }

    /**
     * @return All viewers of this scoreboard
     */
    public Set<ScoreboardViewer> getAllViewers() {
        var all = new HashSet<ScoreboardViewer>();
        this.viewers.values().forEach(all::addAll);
        return all;
    }

    /**
     * @param slot Target slot
     *
     * @return Viewers of the target slot of this scoreboard
     */
    public Set<ScoreboardViewer> getViewers(DisplaySlot slot) {
        return this.viewers.get(slot);
    }

    /**
     * Remove a specific viewer from the target slot of this scoreboard
     *
     * @param viewer Target viewer
     * @param slot   Target slot
     *
     * @return Whether the removal was successful
     */
    public boolean removeViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        var removed = this.viewers.get(slot).remove(viewer);
        if (removed) viewer.hideScoreboardSlot(slot);
        return removed;
    }

    /**
     * Add a viewer to the target slot of this scoreboard
     *
     * @param viewer Target viewer
     * @param slot   Target slot
     *
     * @return Whether the addition was successful
     */
    public boolean addViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        removeInvalidViewers();
        var added = this.viewers.get(slot).add(viewer);
        if (added) viewer.displayScoreboard(this, slot);
        return added;
    }

    /**
     * remove invalid viewers（eg: offline player）
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
     * Check if a specific viewer exists in the target slot of this scoreboard
     *
     * @param viewer Target viewer
     * @param slot   Target slot
     *
     * @return Whether it exists
     */
    public boolean containViewer(ScoreboardViewer viewer, DisplaySlot slot) {
        return this.viewers.get(slot).contains(viewer);
    }

    /**
     * Get the line corresponding to the tracker in this scoreboard (if it exists)
     *
     * @param scorer Tracker
     *
     * @return Corresponding line
     */
    public @Nullable ScoreboardLine getLine(Scorer scorer) {
        return this.lines.get(scorer);
    }

    /**
     * Add a line to this scoreboard
     *
     * @param line Target line
     *
     * @return Whether the addition was successful
     */
    public boolean addLine(ScoreboardLine line) {
        if (wouldCallEvent()) {
            // TODO: event
            //       var event = new ScoreboardLineChangeEvent(this, line, line.getScore(), line.getScore(), ScoreboardLineChangeEvent.ActionType.ADD_LINE);
            //       Server.getInstance().getPluginManager().callEvent(event);
            //       if (event.isCancelled()) return false;
            //       line = event.getLine();
        }
        this.lines.put(line.getScorer(), line);
        updateScore(line);
        return true;
    }

    /**
     * Add a line to this scoreboard
     *
     * @param scorer Tracker
     * @param score  Score
     *
     * @return Whether the addition was successful
     */
    public boolean addLine(Scorer scorer, int score) {
        return addLine(new ScoreboardLine(this, scorer, score));
    }

    /**
     * Convenient interface for plugins to display scores
     *
     * @param text  Name of FakeScorer
     * @param score Score
     *
     * @return Whether the addition was successful
     */
    public boolean addLine(String text, int score) {
        var fakeScorer = new FakeScorer(text);
        return addLine(new ScoreboardLine(this, fakeScorer, score));
    }

    /**
     * Remove the line corresponding to the tracker in this scoreboard (if it exists)
     *
     * @param scorer Target tracker
     *
     * @return Whether the removal was successful
     */
    public boolean removeLine(Scorer scorer) {
        if (lines.isEmpty()) return false;

        var removed = lines.get(scorer);
        if (removed == null) return false;

        if (wouldCallEvent()) {
            // TODO: event
            //       var event = new ScoreboardLineChangeEvent(this, removed, removed.getScore(), removed.getScore(), ScoreboardLineChangeEvent.ActionType.REMOVE_LINE);
            //       Server.getInstance().getPluginManager().callEvent(event);
            //       if (event.isCancelled()) return false;
        }
        this.lines.remove(scorer);
        getAllViewers().forEach(viewer -> viewer.removeScoreboardLine(removed));
        return true;
    }

    /**
     * Remove all lines from the scoreboard
     *
     * @param send Whether to send to viewers
     *
     * @return Whether the removal was successful
     */
    public boolean removeAllLines(boolean send) {
        if (lines.isEmpty()) return false;
        if (wouldCallEvent()) {
            // TODO: event
            //       var event = new ScoreboardLineChangeEvent(this, null, 0, 0, ScoreboardLineChangeEvent.ActionType.REMOVE_ALL_LINES);
            //       Server.getInstance().getPluginManager().callEvent(event);
            //       if (event.isCancelled()) return false;
        }
        if (send) this.lines.keySet().forEach(this::removeLine);
        else this.lines.clear();
        return true;
    }

    /**
     * Check if the tracker has a record in this scoreboard
     *
     * @param scorer Target tracker
     *
     * @return Whether it exists
     */
    public boolean containLine(Scorer scorer) {
        return this.lines.containsKey(scorer);
    }

    /**
     * Send new scores to all viewers <br>
     *
     * @param update Line to update
     */
    public void updateScore(ScoreboardLine update) {
        getAllViewers().forEach(viewer -> viewer.updateScore(update));
    }

    /**
     * Resend this scoreboard and line information to all viewers
     * <p>
     * For example, after making a lot of changes to the scoreboard, call this method
     * <p>
     * It can save a lot of bandwidth
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
     * Convenient interface for plugins
     * <p>
     * Set the content of the scoreboard in the order of the List (using FakeScorer as the tracker)
     * <p>
     * It will overwrite all previous lines
     *
     * @param lines String content to set
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
     * Set the content of the scoreboard in the order of the List <br>
     * It will overwrite all previous lines <br>
     *
     * @param lines Line content to set
     */
    public void setLines(Collection<ScoreboardLine> lines) {
        removeAllLines(false);
        lines.forEach(line -> this.lines.put(line.getScorer(), line));
        resend();
    }

    /**
     * @return Whether changes to this scoreboard will trigger events
     */
    public boolean wouldCallEvent() {
        return Server.getInstance().getScoreboardService().contain(this);
    }
}
