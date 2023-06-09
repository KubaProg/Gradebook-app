INSERT INTO users (id,login, password, role)
VALUES (1,'jakub', '{bcrypt}$2a$12$YyPMqSXuWWX7nLzzyIW6O.Uj32KJO4h4VHK6YDcB00WmXLSpaAVnK', 'STUDENT'),
    (2,'dominik', '{bcrypt}$2a$12$Z229lO.J4rcdoNKwKJiCDevl2hcv5Mf6FxxQvBL5knpKtBNZ0Z6le', 'TEACHER'),
    (3,'michal', '{bcrypt}$2a$12$komupxf.cy31oi.HLvUQKexyXH4N7Mbz6TCRe5nlHTI5HSHtmsxae', 'HEADMASTER');
COMMIT;


INSERT INTO student (id,name, parent_number, surname, user_id)
VALUES (1,'jakub','123456','opielka',1);

INSERT INTO teacher (id,name,salary, surname, user_id)
VALUES (2,'dominik',4300,'szczepanik',2);

INSERT INTO headmaster (id,name, surname, user_id)
VALUES (3, 'michal','moryc',3);

INSERT INTO Subject (teacher_id, name)
VALUES (2, 'Mathematics') , (2, 'Physics'), (2, 'WALENIE KONIA');
