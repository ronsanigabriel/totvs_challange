CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    username TEXT NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);

INSERT INTO users (id, username, login, password, role) VALUES
('root-admin-000', 'Administrador', 'adm', '$2a$10$MKo4Z7NMsMcVuDuU05ahSuQ7BWz91RerPFBZYMHj72aVekbHAVVNS', '0')
ON CONFLICT (id) DO NOTHING;