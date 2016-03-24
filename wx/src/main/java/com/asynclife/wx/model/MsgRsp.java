package com.asynclife.wx.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgRsp extends MsgBase {

	private Image Image;
	
	private String Content;
	
	public MsgRsp() {
		
	}
	
	public MsgRsp(MsgReq req) {
		this.setFromUserName(req.getToUserName());
		this.setToUserName(req.getFromUserName());
		this.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
	}

	public Image getImage() {
		return Image;
	}

	public void setImage(String imageId) {
		Image = new Image(imageId);
	}

	public void setContent(String content) {
		Content = content;
	}
	
}

@XmlAccessorType(XmlAccessType.FIELD)
class Image {
	
	public String MediaId;
	
	public Image() {}

	public Image(String mediaId) {
		super();
		MediaId = mediaId;
	}

}