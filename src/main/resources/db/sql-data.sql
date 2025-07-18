INSERT INTO employee_tb (name, department, email)
VALUES ('ssar', '백엔드 개발자', 'hong@example.com'),
       ('cos', '프론트엔드 개발자', 'lee@example.com');

INSERT INTO course_tb (title, description, capacity, created_at)
VALUES ('Spring Boot 입문', 'Spring Boot의 기본 개념과 실습', 1, CURRENT_TIMESTAMP),
       ('JPA 심화', 'JPA 실무 적용 및 성능 최적화', 2, CURRENT_TIMESTAMP);


INSERT INTO application_tb (employee_id, course_id, applied_at)
VALUES
-- 직원 1번이 코스 1번, 2번 신청
(1, 1, NOW()),
(1, 2, NOW()),
-- 직원 2번도 코스 1번 신청
(2, 1, NOW());