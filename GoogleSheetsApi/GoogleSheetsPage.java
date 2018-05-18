package googlesheetspage;

import base.CommonAPI;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static googleapis.GoogleSheetReader.getSheetsService;

public class GoogleSheetsPage extends CommonAPI {
    public List<List<Object>> getSpreadSheetRecords(String spreadsheetId, String range) throws IOException {
        // Build a new authorized API client service.
        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            return null;
        } else {
            return values;
        }
    }

    // AMZ_TS5: Use google sheets test data to search multiple items
    // AMZ_TS5_TC1: Search multiple items by name from a google sheets file
    public List<String> searchItemByName(String spreadsheetId, String range) throws IOException, InterruptedException {
        List<List<Object>> values = getSpreadSheetRecords(spreadsheetId, range);
        List<String> actual = new ArrayList<>();
        for (List row : values) {
            waitUntilClickAble(By.id("twotabsearchtextbox"));
            typeByIdNEnter("twotabsearchtextbox", row.get(1).toString());
            actual.add(getCurrentPageTitle());
            clearInputFieldById("twotabsearchtextbox");
        }
        return actual;
    }

    // AMZ_TS5_TC2: Search multiple items by bar-code from a google sheets file
    public List<String> searchItemByBarCode(String spreadsheetId, String range) throws IOException, InterruptedException {
        List<List<Object>> values = getSpreadSheetRecords(spreadsheetId, range);
        List<String> actual = new ArrayList<>();
        for (List row : values) {
            waitUntilClickAble(By.id("twotabsearchtextbox"));
            typeByIdNEnter("twotabsearchtextbox", row.get(1).toString());
            actual.add(getTextByXpath("//*[text()='" + row.get(2) + "']"));
            System.out.println(row.get(2));
            clearInputFieldById("twotabsearchtextbox");
        }
        return actual;
    }
}
