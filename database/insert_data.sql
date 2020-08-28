use electronicstore;

insert into role(code,name) values('ADMIN','admin');
insert into role(code,name) values('USER','user');

insert into users(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',1);
insert into users(username,password,fullname,status)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',1);
insert into users(username,password,fullname,status)
values('nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van b',1);
insert into users(username,password,fullname,status)
values('nguyenvanc','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van c',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);
INSERT INTO user_role(user_id,role_id) VALUES (3,2);
INSERT INTO user_role(user_id,role_id) VALUES (4,2);

insert into productcategory(code,name) values('IPHONE','Iphone');
insert into productcategory(code,name) values('SAMSUNG','Samsung');
insert into productcategory(code,name) values('OPPO','Oppo');

insert into feature(code,name) values('KIEU_MAN_HINH','Kiểu màn hình');
insert into feature(code,name) values('CHAT_LIEU_VO','Chất liệu vỏ');
insert into feature(code,name) values('TINH_NANG_CAMERA','Tính năng camera');

insert into featuredetail(code,name,featureid) values('SIEU_TRAN_VIEN','Siêu tràn viền',1);
insert into featuredetail(code,name,featureid) values('TRAN_VIEN','Tràn viền',1);
insert into featuredetail(code,name,featureid) values('KIM_LOAI','Kim loại',2);
insert into featuredetail(code,name,featureid) values('KIM_LOAI_VA_KINH','Kim loại và kính',2);
insert into featuredetail(code,name,featureid) values('CO_CAMERA_GOC_RONG','Có camera góc rộng',3);
insert into featuredetail(code,name,featureid) values('CO_CAMERA_XOA_PHONG','Có camera xóa phông',3);

insert into event(name,percent,code) values('Khuyến mãi 30/4,1/5',20,'discount_1');
insert into event(name,percent,code) values('Khuyến mãi sau dịch covid',50,'discount_2');
insert into event(name,percent,code) values('Không có khuyến mãi',0,'none');











