INSERT INTO orders (order_status, price) VALUES
                                             ('PENDING', 1200),
                                             ('CONFORMED', 2500),
                                             ('PENDING', 1800),
                                             ('CANCELED', 900),
                                             ('CONFORMED', 3200),
                                             ('CANCELED', 1500);



INSERT INTO order_items (product_id, quantity, order_id) VALUES
                                                             (1, 2, 1),
                                                             (2, 1, 1),
                                                             (3, 3, 2),
                                                             (4, 2, 2),
                                                             (5, 1, 3),
                                                             (6, 4, 3);