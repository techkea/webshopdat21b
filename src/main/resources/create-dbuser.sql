DROP USER IF EXISTS webshop_user@localhost;
CREATE USER webshop_user@localhost IDENTIFIED BY 'LangTekstDerErLetAtHuske.';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON webshop.*
    TO webshop_user@localhost;

SELECT User, Host FROM mysql.user;
SHOW GRANTS FOR webshop_user@localhost;