create or replace package insert_purchases as
procedure add_purchases(e_id in purchases.eid%type,p_id in purchases.pid%type,c_id in purchases.cid%type,p_qty in purchases.qty%type);
procedure add_products(p_id in products.pid%type,p_name in products.pname%type,qoh_1 in products.qoh%type, qoh_threshold1 in 
products.qoh_threshold%type,orig in products.original_price%type,disc in products.discnt_rate%type);
end;
/

--procedure for adding a tuple in purchases

create or replace package body insert_purchases as
procedure add_purchases(e_id in purchases.eid%type,p_id in purchases.pid%type,c_id in purchases.cid%type,p_qty in purchases.qty%type)  
is
total_price purchases.total_price%type;
discount_rate products.discnt_rate%type;
original_price products.original_price%type;
pricereduced products.original_price%type;
discnt_price products.original_price%type;
--variable declarations
begin
select prod.discnt_rate,prod.original_price into discount_rate,original_price
from products prod
where prod.pid=p_id;
pricereduced := original_price * discount_rate;
discnt_price := original_price - pricereduced;
total_price := p_qty*discnt_price;
insert into purchases values(pur_#.nextval,e_id,p_id,c_id,p_qty,sysdate,total_price); 
end;

--procedure for adding tuple in products

procedure add_products(p_id in products.pid%type,p_name in products.pname%type,qoh_1 in products.qoh%type, qoh_threshold1 in
products.qoh_threshold%type,orig in products.original_price%type,disc in products.discnt_rate%type)
is
begin
insert into products values(p_id,p_name,qoh_1,qoh_threshold1,orig,disc);
end;
end;
/
show errors
