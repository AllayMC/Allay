package org.allaymc.server.ddui;

import org.allaymc.api.ddui.DataDrivenScreen;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;

/**
 * Runtime controller that manages the active DDUI screen session for a player.
 *
 * @author Miroshka
 */
public final class DataDrivenScreenController {

    private final Player player;
    private DataDrivenScreenSessionImpl activeSession;
    private int nextFormId = 1;

    /**
     * Creates a new DDUI controller for the given player.
     *
     * @param player owner of the DDUI session
     */
    public DataDrivenScreenController(Player player) {
        this.player = player;
    }

    /**
     * Shows the provided screen and replaces the previous DDUI session if one exists.
     *
     * @param screen screen to show
     */
    public void view(DataDrivenScreen screen) {
        removeActiveScreen();

        var session = new DataDrivenScreenSessionImpl(player, screen, nextFormId());
        activeSession = session;
        screen.attachSession(session.dirtyMarker());
        session.show();
    }

    /**
     * Returns the currently active DDUI screen.
     *
     * @return active screen or {@code null} if none is shown
     */
    public DataDrivenScreen getActiveScreen() {
        return activeSession == null ? null : activeSession.getScreen();
    }

    /**
     * Detaches the active DDUI session without sending a close packet.
     *
     * @return removed screen or {@code null} if none was active
     */
    public DataDrivenScreen removeActiveScreen() {
        if (activeSession == null) {
            return null;
        }

        var screen = activeSession.getScreen();
        activeSession.detach();
        activeSession = null;
        return screen;
    }

    /**
     * Detaches the active DDUI session only if the form id matches the currently active one.
     *
     * @param formId DDUI form id reported by the client
     *
     * @return removed screen or {@code null} if the active session was already replaced
     */
    public DataDrivenScreen removeActiveScreen(int formId) {
        if (activeSession == null || activeSession.getFormId() != formId) {
            return null;
        }

        return removeActiveScreen();
    }

    /**
     * Closes the active DDUI screen and clears the session.
     */
    public void closeAll() {
        if (activeSession == null) {
            return;
        }

        activeSession.close();
        activeSession = null;
    }

    /**
     * Flushes pending dirty DDUI properties to the client.
     */
    public void tick() {
        if (activeSession != null) {
            activeSession.flush();
        }
    }

    /**
     * Routes incoming DDUI input data to the active session.
     *
     * @param dataStore data store name
     * @param property root property name
     * @param path property path
     * @param data wire value
     *
     * @return {@code true} if the input was handled
     */
    public boolean handleInput(String dataStore, String property, String path, Object data) {
        return activeSession != null && activeSession.handleInput(dataStore, property, path, data);
    }

    /**
     * Routes an incoming {@link DataStoreUpdate} to the active session.
     *
     * @param update incoming update
     *
     * @return {@code true} if the input was handled
     */
    public boolean handleInput(DataStoreUpdate update) {
        return handleInput(update.getDataStoreName(), update.getProperty(), update.getPath(), update.getData());
    }

    private int nextFormId() {
        var formId = nextFormId;
        nextFormId = nextFormId == Integer.MAX_VALUE ? 1 : nextFormId + 1;
        return formId;
    }
}
