package org.allaymc.api.scoreboard;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityDespawnEvent;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardAddEvent;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardRemoveEvent;
import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.server.PlayerQuitEvent;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.allaymc.api.server.Server;

import java.util.*;

/**
 * {@code ScoreboardManager} manages named objectives, global viewers, display-slot bookkeeping, and
 * persistence calls for scoreboard commands. Entity and player lifecycle listeners remove transient
 * entity lines or refresh player presentation while retaining player scorer records across
 * disconnects.
 *
 * <p>
 * {@code scoreboards} stores objectives by name, {@code display} stores slot associations, and
 * {@code viewers} supplies recipients when those associations are applied. Generated getters expose
 * all three mutable collections. {@code setDisplaySlot} currently sends the replacement but does
 * not record it in {@code display}, so later lookup and persistence can miss that association.
 * Saving writes objectives before display state without rollback; reading removes current state
 * before loading replacements and can leave partial changes when cancellation or failures
 * intervene.
 *
 * @author daoge_cmd
 */
@Getter
public final class ScoreboardManager {

    /**
     * Persistence backend for managed objectives.
     */
    private final ScoreboardStorage storage;
    /**
     * {@code display} holds recorded slot associations exposed by the generated getter.
     * {@code setDisplaySlot} currently sends an association without inserting it here.
     */
    private final Map<DisplaySlot, Scoreboard> display;
    /**
     * {@code viewers} retains global recipients exposed through the generated getter. Direct
     * changes bypass the slot synchronization performed by membership methods.
     */
    private final Set<ScoreboardViewer> viewers;
    /**
     * {@code scoreboards} maps objective names to mutable instances. The generated getter exposes
     * the current backing map, and {@code read} replaces that map reference.
     */
    private Map<String, Scoreboard> scoreboards;

    /**
     * Creates an empty manager and registers entity-despawn, player-join, and player-quit listeners
     * on the supplied server. Nonplayer despawn removes matching scorer lines, while player
     * departure removes client presentation but retains persistent player scores.
     *
     * <p>
     * Joining adds the player as a viewer before refreshing matching scorer lines for global
     * viewers. Quitting removes those displayed lines before viewer removal, and uninitialized
     * players are skipped. These callbacks use the manager's mutable collections directly, so
     * callers must coordinate management operations with the server event thread.
     */
    public ScoreboardManager(Server server, ScoreboardStorage storage) {
        this.storage = storage;
        this.display = new HashMap<>();
        this.viewers = new HashSet<>();
        this.scoreboards = new HashMap<>();

        var eventBus = server.getEventBus();
        eventBus.registerListenerFor(EntityDespawnEvent.class, event -> {
            var entity = event.getEntity();
            // Player scores survive disconnects and use UUID-based scorers; the
            // player lifecycle listeners handle presentation separately from entity-line removal.
            if (entity instanceof EntityPlayer) return;
            removeScorerFromAllScoreboards(new EntityScorer(entity));
        });
        eventBus.registerListenerFor(PlayerJoinEvent.class, event -> {
            var player = event.getPlayer();
            addViewer(player);
            var scorer = new PlayerScorer(player);
            scoreboards.values().forEach(scoreboard -> {
                if (scoreboard.containLine(scorer)) {
                    viewers.forEach(viewer -> viewer.updateScore(scoreboard.getLine(scorer)));
                }
            });
        });
        eventBus.registerListenerFor(PlayerQuitEvent.class, event -> {
            var player = event.getPlayer();
            if (!player.isInitialized()) return;
            var scorer = new PlayerScorer(player);
            scoreboards.values().forEach(scoreboard -> {
                if (scoreboard.containLine(scorer)) {
                    viewers.forEach(viewer -> viewer.removeScoreboardLine(scoreboard.getLine(scorer)));
                }
            });
            removeViewer(player);
        });
    }

    /**
     * Dispatches an add event and registers the objective by name when permitted. Cancellation
     * returns {@code false}; a successful insertion can replace an existing same-name objective
     * without removing its client presentation.
     */
    public boolean add(Scoreboard scoreboard) {
        var event = new ScoreboardAddEvent(scoreboard);
        if (!event.call()) {
            return false;
        }

        scoreboards.put(scoreboard.getObjectiveName(), scoreboard);
        // TODO: Update the scoreboard objective soft enum when dynamic command
        // objective completion is implemented for registrations.
        return true;
    }

    /**
     * Attempts removal by the supplied scoreboard's objective name rather than its object identity.
     * A different registered instance with that name can be removed, and the normal removal event
     * can cancel the operation.
     */
    public boolean remove(Scoreboard scoreboard) {
        return remove(scoreboard.getObjectiveName());
    }

    /**
     * Removes the named objective when present and its removal event permits it, then notifies
     * viewers and clears matching recorded slots. Notification failures leave the objective removed
     * and can prevent later cleanup.
     */
    public boolean remove(String objectiveName) {
        var removed = scoreboards.get(objectiveName);
        if (removed == null) return false;

        var event = new ScoreboardRemoveEvent(removed);
        if (!event.call()) {
            return false;
        }

        scoreboards.remove(objectiveName);
        // TODO: Remove this objective from the dynamic command completion enum
        // when scoreboard objective soft-enum synchronization is implemented.
        viewers.forEach(viewer -> viewer.removeScoreboard(removed));
        display.forEach((slot, scoreboard) -> {
            if (scoreboard != null && scoreboard.getObjectiveName().equals(objectiveName)) {
                clearDisplaySlot(slot);
            }
        });
        return true;
    }

    /**
     * Returns the registered objective for this name, or {@code null} when absent. The result is
     * the retained mutable instance rather than a copy of its lines or display settings.
     */
    public Scoreboard get(String objectiveName) {
        return scoreboards.get(objectiveName);
    }

    /**
     * Returns whether the supplied objective's name exists in the manager map. Object identity is
     * not compared, so another registered instance with the same name produces {@code true}.
     */
    public boolean contain(Scoreboard scoreboard) {
        return scoreboards.containsKey(scoreboard.getObjectiveName());
    }

    /**
     * Returns whether an objective is currently registered under the supplied name.
     */
    public boolean contain(String name) {
        return scoreboards.containsKey(name);
    }

    /**
     * Returns the objective recorded for the supplied display slot, or {@code null} when absent.
     * {@code setDisplaySlot} currently does not store its new association, so this map can differ
     * from client presentation.
     */
    public Scoreboard getDisplaySlot(DisplaySlot slot) {
        return display.get(slot);
    }

    /**
     * Clears a recorded slot and sends the supplied objective to global viewers in that slot.
     * {@code null} objectives are rejected; the implementation currently does not insert the
     * replacement into {@code display}.
     */
    public void setDisplaySlot(DisplaySlot slot, Scoreboard scoreboard) {
        Preconditions.checkNotNull(scoreboard);
        clearDisplaySlot(slot);
        this.viewers.forEach(viewer -> scoreboard.addViewer(viewer, slot));
    }

    /**
     * Removes a recorded slot association and removes global viewers from that objective's slot
     * membership. An unrecorded slot does nothing, even when an earlier unrecorded presentation
     * remains on a client.
     */
    public void clearDisplaySlot(DisplaySlot slot) {
        var old = display.remove(slot);
        if (old != null) this.viewers.forEach(viewer -> old.removeViewer(viewer, slot));
    }

    /**
     * Adds a global viewer and subscribes it to each recorded display association when newly added.
     * Duplicate membership returns {@code false}; notification failure leaves global membership
     * added and later slot work incomplete.
     */
    public boolean addViewer(ScoreboardViewer viewer) {
        var added = this.viewers.add(viewer);
        if (added) this.display.forEach((slot, scoreboard) -> {
            scoreboard.addViewer(viewer, slot);
        });
        return added;
    }

    /**
     * Removes a global viewer and unsubscribes it from each recorded display association when
     * present. Missing membership returns {@code false}; a later notification failure does not
     * restore the removed global membership.
     */
    public boolean removeViewer(ScoreboardViewer viewer) {
        var removed = viewers.remove(viewer);
        if (removed) this.display.forEach((slot, scoreboard) -> {
            scoreboard.removeViewer(viewer, slot);
        });
        return removed;
    }

    /**
     * Saves managed objectives before saving recorded display-slot associations through the storage
     * backend. The operations are separate, so callers must coordinate mutations while both views
     * are consumed. An objective-save failure prevents the display save; a display-save failure
     * does not undo objectives already written. Backends can retain objectives or slots absent from
     * the supplied collections, so this method does not imply full replacement or an atomic
     * snapshot.
     */
    public void save() {
        // Display associations reference objective names, so write objectives first;
        // a later display-save failure leaves the earlier objective writes in storage.
        storage.save(scoreboards.values());
        storage.saveDisplay(display);
    }

    /**
     * Removes current objectives and recorded display memberships before loading replacements from
     * storage. Objective removals dispatch cancellable events, but the later loaded map replaces
     * remaining registrations regardless of those cancellation results.
     *
     * <p>
     * The objective snapshot permits removal during traversal; display keys are traversed live
     * while clearing and can raise {@code ConcurrentModificationException} when populated. Loaded
     * display names missing from the replacement map are ignored. Failures do not restore earlier
     * registrations or client presentation, and applied slots inherit {@code setDisplaySlot}'s
     * missing bookkeeping insertion.
     */
    public void read() {
        // Snapshot objectives because remove mutates the manager map during this
        // pass; the snapshot also preserves the remaining removal candidates for iteration.
        new ArrayList<>(scoreboards.values()).forEach(this::remove);
        this.display.keySet().forEach(this::clearDisplaySlot);

        scoreboards = storage.readAll();
        storage.readDisplay().forEach((slot, objectiveName) -> {
            var scoreboard = get(objectiveName);
            if (scoreboard != null) {
                this.setDisplaySlot(slot, scoreboard);
            }
        });
    }

    /**
     * Attempts to remove the scorer's line from every registered objective. Each objective can
     * independently reject removal through its event, and an exception stops traversal after any
     * earlier successful changes.
     */
    public void removeScorerFromAllScoreboards(Scorer scorer) {
        scoreboards.values().forEach(scoreboard -> scoreboard.removeLine(scorer));
    }
}
