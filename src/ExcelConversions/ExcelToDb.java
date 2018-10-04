package ExcelConversions;

import java.io.File;

public class ExcelToDb {

public static void main(String[] args) {
	final File filepath = new File("src/resources1");
    try {
    	
        PlayerParser.parseAndDisplay("src/resources/player.xml", filepath+"/"+listFilesForFolder(filepath));
//        PlayerParser.parseAndDisplay("src/resources/department.xml", "src/resources/department.xlsx");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
 }

public static String listFilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
        	return fileEntry.getName();
        }
    }
	return null;
}
}
