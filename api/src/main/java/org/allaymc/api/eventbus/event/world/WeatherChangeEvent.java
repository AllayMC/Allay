package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.Weather;

/**
 * @author daoge_cmd
 */
@Getter
public class WeatherChangeEvent extends WorldEvent implements CancellableEvent {
    protected Weather oldWeather;
    protected Weather newWeather;

    public WeatherChangeEvent(World world, Weather oldWeather, Weather newWeather) {
        super(world);
        this.oldWeather = oldWeather;
        this.newWeather = newWeather;
    }
}
