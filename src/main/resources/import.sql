INSERT INTO users (id,login, password, role)
VALUES (1,'jakub', '{bcrypt}$2a$12$YyPMqSXuWWX7nLzzyIW6O.Uj32KJO4h4VHK6YDcB00WmXLSpaAVnK', 'STUDENT'),
       (2,'dominik', '{bcrypt}$2a$12$Z229lO.J4rcdoNKwKJiCDevl2hcv5Mf6FxxQvBL5knpKtBNZ0Z6le', 'TEACHER'),
       (3,'michal', '{bcrypt}$2a$12$komupxf.cy31oi.HLvUQKexyXH4N7Mbz6TCRe5nlHTI5HSHtmsxae', 'HEADMASTER'),
       (4,'arek', '{bcrypt}$2a$12$komupxf.cy31oi.HLvUQKexyXH4N7Mbz6TCRe5nlHTI5HSHtmsxae', 'STUDENT');
COMMIT;


INSERT INTO student (id,name, parent_number, surname, user_id)
VALUES (1,'jakub','123456','opielka',1),
       (2,'arek','567890','Banasik',4);

INSERT INTO teacher (id,name,salary, surname, user_id)
VALUES (1,'dominik',4300,'szczepanik',2);

INSERT INTO headmaster (id,name, surname, user_id)
VALUES (1, 'michal','moryc',3);

INSERT INTO Subject (teacher_id, name)
VALUES (1, 'Mathematics') , (1, 'Physics'), (1, 'English');

INSERT INTO student_subject (student_id, subject_id) VALUES (1, 3);

INSERT INTO grade (id,subject_id, student_id, teacher_id, numerical_value, description)
VALUES (1,3,1,1,4,'siemanko');




