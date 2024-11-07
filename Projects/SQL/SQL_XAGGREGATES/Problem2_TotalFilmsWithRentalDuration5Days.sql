-- Determine the total number of films available for rent, but only count those with a rental duration of 5 days and rename it to Total Film.
-- Write Query Here
SELECT 
    COUNT(*) AS "Total Film" 
FROM film 
WHERE rental_duration = 5;
