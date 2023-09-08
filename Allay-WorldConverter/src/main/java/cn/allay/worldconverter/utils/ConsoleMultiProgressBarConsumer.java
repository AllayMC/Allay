package cn.allay.worldconverter.utils;

import me.tongfei.progressbar.ConsoleProgressBarConsumer;

import java.io.PrintStream;

/**
 * @author Cool_Loong
 */
public class ConsoleMultiProgressBarConsumer extends ConsoleProgressBarConsumer {
    final PrintStream out;
    final boolean first;

    public ConsoleMultiProgressBarConsumer(PrintStream out, boolean isFirst) {
        super(out);
        this.out = out;
        this.first = isFirst;
    }

    public ConsoleMultiProgressBarConsumer(PrintStream out, int maxRenderedLength, boolean isFirst) {
        super(out, maxRenderedLength);
        this.out = out;
        this.first = isFirst;
    }

    @Override
    public void accept(String str) {
        if (first) {
            out.print('\n');
            out.print("-".repeat(this.getMaxRenderedLength()));
        }
        out.print("\r\n");
        super.accept(str);
    }
}
