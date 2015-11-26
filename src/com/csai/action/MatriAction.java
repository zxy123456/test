package com.csai.action;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.csai.POJO.Speciality;
import com.csai.POJO.Student;
import com.csai.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MatriAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String action;
	public Integer specialityid;
	public String matrino;
	public String studentname;
	public Integer currentpage=1;
	public Long studentid;
	public Session sessionHibernate;
	public Session getSessionHibernate() {
		return sessionHibernate;
	}
	public void setSessionHibernate(Session sessionHibernate) {
		this.sessionHibernate = sessionHibernate;
	}		
	@Override
	public String execute() throws Exception {
		if(studentname!=null&&studentname.length()!=0)
			studentname=studentname.trim();
		if(action!=null&&action.length()!=0)
			action=action.trim();
		if(matrino!=null&&matrino.length()!=0)
			matrino=matrino.trim();
		sessionHibernate.beginTransaction();
		Map request = (Map)ActionContext.getContext().get("request");
		//----�����Ҫ����һ��ѧ��---
		if("add".equals(action)){
			String hsql="from Student where matriNo=? and studentName=?"+
				" and speciality.specialityId=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,matrino);
			query.setString(1,studentname);
			query.setInteger(2,specialityid);	
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			if(stuArray.size()<=0){//û�д�ѧ��
				Student stu=new Student();
				stu.setMatriNo(matrino);
				stu.setStudentName(studentname);
				hsql="from Speciality where specialityId="+specialityid;
				Query query1=sessionHibernate.createQuery(hsql);
				Speciality spec=((ArrayList<Speciality>)query1.list()).get(0);
				stu.setSpeciality(spec);
				sessionHibernate.save(stu);
			}
		}
		//----�����Ҫɾ��һ��ѧ��---
		if("del".equals(action)){
			String hsql="from Student where studentId="+studentid;
			Query query=sessionHibernate.createQuery(hsql);
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			if(stuArray.size()==1)
				sessionHibernate.delete(stuArray.get(0));
		}
		//----�����Ҫ��ѯ����----
		int pagesize=5;//ÿҳ��¼����
		int pagecount=0;//��ҳ��
		int recount=0;//�ܼ�¼����
		if("select".equals(action)){
			String hsql=null;
			String hsqlwhere=" where 1=1 ";//where�Ӿ�
			if(studentname!=null&&studentname.length()>=1)
				hsqlwhere+=" and s.studentName like '%"+studentname+"%'";
			if(specialityid!=null&&specialityid.intValue()!=0)
				hsqlwhere+=" and s.speciality.specialityId="+specialityid;
			String hsqlcount="select count(*) from Student s "+hsqlwhere;
			recount = ((Long)sessionHibernate.createQuery(hsqlcount).uniqueResult()).intValue();
			//----�õ���ҳ��----
			if(recount%pagesize==0)//������
				pagecount=recount/pagesize;
			else//��������
				pagecount=(int)(recount/pagesize)+1;
			hsql="from Student s "+hsqlwhere+" order by s.studentId desc";
			System.out.println("****"+hsql);
			Query query=sessionHibernate.createQuery(hsql);
			query.setFirstResult((currentpage-1)*pagesize);
			query.setMaxResults(pagesize);
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			request.put("stuArray", stuArray);				
		}
		//----��ѯ��רҵ����----
		String hsql="from Speciality";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<Speciality> specialityArray =(ArrayList<Speciality>)query.list();
		request.put("specialityArray", specialityArray);
		//----�����ݷ���request----
		request.put("pagesize",pagesize);
		request.put("pagecount",pagecount);
		request.put("currentpage",currentpage);
		request.put("recount",recount);
		sessionHibernate.getTransaction().commit();
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(int specialityid) {
		this.specialityid = specialityid;
	}

	public String getMatrino() {
		return matrino;
	}

	public void setMatrino(String matrino) {
		this.matrino = matrino;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public long getStudentid() {
		return studentid;
	}

	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}

}
