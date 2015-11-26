package com.csai.action;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csai.POJO.Bedchamber;
import com.csai.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class BedchamberAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String action;
	public String bedchambername;
	public String bedchamberid;
	public Session sessionHibernate;
	public Session getSessionHibernate() {
		return sessionHibernate;
	}
	public void setSessionHibernate(Session sessionHibernate) {
		this.sessionHibernate = sessionHibernate;
	}	
	@Override
	public String execute() throws Exception {
		sessionHibernate.beginTransaction();
		Map request = (Map)ActionContext.getContext().get("request");
		//----如果是要增加一个宿舍---
		if("add".equals(action)){
			String hsql="from Bedchamber where bedchamberName=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,bedchambername);
			ArrayList<Bedchamber> bedArray =(ArrayList<Bedchamber>)query.list();
			if(bedArray.size()<=0){//没有这个宿舍
				Bedchamber bedchamber=new Bedchamber();
				bedchamber.setBedchamberName(bedchambername);
				sessionHibernate.save(bedchamber);
			}
		}
		//----如果是删除一个宿舍----
		if("del".equals(action)){
			String hsql="from Bedchamber where bedchamberId=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setInteger(0,Integer.parseInt(bedchamberid));
			ArrayList<Bedchamber> bedArray =(ArrayList<Bedchamber>)query.list();
			if(bedArray.size()>=1)
				sessionHibernate.delete(bedArray.get(0));
		}
		//----查询出已有的宿舍数据----
		String hsql="from Bedchamber";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<Bedchamber> bedArray =(ArrayList<Bedchamber>)query.list();
		request.put("bedArray", bedArray);
		sessionHibernate.getTransaction().commit();
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBedchambername() {
		return bedchambername;
	}

	public void setBedchambername(String bedchambername) {
		this.bedchambername = bedchambername;
	}

	public String getBedchamberid() {
		return bedchamberid;
	}

	public void setBedchamberid(String bedchamberid) {
		this.bedchamberid = bedchamberid;
	}
	
}
