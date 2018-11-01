
CREATE TABLE `STORE_(winetree)_ACCOUNT` (
	`USER_ID`	VARCHAR(20)	PRIMARY KEY NOT NULL,
	`SERVICE_ID` VARCHAR(20) NOT NULL,
	`USER_EMAIL` VARCHAR(50) NOT NULL,
	`USER_PW`	VARCHAR(20)	NOT NULL,
	`USER_TYPE` CHAR(2)	NOT NULL DEFAULT 'C',
	`USER_REGDATE` DATE NOT NULL,
	`POINT`	INTEGER NOT	NULL DEFAULT 0,
	`DELFLAG` CHAR(2) NOT NULL	DEFAULT 'U',
	CONSTRAINT `SERVICE_ACCOUNT_(winetree)_FK` FOREIGN KEY (SERVICE_ID) REFERENCES SERVICE_ACCOUNT (SERVICE_ID) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(winetree)_(1)_CATEGORY`(
   `MENU_SEQ` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `MENU_NAME` VARCHAR(20) NOT NULL,
   `CATEG_NAME` VARCHAR(20) NOT NULL,
   `MENU_INFO` VARCHAR(50) NOT NULL,
   `ORIGIN_FNAME` VARCHAR(2000) NOT NULL,
   `STORED_FNAME` VARCHAR(2000) NOT NULL,
   `MENU_PRICE` INTEGER NOT NULL DEFAULT 0,
   `DELFLAG` CHAR(2) NOT NULL DEFAULT 'N'
);

CREATE TABLE `STORE_(winetree)_(1)_TABLE` (
	`TABLE_SEQ`	INTEGER	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`STORE_SEQ`	INTEGER	NOT NULL,
	`TABLE_NAME` VARCHAR(20)	NOT NULL,
	`RESERVATION` CHAR(2) NOT NULL DEFAULT 'D',
	`MIN_PEOPLE` INTEGER NOT NULL DEFAULT 1,
	`MAX_PEOPLE`	INTEGER	NOT NULL,
	`DELFLAG` CHAR(2) NOT NULL DEFAULT 'N',
	CONSTRAINT `STORE_TABLE_(winetree)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES SERVICE_STORE (STORE_SEQ) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(winetree)_(1)_RESERVATION` (
	`RESERV_SEQ` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`STORE_SEQ` INTEGER NOT NULL,
	`TABLE_SEQ` INTEGER NOT NULL,
	`USER_ID` VARCHAR(20) NOT NULL,
	`RESERV_TIME`	TIMESTAMP NOT NULL,
	`RESERV_PEOPLE`	INTEGER	NOT NULL,
	`DELFLAG`	CHAR(2) NOT NULL DEFAULT 'N',
	CONSTRAINT `STORE_RESERVATION_STORE_SEQ_(winetree)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES SERVICE_STORE (STORE_SEQ) ON DELETE RESTRICT ,
	CONSTRAINT `STORE_RESERVATION_TABLE_SEQ_(winetree)FK` FOREIGN KEY (TABLE_SEQ) REFERENCES `STORE_(winetree)_(1)_TABLE` (TABLE_SEQ) ON DELETE RESTRICT,
	CONSTRAINT `STORE_RESERVATION_USER_ID_(winetree)_FK` FOREIGN KEY (USER_ID) REFERENCES `STORE_(winetree)_ACCOUNT` (USER_ID) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(winetree)_(1)_CASHBOOK` (
  `STORE_SEQ`	INTEGER NOT NULL,
  `CASH_DATE`	TIMESTAMP NOT NULL,
  `CASH_DEPOSIT` INTEGER NULL,
  `CASH_WITHDRAW` INTEGER NULL,
  `CASH_BALANCE` INTEGER NOT NULL DEFAULT '0',
  CONSTRAINT `STORE_CASHBOOK_STORE_SEQ_(winetree)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES SERVICE_STORE (STORE_SEQ) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(winetree)_(1)_INVOICE` (
   `INVOICE_SEQ` INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
   `TABLE_SEQ` INTEGER NOT NULL,
   `STORE_SEQ`	INTEGER NOT NULL,
   `MENU_AMOUNT`	INTEGER NOT NULL,
   `TAX_AMOUNT`	INTEGER NOT NULL,
   `TOTAL_AMOUNT`	INTEGER NOT NULL,
   `DISCOUNT_AMOUNT`	INTEGER NOT NULL,
   `FINAL_AMOUNT`	INTEGER NOT NULL,
   `PAID_DATE` TIMESTAMP NOT NULL,
   `PAYMENT_METHOD` VARCHAR(20) NOT NULL,
	 `DELFLAG`	CHAR(2) NOT NULL DEFAULT 'N',
  CONSTRAINT `STORE_INVOICE_STORE_SEQ_(winetree)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES  `STORE_(winetree)_(1)_TABLE` (STORE_SEQ) ON DELETE RESTRICT,
	CONSTRAINT `STORE_INVOICE_TABLE_SEQ_(winetree)_FK` FOREIGN KEY (TABLE_SEQ) REFERENCES  `STORE_(winetree)_(1)_TABLE` (TABLE_SEQ) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(winetree)_(1)_ORDER` (
   `ORDER_SEQ` INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
   `INVOICE_SEQ` INTEGER NOT NULL,
   `MENU_SEQ` INTEGER NOT NULL,
   `PAID_DATE` TIMESTAMP NOT NULL,
   `MENU_PRICE` INTEGER NOT NULL,
   CONSTRAINT `STORE_ORDER_INVOICE_SEQ_(winetree)_FK` FOREIGN KEY (INVOICE_SEQ) REFERENCES `STORE_(winetree)_(1)_INVOICE` (INVOICE_SEQ) ON DELETE RESTRICT,
   CONSTRAINT `STORE_ORDER_MENU_SEQ_(winetree)_FK` FOREIGN KEY (MENU_SEQ) REFERENCES `STORE_(winetree)_(1)_CATEGORY` (MENU_SEQ) ON DELETE RESTRICT
);





-----





CREATE TABLE `STORE_(hello)_ACCOUNT` (
	`USER_ID`	VARCHAR(20)	PRIMARY KEY NOT NULL,
	`SERVICE_ID` VARCHAR(20) NOT NULL,
	`USER_EMAIL` VARCHAR(50) NOT NULL,
	`USER_PW`	VARCHAR(20)	NOT NULL,
	`USER_TYPE` CHAR(2)	NOT NULL DEFAULT 'C',
	`USER_REGDATE` DATE NOT NULL,
	`POINT`	INTEGER NOT	NULL DEFAULT 0,
	`DELFLAG` CHAR(2) NOT NULL DEFAULT 'U',
	CONSTRAINT `SERVICE_ACCOUNT_(hello)_FK` FOREIGN KEY (SERVICE_ID) REFERENCES SERVICE_ACCOUNT (SERVICE_ID) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(hello)_(2)_CATEGORY`(
   `MENU_SEQ` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `MENU_NAME` VARCHAR(20) NOT NULL,
   `CATEG_NAME` VARCHAR(20) NOT NULL,
   `MENU_INFO` VARCHAR(50) NOT NULL,
   `ORIGIN_FNAME` VARCHAR(2000) NOT NULL,
   `STORED_FNAME` VARCHAR(2000) NOT NULL,
   `MENU_PRICE` INTEGER NOT NULL DEFAULT 0,
   `DELFLAG` CHAR(2) NOT NULL DEFAULT 'N'
);

CREATE TABLE `STORE_(hello)_(2)_TABLE` (
	`TABLE_SEQ`	INTEGER	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`STORE_SEQ`	INTEGER	NOT NULL,
	`TABLE_NAME` VARCHAR(20)	NOT NULL,
	`RESERVATION` CHAR(2) NOT NULL DEFAULT 'D',
	`MIN_PEOPLE` INTEGER NOT NULL DEFAULT 1,
	`MAX_PEOPLE`	INTEGER	NOT NULL,
	`DELFLAG` CHAR(2) NOT NULL DEFAULT 'N',
	CONSTRAINT `STORE_TABLE_(hello)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES SERVICE_STORE (STORE_SEQ) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(hello)_(2)_RESERVATION` (
	`RESERV_SEQ` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`STORE_SEQ` INTEGER NOT NULL,
	`TABLE_SEQ` INTEGER NOT NULL,
	`USER_ID` VARCHAR(20) NOT NULL,
	`RESERV_TIME`	TIMESTAMP NOT NULL,
	`RESERV_PEOPLE`	INTEGER	NOT NULL,
	`DELFLAG`	CHAR(2) NOT NULL DEFAULT 'N',
	CONSTRAINT `STORE_RESERVATION_STORE_SEQ_(hello)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES SERVICE_STORE (STORE_SEQ) ON DELETE RESTRICT ,
	CONSTRAINT `STORE_RESERVATION_TABLE_SEQ_(hello)_FK` FOREIGN KEY (TABLE_SEQ) REFERENCES `STORE_(hello)_(2)_TABLE` (TABLE_SEQ) ON DELETE RESTRICT,
	CONSTRAINT `STORE_RESERVATION_USER_ID_(hello)_FK` FOREIGN KEY (USER_ID) REFERENCES `STORE_(hello)_ACCOUNT` (USER_ID) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(hello)_(2)_CASHBOOK` (
  `STORE_SEQ`	INTEGER NOT NULL,
  `CASH_DATE`	TIMESTAMP NOT NULL,
  `CASH_DEPOSIT` INTEGER NULL,
  `CASH_WITHDRAW` INTEGER NULL,
  `CASH_BALANCE` INTEGER NOT NULL DEFAULT '0',
  CONSTRAINT `STORE_CASHBOOK_STORE_SEQ_(hello)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES SERVICE_STORE (STORE_SEQ) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(hello)_(2)_INVOICE` (
   `INVOICE_SEQ` INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
   `TABLE_SEQ` INTEGER NOT NULL,
   `STORE_SEQ`	INTEGER NOT NULL,
   `MENU_AMOUNT`	INTEGER NOT NULL,
   `TAX_AMOUNT`	INTEGER NOT NULL,
   `TOTAL_AMOUNT`	INTEGER NOT NULL,
   `DISCOUNT_AMOUNT`	INTEGER NOT NULL,
   `FINAL_AMOUNT`	INTEGER NOT NULL,
   `PAID_DATE` TIMESTAMP NOT NULL,
   `PAYMENT_METHOD` VARCHAR(20) NOT NULL,
	 `DELFLAG`	CHAR(2) NOT NULL DEFAULT 'N',
  CONSTRAINT `STORE_INVOICE_STORE_SEQ_(hello)_FK` FOREIGN KEY (STORE_SEQ) REFERENCES  `STORE_(hello)_(2)_TABLE` (STORE_SEQ) ON DELETE RESTRICT,
	CONSTRAINT `STORE_INVOICE_TABLE_SEQ_(hello)_FK` FOREIGN KEY (TABLE_SEQ) REFERENCES  `STORE_(hello)_(2)_TABLE` (TABLE_SEQ) ON DELETE RESTRICT
);

CREATE TABLE `STORE_(hello)_(2)_ORDER` (
   `ORDER_SEQ` INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
   `INVOICE_SEQ` INTEGER NOT NULL,
   `MENU_SEQ` INTEGER NOT NULL,
   `PAID_DATE` TIMESTAMP NOT NULL,
   `MENU_PRICE` INTEGER NOT NULL,
   CONSTRAINT `STORE_ORDER_INVOICE_SEQ_(hello)_FK` FOREIGN KEY (INVOICE_SEQ) REFERENCES `STORE_(hello)_(2)_INVOICE` (INVOICE_SEQ) ON DELETE RESTRICT,
   CONSTRAINT `STORE_ORDER_MENU_SEQ_(hello)_FK` FOREIGN KEY (MENU_SEQ) REFERENCES `STORE_(hello)_(2)_CATEGORY` (MENU_SEQ) ON DELETE RESTRICT
);

COMMIT;