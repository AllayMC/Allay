package org.allaymc.api.item.component;

import org.allaymc.api.player.Player;
import org.allaymc.api.world.Dimension;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

/**
 * @author daoge_cmd
 */
public interface ItemFilledMapBaseComponent extends ItemBaseComponent {
    /**
     * Gets the image of the map.
     *
     * @return the image of the map, or {@code null} if the image is not set
     */
    BufferedImage getImage();

    /**
     * Sets the image of the map. An image in 128x128 is preferred, if the
     * provided image's size isn't 128x128, it will be resized to 128x128.
     *
     * @param image the image to set, or {@code null} to clear the image
     */
    void setImage(BufferedImage image);

    /**
     * Gets the map id.
     *
     * @return the map id
     */
    long getMapId();

    /**
     * @see #renderMap(Dimension, int, int, int)
     */
    default CompletableFuture<BufferedImage> renderMap(Dimension dimension, int x, int z) {
        return this.renderMap(dimension, x, z, 1);
    }

    /**
     * Renders the map for the given dimension at the specified coordinates.
     *
     * @param dimension the dimension to render the map in
     * @param startX    the start x coordinate of the map
     * @param startZ    the start z coordinate of the map
     * @param zoom      the zoom level of the map, which will be multiplied by the width and height of the area
     *                  Default value should be 1, which means the map will cover a 128x128 area.
     * @return the rendered map image
     */
    CompletableFuture<BufferedImage> renderMap(Dimension dimension, int startX, int startZ, int zoom);

    /**
     * Sends the map to the player. Usually you should call this method
     * after called {@link #setImage(BufferedImage)} to let the player
     * see the updated map image.
     *
     * @param player the player to send the map to
     * @throws IllegalStateException if the map image is not set
     */
    void sendToPlayer(Player player);
}
