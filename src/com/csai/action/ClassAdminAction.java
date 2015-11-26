package com.csai.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csai.POJO.ClassTa;
import com.csai.POJO.Student;
import com.csai.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassAdminAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String studentname;
	public String action;
	public String matrino;
	public ArrayList<Student> stuParamArray; 
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
		//----�����ѯ��SQL���----
		String hsqlwhere=new String("");
		String hsql=new String("");
		if("select".equals(action)){//����ǲ�ѯ����
			if(studentname!=null&&studentname.trim().length()!=0)
				hsqlwhere="where studentName like '%"+studentname.trim()+"%' ";
			if(hsqlwhere!=null&&hsqlwhere.length()!=0){
				if(matrino!=null&&matrino.trim().length()!=0)
					hsqlwhere+=" and matriNo like '%"+matrino.trim()+"%' ";
			}else{
				if(matrino!=null&&matrino.trim().length()!=0)
					hsqlwhere=" where matriNo like '%"+matrino.trim()+"%' ";
			}
			hsql="from Student "+hsqlwhere;
			Query query=sessionHibernate.createQuery(hsql);
			List<Student> stuArray =(List<Student>)query.list();
			Map request = (Map)ActionContext.getContext().get("request");
			request.remove("stuArray");
			request.put("stuArray", stuArray);
			//----��ѯ�����е�רҵ����----
			hsql="from ClassTa";
			Query queryClass=sessionHibernate.createQuery(hsql);
			List<ClassTa> classArray =(List<ClassTa>)queryClass.list();
			request.remove("classArray");
			request.put("classArray", classArray);
		}
		//----���÷ְ����----
		if(stuParamArray!=null&&"update".equals(action)){
			for(int i=0;i<stuParamArray.size();i++){
				if(stuParamArray.get(i).getClassTa()!=null&&stuParamArray.get(i).getClassTa().getClassId()!=0){					
					String hsqlstr="from ClassTa where classId="+stuParamArray.get(i).getClassTa().getClassId();
					Query query=sessionHibernate.createQuery(hsqlstr);
					ArrayList<ClassTa> claArray =(ArrayList<ClassTa>)query.list();
					if(claArray.size()>=1){
						hsqlstr="from Student where studentId="+stuParamArray.get(i).getStudentId();
						Query queryStu=sessionHibernate.createQuery(hsqlstr);
						ArrayList<Student> stuArray =(ArrayList<Student>)queryStu.list();
						if(stuArray.size()>=1){
							Student stu=stuArray.get(0);
							stu.setClassTa(claArray.get(0));
							sessionHibernate.save(stu);
						}
					}
				}
			}
		}
		sessionHibernate.getTransaction().commit();
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

	public String getMatrino() {
		return matrino;
	}

	public void setMatrino(String matrino) {
		this.matrino = matrino;
	}

	public ArrayList<Student> getStuParamArray() {
		return stuParamArray;
	}

	public void setStuParamArray(ArrayList<Student> stuParamArray) {
		this.stuParamArray = stuParamArray;
	}

}
