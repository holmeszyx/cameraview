package com.google.android.cameraview;

/**
 * to intercept camera size chooser
 * @author holmes
 */
public interface ObtainSizesInterceptor {

    class DefaultPolicy implements ObtainSizesInterceptor {

        @Override
        public boolean filterPreviewSize(int desiredWidth, int desiredHeight, Size size) {
            return false;
        }

        @Override
        public boolean filterPictureSize(int desiredWidth, int desiredHeight, Size picSize) {
            return false;
        }

        @Override
        public void onPreviewSizeConfirmed(Size size) {

        }

        @Override
        public void onPictureSizeConfirmed(Size pictureSize) {

        }
    }

    /**
     * choose a preview size match expectable width an height
     *
     * @param desiredWidth
     * @param desiredHeight
     * @param size
     * @return true to choose the size and break; false to ignore this size
     */
    boolean filterPreviewSize(int desiredWidth, int desiredHeight, Size size);

    /**
     * choose a output picture size match expectable width an height
     *
     * @param desiredWidth
     * @param desiredHeight
     * @param picSize
     * @return true to choose the size and break; false to ignore this size
     */
    boolean filterPictureSize(int desiredWidth, int desiredHeight, Size picSize);

    /**
     * the final preview size
     * @param size
     */
    void onPreviewSizeConfirmed(Size size);

    /**
     * the final picture size
     * @param pictureSize
     */
    void onPictureSizeConfirmed(Size pictureSize);


}

