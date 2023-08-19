package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {


	@DataProvider(name="Data")//generating the data and pass to another test method in the project
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";//getProperty gives the current project location
		XLUtility xl=new XLUtility(path);
	
		int rownum=xl.getRowCount("Sheet1");//refering to excel utilities methods	
		int colcount=xl.getCellCount("Sheet1",1);//refering to excel utilities methods
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{		
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]= xl.getCellData("Sheet1",i, j); //refering to excel utilities methods 
			}
		}
	
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
	
		int rownum=xl.getRowCount("Sheet1");	
			
		String apidata[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{		
			apidata[i-1]= xl.getCellData("Sheet1",i, 1);  
			
		}
	
		return apidata;
	}
	
}
