package org.allaymc.server.plugin.js;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.Plugin;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
@Slf4j
public class JsPlugin implements Plugin {

    protected Context jsContext;

    public JsPlugin(String entranceSource) {
        jsContext = Context.newBuilder("js")
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true)
                .build();
        jsContext.eval("js", entranceSource);
        initGlobalMembers();
    }

    protected void initGlobalMembers() {
        var binding = jsContext.getBindings("js");
        binding.putMember("plugin", this);
        binding.putMember("log", log);
    }

    @Override
    public void onLoad() {
        tryCallJsFunction("onLoad");
    }

    @Override
    public void onEnable() {
        tryCallJsFunction("onEnable");
    }

    @Override
    public void onDisable() {
        tryCallJsFunction("onDisable");
    }

    protected void tryCallJsFunction(String onLoad) {
        var binding = jsContext.getBindings("js");
        var func = binding.getMember(onLoad);
        if (func.canExecute())
            func.executeVoid();
    }
}
