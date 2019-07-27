CREATE TABLE CATEGORY
(
   ID INT,
   CATEGORY_NAME VARCHAR(100) NOT NULL,
   PARENT_CATEGORY_ID INT,
   PRIMARY KEY(ID),
   FOREIGN KEY(PARENT_CATEGORY_ID) REFERENCES CATEGORY
);

CREATE TABLE PRODUCT
(
     ID INT,
     PRODUCT_NAME VARCHAR(255) NOT NULL,
     CATEGORY_ID INT,
     PRIMARY KEY(ID),
     FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY
);

CREATE TABLE PRODUCT_PRICING
(
     ID INT AUTO_INCREMENT,
     PRODUCT_ID INT NOT NULL,
     PRICE DECIMAL(7,2) NOT NULL,
     VALID_FROM TIMESTAMP NOT NULL,
     VALID_TO TIMESTAMP NOT NULL,
     ACTIVE BOOLEAN,
     PRIMARY KEY(ID),
     FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT
);

CREATE TABLE CATEGORY_TAX
(
    ID INT AUTO_INCREMENT,
    CATEGORY_ID INT NOT NULL,
    TAX_AMOUNT  DECIMAL(7,2) NOT NULL,
    MODIFIER    VARCHAR(20) NOT NULL,
    VALID_FROM  TIMESTAMP NOT NULL,
    VALID_TO    TIMESTAMP NOT NULL,
    ACTIVE BOOLEAN,
    PRIMARY KEY(ID),
    FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY
);

CREATE TABLE ATTRIBUTE
(
    ID INT,
    COLOR VARCHAR(15),
    SIZE VARCHAR(10),
    IMPORTED BOOLEAN,
    PRIMARY KEY(ID)
);

CREATE TABLE ATTRIBUTE_TAX
(
    ID INT AUTO_INCREMENT,
    ATTRIBUTE_ID INT NOT NULL,
    TAX_AMOUNT  DECIMAL(7,2) NOT NULL,
    MODIFIER    VARCHAR(20) NOT NULL,
    VALID_FROM  TIMESTAMP NOT NULL,
    VALID_TO    TIMESTAMP NOT NULL,
    ACTIVE BOOLEAN,
    PRIMARY KEY(ID),
    FOREIGN KEY(ATTRIBUTE_ID) REFERENCES ATTRIBUTE
);

-- same product can have multiple attributes
-- same attribute can have multiple products
CREATE TABLE PRODUCT_ATTRIBUTE
(
    PRODUCT_ID INT NOT NULL,
    ATTRIBUTE_ID INT NOT NULL,
    PRIMARY KEY(PRODUCT_ID, ATTRIBUTE_ID),
    FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT,
    FOREIGN KEY(ATTRIBUTE_ID) REFERENCES ATTRIBUTE
);

