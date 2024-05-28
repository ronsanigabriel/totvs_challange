INSERT INTO users (id, login, password, role) VALUES
    ('s7cab11w-328k-bu13-13f54sd1324z', 'adm', '$2a$10$.5uh.mfNwAkIaI9CJ/3GS.fiDS/GHQCxTKIzakVDFUd70CjqNNcL2', '0')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, login, password, role) VALUES
    ('e2cab12c-f684-42d8-bb4f-13f54878114c', 'gabriel', '$2a$10$.5uh.mfNwAkIaI9CJ/3GS.fiDS/GHQCxTKIzakVDFUd70CjqNNcL2', '0')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, login, password, role) VALUES
    ('5acc9ef8-460d-4f4c-a97c-41dee2b42fd0', 'common', '$2a$10$.5uh.mfNwAkIaI9CJ/3GS.fiDS/GHQCxTKIzakVDFUd70CjqNNcL2', '1')
ON CONFLICT (id) DO NOTHING;