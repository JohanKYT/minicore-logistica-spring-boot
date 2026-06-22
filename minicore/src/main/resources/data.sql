-- data.sql
INSERT INTO zonas (id_zona, nombre_zona, tarifa_por_kg) VALUES (1, 'Norte', 1.50) ON CONFLICT DO NOTHING;
INSERT INTO zonas (id_zona, nombre_zona, tarifa_por_kg) VALUES (2, 'Sur', 2.00) ON CONFLICT DO NOTHING;
INSERT INTO zonas (id_zona, nombre_zona, tarifa_por_kg) VALUES (3, 'Centro', 1.00) ON CONFLICT DO NOTHING;

INSERT INTO repartidor (id_repartidor, nombre, email) VALUES (1, 'Andrés', 'andres@mail.com') ON CONFLICT DO NOTHING;
INSERT INTO repartidor (id_repartidor, nombre, email) VALUES (2, 'Camila', 'camila@mail.com') ON CONFLICT DO NOTHING;
INSERT INTO repartidor (id_repartidor, nombre, email) VALUES (3, 'Luis', 'luis@mail.com') ON CONFLICT DO NOTHING;

-- Envíos de mayo para Andrés (Norte)
INSERT INTO envios (id_envio, id_repartidor, id_zona, peso_kg, fecha_envio) VALUES (1, 1, 1, 10.0, '2025-05-10') ON CONFLICT DO NOTHING;
INSERT INTO envios (id_envio, id_repartidor, id_zona, peso_kg, fecha_envio) VALUES (2, 1, 1, 22.0, '2025-05-15') ON CONFLICT DO NOTHING;

-- Envíos de mayo para Camila (Sur)
INSERT INTO envios (id_envio, id_repartidor, id_zona, peso_kg, fecha_envio) VALUES (3, 2, 2, 18.0, '2025-05-20') ON CONFLICT DO NOTHING;