package tutorial;  
import com.opensymphony.xwork2.ActionSupport;  

import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
public class HelloWorld extends ActionSupport  implements RequestAware 
{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Object> map;
	HttpServletRequest request=null;
	public void setRequest(Map<String, Object> map) {
		  this.map = map;
	}
	private String Author;
    public String getAuthor() {
    	return Author;
    }
    public void setAuthor(String Author) {
    	this.Author = Author;
    }
    public String execute(){
    	request = ServletActionContext.getRequest(); 
    	Author=request.getParameter("Author");
    	//System.out.println("!!!!!");
    	if(Find(Author))
    		return SUCCESS;
    	else
    		return ERROR;
    }
    public boolean Find(String Author) {
    	boolean flag = false;
    	Connection conn = null;
    	ResultSet res1 = null, res2 = null;
    	Statement st = null, st2 = null;
    	ArrayList<data> ls = new ArrayList<data>();
    	String DBDriver = "com.mysql.jdbc.Driver";
    	try {
    		Class.forName(DBDriver).newInstance();
    	} catch (Exception e) {
    		System.out.print("连接错误1！");
    		e.printStackTrace();
    	} try{
    		conn = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_mybook1","o3z305zz0j" ,"w1jl03w3jikh1ki4mijjm3i4ky4j25h0zhijjm01");
    	}catch (Exception e) {
    		System.out.print("连接错误2！");
    		e.printStackTrace();
    	} try{
    		st = conn.createStatement();
    		st2 = conn.createStatement();
    	}catch (Exception e) {
    		System.out.print("连接错误3！");
    		e.printStackTrace();
    	} try{
    		System.out.println("测试连接1！"+Author);
    		if (Author.isEmpty()) {
    			System.out.println("empty");
    			res1 = st.executeQuery("select * from author");
    		}
    		else 
    			res1 = st.executeQuery("select * from author where name ='" + Author +"'");
    	}catch (Exception e) {
    		System.out.print("连接错误4！");
    		e.printStackTrace();
    	} try{
    		int cnt=0;
    		System.out.println("cnt1:"+cnt);
    		while (res1.next()) {
    			System.out.println("get id");
    			int id = res1.getInt("AuthorID");
    			System.out.println("id:"+id);
    			cnt++;
    			System.out.println("res2 begin");
    			res2 = st2.executeQuery("select * from book where AuthorID ='" + id + "'");
    			System.out.println("res2 success");
    			while (res2.next()) {
    				data new_data = new data();
    				new_data.ISBN = res2.getString("ISBN");
    				new_data.Title = res2.getString("Title");
    				new_data.Author = res1.getString("name");
    				new_data.Publisher = res2.getString("Publisher");
    				new_data.PublishDate = res2.getInt("PublishDate");
    				new_data.Price = res2.getDouble("Price");
    				ls.add(new_data);
    				flag = true;
    			}
    		}
    		System.out.println("cnt2:"+cnt);
    		map.put("ls", ls);
    		
    	} catch (Exception e) {
    		System.out.print("连接错误5！");
    		e.printStackTrace();
    	}
    	System.out.println("ls:"+ls.size());
    	return flag;
    }
}  