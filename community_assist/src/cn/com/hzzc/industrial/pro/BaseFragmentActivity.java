package cn.com.hzzc.industrial.pro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import cn.com.hzzc.industrial.pro.util.ActivityCollector;

public class BaseFragmentActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
	}
}
