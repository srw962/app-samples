WebService最大的好处---异构平台的通用服务，与具体语言无关的网络服务。

dtd

schema - xsd - java

xml - stax dom4j 


wsimport 通过WSDL导出Java类
	wsimport -d F:\asynclife\source\app\app-webservice\target -p com.ws.client -keep -verbose http://localhost:8083/myWS/add?wsdl

Maven 插件
--------------
方式一：
SOAP - Simple Object Access Protocal 基于XML传递消息
	WSDL的结构
		1.types 描述方法的参数类型，通过xsd进行描述
		2.message 传递的消息：请求消息、响应消息
		3.portType	指定接口，以及接口中的方法
		4.binding	指定传递消息所使用的样式-以XML样式封装数据
		5.service	指定服务发布的名称、地址

	TCPMon 监控传输的SOAP消息内容 
		
方式二：
jax-ws - 直接用Java发布WebService


方式三：
webservice框架
CXF、Axis、Metro

==============================================

	