package com.csai.action;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csai.POJO.Student;
import com.csai.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class RegStatusAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String studentname;
	public String action;
	public Session sessionHibernate;
	public Session getSessionHibernate() {
		return sessionHibernate;
	}
	public void setSessionHibernate(Session sessionHibernate) {
		this.sessionHibernate = sessionHibernate;
	}	
	@Override
	public String execute() {
		if("select".equals(action)){
			//----查询出已有的数据----
			try{
				String hsql="from Student ";
				if(studentname!=null&&studentname.length()!=0)
					hsql+="where studentName like '%"+studentname+"%'";
				Session sessionHiberante=HibernateUtil.getSession();
				sessionHiberante.beginTransaction();
				Query query=sessionHiberante.createQuery(hsql);
				ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
				sessionHiberante.getTransaction().commit();
				Map<String,ArrayList<Student>> request = (Map<String,ArrayList<Student>>)ActionContext.getContext().get("request");
				request.put("stuArray", stuArray);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
