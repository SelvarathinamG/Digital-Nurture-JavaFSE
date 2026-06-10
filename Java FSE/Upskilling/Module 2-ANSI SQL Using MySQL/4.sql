SELECT 
    e.event_id,
    e.title,
    COUNT(CASE WHEN HOUR(s.start_time) >= 10 AND HOUR(s.start_time) < 12 THEN 1 END) AS sessions_10am_to_12pm
FROM events e
LEFT JOIN sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title
ORDER BY sessions_10am_to_12pm DESC, e.event_id ASC;
