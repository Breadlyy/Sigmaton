CREATE SEQUENCE IF NOT EXISTS account_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS budget_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS category_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS transaction_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE account
(
    id       BIGINT NOT NULL,
    login    VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE budget
(
    id         BIGINT NOT NULL,
    amount     BIGINT,
    account_id BIGINT,
    CONSTRAINT pk_budget PRIMARY KEY (id)
);

CREATE TABLE category
(
    id        BIGINT NOT NULL,
    name      VARCHAR(255),
    type      SMALLINT,
    budget_id BIGINT,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE transaction
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255),
    timestamp   TIMESTAMP WITHOUT TIME ZONE,
    amount      BIGINT,
    category_id BIGINT,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

ALTER TABLE budget
    ADD CONSTRAINT uc_budget_account UNIQUE (account_id);

ALTER TABLE budget
    ADD CONSTRAINT FK_BUDGET_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_BUDGET FOREIGN KEY (budget_id) REFERENCES budget (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);