create or replace package func_package as
type emp is ref cursor;
type cust is ref cursor;
type prod is ref cursor;
type sup is ref cursor;
type pur is ref cursor;
type supply is ref cursor;
type log is ref cursor;
type sales_cursor is ref cursor;
function show_emp return emp;
function show_cust return cust;
function show_prod return prod;
function show_sup return sup;
function show_pur return pur;
function show_logs return log;
function show_supply return supply;
function report_monthly_sale(prod_id in products.pid%type)return sales_cursor;
end;
/
show errors

-- procedure for show records in employees

create or replace package body func_package as
function show_emp
return emp as
showemp emp;
begin
open showemp for
select * from employees;
return showemp;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);

end;

-- procedure for show records in products

function show_prod
return prod as
showprod prod;
begin
open showprod for
select * from products;
return showprod;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);
end;

-- procedure for show records in customers

function show_cust
return cust as
showcust cust;
begin
open showcust for
select * from customers;
return showcust;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);

end;

-- procedure for show records in suppliers

function show_sup
return sup as
showsup sup;
begin
open showsup for
select * from suppliers;
return showsup;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);

end;

-- procedure for show records in purchases

function show_pur
return pur as
showpur pur;
begin
open showpur for
select * from purchases;
return showpur;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);
end;

-- procedure for show records in supply

function show_supply
return supply as
showsupply supply;
begin
open showsupply for
select * from supply;
return showsupply;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);
end;


-- procedure for show records in logs

function show_logs
return log as
showlog log;
begin
open showlog for
select * from logs;
return showlog;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);

end;


-- procedure for showing monthly reocrd for the product entered

function report_monthly_sale(prod_id in products.pid%type)
return sales_cursor as sc sales_cursor;
declare
c number;
begin
open sc for
select count(*) into c from products where pid=prod_id;
if(c=0) then 
raise_application_error(-20001, 'exception');
end if;
select p.pname, to_char(pu.ptime,'mon') as month,to_char(pu.ptime,'yyyy') as year, sum(pu.qty)as totalqty,
	sum(pu.total_price) as total_qty_price,
	avg(pu.total_price/qty) as avg_price   
	from purchases pu, products p
	where p.pid = prod_id and p.pid = pu.pid
	group by to_char(pu.ptime,'mon'),to_char(pu.ptime,'yyyy'),p.pname ;
return sc;
EXCEPTION
WHEN OTHERS THEN
dbms_output.put_line( 'SQL Error: ' || ' SQLCODE=' || SQLCODE || 'SQLERRM=' || SQLERRM);

end;

end;

/
show errors







