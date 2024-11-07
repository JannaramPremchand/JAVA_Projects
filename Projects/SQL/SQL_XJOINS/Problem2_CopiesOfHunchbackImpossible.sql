-- How many copies of the film "Hunchback Impossible" exist in the inventory system?
-- Write Query Here
SELECT COUNT(i.inventory_id) AS "Number of Copies"
FROM inventory i
JOIN film f ON i.film_id = f.film_id
WHERE f.title = 'Hunchback Impossible';
