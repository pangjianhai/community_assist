package cn.com.hzzc.industrial.pro.opsinterface;

import cn.com.hzzc.industrial.pro.entity.ActivityEntity;

/**
 * @TODO 为点击活动添加事件
 * @author pang
 *
 */
public interface IActivityOperator {

	/**
	 * @param bean
	 * @user:pang
	 * @data:2015年10月15日
	 * @todo:添加点击事件
	 * @return:void
	 */
	public void clickAct(ActivityEntity bean);
}
