CREATE SCHEMA PORTAL;

-- Drop table

-- DROP TABLE PORTAL.PORTAL

CREATE TABLE PORTAL.PORTAL (
	CATEGORY VARCHAR(100),
	SUBCATEGORY VARCHAR(100),
	"STORE #" VARCHAR(100),
	ID BIGINT DEFAULT NOT NULL AUTO_INCREMENT,
	DEPT VARCHAR(100),
	CLASS VARCHAR(100),
	CONSTRAINT CONSTRAINT_8 PRIMARY KEY (ID)
) ;
CREATE UNIQUE INDEX PRIMARY_KEY_A ON PORTAL.PORTAL (ID) ;

CREATE TABLE PORTAL.PDF (
	PDF_ID IDENTITY NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100),
	CONTENT BLOB,
	DESCRIPTION CLOB
) ;
