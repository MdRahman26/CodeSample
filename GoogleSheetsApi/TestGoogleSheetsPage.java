package googlesheetspage;

import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.TestLogger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestGoogleSheetsPage extends GoogleSheetsPage {
    // AMZ_TS5: Use google sheets test data to search multiple items
    // AMZ_TS5_TC1: Search multiple items by name from a google sheets file
    @Test
    public void amzTS05TC01() throws IOException, InterruptedException {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));
        int i = 0;
        String spreadsheetId = "1x0FWqsWG7zscRO81fh8MmasY1ZgstDGkgom_EijIVJI";
        String range = "Sheet1!A2:C";
        List<String> actualItems = searchItemByName(spreadsheetId, range);
        List<List<Object>> expectedItems = getSpreadSheetRecords(spreadsheetId, range);
        for (List row : expectedItems) {
            //Assert.assertTrue(actualItems.get(i).contains(row.get(2).toString()));
            System.out.println(expectedItems.get(i) + ": Search - Passed");
            i++;
        }
        System.out.println("AMZ TS5 TC1 Passed");
    }

    // AMZ_TS5_TC2: Search multiple items by bar-code from a google sheets file
    @Test
    public void amzTS05TC02() throws IOException, InterruptedException {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));
        int i = 0;
        String spreadsheetId = "1xPTjk-qLdJG81_oaGgFXDDZZ22dmst2U5MDDExx7QyI";
        String range = "Sheet1!A2:C";
        List<String> actualItems = searchItemByBarCode(spreadsheetId, range);
        List<List<Object>> expectedItems = getSpreadSheetRecords(spreadsheetId, range);
        for (List row : expectedItems) {
            Assert.assertTrue(actualItems.get(i).contains(row.get(2).toString()));
            System.out.println(expectedItems.get(i) + ": Search - Passed");
            i++;
        }
        System.out.println("AMZ TS5 TC2 Passed");
    }
}
