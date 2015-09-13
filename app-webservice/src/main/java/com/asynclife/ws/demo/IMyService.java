package com.asynclife.ws.demo;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * SEI: Service Endpoint Interface
 *  服务切入点接口
 */
@WebService
public interface IMyService {
	
	@WebResult(name="addResult")
	public long add(@WebParam(name="num1") int num1, @WebParam(name="num2")int num2);
	
}
