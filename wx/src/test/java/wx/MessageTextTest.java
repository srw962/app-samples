package wx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asynclife.wx.App;
import com.asynclife.wx.model.MsgReq;
import com.asynclife.wx.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class MessageTextTest {

	/**
	 * 响应消息
	 * 	普通消息：文本/图片/图文
	 * 	事件消息
	 */
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageService messageService;
	
	@Test
	public void testRspImage() {
		
		MsgReq req = new MsgReq();
		req.setFromUserName("123456");
		req.setToUserName("abcdef");
		req.setCreateTime(String.valueOf(System.currentTimeMillis()/1000));
		req.setMsgType("text");
//		req.setContent("this is a test string");
//		req.setMsgId(UUID.randomUUID().toString());
		
		String rsp = messageService.returnPic(req);
		logger.info(rsp);
	}
	
	
}
