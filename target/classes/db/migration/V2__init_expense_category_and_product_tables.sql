create table expenses(
    id int primary key AUTO_INCREMENT,
    expense_date date,
    created_time date,
    record_owner_id int,
    payment_method varchar(20),
    expense_category varchar(20),
    payment_amount decimal,
    note varchar(120)
);
create table incomes(
   id int primary key AUTO_INCREMENT,
   income_date date,
   created_time date,
   record_owner_id int,
   payment_method varchar(20),
   income_category varchar(20),
   payment_amount decimal,
   note varchar(120)
);

alter table incomes add foreign key(record_owner_id) references users(id);
alter table expenses add foreign key(record_owner_id) references users(id);