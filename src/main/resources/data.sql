INSERT INTO users(username,password,enabled)
VALUES ('jan','{noop}jan1', true);

INSERT INTO users(username,password,enabled)
VALUES ('ania','{noop}Ania1', true);

INSERT INTO authorities (username, authority)
VALUES ('jan', 'ROLE_USER');

INSERT INTO authorities (username, authority)
VALUES ('jan', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
VALUES ('ania', 'ROLE_USER');

INSERT INTO userrole(role, description)
VALUES ("ROLE_USER", "default role for user");
