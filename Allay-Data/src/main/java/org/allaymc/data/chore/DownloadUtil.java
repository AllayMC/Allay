package org.allaymc.data.chore;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@UtilityClass
public class DownloadUtil {
    @SneakyThrows
    public void download(String url, String into) {
        download(null, null, url, into);
    }

    @SneakyThrows
    public void download(String proxyHost, Integer proxyPort, String url, String into) {
        var builder = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS);
        if (proxyHost != null) {
            builder.proxy(ProxySelector.of(new InetSocketAddress(proxyHost, proxyPort)));
        }
        var client = builder.build();
        Path path = Path.of(into);
        Files.deleteIfExists(path);
        var resp = client.send(HttpRequest.newBuilder().uri(URI.create(url)).
                header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
                .GET().build(), HttpResponse.BodyHandlers.ofFile(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
        System.out.println(resp.statusCode());
    }

    @SneakyThrows
    public InputStream downloadAsStream(String url) {
        return downloadAsStream(null, null, url);
    }

    @SneakyThrows
    public InputStream downloadAsStream(String proxyHost, Integer proxyPort, String url) {
        var builder = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS);
        if (proxyHost != null) {
            builder.proxy(ProxySelector.of(new InetSocketAddress(proxyHost, proxyPort)));
        }
        var client = builder.build();
        var resp = client.send(HttpRequest.newBuilder().uri(URI.create(url)).
                header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
                .GET().build(), HttpResponse.BodyHandlers.ofInputStream());
        if (resp.statusCode() == 200) {
            System.out.println("Download success " + url);
            return resp.body();
        } else {
            System.out.println("Download error " + url);
            return null;
        }
    }
}
