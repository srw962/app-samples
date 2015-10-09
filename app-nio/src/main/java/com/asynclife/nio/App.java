package com.asynclife.nio;

/**
 * I/O应用：
 * 	1. 本机文件读写
 * 	2. 远程数据传输：处理Socket
 *
 * NIO与传统IO的区别：
 * 1. 	NIO: block-oriented
 * 		传统IO: stream-oriented
 * 		NIO按块处理数据，传统IO按Stream字节(1byte=8bit)流处理数据
 * 
 * 2. NIO的核心
 * 		Buffer	读：read from a buffer  写：write to a buffer
 * 		When data is read, it is read directly into a buffer.
 * 		When data is written, it is written into a buffer. 
 * 		
 * 		Buffer-每种原始数据类型都有其对应的Buffer类
 * 		ByteBuffer(底层为byte[])  CharBuffer ShortBuffer IntBuffer LongBuffer FloatBuffer DoubleBuffer
 * 
 * 		Channel
 * 			bi-directional 双向通道 
 * 			A Channel can be opened for reading, for writing, or for both.
 * 
 * 
 * 		Buffer Internal State
 * 			Position	
 * 				when read(input channel -> buffer):  
 * 					将数据从channel读入到buffer，position指向的是下一个字节写入缓冲区的位置.
 * 				when write(buffer -> output channel): 
 * 					将数据从buffer写出到channel，position指向的是下一个被读出的数据在缓冲区的位置
 * 			Limit
 * 				when read(input channel -> buffer):  
 * 					从channel读入的数据可以放入缓冲区的上限位置（不含）
 * 				when write(buffer -> output channel): 
 * 					从buffer写出的数据的上限位置（不含）
 * 				
 * 			Capacity
 * 				缓冲区数组的容量
 * 			
 * 			position <= limit <= capacity
 * 			
 * 
 * 			flip-每次从buffer写出数据到channel时，都需要调用flip()重新缓冲区的内部状态
 * 			when read over: invoke flip()
 * 				1. sets the limit to the current position. 【limit = position】
 * 				2. sets the position to 0. 【position = 0】
 * 			重新设置limit和position的值，为write做准备(buffer -> output channel)
 * 			每完成1次从channel读入buffer的动作后，设置limit和position
 * 			
 * 			clear-每次从channel读入数据到buffer时，都需要调用clear()重置缓冲区的内部状态，准备接收新的数据
 * 			when write over/before read data from channel to buffer: invoke clear()
 * 				1. sets the limit to match the capacity. 【limit=capacity】
 * 				2. sets the position to 0.	【position = 0】
 * 		
 * 		Buffer的高级应用
 * 			buffer allocation
 * 				ByteBuffer buffer = ByteBuffer.allocate( 1024 );
 * 			wrapping
 * 				byte array[] = new byte[1024];
 * 				ByteBuffer buffer = ByteBuffer.wrap( array );
 * 			slicing-sharing buffer
 * 				从缓冲区提取一片区域作为新的buffer，对新buffer的修改将也就是对原始buffer中数据的修改
 * 			read-only buffers, which protect data from modification.
 * 				asReadOnlyBuffer()
 *  		direct buffers,which map directly onto the underlying OS buffers.
 *  			利用操作系统底层的数据缓冲区进行数据拷贝，速度比普通buffer快
 *  		memory-mapped files	
 *  			速度最快的buffer，利用操作系统的支持，直接将文件/部分文件中的数据拷贝到内存中进行处理
 *  			危险的是：程序对mappedBuffer中数据进行了修改，这些修改将立即作用到磁盘文件上。
 *  
 *  	Scatter & Gather
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello NIO!");
	}
}
