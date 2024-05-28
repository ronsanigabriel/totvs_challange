CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);

CREATE TABLE payments (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    user_id TEXT NOT NULL,
    description TEXT NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    dueDate TEXT NOT NULL,
    finishDate TEXT,
    status TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);