<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="Speciality" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">¼��רҵ����</font></td>
  </tr>
  <tr>
    <td width="100%">��
    ������רҵ���ƣ�
    <s:textfield name="specialityname"/>
    <s:hidden name="action" value="add"/>
    <s:submit value="�ύ"/>
    </td>
  </tr>
</table>
</s:form>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="3">
    <font color="#0000FF">����רҵ����</font></td>
  </tr>
  <tr>
    <td width="20%" align="center">��
		���
    </td>
    <td width="60%" align="center">��
		רҵ����
    </td> 
    <td width="20%" align="center">��
		ɾ����
    </td>      
  </tr>
<s:iterator value="#request.specialityArray" status="status">
  <tr>
    <td width="20%" align="center">��
		<s:property value="#status.count"/>
    </td>
    <td width="60%" align="center">��
    	<s:property value="specialityName"/>
    </td> 
    <td width="20%" align="center">��
		<a href="Speciality.action?action=del&specialityid=<s:property value="specialityId"/>">
		ɾ����</a>
    </td>      
  </tr>
</s:iterator>
</table>
</body>
</html>