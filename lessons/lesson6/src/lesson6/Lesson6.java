package lesson6;

import okhttp3.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Lesson6 {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.weather.yandex.ru")
                .addPathSegment("v2")
                .addPathSegment("forecast")
                .addQueryParameter("lat", "59.9386")
                .addQueryParameter("lon", "30.3141")
                .addQueryParameter("lang", "ru_RU")
                .addQueryParameter("limit", "5")
                .build();
        System.out.println(url.toString());

        Request requestHttps = new Request.Builder()
                .url(url)
                .addHeader("X-Yandex-API-Key", "3e15d44a-61f1-480f-a8da-7464bce04573")
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(requestHttps).execute();
        String body = response.body().string();
        System.out.println(body);
    }
}


