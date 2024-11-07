-- Write a query to find films that have the same rental duration and replacement cost as the film "ACADEMY DINOSAUR". Use a multiple-column subquery.
-- Write Query Here
SELECT 
    f.title, 
    f.rental_duration, 
    f.replacement_cost
FROM 
    film f
WHERE 
    (f.rental_duration, f.replacement_cost) IN (
        SELECT 
            rental_duration, 
            replacement_cost
        FROM 
            film
        WHERE 
            title = 'ACADEMY DINOSAUR'
    );
