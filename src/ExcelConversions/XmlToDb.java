package ExcelConversions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
//import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONException;

public class XmlToDb {
	
	static String line="",str="";
    public static void main(String[] args) throws JSONException, IOException {
        String link = "./src/resources/employees.xml";
        BufferedReader br = new BufferedReader(new FileReader(link));
        while ((line = br.readLine()) != null) 
        {   
            str+=line;  
        }
        JSONObject jsondata = XML.toJSONObject(str);
        
        try{
            Connection con=null;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/localdb","root","root");
            PreparedStatement pstmt=con.prepareStatement("INSERT INTO employee(name,payment,birthdate) VALUES(?,?,?)");
                       
            JSONObject employeeJson = (JSONObject) jsondata.get("employee");
            JSONArray employeesArray= (JSONArray) employeeJson.get("emp");
            Iterator i = employeesArray.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                
                pstmt.setString(1, innerObj.get("name").toString());
                BigDecimal num=new BigDecimal(innerObj.get("payment").toString()); 
                pstmt.setBigDecimal(2,num);
                pstmt.setObject(3, innerObj.get("birthDate"));
                pstmt.execute();
            }
        }
        catch(Exception e){e.printStackTrace();}
        }
}
