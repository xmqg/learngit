package tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Delete extends ActionSupport{
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=null;
	private String id;
    public String getid() {
    	return id;
    }
    public void setid(String id) {
    	this.id = id;
    }
    public String execute(){
    	request = ServletActionContext.getRequest(); 
    	id=request.getParameter("id");
    	if(tryDelete(id))
    		return SUCCESS;
    	else
    		return ERROR;
    }
    public boolean tryDelete(String id) {
    	boolean flag = false;
    	int res=0;
    	Connection conn = null;
    	Statement st = null;
    	String DBDriver = "com.mysql.jdbc.Driver";
    	try {
    		Class.forName(DBDriver).newInstance();
    		conn = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_mybook1","o3z305zz0j" ,"w1jl03w3jikh1ki4mijjm3i4ky4j25h0zhijjm01");
    		st = conn.createStatement();
    		System.out.println("测试连接3！"+id);
    		res = st.executeUpdate("delete from book where ISBN ='" + id +"'");
    		if (res==1)
    			flag=true;
    		System.out.println("end");
    		
    	} catch (Exception e) {
    		System.out.print("连接错误3！");
    		e.printStackTrace();
    	}
    	return flag;
    }
}
