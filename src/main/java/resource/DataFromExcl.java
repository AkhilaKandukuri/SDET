package resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

//import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataFromExcl {

	public FileInputStream fis;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	Iterator<Row> rows;
	Iterator<Row> temp;
	 Row r;
	 Row first;
	public DataFromExcl() throws IOException
	{
		fis=new FileInputStream("E:\\eclipse\\workspace\\SDET\\excelsample.xlsx");
		 wb=new XSSFWorkbook(fis);
		 sheet=wb.getSheet("Sheet1");
		 rows= sheet.iterator();
		 temp=sheet.iterator();
		rows.next();
		
		
		
	}
	@Test
	public ArrayList<String> getData() throws IOException
	{
		ArrayList<String> al=new ArrayList<String>();
		
		r= rows.next();	
		//while(rows.hasNext())
		//{
			
			
			Iterator<Cell> c=r.cellIterator();
			c.next();
			while(c.hasNext())
			{
				
				Cell ce=c.next();
				if(ce.getCellType()==CellType.STRING)
				al.add(ce.getStringCellValue());
				else
				{
					al.add(NumberToTextConverter.toText(ce.getNumericCellValue()));
					//System.out.println(ce.getNumericCellValue());
					//al.add(ce.getNumericCellValue());
				}
				}
				return al;
			
				//	}
		//return al;
		
		//return al;
		}
	@Test()
	public int getRowCount()
	{
		int i=0;
		while(temp.hasNext())
		{
			
			i++;
			temp.next();
		}
		
		return i;
		
	}
}
