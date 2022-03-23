DROP USER IF EXISTS web_user@localhost;
CREATE USER web_user@localhost IDENTIFIED BY 'LangTekstDerErLetAtHuske.';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON webshop.*
    TO web_user@localhost;

SELECT User, Host FROM mysql.user;
SHOW GRANTS FOR web_user@localhost;