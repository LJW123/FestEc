package ljw123.github.io.latte.net;


import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import ljw123.github.io.latte.BuildConfig;
import ljw123.github.io.latte.app.ConfigType;
import ljw123.github.io.latte.app.Latte;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author xiaofo on 2018/6/22.
 */

public class RestCreator {
    public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    public static WeakHashMap<String, Object> getParams() {
        return PARAMS;
    }

    public static final class OkHttpHolder {
        public static final int TIMEOUT = 60;
        private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);
        public static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public static final class RetrofitHolder {
        public static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        public static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static final class RestServiceHolder {
        public static final RestService REST_SERVICE = RetrofitHolder.RETROFIT.create(RestService.class);
    }
}
