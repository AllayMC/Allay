package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
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
                .enums("weather", Weather.class)
                .exec(context -> {
                    Weather weather = Weather.valueOf(((String)context.getResult(0)).toUpperCase());
                    if (weather == Weather.CLEAR) {
                        context.getSender().getCmdExecuteLocation().dimension().getWorld().clearWeather();
                    } else {
                        context.getSender().getCmdExecuteLocation().dimension().getWorld().addWeather(weather);
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
                    var weathers = context.getSender().getCmdExecuteLocation().dimension().getWorld().getWeathers();
                    if (weathers.contains(Weather.CLEAR)) {
                        context.getSender().sendTr(TrKeys.M_COMMANDS_WEATHER_QUERY, "clear");
                        return context.success();
                    }
                    if (weathers.contains(Weather.THUNDER)) {
                        context.getSender().sendTr(TrKeys.M_COMMANDS_WEATHER_QUERY, "rain and thunder");
                        return context.success();
                    }
                    context.getSender().sendTr(TrKeys.M_COMMANDS_WEATHER_QUERY, "rain");
                    return context.success();
                });
    }
}
