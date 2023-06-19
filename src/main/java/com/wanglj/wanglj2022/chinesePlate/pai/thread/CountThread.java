package com.wanglj.wanglj2022.chinesePlate.pai.thread;

import com.alibaba.fastjson.JSON;

import com.wanglj.wanglj2022.chinesePlate.pai.model.Message;
import com.wanglj.wanglj2022.chinesePlate.pai.view.MainFrame;

public class CountThread extends Thread
{
     private int i;
     
     private MainFrame mainFrame;
     
     private boolean isRun;
     
     
     public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public CountThread(int i, MainFrame mainFrame)
     {
    	 isRun=true;
		 this.i=i;
    	 this.mainFrame=mainFrame;
     }
     
     public void run()
     {
    	 while(i>=0&&isRun)
    	 {
    		 mainFrame.timeLabel.setText(i+"");
    		 
    		 i--;
    		 
    		 try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    	 }
    	 //ʱ�䵽  ���ߵ����
    	 Message msg=null;
    	 if(i==-1||(isRun==false&&mainFrame.isLord==false))
    	 {
    		 msg=new Message(1,mainFrame.currentPlayer.getId(),"����",null);
    		 mainFrame.lordLabel1.setVisible(false);
    		 mainFrame.lordLabel2.setVisible(false);
    		 mainFrame.timeLabel.setVisible(false);
    	 }
    	 if(isRun==false&&mainFrame.isLord==true)
    	 {
    		 msg=new Message(2,mainFrame.currentPlayer.getId(),"������",null);
    	 }
    	 
    	 mainFrame.sendThread.setMsg(JSON.toJSONString(msg));
     }
     
}











