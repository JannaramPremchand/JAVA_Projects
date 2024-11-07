-- Write a query that returns the customer_id, the total spent amount (rename this column as total_amount), and the number of rentals (rename this column as total_rentals) of the customers that paid more than $100 in total and made less than 25 rentals.
-- Write Query Here
SELECT 
    p.customer_id,
    SUM(p.amount) AS total_amount,
    COUNT(r.rental_id) AS total_rentals
FROM 
    payment p
JOIN 
    rental r ON p.rental_id = r.rental_id
GROUP BY 
    p.customer_id
HAVING 
    SUM(p.amount) > 100 AND COUNT(r.rental_id) < 25;

