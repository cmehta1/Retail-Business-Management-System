--trigger for inserting in purchases

set serveroutput on
create or replace trigger InsertPurchase 
after insert on purchases
for each row
begin   
insert into logs values(log_#.nextval,'user',sysdate,'purchases','insert',:new.pur#);
end;
/ 
show errors

--trigger for updating products

set serveroutput on
create or replace trigger UpdateProducts 
after update of qoh on products for each row
begin
insert into logs
values(log_#.nextval,'user',sysdate,'products','update',:new.pid);
end;

/
show errors

--trigger for updating customers

set serveroutput on
create or replace trigger UpdateCustomers 
after update of visits_made on customers
for each row
begin
insert into logs values(log_#.nextval,'user',sysdate,'customers','update',:new.cid) ;
end;
/
show errors

--trigger for inserting in supply

set serveroutput on
create or replace trigger InsertSupply
after insert  on supply
for each row
begin
insert into logs values(log_#.nextval,'user',sysdate,'supply','insert',:new.sup#) ;
end;
/ 
show errors

