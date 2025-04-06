-- 기능 정의
INSERT INTO feature (name, limit_amount, credit_per_use) VALUES
('AI 번역', 2000, 10),
('AI 교정', 1000, 10),
('AI 뉘앙스 조절', 1500, 20),
('AI 초안 작성', 200, 50);

-- 요금제 정의
INSERT INTO plan (name) VALUES ('기본 요금제');

-- 요금제와 기능 연결
INSERT INTO plan_features (plan_id, feature_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4);

-- 회사 정보 정의
INSERT INTO company (name, credit, current_plan_id) VALUES
('A사', 5000, 1),
('B사', 10000, 1),
('C사', 10000, 1);
