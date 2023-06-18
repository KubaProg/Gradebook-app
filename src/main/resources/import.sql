INSERT INTO users (login, password, role)
VALUES ('jakub', '{bcrypt}$2a$12$YyPMqSXuWWX7nLzzyIW6O.Uj32KJO4h4VHK6YDcB00WmXLSpaAVnK', 'STUDENT'),
       ('dominik', '{bcrypt}$2a$12$Z229lO.J4rcdoNKwKJiCDevl2hcv5Mf6FxxQvBL5knpKtBNZ0Z6le', 'TEACHER'),
       ('michal', '{bcrypt}$2a$12$komupxf.cy31oi.HLvUQKexyXH4N7Mbz6TCRe5nlHTI5HSHtmsxae', 'HEADMASTER'),
       ('arek', '{bcrypt}$2a$12$komupxf.cy31oi.HLvUQKexyXH4N7Mbz6TCRe5nlHTI5HSHtmsxae', 'STUDENT');
COMMIT;

INSERT INTO student (name, parent_number, surname, user_id)
VALUES ('jakub','123456','opielka',1),
       ('arek','567890','Banasik',4);

INSERT INTO teacher (name,salary, surname, user_id)
VALUES ('dominik',4300,'szczepanik',2);

INSERT INTO headmaster (id,name, surname, user_id)
VALUES (1, 'michal','moryc',3);

INSERT INTO Subject (teacher_id, name)
VALUES (1, 'Mathematics') , (1, 'Physics'), (1, 'English');

INSERT INTO student_subject (student_id, subject_id) VALUES (1, 3);

INSERT INTO grade (subject_id, student_id, teacher_id, numerical_value, description)
VALUES (3,1,1,4,'siemanko');
--
--


