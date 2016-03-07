package com.hdc.process.task.ServiceTask;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hdc.entity.FeedbackRecord;
import com.hdc.entity.TaskInfo;
import com.hdc.service.IFeedbackRecordService;
import com.hdc.service.ITaskInfoService;
/**
 * 检查反馈情况
 * @author ZML
 *
 */
@Component
public class CheckFeedback implements JavaDelegate {

	@Autowired
	private ITaskInfoService taskInfoService;
	
	@Autowired
	private IFeedbackRecordService feedbackService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String taskInfoId = (String) execution.getVariable("taskInfoId");
		TaskInfo taskInfo = this.taskInfoService.findById(new Integer(taskInfoId));
		List<FeedbackRecord> list =  this.feedbackService.findByTaskId(new Integer(taskInfoId));
		Integer feedbackCycle = taskInfo.getFeedbackCycle();	//获取反馈周期
		switch (feedbackCycle) {
			case 0:	//默认一次
				if(list.size() > 0){
					execution.setVariable("needFeedback", false);	//无需提示填写反馈信息
				} else {
					Date beginDate = new Date();
			  	    Date endDate = taskInfo.getEndTaskDate();
			  	    long betweenDays = (long)((endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 *24) + 0.5); 
			  	    if(betweenDays <= 2) {	//提前两天提示
			  	    	execution.setVariable("needFeedback", true);
			  	    }
				}
				break;
			case 1: //每周一次
				Date assignDate = taskInfo.getAssignDate();		//交办时间
				Date endTaskDate = taskInfo.getEndTaskDate();	//办结日期
				Date nowDate = new Date();
				long betweenDays = (long)((nowDate.getTime() - assignDate.getTime()) / (1000 * 60 * 60 *24) + 0.5); 
				System.out.println(betweenDays/7);
				long weeks = betweenDays/7;
				
				GregorianCalendar gc=new GregorianCalendar(); 
			  	gc.setTime(assignDate); 
			  	while(gc.getTime().getTime() <= endTaskDate.getTime()) {	//交办日期 < 半截日期
			  		gc.add(3, 1);	//加一周
			  		
			  	}
			  	
				
				
				
				
				break;
			case 2: //每月一次
				
				break;
			default:
				break;
		}
		
	}

}
