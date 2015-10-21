package cn.com.hzzc.industrial.pro.cons;

public interface SystemConst {

	public static final String json_param_name = "para";
	public static final String share_doc_name = "hzzc_soft_android_dir";

	public static final String mobile_local_dir = "G_INDUSTRIAL";

	/**
	 * 图片缓存地址
	 */
	public static final String mobile_local_dir_for_pic = mobile_local_dir
			+ "/PIC_COLLECTION";

	public static final String server_url = "http://192.168.0.105:8080/IotApp/";

	/**
	 * 
	 * @author pang
	 * @todo URL常量
	 *
	 */
	public class Type2Url {
		/**
		 * 保存类型2的活动
		 */
		public static final String save_act_type_2 = "commController/addCommenActivity.do";
		public static final String addQuestionItem = "commController/addQuestionItem.do";
		public static final String queryQuestionItemByquestionId = "commController/queryQuestionItemByquestionId.do";
		public static final String editQuestionItem = "commController/editQuestionItem.do";
		public static final String delQuestionItem = "commController/delQuestionItem.do";
		public static final String queryCommenActivityByIdForQuestionDetailIdByType = "commController/queryCommenActivityByIdForQuestionDetailIdByType.do";

		public static final String queryCommenActivityBysocietyId = "commController/queryCommenActivityBysocietyId.do";
	}

	public class Type3Url {
		/**
		 * 添加统计条目
		 */
		public static final String addActivityStatisticItem = "commController/addActivityStatisticItem.do";
		/**
		 * 根据统计ID获取统计项目
		 */
		public static final String queryActivityStatisticItemByStatisticId = "commController/queryActivityStatisticItemByStatisticId.do";
	}
}
