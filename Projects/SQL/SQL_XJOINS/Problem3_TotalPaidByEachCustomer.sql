-- Calculate the total amount paid by each customer. Display the customer_id, first_name, last_name, and the total amount paid, and sort the results alphabetically by the last_name and then by the first_name.
-- Write Query Here
SELECT 
    c.customer_id, 
    c.first_name, 
    c.last_name, 
    SUM(p.amount) AS total_paid
FROM 
    customer c
JOIN 
    payment p ON c.customer_id = p.customer_id
GROUP BY 
    c.customer_id, c.first_name, c.last_name
ORDER BY 
    c.last_name, c.first_name;
