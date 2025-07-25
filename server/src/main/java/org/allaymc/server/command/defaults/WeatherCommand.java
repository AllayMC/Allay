package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.Weather;

/**
 * @author daoge_cmd
 */
public class WeatherCommand extends SimpleCommand {

    public WeatherCommand() {
        super("weather", TrKeys.M_COMMANDS_WEATHER_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .enumClass("weather", Weather.class)
                .exec(context -> {
                    Weather weather = context.getResult(0);
                    if (weather == Weather.CLEAR) {
                        context.getSender().getCommandExecuteLocation().dimension().getWorld().clearWeather();
                    } else {
                        context.getSender().getCommandExecuteLocation().dimension().getWorld().addWeather(weather);
                    }
                    context.addOutput(switch (weather) {
                        case CLEAR -> TrKeys.M_COMMANDS_WEATHER_CLEAR;
                        case RAIN -> TrKeys.M_COMMANDS_WEATHER_RAIN;
                        case THUNDER -> TrKeys.M_COMMANDS_WEATHER_THUNDER;
                    });
                    return context.success();
                })
                .root()
                .key("query")
                .exec(context -> {
                    var weathers = context.getSender().getCommandExecuteLocation().dimension().getWorld().getWeathers();
                    var currentWeather = Weather.CLEAR;
                    if (weathers.contains(Weather.THUNDER)) {
                        currentWeather = Weather.THUNDER;
                    } else if (weathers.contains(Weather.RAIN)) {
                        currentWeather = Weather.RAIN;
                    }

                    context.getSender().sendTr(TrKeys.M_COMMANDS_WEATHER_QUERY, currentWeather.name().toLowerCase());
                    return context.success();
                });
    }
}
