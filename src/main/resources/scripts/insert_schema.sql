use petbank;

insert into `user` (`first_name`, `last_name`,`email`, `password`, `isActive`, `role`) values
('Сергій', 'Адміненко','admin@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'ADMIN'),
('Папа', 'Карло', 'user1@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'CUSTOMER'),
('Буратіно','Карло', 'user2@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'CUSTOMER'),
('Федір', 'Дядя', 'user3@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', false, 'CUSTOMER'),
('Дженіфер', 'Лопес', 'user4@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'CUSTOMER');

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

insert into `card`  (`card_name`,`number`,`card_condition`, `account_id`)  values
('UNIVERSAL', 1234,  'ACTIVE', 1),
('CREDIT', 1235, 'ACTIVE', 2),
('UNIVERSAL', 1236, 'ACTIVE', 3),
('CREDIT', 1237, 'BLOCKED', 4),
('UNIVERSAL', 1238, 'ACTIVE', 5),
('INTERNET', 1239, 'BLOCKED', 6),
('UNIVERSAL', 1240, 'PENDING', 7),
('UNIVERSAL', 1241, 'ACTIVE', 8),
('UNIVERSAL', 1242, 'BLOCKED', 9),
('CREDIT', 1243, 'PENDING', 10);

insert into `payment`  (`date`, `debit_account_id`,`credit_account_id`, `amount`, `description`, `status`)  values
('2019-08-20 11:00:00', 1, 2, 20.00, 'оплата за','SAVE'),
('2019-08-20 11:00:00', 3, 4, 15.80, 'оплата за','PAID'),
('2019-08-25 12:20:00', 4, 2, 25.00, 'оплата за','PAID'),
('2019-09-12 10:00:01', 3, 2, 20.30, 'оплата за','PAID');
