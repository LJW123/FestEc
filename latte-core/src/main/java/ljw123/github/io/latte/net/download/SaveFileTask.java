package ljw123.github.io.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.File;
import java.io.InputStream;

import ljw123.github.io.latte.app.Latte;
import ljw123.github.io.latte.net.callback.IRequest;
import ljw123.github.io.latte.net.callback.ISuccess;
import ljw123.github.io.latte.util.FileUtil;
import okhttp3.ResponseBody;

/**
 * @author xiaofo on 2018/6/24.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    public static final String APK_EXTENSION = "apk";
    private final ISuccess SUCESS;
    private final IRequest REQUEST;

    /**
     * 目前只传递 request和Success做简单处理 后续可以根据需求添加
     *
     * @param success
     * @param request
     */
    public SaveFileTask(ISuccess success, IRequest request) {
        this.SUCESS = success;
        this.REQUEST = request;
    }

    @Override
    protected File doInBackground(Object[] objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody responseBody = (ResponseBody) objects[2];
        InputStream is = responseBody.byteStream();
        if (TextUtils.isEmpty(downloadDir)) {
            downloadDir = "down_loads";
        }
        if (TextUtils.isEmpty(extension)) {
            extension = "";
        }
        String name = (String) objects[3];
        if (TextUtils.isEmpty(name)) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCESS != null) {
            SUCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals(APK_EXTENSION)) {
            Intent install = new Intent();
            install.setAction(Intent.ACTION_VIEW);
            Uri uri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                uri = FileProvider.getUriForFile(Latte.getApplicationContext(),
                        "ljw123.github.io.latte.myfileprovider", file);
            } else {
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                uri = Uri.fromFile(file);
            }
            install.setDataAndType(uri, "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
