/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.spreadsheetimport.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.spreadsheetimport.SpreadsheetImportTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpreadsheetImportService extends OpenmrsService {
	
	SessionFactory getSessionFactory();
	
	@Transactional(readOnly = true)
	List<SpreadsheetImportTemplate> getAllTemplates();
	
	@Transactional(readOnly = true)
	SpreadsheetImportTemplate getTemplateById(Integer id);
	
	@Transactional
	SpreadsheetImportTemplate saveSpreadsheetImportTemplate(SpreadsheetImportTemplate template);
	
	@Transactional
	void deleteSpreadsheetImportTemplate(SpreadsheetImportTemplate template);
	
	String getPredfinedValueById(int id);
}
