 

<html>

  <body>
  	
    <s:iterator value="#request.ls" var="mylist">
    		<td> <s:property value="#mylist.ISBN"/> </td>
    		<td> <s:property value="#mylist.Title"/> </td>
    		<td> <s:property value="#mylist.Publisher"/> </td>
    		<td> <s:property value="#mylist.PublishDate"/> </td>
    		<td> <s:property value="#mylist.Price"/> </td>
    		<td> <s:property value="#mylist.Author"/> </td>
    		<td> <s:property value="#mylist.AuthorAge"/> </td>
    		<td> <s:property value="#mylist.AuthorCountry"/> </td> <br/>
	</s:iterator>
	<a href="login.jsp">���س�ʼҳ��</a>
  </body>
</html> 