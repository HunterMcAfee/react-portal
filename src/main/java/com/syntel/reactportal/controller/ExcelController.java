package com.syntel.reactportal.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/file")
@RestController
public class ExcelController {

    @CrossOrigin
    @PostMapping("/read")
    public void readExcelFile(@RequestParam("files") MultipartFile[] excelFiles) throws IOException {
        for (MultipartFile file : excelFiles) {
            System.out.println(file.getContentType());
            InputStream inputStream = file.getInputStream();

            Workbook workbook = typedWorkbook(file, inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Map<Integer, List<String>> data = new HashMap<>();
            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());
                for (Cell cell : row) {
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data.get(i).add(cell.getDateCellValue() + "");
                            } else {
                                data.get(i).add(cell.getNumericCellValue() + "");
                            }
                            break;
                        case BOOLEAN:
                            data.get(i).add(cell.getBooleanCellValue() + "");
                            break;
                        case FORMULA:
                            data.get(i).add(cell.getCellFormula() + "");
                            break;
                        default:
                            data.get(new Integer(i)).add(" ");
                    }
                }
                i++;
            }
        }
    }

    public Workbook typedWorkbook(MultipartFile file, InputStream inputStream) throws IOException {
        if (file.getContentType().equalsIgnoreCase("application/vnd.ms-excel")) {
            return new HSSFWorkbook(inputStream);
        } else if (file.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return new XSSFWorkbook(inputStream);
        } else {
            return null;
        }
    }

}
