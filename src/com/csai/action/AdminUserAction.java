package com.csai.action;
import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.csai.POJO.AdminUser;
import com.csai.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String action;
	public String adminusername;
	public String adminuserpassword;
	public String adminuserrole;
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
		//----如果是要增加一个用户---
		if("add".equals(action)){
			String hsql="from AdminUser where adminUserName=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,adminusername);
			ArrayList<AdminUser> result =(ArrayList<AdminUser>)query.list();
			if(result.size()<1){//没有这个用户
				AdminUser adminuser=new AdminUser();
				adminuser.setAdminUserName(adminusername);
				adminuser.setAdminUserPassword(adminuserpassword);
				adminuser.setAdminUserRole(Integer.parseInt(adminuserrole));
				sessionHibernate.save(adminuser);
			}
		}
		//----如果是删除一个用户----
		if("del".equals(action)){
			String hsql="from AdminUser where adminUserName=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,adminusername);
			ArrayList<AdminUser> result =(ArrayList<AdminUser>)query.list();
			if(result.size()==1)
				sessionHibernate.delete(result.get(0));
		}
		//----查询出已有的用户数据
		String hsql="from AdminUser";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<AdminUser> userArray =(ArrayList<AdminUser>)query.list();
		Map<String,ArrayList<AdminUser>> request = (Map<String,ArrayList<AdminUser>>)ActionContext.getContext().get("request");
		request.remove("userArray");
		request.put("userArray", userArray);
		sessionHibernate.getTransaction().commit();
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAdminusername() {
		return adminusername;
	}

	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}

	public String getAdminuserpassword() {
		return adminuserpassword;
	}

	public void setAdminuserpassword(String adminuserpassword) {
		this.adminuserpassword = adminuserpassword;
	}

	public String getAdminuserrole() {
		return adminuserrole;
	}

	public void setAdminuserrole(String adminuserrole) {
		this.adminuserrole = adminuserrole;
	}
	
	

}
