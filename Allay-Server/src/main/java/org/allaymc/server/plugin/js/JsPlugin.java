package org.allaymc.server.plugin.js;

import org.allaymc.api.plugin.Plugin;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class JsPlugin implements Plugin {

    protected Context jsContext;

    public JsPlugin(String entranceSource) {
        jsContext = Context.newBuilder("js")
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true)
                .build();
        jsContext.eval("js", entranceSource);
    }

    @Override
    public void onLoad() {
        jsContext.getBindings("js").invokeMember("onLoad");
    }

    @Override
    public void onEnable() {
        jsContext.getBindings("js").invokeMember("onEnable");
    }

    @Override
    public void onDisable() {
        jsContext.getBindings("js").invokeMember("onDisable");
    }
}
