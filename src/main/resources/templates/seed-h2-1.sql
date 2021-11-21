INSERT INTO USERS (id,USERNAME,FULL_NAME,PASSWORD)
	                VALUES (1,'first_user','First User','secret' );

INSERT INTO estimator (e_key, us_key, e_name, e_admin, visible) VALUES(1, 1, 'Administrator', 1, 1);

INSERT INTO company (c_key, c_name, c_desc, address1, address2, contact, visible, is_default)
VALUES(1, 'First LLC', '', '128 ABC St.', 'Durham, NC 27519', 'Contact Me', 1, 1);

INSERT INTO `location` (el_key, address, city, state, zip) VALUES(1, '1030 5th Avenue', 'Cary', 'NC', '27519');

INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(1, 1, 1, 1, '1030 5th Avenue - Pipe scaffolding', 0.0000,  'Draft', '2019-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(1, 1, '2021-03-12 21:42:25.000', 'Roof Project', 1, NULL, 0, 'Draft', NULL);

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(1, 1, 'Apply 1 Layer of Siplast Teranap- 1M Sand to the surfaces', 9000, '1)', 'A) Roof Replacement Revised 2021', 'Task', 1.000, 'Torch', 0.00, 0,'Default');

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(2, 1, 'existing window and door opening including new galvanized steel lintel and waterproofing.', 1750.00, '2)', 'A) Roof Replacement Revised 2021', 'Reconstruct', 2.000, 'Loc', 0.00, 0,'Default');

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(3, 1, 'and apply new liquid membrane over all base flashing up to the coping stone.', 22, '3)', 'A) Roof Replacement Revised 2021', 'Prepare', 1200.000, 'sf', 0.00, 0,'Default');

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(4, 1, 'and install new metal door with frame including 2 coats finish paint.', 1700.00, '4)', 'B) New Bathroom', 'Provide', 1.000, 'Loc', 0.00, 0,'Default');

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(5, 1, 'and install new light fixtures and 2 outlets.', 1, '5)', 'B) New Bathroom', 'Provide', 800.000, 'ls', 0.00, 0,'Default');


INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(2, 1, 1, 1, '234 13th Court - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(2, 2, '2021-03-12 21:42:25.000', 'Another Root Project', 1, NULL, 0, 'Draft', NULL);

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(6, 2, 'Apply 1 Layer - 1M Sand to the surfaces', 9000, '1)', 'A) Roof Replacement Revised', 'Task', 1.000, 'Torch', 0.00, 0,'Default');

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(7, 2, 'window and door opening including new galvanized steel lintel and waterproofing.', 1750.00, '2)', 'A) Roof Replacement Revised', 'Reconstruct', 2.000, 'Loc', 0.00, 0,'Default');

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(8, 2, 'new liquid membrane over all base flashing up to the coping stone.', 22, '3)', 'A) Roof Replacement Revised', 'Prepare', 1200.000, 'sf', 0.00, 0,'Default');

INSERT INTO `task` (pst_key, pr_key, pst_descr, c_cost, pst_id, s_name, t_name, c_qty, c_unit, t_pr, t_pr_type, t_type)
VALUES(9, 2, 'new metal door with frame including 2 coats finish paint.', 1700.00, '4)', 'B) New Bathroom', 'Provide', 1.000, 'Loc', 0.00, 0,'Default');



INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(3, 1, 1, 1, '532 Landscape Court - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(3, 3, '2021-03-12 21:42:25.000', 'Another Root Project', 1, NULL, 0, 'Draft', NULL);


INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(4, 1, 1, 1, '2343 Moon Street - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(4, 4, '2021-04-12 21:42:25.000', 'Yet Another Root Project', 1, NULL, 0, 'Draft', NULL);

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(5 4, '2021-04-13 21:42:25.000', 'Change Order Root Project', 1, NULL, 0, 'Draft', NULL);


INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(5, 1, 1, 1, '30 Moon Street - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(6, 5, '2021-04-12 21:42:25.000', 'Yet Another Root Project', 1, NULL, 0, 'Draft', NULL);


INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(6, 1, 1, 1, '31 Moon Street - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(7, 6, '2021-04-12 21:42:25.000', 'Yet Another Root Project', 1, NULL, 0, 'Draft', NULL);

INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(7, 1, 1, 1, '32 Moon Street - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(8, 7, '2021-04-12 21:42:25.000', 'Yet Another Root Project', 1, NULL, 0, 'Draft', NULL);

INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(8, 1, 1, 1, '33 Moon Street - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(9, 1, 1, 1, '34 Moon Street - Test', 0.0000,  'Draft', '2020-07-15', '');



INSERT INTO `project` (pp_key, el_key, e_key, c_key, pp_name, tax, pp_status, pp_date, pp_owner)
VALUES(10, 1, 1, 1, '987 Testing Court - Test', 0.0000,  'Draft', '2020-07-15', '');

INSERT INTO `proposal` (pr_key, pp_key, pr_date, pr_name, editable, p_pr, p_pr_type, pr_status, authorize)
VALUES(9, 10, '2021-04-12 21:42:25.000', 'Another Root Project', 1, NULL, 0, 'Draft', NULL);
