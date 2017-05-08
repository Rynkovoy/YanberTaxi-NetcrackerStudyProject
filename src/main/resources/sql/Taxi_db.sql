INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (1,NULL,NULL,'Drivers',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (2,NULL,NULL,'Clients',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (3,NULL,NULL,'Orders',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (4,NULL,NULL,'Cars',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (5,NULL,NULL,'Orders_status',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (6,NULL,NULL,'Drivers_status',NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) VALUES (7,NULL,NULL,'Routes',NULL);

INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (1,1,NULL,NULL,'Last_name');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (2,1,NULL,NULL,'First_name');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (3,1,NULL,NULL,'Middle_name');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (4,1,NULL,NULL,'Phone_number');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (5,1,NULL,NULL,'Email');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (6,1,NULL,NULL,'Hire_date');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (7,1,NULL,NULL,'Experience');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (8,1,NULL,NULL,'Rating');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (9,1,NULL,NULL,'Status');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (10,1,NULL,NULL,'Unbun_date');



INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (11,2,NULL,NULL,'Last_name');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (12,2,NULL,NULL,'First_name');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (13,2,NULL,NULL,'Middle_name');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (14,2,NULL,NULL,'Phone_number');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (15,2,NULL,NULL,'Points');


INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (16,3,2,NULL,'Client_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (17,3,1,NULL,'Driver_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (18,3,NULL,NULL,'Status');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (19,3,NULL,NULL,'Cost');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (20,3,NULL,NULL,'Distance');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (21,3,NULL,NULL,'Driver_rating');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (22,3,NULL,NULL,'Driver_memo');


INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (23,4,NULL,NULL,'Name');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (24,4,NULL,NULL,'Model_type');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (25,4,NULL,NULL,'Prodaction_date');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (26,4,NULL,NULL,'Seats_count');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (27,4,NULL,NULL,'Child_seat');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (28,4,1,NULL,'Driver_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (29,4,NULL,NULL,'State_number');


INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (30,5,3,NULL,'Order_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (31,5,NULL,NULL,'Status');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (32,5,NULL,NULL,'Timestamp');


INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (33,6,1,NULL,'Driver_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (34,6,NULL,NULL,'Status');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (35,6,NULL,NULL,'Timestamp');


INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (36,7,3,NULL,'Order_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (37,7,NULL,NULL,'Check_point');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME) VALUES (38,7,NULL,NULL,'Show_order');

INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (9,1,'approval');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (9,2,'off dut');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (9,3,'free');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (9,4,'on call');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (9,5,'perform order');

INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (18,6,'new');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (18,7,'acceptedt');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (18,8,'performed');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (18,9,'performing');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (18,10,'canceled');

INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (31,11,'new');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (31,12,'acceptedt');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (31,13,'performed');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (31,14,'performing');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (31,15,'canceled');

INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (34,16,'approval');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (34,17,'off dut');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (34,18,'free');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (34,19,'on call');
INSERT INTO LISTS (attr_id,list_value_id,value) VALUES (34,20,'perform order');

create or replace trigger object_id_trg
before insert on objects
for each row
begin
if :new.object_id is null then
select nvl(MAX(object_id),0)+1 into :new.object_id from objects;
end if;
end;
/

/*������ �������� ��� �������� ������ �������*/

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (11,NULL,1,'Driver Natasha',NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (1,11,'Natasha',NULL,NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (2,11,'Stavitskaya',NULL,NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (3,11,'Nick',NULL,NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (4,11,'0631486586',NULL,NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (5,11,'Natasha@gmail.com',NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (6,11,NULL,'01/08/2017',NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (7,11,4,NULL,NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (8,11,10,NULL,NULL);
INSERT INTO ATTRIBUTES(ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,list_value_id) VALUES (9,11,NULL,NULL,3);









