package com.smart.anno;

public class ForumService {
    @NeedTest(true) // NeedTest的变量名字为value()，这里可以省略value = true
	public void deleteForum(int forumId){
		System.out.println("删除论坛模块："+forumId);
	}
    /**
     * 
     * @param topicId
     */
    @NeedTest()
    public void deleteTopic(int topicId){
		System.out.println("删除论坛主题："+topicId);
	}	
}
