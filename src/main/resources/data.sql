--管理者テーブルデータ
INSERT INTO admins(name,password,authority,email,tel) VALUES('田中太郎','tatosan8',0,'tanaka@aaa.com','0256483159');
INSERT INTO admins(name,password,authority,email,tel) VALUES('鈴木次郎','suzuki777',1,'suzuki@bbb.com','0956412837');
INSERT INTO admins(name,password,authority,email,tel) VALUES('山本三郎','saburoo833',1,'saburo@yyy.com','0354816759');
INSERT INTO admins(name,password,authority,email,tel) VALUES('伊藤桃子','mokoko87',2,'momoko.ito@iii.com','0152664426');
INSERT INTO admins(name,password,authority,email,tel) VALUES('橋本健','ibaraki315',2,'takeru.hashimoto@hhh.com','0581643835');
INSERT INTO admins(name,password,authority,email,tel) VALUES('渡辺和子','si-sa-7878',3,'kazuko.watanabe@www.com','0736668946');

--宿泊カテゴリー
INSERT INTO categories(name) VALUES('ビジネス');
INSERT INTO categories(name) VALUES('リゾート');
INSERT INTO categories(name) VALUES('旅館');
INSERT INTO categories(name) VALUES('その他');

--部屋の種類
INSERT INTO room_types(id,name) VALUES(1,'シングル');
INSERT INTO room_types(id,name) VALUES(2,'ダブル');
INSERT INTO room_types(id,name) VALUES(3,'和室');
--風呂の種類
INSERT INTO bath_types(id,name) VALUES(1,'ユニットバス');
INSERT INTO bath_types(id,name) VALUES(2,'風呂');
INSERT INTO bath_types(id,name) VALUES(3,'大浴場');

--言語の種類
INSERT INTO languages(id,name) VALUES(1,'日本語');
INSERT INTO languages(id,name) VALUES(2,'日本語、英語');
INSERT INTO languages(id,name) VALUES(3,'日本語、中国語');
INSERT INTO languages(id,name) VALUES(4,'日本語、英語、中国語');

--宿泊施設
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(1,2,'スペシャルホテル','042-543-8795','東京都品川区',2,'アメニティ');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(1,3,'円楽','035-715-9465','大阪府堺市',1,'FREE Wi-Fi');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(1,1,'ロイヤルホテル','090-4601-3577','福岡県北九州市',1,'アメニティ');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(2,2,'オーシャンテラスリゾートホテル','087-543-9001','沖縄県石垣市',3,'FREE Wi-Fi、アメニティ');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(2,1,'スノーリゾートホテル','057-430-0597','北海道帯広市',1,'FREE Wi-Fi、アメニティ');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(2,2,'ディズニーホテル','048-139-7468','千葉県浦安市',4,'FREE Wi-Fi、アメニティ');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(3,1,'紅葉','0285-85-1657','栃木県那須塩原市',3,'アメニティ');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(3,2,'桜館','073-458-9139','神奈川県箱根町',4,'FREE Wi-Fi、アメニティ');
INSERT INTO accommodations(category_id, bath_id, name,tel,address,language_id,content) VALUES(3,3,'紫陽花','078-891-6725','青森県',1,'FREE Wi-Fi、アメニティ');

--顧客向け提供プラン
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,8000,1,'2026-02-17');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,16000,1,'2026-02-15');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,11000,1,'2026-10-05');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,7000,2,'2026-02-19');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,14000,2,'2025-11-05');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,10000,2,'2025-09-19');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,7500,3,'2025-12-07');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,15000,3,'2025-09-15');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,10500,3,'2025-10-03');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,20000,4,'2025-10-13');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,40000,4,'2025-12-07');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,30000,4,'2025-09-26');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,10000,2,'2025-09-20');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,36000,5,'2025-10-24');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,27000,5,'2025-09-10');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,50000,6,'2026-01-20');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,100000,6,'2026-01-23');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,75000,6,'2025-11-20');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,14000,7,'2025-07-12');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,28000,7,'2025-09-27');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,21000,7,'2025-10-20');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,25000,8,'2026-02-04');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,50000,8,'2026-02-25');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,38000,8,'2026-03-22');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(1,12500,9,'2025-07-19');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(2,25000,9,'2025-07-05');
INSERT INTO plans(room_id,price, accommodation_id,date) VALUES(3,19000,9,'2025-09-30');

--顧客
INSERT INTO users(name,tel,address,password,gender,nickname,email,birthday) VALUES('佐藤花子','08045678912','東京都港区','hanatan123',1,'はなたん','hanako.sato@sss.com','1990-01-01');
INSERT INTO users(name,tel,address,password,gender,nickname,email,birthday) VALUES('高橋一郎','08098732178','京都府宇治市','itiro-258',0,'いちろう','ichiro.takahashi@ttt.com','1995-02-01');

--予約
INSERT INTO reserves(user_id,plan_id,quantity,room_count) VALUES(1,1,1,2);
INSERT INTO reserves(user_id,plan_id,quantity,room_count) VALUES(1,15,2,1);
INSERT INTO reserves(user_id,plan_id,quantity,room_count) VALUES(2,7,1,2);
INSERT INTO reserves(user_id,plan_id,quantity,room_count) VALUES(2,25,2,1);
