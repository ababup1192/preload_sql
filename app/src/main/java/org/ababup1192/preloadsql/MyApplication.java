package org.ababup1192.preloadsql;

import android.app.Application;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Androidが起動した時に動くクラス, AndroidManifestのApplicationタグにAndroid:nameを追記
public class MyApplication extends Application {
    public final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        /** Called when the Application-class is first created. */
        Log.d(TAG, "--- onCreate() in ---");

        /*
            データベース初期化処理をここに書く。
         */

        // src/main/assets にファイルを置く。
        String fileName = "hello.txt";
        List<String> strList = inputFile(fileName);
        for (String str : strList) {
            // strにSQL文もしくは、追加するデータ(CSVなど)が読み込まれている。
            /*
                データベースにinsert処理
             */
            Log.d(TAG, str);
        }
    }

    // ファイル読み込み関数
    public List<String> inputFile(String fileName) {
        List<String> strList = new ArrayList<String>();
        AssetManager asset = getResources().getAssets();
        try {
            InputStream fileInput = asset.open(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(fileInput));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                //ArrayListに追加
                strList.add(str);
            }
            fileInput.close();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return strList;
    }
}
