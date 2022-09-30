INSERT INTO restaurant (id, zip_address, complement, name) VALUES
(1, '58113616', 'Próximo ao Jaime Caetano',  'Deguste Oriental'),
(2, '58465112', 'Próximo a praça do Alto', 'Lanchonete do Amaro');

INSERT INTO client (id, complement, zip_address, name) VALUES
(1, 'Próximo ao Quiosque das Palmeiras', '58309616',  'Zoe');

INSERT INTO product (id, is_available,  name, unit_price, restaurant_id) VALUES
(1, true, 'Sushi 40 peças', 106.001, 1),
(2, true, 'Temaki', 26.00, 1),
(3, true, 'X-burguer', 6.00, 2);

INSERT INTO shopping_cart (id, form_payment, is_closed, total_value, client_id) VALUES
(1, 0, false, 0.0, 1);
