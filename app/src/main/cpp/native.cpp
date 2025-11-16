#include <jni.h>
#include <opencv2/opencv.hpp>

extern "C"
JNIEXPORT jbyteArray JNICALL
Java_com_example_opedge_NativeLib_processFrame(
        JNIEnv *env,
        jobject thiz,
        jbyteArray data,
        jint width,
        jint height) {

    // Convert input byte array to cv::Mat (grayscale)
    jbyte *inputBytes = env->GetByteArrayElements(data, nullptr);

    cv::Mat yuv(height + height / 2, width, CV_8UC1, inputBytes);
    cv::Mat bgr;
    cv::cvtColor(yuv, bgr, cv::COLOR_YUV2BGR_NV21);

    // Apply Canny edge detection
    cv::Mat edges;
    cv::Canny(bgr, edges, 100, 200);

    // Convert result back to byte array
    jbyteArray output = env->NewByteArray(edges.total());
    env->SetByteArrayRegion(output, 0, edges.total(), reinterpret_cast<jbyte*>(edges.data));

    env->ReleaseByteArrayElements(data, inputBytes, 0);
    return output;
}
