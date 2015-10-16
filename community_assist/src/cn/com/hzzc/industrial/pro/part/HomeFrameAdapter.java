package cn.com.hzzc.industrial.pro.part;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @todo fragment适配器
 * @author pang
 *
 */
public class HomeFrameAdapter extends FragmentPagerAdapter {

	List<Fragment> list;

	public HomeFrameAdapter(FragmentManager fragmentManager, List<Fragment> lst) {
		super(fragmentManager);
		this.list = lst;

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Fragment getItem(int i) {
		return list.get(i);
	}

	@Override
	public int getItemPosition(Object index) {
		return super.getItemPosition(index);
	}

}