<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="Matri"  theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">¼��¼ȡѧ������</font></td>
  </tr>
  <tr>
    <td width="100%" align="left">��
    ������ѧ��������
    <s:textfield name="studentname"/>
    ��ѡȡ¼ȡרҵ:
	<s:select name="specialityid" listKey="specialityId"
		listValue="specialityName" list="#request.specialityArray"
		headerKey="" headerValue="==��ѡ��==">
    </s:select><br>
    ������¼ȡ֪ͨ��ţ�<s:textfield name="matrino"/>
    <s:hidden name="action" value="add"/>
    <s:submit value="�ύ"/>
    </td>
  </tr>
</table>
</s:form>
<s:form method="post" action="Matri"  theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0"  colspan="5">
    <font color="#0000FF">��ѯ��¼���ѧ������</font></td>
  </tr>
  <tr>
  <tr>
    <td width="100%" align="left" colspan="5">��
    ������ѧ��������
    <s:textfield name="studentname"/>
    ��ѡȡ¼ȡרҵ:
	<s:select name="specialityid" listKey="specialityId"
		listValue="specialityName" list="#request.specialityArray"
		headerKey="" headerValue="==��ѡ��==">
    </s:select>
    <s:hidden name="action" value="select"/>
    <s:submit value="�ύ"/>
    </td>
  </tr>
  <tr>
  	<td align="center" colspan="5"><font color="#0000FF">
  	<s:if test="#request.pagecount>1&&#request.currentpage>1">
  		<a href="Matri.action?currentpage=1&action=<s:property value="action"
  		/>&specialityid=<s:property value="specialityid"
  		/>&studentname=<s:property value="studentname"/>">��ҳ</a>&nbsp;
  		<a href="Matri.action?currentpage=<s:property value="#request.currentpage-1"
  		/>&action=<s:property value="action"
  		/>&specialityid=<s:property value="specialityid"
  		/>&studentname=<s:property value="studentname"/>">��һҳ</a>&nbsp;
  	</s:if>
  	<s:if test="#request.pagecount>1&&#request.currentpage<#request.pagecount">
  		<a href="Matri.action?currentpage=<s:property value="#request.currentpage+1"
  		/>&action=<s:property value="action"
  		/>&specialityid=<s:property value="specialityid"
  		/>&studentname=<s:property value="studentname"
  		/>">��һҳ</a>&nbsp;
  		<a href="Matri.action?currentpage=<s:property value="#request.pagecount"
  		/>&action=<s:property value="action"
  		/>&specialityid=<s:property value="specialityid"
  		/>&studentname=<s:property value="studentname"/>">βҳ</a>&nbsp;
  	</s:if>
  	��<s:property value="#request.recount"/>����¼��
  	��<s:property value="#request.pagecount"/>ҳ��
  	��ǰ��<s:property value="#request.currentpage"/>ҳ
  	</font></td>
  </tr>
  <tr bgcolor="#C0C0C0">
  	<td align="center"><font color="#0000FF">���</font></td>
  	<td align="center"><font color="#0000FF">����</font></td>
  	<td align="center"><font color="#0000FF">¼ȡרҵ</font></td>
  	<td align="center"><font color="#0000FF">¼ȡ֪ͨ���</font></td>
  	<td align="center"><font color="#0000FF">ɾ����</font></td>
  </tr>
<s:iterator value="#request.stuArray" status="status">
  <tr>
  	<td align="center"><s:property value="#status.count"/></td>
  	<td align="center">
  		<s:property value="studentName"/>
  	</td>
  	<td align="center">
  		<s:property value="speciality.specialityName"/>
  	</td>
  	<td align="center">
  		<s:property value="MatriNo"/>
  	</td>
  	<td align="center">
  		<a href="Matri.action?action=del&studentid=<s:property value="StudentId"/>">ɾ��</a>
  	</td>
  </tr>
 </s:iterator>
</table>
</s:form>
</body>
</html>