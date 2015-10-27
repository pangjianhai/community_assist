package cn.com.hzzc.industrial.pro.opsinterface;

import cn.com.hzzc.industrial.pro.entity.TopicPostEntity;

/**
 * @todo 主题评论列表监听器
 * @author pang
 *
 */
public interface ITopicCommentListener {

	/**
	 * @param index
	 * @param tpe
	 * @user:pang
	 * @data:2015年9月10日
	 * @todo:点赞
	 * @return:void
	 */
	public void addGood(int index, TopicPostEntity tpe);

	/**
	 * @param index
	 * @param tpe
	 * @user:pang
	 * @data:2015年9月10日
	 * @todo:查看下详情
	 * @return:void
	 */
	public void detailShow(int index, TopicPostEntity tpe);

	/**
	 * 
	 * @param index
	 * @param tpe
	 * @user:pang
	 * @data:2015年9月10日
	 * @todo:查看详情
	 * @return:void
	 */
	public void userShow(int index, TopicPostEntity tpe);

	/**
	 * 
	 * @param index
	 * @param tpe
	 * @user:pang
	 * @data:2015年9月10日
	 * @todo:转发到第三平台
	 * @return:void
	 */
	public void to3Platform(int index, TopicPostEntity tpe);
}
