package cn.com.hzzc.industrial.pro.opsinterface;

/**
 * @todo 选项操作接口
 * @author pang
 *
 */
public interface IQuestionItemOperator {

	/**
	 * 
	 * @param index
	 * @param id
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:编辑
	 * @return:void
	 */
	public void edit(int index, String id);

	/**
	 * 
	 * @param index
	 * @param id
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:删除
	 * @return:void
	 */
	public void del(int index, String id);
}
