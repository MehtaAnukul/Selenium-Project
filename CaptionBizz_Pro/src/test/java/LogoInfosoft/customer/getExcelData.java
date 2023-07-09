//package LogoInfosoft.customer;
//
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class getExcelData {
//    public static void main(String[] args) throws IOException {
//        String filePath = System.getProperty("user.home") + "\\Downloads" + "\\data.xlsx";
//        FileInputStream fis = new FileInputStream(filePath);
//        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheetAt(0);
//
//        int cellCount = sheet.getRow(0).getLastCellNum();
//        int rowCount = sheet.getPhysicalNumberOfRows();
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < cellCount; j++) {
//                System.out.print(sheet.getRow(i).getCell(j)+"\t");
//            }
//            System.out.println();
//        }
//    }
//}
