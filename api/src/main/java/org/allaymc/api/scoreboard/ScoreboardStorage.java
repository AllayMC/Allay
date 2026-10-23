package org.allaymc.api.scoreboard;


import org.allaymc.api.scoreboard.data.DisplaySlot;

import java.util.Collection;
import java.util.Map;

/**
 * {@code ScoreboardStorage} persists objective definitions, scores, and display-slot names through
 * a storage backend. Objective data and display associations use separate operations, so callers
 * control their ordering and cannot assume that a combined manager save forms one transaction.
 *
 * @author daoge_cmd
 */
public interface ScoreboardStorage {
    /**
     * Persists the supplied objective definition and its scorer lines through the backend. This
     * operation does not save display associations; callers invoke {@code saveDisplay} separately
     * when slot references must also be recorded.
     */
    void save(Scoreboard scoreboard);

    /**
     * Persists each supplied objective through the backend without saving display associations. The
     * collection need not replace all stored objectives, and failure can leave an earlier subset
     * already written.
     */
    void save(Collection<Scoreboard> scoreboards);

    /**
     * Persists the supplied display-slot associations using objective names that can be resolved
     * after loading. This operation is separate from objective saving and need not remove
     * previously stored slots absent from the map.
     */
    void saveDisplay(Map<DisplaySlot, Scoreboard> display);

    /**
     * Reads stored objectives into a map keyed by objective name for manager loading. Backends
     * reconstruct runtime line identities and do not restore viewer subscriptions from the
     * separately stored display associations.
     */
    Map<String, Scoreboard> readAll();

    /**
     * Reads a stored objective by name, returning {@code null} when none exists. The result
     * supplies objective data and scorer lines without restoring viewer memberships or applying
     * stored display associations.
     */
    Scoreboard read(String name);

    /**
     * Reads display-slot associations as objective names rather than live scoreboard instances.
     * Callers resolve those names against loaded objectives and decide how to handle references
     * with no matching stored objective.
     */
    Map<DisplaySlot, String> readDisplay();

    /**
     * Removes the named objective from storage without removing display associations automatically.
     * Callers must update or tolerate any separately stored slot reference that still points to the
     * removed name.
     */
    void remove(String name);

    /**
     * Removes stored objective definitions without implying removal of separately stored display
     * associations. A later manager load must still resolve any remaining slot names against the
     * objectives that are available.
     */
    void removeAll();

    /**
     * Returns whether the storage backend currently contains the named objective definition.
     */
    boolean contain(String name);
}
