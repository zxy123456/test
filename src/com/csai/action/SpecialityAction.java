package com.csai.action;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csai.POJO.Speciality;
import com.csai.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SpecialityAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String specialityname;
	public int specialityid;
	public String action;
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
		//----�����Ҫ����һ��רҵ---
		if("add".equals(action)){
			String hsql="from Speciality where specialityname=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,specialityname);
			ArrayList<Speciality> specArray =(ArrayList<Speciality>)query.list();		
			if(specArray.size()<=0){//û�����רҵ
				Speciality spec=new Speciality();
				spec.setSpecialityName(specialityname);
				sessionHibernate.save(spec);
			}
		}
		//----�����ɾ��һ��רҵ----
		if("del".equals(action)){
			String hsql="from Speciality where specialityid=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setInteger(0,specialityid);
			ArrayList<Speciality> specArray =(ArrayList<Speciality>)query.list();
			if(specArray.size()>=1)
				sessionHibernate.delete(specArray.get(0));
		}
		//----��ѯ�����е�רҵ����----
		String hsql="from Speciality";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<Speciality> specialityArray=(ArrayList<Speciality>)query.list();
		request.put("specialityArray", specialityArray);	
		sessionHibernate.getTransaction().commit();
		return SUCCESS;
	}

	public String getSpecialityname() {
		return specialityname;
	}

	public void setSpecialityname(String specialityname) {
		this.specialityname = specialityname;
	}

	public int getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(int specialityid) {
		this.specialityid = specialityid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	

}
