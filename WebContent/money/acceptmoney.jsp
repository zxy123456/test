<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="AcceptMoney" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="700">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">Ҫ��ѯ������</font></td>
  </tr>
  <tr>
    <td width="100%">��
    ������������
    <s:textfield name="studentname"/>
    ������¼ȡ֪ͨ��ţ�
    <s:textfield name="matrino"/>
    <s:hidden name="action" value="select"/>
    <s:submit value="�ύ"/>
    </td>
  </tr>
</table>
</s:form>
<s:form method="post" action="AcceptMoney" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="700">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="7">
    <font color="#0000FF">��ѯ����ѧ������</font></td>
  </tr>
  <tr>
    <td width="5%" align="center">���</td>
    <td width="12%" align="center">����</td> 
    <td width="16%" align="center">¼ȡ֪ͨ���</td> 
    <td width="12%" align="center">¼ȡרҵ</td>  
    <td width="15%" align="center">���ڰ༶</td>
    <td width="15%" align="center">���ѽ��</td>
    <td width="15%" align="center">�Ƿ���</td>
  </tr>
<s:set name="stuArray" value="#request.stuArray" scope="action"/>
<s:if test="#stuArray!=null">
	<s:iterator value="#stuArray" status="status">
  <tr>
    <td width="5%" align="center"><s:property value="#status.count"/></td>
    <td width="12%" align="center"><s:property value="studentName"/></td> 
    <td width="16%" align="center"><s:property value="matriNo"/></td> 
    <td width="12%" align="center">
    <s:property value="speciality.specialityName"/>
    </td>  
    <td width="15%" align="center">
    <s:hidden name="stuParamArray[%{#status.index}].studentId" value="%{StudentId}"/>
    <s:if test="classTa.className!=null">
    	<s:property value="classTa.className"/> 
    </s:if>
    <s:else>
    	��δ�ְ�
    </s:else>
    </td> 
    <td width="15%" align="center">
    <s:if test="classTa.className!=null&&ClassId!=0">
    	<s:textfield name="stuParamArray[%{#status.index}].PayAmount" value="%{payAmount}" size="12"/>
    </s:if>
    </td>
    <td width="15%" align="center">
    <s:if test="classTa.className!=null&&ClassId!=0">
    	<s:radio name="stuParamArray[%{#status.index}].payOK" value="%{payOK}"
    		list="#{1:'��',0:'��'}"/>
    </s:if> 
    </td>
  </tr>
	</s:iterator>
</s:if> 
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="8">
    <font color="#0000FF">
    <s:submit value="ȷ ��"/>
    </font></td>
  </tr>	
  <s:hidden name="action" value="update"/>
</table>
</s:form>
</body>
</html>