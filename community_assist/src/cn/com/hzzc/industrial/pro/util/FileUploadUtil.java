package cn.com.hzzc.industrial.pro.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;

/**
 * 
 * @author pang
 * @todo 文件上传
 *
 */
public class FileUploadUtil {

	public static final String SUCCESS = "1";
	public static final String FAILURE = "0";

	public static String uploadFileAndStringPairCompressed(String actionUrl,
			Map<String, String> params, List<File> files) {
		HttpURLConnection conn = null;
		try {
			String BOUNDARY = java.util.UUID.randomUUID().toString();
			String PREFIX = "--";
			String LINEND = "\r\n";
			String MULTIPART_FORM_DATA = "multipart/form-data";
			String CHARSET = "UTF-8";
			URL uri = new URL(actionUrl);
			conn = (HttpURLConnection) uri.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
					+ ";boundary=" + BOUNDARY);
			DataOutputStream outStream = new DataOutputStream(
					conn.getOutputStream());
			if (params != null && !params.isEmpty()) {
				StringBuilder textEntity = new StringBuilder("\r\n");
				for (Map.Entry<String, String> entry : params.entrySet()) {// 分别上传文本字段
					textEntity.append("--");
					textEntity.append(BOUNDARY);
					textEntity.append("\r\n");
					textEntity.append("Content-Disposition: form-data; name=\""
							+ entry.getKey() + "\"\r\n\r\n");
					textEntity.append(entry.getValue());
					textEntity.append("\r\n");
				}
				textEntity.append("\r\n");
				outStream.write(textEntity.toString().getBytes());
			}
			if (files != null && files.size() > 0) {
				for (int i = 0; i < files.size(); i++) {
					File f = files.get(i);
					StringBuilder sb1 = new StringBuilder("");
					sb1.append(PREFIX);
					sb1.append(BOUNDARY);
					sb1.append(LINEND);
					sb1.append("Content-Disposition:form-data;name=\"file" + i
							+ "\";filename=\"" + f.getName() + "\"" + LINEND);
					sb1.append("Content-Type:application/octet-stream;charset="
							+ CHARSET + LINEND);
					sb1.append(LINEND);
					outStream.write(sb1.toString().getBytes());
					// *************处理文件开
					String filePath = f.getPath();
					//
					// // 尺寸压缩
					// Bitmap bm = thumbnailImg(filePath);
					// saveMyBitmap("aaaa", bm);
					// 质量压缩
					// Bitmap bm2 = scaleLoad(filePath);
					Bitmap bm2 = BitmapLoader.getBitmapFromFile(filePath, 720,
							1280);
					// 上传压缩信息
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bm2.compress(Bitmap.CompressFormat.JPEG, 60, stream);

					InputStream is = new ByteArrayInputStream(
							stream.toByteArray());
					// ************处理文件结束
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						outStream.write(buffer, 0, len);
					}
					is.close();
					outStream.write(LINEND.getBytes());
				}
			}

			byte[] end_data = ((PREFIX + BOUNDARY + PREFIX + LINEND))
					.getBytes();
			outStream.write(end_data);
			outStream.flush();
			int res = conn.getResponseCode();
			if (res == 200) {
				return SUCCESS;
			} else {
				return FAILURE;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return SUCCESS;
	}

}
