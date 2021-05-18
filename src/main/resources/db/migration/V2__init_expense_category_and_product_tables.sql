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
    product_id int,
    paymentAmount decimal
);

alter table products add foreign key(category_name) references categories(name);
alter table expenses add foreign key(product_id) references products(id);