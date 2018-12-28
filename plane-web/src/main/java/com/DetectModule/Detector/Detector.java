package com.DetectModule.Detector;

public class Detector {
    public static final String LibPath = "/home/gxdx/temp/darknet-master";
    public static final String CudaLibPath = "/usr/local/cuda-10.0/lib64";

    static {
        System.load(CudaLibPath + "/" + "libcublas.so.10.0");
        System.load(CudaLibPath + "/" + "libcudart.so.10.0");
        System.load(CudaLibPath + "/" + "libcudnn.so.7");
        System.load(CudaLibPath + "/" + "libcurand.so.10.0");
        System.load(CudaLibPath + "/" + "libcusolver.so.10.0");
        System.load(LibPath + "/" + "darknet.so");
    }

    public static void main(String[] args) {
        Detector detector = new Detector();
        detector.run("/home/gxdx/test", "/home/gxdx/result");
    }

    public native void run(String loadPath, String savePath);
}


