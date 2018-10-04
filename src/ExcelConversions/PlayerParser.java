package ExcelConversions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;

import model.Player;

public class PlayerParser {
   String mappingFile;
   String excelFile;
   
   private PlayerParser()
   {
     
   }
   
   private static PlayerParser init(){
   
      return new PlayerParser();
   }
   
   private void setExternalFileLocation(String mappingFile , String excelFile){
      this.mappingFile=mappingFile;
      this.excelFile=excelFile;
   }
   
   
   public static void parseAndDisplay(String mappingFile , String excelFile ) throws Exception
   {
      PlayerParser ref = init();
      ref.setExternalFileLocation(mappingFile,excelFile);
      List entities = ref.doConfigtask();
      ref.display(entities);
   }
   
   private void display(List entities){
	   Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	   
      for(Object entity : entities)
      {
    	  session.save(entity);
          System.out.println(entity);
      }
      session.getTransaction().commit();
		HibernateUtil.shutdown();
   }
   
   private List<Object>  doConfigtask() throws Exception{
	      final XLSReader xlsReader = ReaderBuilder.buildFromXML(new File(mappingFile));
	       final Map<String, Object> beans = new HashMap<String, Object>();
	       InputStream inputStream = new BufferedInputStream(new FileInputStream(excelFile));
	       
	       final List<Object> result = new ArrayList<Object>();
	       beans.put("items", result);
	       
	       xlsReader.read(inputStream, beans);      
	       if(result.isEmpty()) {
	    	   System.out.println("No records");
	       }
	       return result;
	   }
}
