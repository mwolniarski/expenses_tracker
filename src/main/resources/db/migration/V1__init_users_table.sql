create table users(
    id int primary key AUTO_INCREMENT,
    email varchar(50),
    password varchar(120),
    username varchar(50),
    active bit,
    locked bit,
    expired bit
)