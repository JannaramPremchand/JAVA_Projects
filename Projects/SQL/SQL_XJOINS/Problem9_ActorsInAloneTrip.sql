-- Display all actors who appear in the film "Alone Trip" using subqueries.
-- Write Query Here
SELECT 
    a.first_name, 
    a.last_name
FROM 
    actor a
JOIN 
    film_actor fa ON a.actor_id = fa.actor_id
JOIN 
    film f ON fa.film_id = f.film_id
WHERE 
    f.title = 'Alone Trip';

