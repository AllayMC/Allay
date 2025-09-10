package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.Weather;

import java.util.Set;

/**
 * @author daoge_cmd
 */
@Getter
public class WeatherChangeEvent extends WorldEvent implements CancellableEvent {
    protected Set<Weather> oldWeathers;
    protected Set<Weather> newWeathers;

    public WeatherChangeEvent(World world, Set<Weather> oldWeathers, Set<Weather> newWeathers) {
        super(world);
        this.oldWeathers = oldWeathers;
        this.newWeathers = newWeathers;
    }
}
