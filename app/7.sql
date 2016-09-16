--creating trigger on inserting in purchases

set serveroutput on
create or replace trigger pur_trig
after insert on purchases
for each row
declare
qoh_t products.qoh%type;
threshold_t products.qoh_threshold%type;
last_visit_date_t customers.last_visit_date%type;
smin supply.sid%type;
temp products.qoh%type;
M products.qoh%type;
quan supply.quantity%type;
--declaring the variables
--updating the qoh and decrementing it by the quantity ordered
--updating the date
begin
	update products set qoh = qoh - :new.qty where pid= :new.pid;
	select qoh,qoh_threshold into qoh_t,threshold_t from products where pid = :new.pid;
	select last_visit_date into last_visit_date_t from customers where cid= :new.cid;
	if(TO_CHAR(last_visit_date_t,'dd-Mon-yyyy')!=TO_CHAR(SYSDATE,'dd-Mon-yyyy')) then
		update customers set visits_made = visits_made+1, last_visit_date=sysdate where cid = :new.cid;
	end if;
	-- if the quantity updated is lesser than therehold, supply tuple is inserted and the supplier with least id is selected
	if(qoh_t < threshold_t) then
		dbms_output.put_line('quantity is less than threshold');
		dbms_output.put_line('qoh:' ||qoh);
		select min(sid) into smin from supply where pid= :new.pid;
		temp := threshold_t-qoh_t;
		M := temp+1;
		quan := M+10+qoh_t;
		insert into supply values(sup_#.nextval, :new.pid,smin,SYSDATE,quan);
		qoh_t:=qoh_t+quan;
		update products set qoh=qoh_t where pid= :new.pid;
		dbms_output.put_line('new qoh is :' ||qoh_t);
	end if;
end;
/
show errors
