-- Retrieve the number of cities available for each country in the city table. Show the count of cities available for each country. Display the results for the first 15 countries in ascending order.
-- Write Query Here
SELECT 
    country_id, 
    COUNT(*) AS "Number of Cities" 
FROM city 
GROUP BY country_id 
ORDER BY country_id 
LIMIT 15;
