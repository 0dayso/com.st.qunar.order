package com.st.qunar.order;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;

public class Test {

	public static void main(String[] args) throws IOException {
		String ip = "221.122.126.76";
		String uid = "yglxs005";
		String pw = "yg9382";
		String sessionid = DigestUtils.md5Hex(uid + pw + "123.118.169.61");
		String termid = uid;

		String url = "http://" + ip + ":352/ib_tranx_req.asp?uid=" + uid + "&sessionid=" + sessionid + "&termid=" + uid
				+ "&cmd=detr&ticket=7842126405169";
		System.out.println(url);
		byte[] rets = Request.Get(url).execute().returnContent().asBytes();
		System.out.println(new String(rets, "GBK"));
	}
}
