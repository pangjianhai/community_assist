package cn.com.hzzc.industrial.pro;

import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public abstract class ParentActFragment extends Fragment {

	public int tag;
	public View mMainView;
	private static final int DEFAULT_TAG = -1;
	/**
	 * 用户ID
	 */
	public String userId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 获取用户ID
		 */
		userId = GloableApplication.getUserId();
		Bundle b = getArguments();
		tag = (null == b ? DEFAULT_TAG : b.getInt("tag"));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	/**
	 * 
	 * @return
	 * @user:pang
	 * @data:2015年7月20日
	 * @todo:是否登录使用APP
	 * @return:boolean
	 */
	public boolean isLogin() {
		if (userId == null || "".equals(userId)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @tags @param contenxt
	 * @tags @return
	 * @date 2015年5月28日
	 * @todo 判断是否可以联网
	 * @author pang
	 */
	public boolean isNetWorkConnected() {
		ConnectivityManager cm = (ConnectivityManager) GloableApplication
				.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni != null && ni.isAvailable()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param url
	 *            地址
	 * @param p
	 *            参数
	 * @param rcb
	 *            回调函数
	 * @user:pang
	 * @data:2015年7月13日
	 * @todo:发送普通的POST http请求
	 * @return:void
	 */
	public void send_normal_request(String url, Map<String, String> p,
			RequestCallBack<?> rcb) {
		if (!isNetWorkConnected()) {
			Toast.makeText(GloableApplication.getContext(), "没有网络咯！",
					Toast.LENGTH_SHORT).show();
			return;
		}
		RequestParams params = new RequestParams();
		if (p != null) {
			Iterator<Map.Entry<String, String>> it = p.entrySet().iterator();
			/**
			 * 添加参数
			 */
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				params.addBodyParameter(entry.getKey(), entry.getValue());
			}
		}
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, url, params, rcb);
	}

	/**************************************** 关于非登录用户需要提示的popwindow ********************************************/

}
