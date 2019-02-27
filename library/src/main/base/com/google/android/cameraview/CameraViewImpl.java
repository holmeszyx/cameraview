/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.cameraview;

import android.view.View;

import java.util.Set;

import androidx.annotation.NonNull;

abstract class CameraViewImpl {

    protected final Callback mCallback;

    protected final PreviewImpl mPreview;

    protected ObtainSizesInterceptor mSizesInterceptor;

    CameraViewImpl(Callback callback, PreviewImpl preview) {
        this(callback, preview, null);
    }

    CameraViewImpl(Callback callback, PreviewImpl preview, ObtainSizesInterceptor sizesInterceptor) {
        mCallback = callback;
        mPreview = preview;
        mSizesInterceptor = sizesInterceptor;
        checkSizeInterceptor();
    }

    View getView() {
        return mPreview.getView();
    }

    /**
     * @return {@code true} if the implementation was able to start the camera session.
     */
    abstract boolean start();

    abstract void stop();

    abstract boolean isCameraOpened();

    abstract void setFacing(int facing);

    abstract int getFacing();

    abstract Set<AspectRatio> getSupportedAspectRatios();

    /**
     * @return {@code true} if the aspect ratio was changed.
     */
    abstract boolean setAspectRatio(AspectRatio ratio);

    abstract AspectRatio getAspectRatio();

    abstract void setAutoFocus(boolean autoFocus);

    abstract boolean getAutoFocus();

    abstract void setFlash(int flash);

    abstract int getFlash();

    abstract void takePicture();

    abstract void setDisplayOrientation(int displayOrientation);

    interface Callback {

        void onCameraOpened();

        void onCameraClosed();

        void onPictureTaken(byte[] data);

    }

    void setSizesInterceptor(ObtainSizesInterceptor interceptor) {
        mSizesInterceptor = interceptor;
        checkSizeInterceptor();
    }

    public ObtainSizesInterceptor getSizesInterceptor() {
        return mSizesInterceptor;
    }

    /**
     * make sure that size interceptor is not null
     * @return if it's false, means the value is null, and make it to default.
     */
    protected boolean checkSizeInterceptor() {
        if (mSizesInterceptor == null) {
            mSizesInterceptor = new ObtainSizesInterceptor.DefaultPolicy();
            return false;
        }
        return true;
    }

}
