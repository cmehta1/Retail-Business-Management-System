--trigger to check the qoh

create or replace trigger check_qoh
before insert or update on purchases
for each row
declare
quantity_exceeding exception;
prod_qoh products.qoh%type;
begin
select qoh into prod_qoh
from products prod
where prod.pid=:new.pid;
--checking if the quantity entered is less than threshold
if(:new.qty>=prod_qoh) then raise quantity_exceeding;
end if;
exception 
when quantity_exceeding then
raise_application_error(-20001,'Insufficient quantity in stock');
end;
/
