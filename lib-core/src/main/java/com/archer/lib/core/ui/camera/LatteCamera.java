package com.archer.lib.core.ui.camera;

import android.net.Uri;

import com.archer.lib.core.delegates.PermissionCheckerDelegate;
import com.archer.lib.core.util.file.FileUtil;

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
