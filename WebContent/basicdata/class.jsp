<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="Class" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">¼��༶����</font></td>
  </tr>
  <tr>
    <td width="100%">��
    ������༶���ƣ�
    <s:textfield name="classname"/>
    <s:hidden name="action" value="add"/>
    <s:submit value="�ύ"/>
    </td>
  </tr>
</table>
</s:form>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="3">
    <font color="#0000FF">���а༶����</font></td>
  </tr>
  <tr>
    <td width="20%" align="center">��
		���
    </td>
    <td width="60%" align="center">��
		�༶����
    </td> 
    <td width="20%" align="center">��
		ɾ����
    </td>      
  </tr>
<s:iterator value="#request.classArray" status="status">
  <tr>
    <td width="20%" align="center">��
		<s:property value="#status.count"/>
    </td>
    <td width="60%" align="center">��
    	<s:property value="className"/>
    </td> 
    <td width="20%" align="center">��
		<a href="Class.action?action=del&classid=<s:property value="classId"/>">
		ɾ����</a>
    </td>      
  </tr>
</s:iterator>
</table>
</body>
</html>