package org.openmrs.module.spreadsheetimport;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Willa aka Baba Imu on 9/25/18.
 */
public class SpreadsheetImportTemplatePrespecifiedValueTest {
    @Test
    public void ensureEqualsWorksWithNullTableDotColumnField() {
        SpreadsheetImportTemplatePrespecifiedValue tpv = new SpreadsheetImportTemplatePrespecifiedValue();
        SpreadsheetImportTemplatePrespecifiedValue otherTpv = new SpreadsheetImportTemplatePrespecifiedValue();
        otherTpv.setTableDotColumn("concept.concept_id");
        Assert.assertFalse(tpv.equals(otherTpv));
    }
}
