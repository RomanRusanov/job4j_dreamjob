CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    name TEXT
);
CREATE TABLE candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    photo_id NUMERIC
);
CREATE TABLE photo (
    id SERIAL PRIMARY KEY,
    path TEXT
);
CREATE TABLE userapp (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);