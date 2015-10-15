package cn.com.hzzc.industrial.pro;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.jpush.android.api.JPushInterface;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class GloableApplication extends Application {
	private static final String TAG = "JPush";

	private static String userId = "";
	/**
	 * 当前上下文环境
	 */
	private static Context context;

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
		context = getApplicationContext();
		initJPush();
		initImageLoader();
	}

	public void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getContext())
				.memoryCacheExtraOptions(480, 800)
				// 本地缓存的详细信息(缓存的最大长宽)
				.diskCacheExtraOptions(480, 800, null)
				// default 线程池内加载的数量
				.threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				.denyCacheImageMultipleSizesInMemory()
				// 可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				// 内存缓存的最大值
				.memoryCacheSizePercentage(13)
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
				.diskCache(getImageDiskCache())
				.diskCacheSize(50 * 1024 * 1024)
				.defaultDisplayImageOptions(getDisplayImageOption())
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
				.writeDebugLogs().build();// 创建
		ImageLoader.getInstance().init(config);
	}

	public void initJPush() {
		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
		JPushInterface.init(this); // 初始化 JPush
	}

	/**
	 * 
	 * @tags @return
	 * @date 2015年5月5日
	 * @todo 获取当前上下文环境
	 * @author pang
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * 
	 * @return
	 * @user:pang
	 * @data:2015年8月13日
	 * @todo:实例化imageloader硬盘缓存具体类
	 * @return:UnlimitedDiscCache
	 */
	public static UnlimitedDiscCache getImageDiskCache() {
		File p = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
		String parent = p.getParent();
		/**
		 * 手机图片缓存路径
		 */
		File cacheDir = new File(parent + "/"
				+ SystemConst.mobile_local_dir_for_pic);
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		return new UnlimitedDiscCache(cacheDir);
	}

	/**
	 * 
	 * @tags @return
	 * @date 2015年5月19日
	 * @todo 获取加载图片时候的配置
	 * @author pang
	 */
	public static DisplayImageOptions getDisplayImageOption() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(false)
				// default 设置图片在加载前是否重置、复位
				.delayBeforeLoading(1000)
				// 下载前的延迟时间
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)
				// 设置下载的图片是否缓存在SD卡中
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				// 设置图片以如何的编码方式显示、
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565)
				// default 设置图片的解码类型
				// .displayer(new RoundedBitmapDisplayer(5)) // 设置成圆角图片
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.considerExifParams(true).handler(new Handler()) // default
				.build();
		return options;
	}
}
