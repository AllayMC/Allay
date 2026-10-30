package org.allaymc.api.item.component;

import org.allaymc.api.player.Player;
import org.allaymc.api.world.Dimension;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

/**
 * {@code ItemFilledMapBaseComponent} exposes a filled map's identifier and
 * mutable image, including terrain rendering and delivery to players.
 * Rendering stores its completed image on the item, while callers can retrieve
 * or replace the image before sending another update.
 *
 * @author daoge_cmd
 */
public interface ItemFilledMapBaseComponent extends ItemBaseComponent {
    /**
     * Returns the currently stored map image, or {@code null} when absent. The
     * returned image is shared with the component, so pixel changes also
     * affect later map delivery and serialization.
     */
    BufferedImage getImage();

    /**
     * Replaces the stored image with a version scaled to 128 by 128 pixels.
     * The server implementation rejects {@code null}; an exact-size source is
     * stored before allocation, so a later allocation failure can leave that
     * source as the current image.
     */
    void setImage(BufferedImage image);

    /**
     * Returns the identifier used when sending this map image.
     */
    long getMapId();

    /**
     * Renders and stores a map using one block per image pixel. This overload
     * passes a zoom of {@code 1} to the full rendering operation and returns
     * its completion future.
     */
    default CompletableFuture<BufferedImage> renderMap(Dimension dimension, int x, int z) {
        return this.renderMap(dimension, x, z, 1);
    }

    /**
     * Renders terrain beginning at the supplied block coordinates and stores
     * the completed image. {@code zoom} selects the block spacing between
     * samples; a zoom of {@code 0} skips sampling, while asynchronous
     * rendering failures produce a logged transparent replacement.
     */
    CompletableFuture<BufferedImage> renderMap(Dimension dimension, int startX, int startZ, int zoom);

    /**
     * Sends the currently stored map image to the supplied player. Callers can
     * send again after changing the image; an absent image causes
     * {@link IllegalStateException} before map data is sent.
     */
    void sendToPlayer(Player player);
}
