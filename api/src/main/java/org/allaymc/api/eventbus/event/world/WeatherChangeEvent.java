package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.Weather;

/**
 * {@code WeatherChangeEvent} carries the previous weather and a proposed
 * replacement for a world. Listeners can cancel this event before the world
 * setter stores and broadcasts the requested weather; the event exposes both
 * values without a replacement setter.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class WeatherChangeEvent extends WorldEvent implements CancellableEvent {
    /**
     * Weather before the proposed change.
     */
    protected Weather oldWeather;
    /**
     * Weather requested by the world setter.
     */
    protected Weather newWeather;

    /**
     * Creates a weather proposal retaining the old and requested weather
     * values.
     */
    public WeatherChangeEvent(World world, Weather oldWeather, Weather newWeather) {
        super(world);
        this.oldWeather = oldWeather;
        this.newWeather = newWeather;
    }
}
