package cn.com.hzzc.industrial.pro;

import android.app.Application;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

public class GloableApplication extends Application {
	private static final String TAG = "JPush";

	private static String userId = "";

	public static void setLoginUserId(String str) {
		userId = str;
	}

	public static String getUserId() {
		return userId;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "[ExampleApplication] onCreate");
		super.onCreate();

		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
		JPushInterface.init(this); // 初始化 JPush
	}

}
