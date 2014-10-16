package tutorial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;  
public class Information extends ActionSupport  implements RequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Object> map;
	HttpServletRequest request=null;
	public void setRequest(Map<String, Object> map) {
		  this.map = map;
	}
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
    	if(GetInfo(id))
    		return SUCCESS;
    	else
    		return ERROR;
    }
    public boolean GetInfo(String id) {
    	boolean flag = false;
    	Connection conn = null;
    	ResultSet res1 = null, res2 = null;
    	Statement st = null, st2 = null;
    	ArrayList<data> ls = new ArrayList<data>();
    	String DBDriver = "com.mysql.jdbc.Driver";
    	try {
    		Class.forName(DBDriver).newInstance();
    		conn = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_mybook1","o3z305zz0j" ,"w1jl03w3jikh1ki4mijjm3i4ky4j25h0zhijjm01");
    		st = conn.createStatement();
    		st2 = conn.createStatement();
    		System.out.println("测试连接2！"+id);
    		res1 = st.executeQuery("select * from book where ISBN ='" + id +"'");
    		while (res1.next()) {
    			int aid = res1.getInt("AuthorID");
    			System.out.println("ID:"+aid);
    			res2 = st2.executeQuery("select * from author where AuthorID ='" + aid + "'");
    			while (res2.next()) {
    				data new_data = new data();
    				new_data.ISBN = res1.getString("ISBN");
    				new_data.Title = res1.getString("Title");
    				new_data.Author = res2.getString("name");
    				new_data.Publisher = res1.getString("Publisher");
    				new_data.PublishDate = res1.getInt("PublishDate");
    				new_data.Price = res1.getDouble("Price");
    				new_data.AuthorAge = res2.getInt("Age");
    				new_data.AuthorCountry = res2.getString("Country");
    				ls.add(new_data);
    				flag = true;
    			}
    		}
    		System.out.println("end");
    		map.put("ls", ls);
    		
    	} catch (Exception e) {
    		System.out.print("连接错误！");
    		e.printStackTrace();
    	}
    	return flag;
    }
}
