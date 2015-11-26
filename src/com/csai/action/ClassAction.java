package com.csai.action;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csai.POJO.ClassTa;
import com.csai.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class ClassAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String action;
	public String classname;
	public String classid;
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
		//----�����Ҫ����һ���༶---
		if("add".equals(action)){
			String hsql="from ClassTa where className=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,classname);
			ArrayList<ClassTa> claArray =(ArrayList<ClassTa>)query.list();
			if(claArray.size()<=0){//û������༶
				ClassTa cla=new ClassTa();
				cla.setClassName(classname);
				sessionHibernate.save(cla);
			}
		}
		//----�����ɾ��һ���༶----
		if("del".equals(action)){
			String hsql="from ClassTa where classId=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setInteger(0,Integer.parseInt(classid));
			ArrayList<ClassTa> claArray =(ArrayList<ClassTa>)query.list();
			if(claArray.size()>=1)
				sessionHibernate.delete(claArray.get(0));
		}
		//----��ѯ�����еİ༶����----
		String hsql="from ClassTa";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<ClassTa> classArray =(ArrayList<ClassTa>)query.list();
		request.put("classArray", classArray);
		sessionHibernate.getTransaction().commit();
		return SUCCESS;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
}
