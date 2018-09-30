package com.DetectModule.Detector;

public class Detector {
	public static final String LibPath = "/home/gxdx_ai/linux";
	
	public native void run(String loadPath, String savePath);
	
	public native void log(int line);
	
	public native void log(int from, int to);
	
	public native void logAll();
	
	public native String getLog(int line);
	
	public native String getAllLog();
	
	static {
		System.load(LibPath + "/" + "libopencv_core.so.3.2.0");
		System.load(LibPath + "/" + "libopencv_imgcodecs.so.3.2.0");
		System.load(LibPath + "/" + "libopencv_highgui.so.3.2.0");
		System.load(LibPath + "/" + "libdetector.so");	
	}
	
	public static void main(String[] args) {
		Detector detector = new Detector();
		detector.run("/home/leafwaltz/opencvtest", "/home/leafwaltz/opencvtest/fuck");
		detector.logAll();
	}
}

