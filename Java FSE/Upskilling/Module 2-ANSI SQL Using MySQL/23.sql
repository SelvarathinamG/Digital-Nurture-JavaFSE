SELECT 
    DATE_FORMAT(r.registration_date, '%Y-%m') AS year_month,
    COUNT(r.registration_id) AS registrations_count
FROM registrations r
WHERE r.registration_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
    AND r.registration_date <= CURDATE()
GROUP BY DATE_FORMAT(r.registration_date, '%Y-%m')
ORDER BY year_month ASC;
