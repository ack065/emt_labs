-- views.sql
CREATE MATERIALIZED VIEW accommodations_by_host AS
SELECT h.id AS host_id, COUNT(b.id) AS accommodations_count
FROM host h
LEFT JOIN booking b ON h.id = b.host_id
GROUP BY h.id;

CREATE MATERIALIZED VIEW hosts_by_country AS
SELECT
    c.id AS country_id,
    c.name AS country_name,
    COUNT(h.id) AS hosts_count
FROM country c
         LEFT JOIN host h ON c.id = h.country_id
GROUP BY c.id, c.name;