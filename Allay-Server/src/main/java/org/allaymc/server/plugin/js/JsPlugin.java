package org.allaymc.server.plugin.js;

import lombok.SneakyThrows;
import org.allaymc.api.plugin.Plugin;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.io.IOAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class JsPlugin extends Plugin {

    protected File entranceFile;
    protected Context jsContext;
    protected Value jsExport;
    protected Logger log;

    public JsPlugin(File entranceFile) {
        this.entranceFile = entranceFile;
    }

    @SneakyThrows
    @Override
    public void onLoad() {
        log = LoggerFactory.getLogger(pluginContainer.descriptor().getName());
        jsContext = Context.newBuilder("js")
                .allowIO(IOAccess.ALL)
                .allowAllAccess(true)
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLoading(true)
                .allowHostClassLookup(className -> true)
                .option("js.esm-eval-returns-exports", "true")
                .build();
        initGlobalMembers();
        jsExport = jsContext.eval(
                Source.newBuilder("js", entranceFile)
                        .name(entranceFile.getName())
                        .mimeType("application/javascript+module")
                        .build()
        );
        tryCallJsFunction("onLoad");
    }

    protected void initGlobalMembers() {
        var binding = jsContext.getBindings("js");
        binding.putMember("plugin", this);
        binding.putMember("console", log);
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
        var func = jsExport.getMember(onLoad);
        if (func.canExecute())
            func.executeVoid();
    }
}
