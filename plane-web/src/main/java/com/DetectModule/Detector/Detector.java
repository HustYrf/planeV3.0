package com.DetectModule.Detector;


public class Detector {
	
	public static final String LibPath = "D:/PlaneGit/planeV3.0/plane-web";
	
	public native void run(String loadPath, String savePath);
	
	public native void log(int line);
	
	public native void log(int from, int to);
	
	public native void logAll();
	
	public native String getLog(int line);
	
	public native String getAllLog();
	
	static {
		System.load(LibPath + "/" + "opencv_world320.dll");
		System.load(LibPath + "/" + "Detector.dll");
	}
	
	public static void main(String[] args) {
		Detector detector = new Detector();
		detector.run("D:/source","D:/alarm");
		detector.logAll();
	}
}

