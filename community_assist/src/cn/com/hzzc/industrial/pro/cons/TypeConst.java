package cn.com.hzzc.industrial.pro.cons;

/**
 * @todo 业务常量
 * @author pang
 *
 */
public interface TypeConst {

	/**
	 * @todo活动类型常量
	 * @author pang
	 *
	 */
	public interface ActTypeConst {
		// 下线活动
		public static final String TYPE_ACTIVITY_OFFLINE = "0";
		// 调查问卷
		public static final String TYPE_ACTIVITY_QUESTION = "1";
		// 统计活动
		public static final String TYPE_ACTIVITY_STATICTICS = "2";
		// 优惠活动
		public static final String TYPE_ACTIVITY_YOUHUI = "3";
	}
}
