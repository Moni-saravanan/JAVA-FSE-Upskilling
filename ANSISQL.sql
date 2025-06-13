DROP DATABASE CTS;
CREATE database CTS;
USE CTS;
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,       
    full_name VARCHAR(100) NOT NULL,              
    email VARCHAR(100) UNIQUE NOT NULL,          
    city VARCHAR(100) NOT NULL,                   
    registration_date DATE NOT NULL               
);

CREATE TABLE events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,                         
    title VARCHAR(200) NOT NULL,                                      
    description TEXT,                                                
    city VARCHAR(100) NOT NULL,                                      
    start_date DATETIME NOT NULL,                                    
    end_date DATETIME NOT NULL,                                       
    status ENUM('upcoming', 'completed', 'cancelled') DEFAULT 'upcoming', 
    organizer_id INT,                                                
    FOREIGN KEY (organizer_id) REFERENCES users(user_id)            
);

CREATE TABLE sessions (
    session_id INT PRIMARY KEY AUTO_INCREMENT,              
    event_id INT NOT NULL,                                   
    title VARCHAR(200) NOT NULL,                             
    speaker_name VARCHAR(100) NOT NULL,                     
    start_time DATETIME NOT NULL,                            
    end_time DATETIME NOT NULL,                             
    FOREIGN KEY (event_id) REFERENCES events(event_id)      
);
CREATE TABLE registrations (
    registration_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    registration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    UNIQUE (user_id, event_id) 
);
CREATE TABLE feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comments TEXT,
    feedback_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    UNIQUE (user_id, event_id) 
);
CREATE TABLE resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,                   
    event_id INT NOT NULL,                                        
    resource_type ENUM('pdf', 'image', 'link') NOT NULL,          
    resource_url VARCHAR(255) NOT NULL,                           
    uploaded_at DATETIME NOT NULL,                               
    FOREIGN KEY (event_id) REFERENCES events(event_id)            
);

INSERT INTO users (full_name, email, city, registration_date)
VALUES 
('Alice Johnson', 'alice@example.com', 'New York', '2024-12-01'),
('Bob Smith', 'bob@example.com', 'Los Angeles', '2024-12-05'),
('Charlie Lee', 'charlie@example.com', 'Chicago', '2024-12-10'),
('Diana King', 'diana@example.com', 'New York', '2025-01-15'),
('Ethan Hunt', 'ethan@example.com', 'Los Angeles', '2025-02-01');

INSERT INTO events (title, description, city, start_date, end_date, status, organizer_id)
VALUES
('Tech Innovators Meetup', 'A meetup for tech enthusiasts.', 'New York', '2025-06-10 10:00:00', '2025-06-10 16:00:00', 'upcoming', 1),
('AI & ML Conference', 'Conference on AI and ML advancements.', 'Chicago', '2025-05-15 09:00:00', '2025-05-15 17:00:00', 'completed', 3),
('Frontend Development Bootcamp', 'Hands-on training on frontend tech.', 'Los Angeles', '2025-07-01 10:00:00', '2025-07-03 16:00:00', 'upcoming', 2);

INSERT INTO sessions (event_id, title, speaker_name, start_time, end_time)
VALUES
(1,'Opening Keynote', 'Dr. Tech', '2025-06-10 10:00:00', '2025-06-10 11:00:00'),
(2,'Future of Web Dev', 'Alice Johnson', '2025-06-10 11:15:00', '2025-06-10 12:30:00'),
(3,'AI in Healthcare', 'Charlie Lee', '2025-05-15 09:30:00', '2025-05-15 11:00:00'),
(4,'Intro to HTML5', 'Bob Smith', '2025-07-01 10:00:00', '2025-07-01 12:00:00');

INSERT INTO registrations (user_id, event_id, registration_date)
VALUES
(1, 1, '2025-05-01'),
(2, 1, '2025-05-02'),
(3, 2, '2025-04-30'),
(4, 2, '2025-04-28'),
(5, 3, '2025-06-15');

INSERT INTO feedback (user_id, event_id, rating, comments, feedback_date)
VALUES
(3, 2, 4, 'Great insights!', '2025-05-16'),
(4, 2, 5, 'Very informative.', '2025-05-16'),
(2, 1, 3, 'Could be better.', '2025-06-11');

/* 1 User Upcoming Events*/
SELECT e.event_id,
       e.title,
       e.description,
       e.city,
       e.start_date,
       e.end_date,
       e.status
FROM events e
JOIN registrations r
  ON e.event_id = r.event_id
JOIN users u
  ON r.user_id = u.user_id
WHERE u.user_id = 1
  AND e.status = 'upcoming'
  AND e.city = u.city
ORDER BY e.start_date ASC;

    /*2 Top Rated Events*/
    SELECT e.event_id,
       e.title,
       AVG(f.rating) AS average_rating,
       COUNT(f.feedback_id) AS feedback_count
FROM events e
JOIN feedback f
  ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY average_rating DESC;

/*3.Inactive Users*/

SELECT u.user_id,
       u.full_name,
       u.email,
       u.city,
       u.registration_date
FROM users u
LEFT JOIN registrations r
  ON u.user_id = r.user_id
  AND r.registration_date >= CURDATE() - INTERVAL 90 DAY
WHERE r.registration_id IS NULL;

/*4.Peak Session Hours*/
SELECT 
    event_id,
    COUNT(session_id) AS sessions_count
FROM sessions
WHERE 
    TIME(start_time) >= '10:00:00'
    AND TIME(end_time) <= '12:00:00'
GROUP BY event_id;

/*5Most Active Cities*/
SELECT 
    u.city,
    COUNT(DISTINCT r.user_id) AS distinct_user_count
FROM users u
JOIN registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY distinct_user_count DESC
LIMIT 5;

/*6Event Resource Summary*/
SELECT 
    event_id,
    resource_type,
    COUNT(resource_id) AS resource_count
FROM resources
GROUP BY event_id, resource_type
ORDER BY event_id, resource_type;

/*7Low Feedback Alerts*/
SELECT 
    u.user_id,
    u.full_name,
    f.rating,
    f.comments,
    e.title AS event_name
FROM feedback f
JOIN users u ON f.user_id = u.user_id
JOIN events e ON f.event_id = e.event_id
WHERE f.rating < 3;

/*8 Sessions per Upcoming Event*/
SELECT 
    e.event_id,
    e.title,
    e.start_date,
    e.end_date,
    e.status,
    COUNT(s.session_id) AS session_count
FROM events e
LEFT JOIN sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title, e.start_date, e.end_date, e.status
ORDER BY e.start_date ASC;

/*9 Organizer Event Summary*/
SELECT 
    u.user_id,
    u.full_name,
    e.status,
    COUNT(e.event_id) AS event_count
FROM users u
JOIN events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name, e.status
ORDER BY u.user_id, e.status;

/*10. Feedback Gap*/
SELECT e.event_id,
       e.title
FROM events e
JOIN registrations r ON e.event_id = r.event_id
LEFT JOIN feedback f ON e.event_id = f.event_id
WHERE f.feedback_id IS NULL
GROUP BY e.event_id, e.title;

/*11 Daily New User Count*/
SELECT 
    registration_date,
    COUNT(DISTINCT user_id) AS users_registered
FROM registrations
WHERE registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY registration_date
ORDER BY registration_date ASC;

/*12. Event with Maximum Sessions*/
SELECT 
    e.event_id,
    e.title,
    COUNT(s.session_id) AS session_count
FROM events e
JOIN sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title
HAVING session_count = (
    SELECT MAX(session_count_sub)
    FROM (
        SELECT COUNT(session_id) AS session_count_sub
        FROM sessions
        GROUP BY event_id
    ) AS subquery
)
;

/*13. Average Rating per City*/
SELECT 
    e.city,
    AVG(f.rating) AS average_rating
FROM events e
JOIN feedback f ON e.event_id = f.event_id
GROUP BY e.city
ORDER BY average_rating DESC;

/*14 . Most Registered Events*/
SELECT 
    e.event_id,
    e.title,
    COUNT(r.registration_id) AS total_registrations
FROM events e
JOIN registrations r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title
ORDER BY total_registrations DESC
LIMIT 3;

/*15 Event Session Time Conflict*/
SELECT 
    s1.event_id,
    s1.session_id AS session1_id,
    s1.title AS session1_title,
    s1.start_time AS session1_start,
    s1.end_time AS session1_end,
    s2.session_id AS session2_id,
    s2.title AS session2_title,
    s2.start_time AS session2_start,
    s2.end_time AS session2_end
FROM sessions s1
JOIN sessions s2
  ON s1.event_id = s2.event_id
  AND s1.session_id < s2.session_id
  AND s1.start_time < s2.end_time
  AND s1.end_time > s2.start_time
ORDER BY s1.event_id, s1.session_id, s2.session_id;

/*16Unregistered Active Users*/
SELECT 
    u.user_id,
    u.full_name,
    u.email,
    u.registration_date
FROM users u
LEFT JOIN registrations r ON u.user_id = r.user_id
WHERE u.registration_date >= CURDATE() - INTERVAL 30 DAY
  AND r.registration_id IS NULL;
  
  /*17 Multi-Session Speakers*/
  SELECT 
    speaker_name,
    COUNT(session_id) AS session_count
FROM sessions
GROUP BY speaker_name
HAVING session_count > 1;

/*18 Resource Availability Check*/
SELECT 
    e.event_id,
    e.title
FROM events e
LEFT JOIN resources r ON e.event_id = r.event_id
WHERE r.resource_id IS NULL;

/*19 Completed Events with Feedback Summary*/
SELECT 
    e.event_id,
    e.title,
    COUNT(f.feedback_id) AS total_feedbacks,
    ROUND(AVG(f.rating), 2) AS average_rating
FROM events e
LEFT JOIN feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title
ORDER BY average_rating DESC;

/*20 User Engagement Index*/
SELECT 
    u.user_id,
    u.full_name,
    COUNT(DISTINCT r.event_id) AS events_attended,
    COUNT(f.feedback_id) AS feedbacks_submitted
FROM users u
LEFT JOIN registrations r ON u.user_id = r.user_id
LEFT JOIN feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY u.user_id;

/*21 Top Feedback Providers*/
SELECT 
    u.user_id,
    u.full_name,
    COUNT(f.feedback_id) AS feedback_count
FROM users u
JOIN feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY feedback_count DESC
LIMIT 5;

/*22 Duplicate Registrations Check*/
SELECT 
    user_id,
    event_id,
    COUNT(*) AS registration_count
FROM registrations
GROUP BY user_id, event_id
HAVING registration_count > 1;

/*23 Registration Trends*/

    SELECT 
    DATE_FORMAT(registration_date, '%Y-%m') AS reg_month,
    COUNT(*) AS registration_count
FROM registrations
WHERE registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY DATE_FORMAT(registration_date, '%Y-%m')
ORDER BY DATE_FORMAT(registration_date, '%Y-%m');

/*24 Average Session Duration per Event*/

SELECT 
    event_id,
    AVG(TIMESTAMPDIFF(MINUTE, start_time, end_time)) AS avg_session_duration_minutes
FROM sessions
GROUP BY event_id;

/*25 Events Without Sessions*/

SELECT 
    e.event_id,
    e.title
FROM events e
LEFT JOIN sessions s ON e.event_id = s.event_id
WHERE s.session_id IS NULL;















    
    
    
    



