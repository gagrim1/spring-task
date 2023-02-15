CREATE TABLE votes (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64)
);

CREATE TABLE quotes (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(255)                 NOT NULL,
    update_date TIMESTAMP WITH TIME ZONE NOT NULL,
    user_id BIGINT REFERENCES users(id)  NOT NULL,
    vote_id BIGINT REFERENCES votes(id)  NOT NULL
);