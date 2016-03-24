package com.asynclife.wx.service;

import com.asynclife.wx.enums.MsgType;
import com.asynclife.wx.model.MsgReq;
import com.asynclife.wx.model.MsgRsp;
import com.asynclife.wx.util.JaxbHelper;

public class CommonService {
	
	public static String simpleText(MsgReq msgReq, String content) {
		MsgRsp msgRsp = new MsgRsp(msgReq);
		msgRsp.setMsgType(MsgType.text.name());
		msgRsp.setContent(content);
		
		return JaxbHelper.toXml(msgRsp, MsgRsp.class);
	}
	
	
}
