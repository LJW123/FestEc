package ljw123.github.io.latte.net;

import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author xiaofo on 2018/6/22.
 */

public interface RestService {
    /**
     * GET方法请求
     *
     * @param url
     * @param params
     * @return
     */
    @GET
    Call<String> get(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    /**
     * PUT请求
     *
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

    /**
     * DELETE请求
     *
     * @param url
     * @param params
     * @return
     */
    @GET
    Call<String> delete(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    /**
     * 下载
     *
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    /**
     * 上传
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);

}
