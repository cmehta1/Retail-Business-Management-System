create or replace package monthly_report as
type sales_cursor is ref cursor;
function report_monthly_sale(prod_id in products.pid%type)return sales_cursor;
end;
/
create or replace package body monthly_report as
function report_monthly_sale(prod_id in products.pid%type)
return sales_cursor as sc sales_cursor;
begin
open sc for
select p.pname,sum(pu.qty) as totalqty, to_char(pu.ptime,'mon') as month,to_char(pu.ptime,'yyyy') as year, 
	sum(pu.total_price) as total_qty_price,
	avg(pu.total_price/qty) as avg_price   
	from purchases pu, products p
	where p.pid = prod_id and p.pid = pu.pid
	group by to_char(pu.ptime,'mon'),to_char(pu.ptime,'yyyy'),p.pname ;
return sc;
end;
end;
/



