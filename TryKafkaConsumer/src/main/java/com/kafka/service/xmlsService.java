package com.kafka.service;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.kafka.model.Sms;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class xmlsService {
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public int updateExcell(XSSFSheet sheet,Sms sms,int lastRowNum) {
		Cell cell;
		Row row ;
		int cellNum=0;
		int rowNum=lastRowNum;
		boolean isFound=false;
		for(int i=0;i<lastRowNum && !isFound;i++) {
			row=sheet.getRow(i);
			System.out.println((row.getCell(0).getNumericCellValue()-sms.getId()));
			if(Math.abs((row.getCell(0).getNumericCellValue()-sms.getId()))<0.5) {
				System.out.println((row.getCell(0).getNumericCellValue()-sms.getId()));
				cellNum=1;
				rowNum=i;
				isFound=true;
			}
		}
		if(!isFound) {
	        row = sheet.createRow(rowNum++);
	        cell=row.createCell(cellNum++);
	        cell.setCellValue((Integer)sms.getId());
	        cell=row.createCell(cellNum++);
	        cell.setCellValue((String)sms.getSmsPhoneNumber());
	        cell=row.createCell(cellNum++);
	        cell.setCellValue((String)sms.getMessage());
	        cellNum=0;
		}else {
			row=sheet.getRow(rowNum++);
			row.getCell(cellNum++).setCellValue((String)sms.getSmsPhoneNumber());
			row.getCell(cellNum++).setCellValue((String)sms.getMessage());
			cellNum=0;
		}
		return rowNum;
	}
	
	public boolean generateExcell(List<Sms> smsList) {
		///home/minhaz.uddin/Documents/workspace-sts-3.9.0.RELEASE/TryKafkaConsumer/2019-07-16.xlsx
		File f = new File(""+getDate()+".xlsx");
		 int rowNum=0;
		 XSSFWorkbook workbook = null;
		 XSSFSheet sheet = null;
		if (f.isFile() && f.canRead()) {
			log.debug("File Found");
			System.out.println("File Found");
		  try {
		    FileInputStream myxls = new FileInputStream(f);
		    workbook = new XSSFWorkbook(myxls);
		    sheet = workbook.getSheetAt(0);
		    rowNum=sheet.getLastRowNum()+1;
		    System.out.println("____________________Last Row Number:"+rowNum);
		
		  } catch (IOException ex) {
			  log.error(ex.toString());
		  }
		}else {
			log.debug("FileNot Found");
			System.out.println("FileNot Found\"");

			 workbook = new XSSFWorkbook(); 
			 sheet = workbook.createSheet("smsList"); 	
		}
		if(!workbook.equals(null) && !sheet.equals(null)) {
	        for(Sms sms:smsList) {
	        rowNum=updateExcell(sheet,sms,rowNum);
	        
	    } 
	        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
	        try { 
	        	log.debug(""+getDate()+".xlsx");
	        	System.out.println(""+getDate()+".xlsx");
	            FileOutputStream out = new FileOutputStream(new File(""+getDate()+".xlsx")); 
	            workbook.write(out); 
	            out.close(); 
	            log.debug("xlsx successfully written on disk.");
	            System.out.println("xlsx successfully written on disk.");
	            System.out.println(""); 
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
			return true;
		}else {
			log.error("\nError And Return False");
			System.out.println("\nError And Return False");
			return false;
		}


		
	}
}
