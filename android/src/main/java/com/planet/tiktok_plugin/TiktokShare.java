package com.planet.tiktok_plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.bytedance.sdk.open.tiktok.TikTokOpenApiFactory;
import com.bytedance.sdk.open.tiktok.TikTokOpenConfig;
import com.bytedance.sdk.open.tiktok.api.TikTokOpenApi;
import com.bytedance.sdk.open.tiktok.base.ImageObject;
import com.bytedance.sdk.open.tiktok.base.MediaContent;
import com.bytedance.sdk.open.tiktok.base.MicroAppInfo;
import com.bytedance.sdk.open.tiktok.share.Share;

import java.io.File;
import java.util.ArrayList;

import io.flutter.plugin.common.MethodChannel;

public class TiktokShare {

    private final Activity activity;

    public TiktokShare(Activity activity) {
        this.activity = activity;
        String clientKey = "aw55m62uzlzree2t";
        TikTokOpenApiFactory.init(new TikTokOpenConfig(clientKey));
    }

    void sharePhotos(MethodChannel.Result result, ArrayList<String> images) {
        TikTokOpenApi tiktokOpenApi = TikTokOpenApiFactory.create(activity);
        if (tiktokOpenApi.isShareSupportFileProvider() &&
                android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ArrayList<String> imagePaths = new ArrayList<>();
            for (String image: images) {
                imagePaths.add(getFileUri(activity.getApplicationContext(), image));
            }
            Share.Request request = new Share.Request();
            ImageObject imageObject = new ImageObject();
            imageObject.mImagePaths = imagePaths;
            MediaContent mediaContent = new MediaContent();
            mediaContent.mMediaObject = imageObject;
            ArrayList<String> hashTagList = new ArrayList<String>();
            hashTagList.add("Zervoapp");
            request.mHashTagList = hashTagList;
            MicroAppInfo microAppInfo = new MicroAppInfo();
            microAppInfo.setAppId("com.planet.pinponapp");
            microAppInfo.setAppTitle("Zervo");
            microAppInfo.setAppUrl("https://www.zervo.me");
            microAppInfo.setDescription("Avatar Party x Anime Roleplay");
            request.mMicroAppInfo = microAppInfo;
            request.callerLocalEntry = "com.planet.pinponapp.MainActivity";
            request.mMediaContent = mediaContent;
            request.callerLocalEntry = "com.planet.pinponapp.MainActivity";
            result.success(tiktokOpenApi.share(request));
        } else {
//            Toast.makeText(TestFileProviderActivity.this, "Unsupported version", Toast.LENGTH_LONG).show();
        }
    }

    public String getFileUri(Context context, String filePath) {
        try {
//            String filePath = context.getExternalFilesDir(null) + "/shareData/" + relativePath;
            File file = new File(filePath);
            // The package name here must be consistent with what was provided in the AndroidManifest.xml's authorities.
            Uri contentUri = FileProvider.getUriForFile(context,
                    "com.planet.pinponapp.fileprovider", file);
            // Authorize permission for TikTok build variants
            context.grantUriPermission("com.zhiliaoapp.musically",
                    contentUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.grantUriPermission("com.ss.android.ugc.trill",
                    contentUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            return contentUri.toString(); // contentUri.toString() should be prefixed with "content://"

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    /// 是否安装了tiktok
    public  boolean isInstallTikTok(){
        TikTokOpenApi tiktokOpenApi = TikTokOpenApiFactory.create(activity);
        return tiktokOpenApi.isAppInstalled();
    }
}
