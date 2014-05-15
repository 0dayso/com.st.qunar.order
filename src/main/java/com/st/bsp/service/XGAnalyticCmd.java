package com.st.bsp.service;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;

public class XGAnalyticCmd {
	// http://221.122.126.76:352/ib_tranx_req.asp?uid=yglxs005&sessionid=f25cc15e74ef8fcea6037a2de8678789&termid=yglxs005&cmd=detr&ticket=7842126405169
	// <?xml version="1.0" encoding="GB2312"?>
	// <cmd ret_value="1">
	// <ticket no="7842126405169">
	// <ACCESS_TKT>0</ACCESS_TKT>
	// <NAME>姚素芬</NAME>
	// <NI>150403196011261048</NI>
	// <RP></RP>
	// <VOID>1</VOID>
	// <ISSUEDATE>2014-04-12</ISSUEDATE>
	// <OFFICECODE></OFFICECODE>
	// <CATACODE>08685898</CATACODE>
	// <CNY>1110.00</CNY>
	// <TCNYCN>50.00</TCNYCN>
	// <TCNYYQ>120.00</TCNYYQ>
	// <TKTYPE>ET</TKTYPE>
	// <EI>BUDEQIANZHUAN不得签转/BIANGENGTUIPIAOSHOUFEI变更退票收</EI>
	// <PNR>ME9PCM</PNR>
	// <TKTSTATE>USED/CLOSED</TKTSTATE>
	// <PRINTDEV>0</PRINTDEV>
	// <VOYAGES count="1">
	// <VOYAGE index="1">
	// <CITYSTART code="PEK">北京</CITYSTART>
	// <CITYARRIVE code="NNG">南宁</CITYARRIVE>
	// <AIRPORTSTART code="PEK">北京首都机场</AIRPORTSTART>
	// <AIRPORTARRIVE code="NNG">南宁吴圩机场</AIRPORTARRIVE>
	// <CARRIER>CZ</CARRIER>
	// <STARTT></STARTT>
	// <ARRIVALT></ARRIVALT>
	// <FLIGHT>3278</FLIGHT>
	// <CLASS>L</CLASS>
	// <CLASSDISCOUNT></CLASSDISCOUNT>
	// <FAREBASIS>LGFDS10</FAREBASIS>
	// <DATE>27APR</DATE>
	// <TIME>1200</TIME>
	// <ALLOW>20K</ALLOW>
	// <STATE></STATE>
	// </VOYAGE>
	// </VOYAGES>
	// </ticket>
	// </cmd>
	//

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
