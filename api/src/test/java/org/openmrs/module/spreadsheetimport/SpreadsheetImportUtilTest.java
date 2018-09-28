package org.openmrs.module.spreadsheetimport;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.spreadsheetimport.service.SpreadsheetImportService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SpreadsheetImportUtilTest extends BaseModuleContextSensitiveTest {
    private static final String[] TEST_DATA_FILES = {
        "Test_template_dataset1.xml"
    };

    @Before
    public void setup() throws Exception {
        for(String testDataFile: TEST_DATA_FILES) {
            executeDataSet(testDataFile);
        }
    }

    @Test
    public void mergeDataInRowToTemplateColumns_shouldDoMergeAllDataToRightColumns() throws IOException, InvalidFormatException {
        InputStream is = new ClassPathResource("simple.xls").getInputStream();
        Workbook book = WorkbookFactory.create(is);

        Sheet sheet = book.getSheetAt(0);
        List<String> columnNames = SpreadsheetImportUtil.getColumnNamesFromFirstRow(sheet);
        // Get the only template in test data.
        SpreadsheetImportTemplate template = Context.getService(SpreadsheetImportService.class).getTemplateById(1);
        Map<UniqueImport, Set<SpreadsheetImportTemplateColumn>> uniqueImportSetMap = template.getMapOfUniqueImportToColumnSetSortedByImportIdx();
        Row secondRow = sheet.getRow(1);
        boolean rowHasData = SpreadsheetImportUtil.mergeDataInRowToMapUniqueImportToColumnsSetSortedByImportIdx(uniqueImportSetMap, secondRow, columnNames);

        assertTrue(rowHasData);
        assertEquals("Number of columns in spreadsheet", 11, columnNames.size());

        // Check if each value is correct.
        Collection<Set<SpreadsheetImportTemplateColumn>> columnSets = uniqueImportSetMap.values();
        for(Set<SpreadsheetImportTemplateColumn> columnSet: columnSets) {
            for(SpreadsheetImportTemplateColumn column: columnSet) {
                assertNotNull(column.getValue());
            }
        }
    }
}
