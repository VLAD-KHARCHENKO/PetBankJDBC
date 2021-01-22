use petbank;

insert into `user` (`first_name`, `last_name`,`email`, `password`, `isActive`, `role`) values
('Сергій', 'Адміненко','admin@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'ADMIN'),
('Папа', 'Карло', 'user1@g.com', 'password', true, 'CUSTOMER'),
('Буратіно','Карло', 'user2@g.com', 'password', true, 'CUSTOMER'),
('Федір', 'Дядя', 'user3@g.com', 'password', false, 'CUSTOMER'),
('Дженіфер', 'Лопес', 'user4@g.com', 'password', true, 'CUSTOMER');

insert into `account`  (`number`, `balance`,`isActive`, `user_id`)  values
('UA26001', '123.76', true, 2),
('UA26002', '667.75', true, 2),
('UA26003', '2346.00', true, 3),
('UA26004', '464.84', true, 4),
('UA26005', '453.67', true, 5),
('UA26006', '3534.06', true, 3),
('UA26007', '5355.43', true, 2),
('UA26008', '0.00', false, 3),
('UA26009', '157647.56', true, 4),
('UA260010', '0.00', false, 5);

insert into `card`  (`card_name`,`isActive`, `account_id`)  values
('UNIVERSAL',  true, 1),
('CREDIT',  true, 2),
('UNIVERSAL',  true, 3),
('CREDIT',  true, 4),
('UNIVERSAL',  true, 5),
('INTERNET', true, 6),
('UNIVERSAL',  true, 7),
('ANOTHER',  false, 8),
('UNIVERSAL',  true, 9),
('CREDIT',  false, 10);

insert into `card_list`  ( `account_id`, `card_id`)  values
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

insert into `payment`  (`date`, `debit_account_id`,`credit_account_id`, `amount`, `description`, `status`)  values
('2019-08-20 11:00:00', 1, 2, 20.00, 'оплата за','SAVE'),
('2019-08-20 11:00:00', 3, 4, 15.80, 'оплата за','PAID'),
('2019-08-25 12:20:00', 4, 2, 25.00, 'оплата за','PAID'),
('2019-09-12 10:00:01', 3, 2, 20.30, 'оплата за','PAID');
