INSERT INTO stretchy_parameter (parameter_name, parameter_variable, parameter_label, parameter_displayType, parameter_FormatType, parameter_default, special, selectOne, selectAll, parameter_sql, parent_id) VALUES ('Center', 'Center', 'Center', 'select', 'number', '0', NULL, NULL, NULL, 'select grp.id AS CENTER_ID,grp.display_name AS CENTER_NAME \r\nfrom m_group grp\r\n inner join m_staff stf on grp.staff_id=stf.id \r\nwhere grp.level_id=1 and (stf.id=\'${loanOfficerId}\' or \'-1\'=\'${loanOfficerId}\') order by CENTER_NAME\r\n\r\n', 6);

INSERT INTO stretchy_report ( report_name, report_type, report_subtype, report_category, report_sql, description, core_report, use_report) VALUES ('Custom Collection Sheet', 'Pentaho', NULL, 'Loan', NULL, NULL, 0, 1);

INSERT INTO stretchy_report_parameter ( report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Custom Collection Sheet'), (select sp.id from stretchy_parameter sp where sp.parameter_name='OfficeIdSelectOne'), 'Office');
INSERT INTO stretchy_report_parameter ( report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Custom Collection Sheet'), (select sp.id from stretchy_parameter sp where sp.parameter_name='Center'), 'Center');
INSERT INTO stretchy_report_parameter ( report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Custom Collection Sheet'), (select sp.id from stretchy_parameter sp where sp.parameter_name='loanOfficerIdSelectAll'), 'loan_officer');
INSERT INTO stretchy_report_parameter ( report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Custom Collection Sheet'), (select sp.id from stretchy_parameter sp where sp.parameter_name='endDateSelect'), 'meeting_date');

INSERT INTO stretchy_parameter (parameter_name, parameter_variable, parameter_label, parameter_displayType, parameter_FormatType, parameter_default, special, selectOne, selectAll, parameter_sql, parent_id) VALUES ('centerByBranch', 'Center', 'Center', 'select', 'number', '0', NULL, NULL, NULL, 'select ct.id,ct.display_name\r\nfrom m_group ct \r\ninner join m_office of on ct.office_id=of.id \r\nwhere ct.level_id=1 AND of.id=${officeId}     \r\norder by ct.display_name ', 5);

INSERT INTO stretchy_report (report_name, report_type, report_subtype, report_category, report_sql, description, core_report, use_report) VALUES ('Letter', 'Pentaho', NULL, 'Loan', NULL, NULL, 0, 1);

INSERT INTO stretchy_report_parameter (report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Letter'), (select sp.id from stretchy_parameter sp where sp.parameter_name='OfficeIdSelectOne'), 'Office');
INSERT INTO stretchy_report_parameter (report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Letter'), (select sp.id from stretchy_parameter sp where sp.parameter_name='startDateSelect'), 'from_date');
INSERT INTO stretchy_report_parameter (report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Letter'), (select sp.id from stretchy_parameter sp where sp.parameter_name='centerByBranch'), 'Center');
INSERT INTO stretchy_report_parameter (report_id, parameter_id, report_parameter_name) VALUES ((select sr.id From stretchy_report sr where sr.report_name='Letter'), select sp.id from stretchy_parameter sp where sp.parameter_name='endDateSelect'), 'to_date');



