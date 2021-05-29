create table categories(
    name varchar(40) primary key
);
create table products(
    id int primary key AUTO_INCREMENT,
    category_name varchar(40),
    product_name varchar(40),
    description varchar(120)
);
create table expenses(
    id int primary key AUTO_INCREMENT,
    purchase_date date,
    paymentAmount decimal
);
create table product_lines(
    id int primary key AUTO_INCREMENT,
    product_id int,
    expense_id int,
    quantity int,
    unit_price decimal,
    total_price decimal
);

alter table products add foreign key(category_name) references categories(name);

alter table product_lines add foreign key(product_id) references products(id);
alter table product_lines add foreign key(expense_id) references expenses(id);