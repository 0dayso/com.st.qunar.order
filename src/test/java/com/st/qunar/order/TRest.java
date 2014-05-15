package com.st.qunar.order;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.fluent.Request;

public class TRest {

	public static void main(String[] args) throws IOException {
		String strUrl = "http://www.travelsky.com/tsky/searchFlight.jsp";

		// String ret = Request
		// .Post(strUrl)
		// .bodyForm(
		// Form.form().add("act", "query").add("org", "%E5%8C%97%E4%BA%AC")
		// .add("dst", "%E4%B8%8A%E6%B5%B7").add("date", "2014-05-15").add("randCode", "8REG")
		// .add("submit", "%E6%9F%A5%E8%AF%A2")
		// .add("airline", "%E8%88%AA%E7%A9%BA%E5%85%AC%E5%8F%B8").build()).execute()
		// .returnContent().asString();

		strUrl = "this.src='servlet/CallYanServlet?now=' + new Date().getTime()+'&title=nohome'";
		String ret = Request.Get(strUrl).execute().returnContent().asString();

		// String ret = sendHttpPostRequest(strUrl);

		System.out.println(ret);
	}

	public static String sendHttpPostRequest(String strUrl) {
		URL url;
		HttpURLConnection httpConn;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(strUrl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Set-Cookie", "JSESSIONID=BAD1A80DF975BBC37B4FBA36D9485D61.TSkyServer1");
			StringBuffer parament = new StringBuffer();
			// for (Map.Entry<String, String> entry : paramentMap.entrySet()) {
			// String key = entry.getKey();
			// if ((key == null) || "".equals(key)) {
			// parament.append(URLEncoder.encode(entry.getValue(), "utf-8") + "&");
			// } else {
			// parament.append(key + "=" + URLEncoder.encode(entry.getValue(), "utf-8") + "&");
			// }
			// }
			// if (parament.toString().endsWith("&")) {
			// parament.setLength(parament.length() - 1);
			// }
			parament.setLength(0);
			parament.append("act=query&org=%E5%8C%97%E4%BA%AC&dst=%E4%B8%8A%E6%B5%B7&date=2014-05-16&randCode=FAR9&submit=%E6%9F%A5%E8%AF%A2&airline=%E8%88%AA%E7%A9%BA%E5%85%AC%E5%8F%B8");
			httpConn.setRequestProperty("Content-Length", String.valueOf(parament.toString().getBytes().length));
			System.out.println(strUrl);
			System.out.println(parament.toString());
			httpConn.getOutputStream().write(parament.toString().getBytes());
			httpConn.getOutputStream().flush();
			httpConn.getOutputStream().close();
			InputStream is = httpConn.getInputStream();
			int nRead;
			byte[] bBuffer = new byte[1024];
			do {
				nRead = is.read(bBuffer);
				if (nRead <= 0) {
					break;
				}
				sb.append(new String(bBuffer, 0, nRead, "utf-8"));
			} while (true);

			String key = "";
			for (int i = 1; (key = httpConn.getHeaderFieldKey(i)) != null; i++) {
				System.out.println(i + ":" + httpConn.getHeaderField(key));
				if (key.equalsIgnoreCase("Set-Cookie")) {
					System.out.println(i + ":" + httpConn.getHeaderField(key));
					// sessionId = sessionId.substring(0, sessionId.indexOf(";"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
