package org.allaymc.api.ddui;

import org.jetbrains.annotations.ApiStatus;

/**
 * Represents an object capable of viewing and interacting with DDUI screens.
 *
 * @author Miroshka
 */
public interface DataDrivenScreenViewer {

    /**
     * Shows the provided DDUI screen.
     *
     * @param screen screen to show
     */
    void viewDataDrivenScreen(DataDrivenScreen screen);

    /**
     * Returns the currently active DDUI screen.
     *
     * @return active DDUI screen or {@code null} if none is open
     */
    DataDrivenScreen getDataDrivenScreen();

    /**
     * Removes the active DDUI screen from the viewer without sending a close packet.
     *
     * @return removed screen or {@code null} if none was open
     */
    @ApiStatus.Internal
    DataDrivenScreen removeDataDrivenScreen();

    /**
     * Removes the active DDUI screen only if it matches the given form id.
     *
     * @param formId DDUI form id reported by the client
     *
     * @return removed screen or {@code null} if there was no matching active screen
     */
    @ApiStatus.Internal
    DataDrivenScreen removeDataDrivenScreen(int formId);

    /**
     * Closes all DDUI screens currently shown to this viewer.
     */
    void closeDataDrivenScreens();

    /**
     * Routes incoming DDUI input data to the active screen.
     *
     * @param dataStore source data store name
     * @param property root property name
     * @param path property path
     * @param data incoming wire value
     *
     * @return {@code true} if the input was handled
     */
    @ApiStatus.Internal
    boolean handleDataDrivenScreenInput(String dataStore, String property, String path, Object data);
}
