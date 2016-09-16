
/*Here we have created sequence pur_# to get value os purchase id starting from 100000 and incremented by 1 every time we need to enter value of it into the table*/
create sequence pur_# minvalue 100000 maxvalue 999999 start with 100000 increment by 1;

/*Here we have created sequence sup_# to get value os supplier id starting from 1000 and incremented by 1 every time we need to enter value of it into the table*/
create sequence sup_# minvalue 1000 maxvalue 9999 start with 1000 increment by 1;

/*Here we have created sequence pur_# to get value os log id starting from 10000 and incremented by 1 every time we need to enter value of it into the table*/
create sequence log_# minvalue 10000 maxvalue 99999 start with 10000 increment by 1;
