-- Find the number of payments made by the customer ELEANOR HUNT in the payment table and rename it to Payments by ELEANOR HUNT.
-- Write Query Here
SELECT 
    COUNT(p.payment_id) AS `Payments by ELEANOR HUNT`
FROM 
    payment p
JOIN 
    customer c ON p.customer_id = c.customer_id
WHERE 
    CONCAT(c.first_name, ' ', c.last_name) = 'ELEANOR HUNT';
