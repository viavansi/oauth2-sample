DROP TABLE IF EXISTS OAUTH_CLIENT_DETAILS;
create table OAUTH_CLIENT_DETAILS (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

DROP TABLE IF EXISTS CREDENTIALS;
CREATE TABLE CREDENTIALS (
  id       INTEGER,
  enabled  BOOLEAN      NOT NULL,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  version  INTEGER,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS CREDENTIALS_AUTHORITIES;
CREATE TABLE CREDENTIALS_AUTHORITIES (
  credentials_id BIGINT NOT NULL,
  authorities_id BIGINT NOT NULL
);

DROP TABLE IF EXISTS OAUTH_CLIENT_TOKEN;
CREATE TABLE OAUTH_CLIENT_TOKEN (
  token_id          VARCHAR(256),
  token             LONGVARBINARY,
  authentication_id VARCHAR(256),
  user_name         VARCHAR(256),
  client_id         VARCHAR(256)
);

DROP TABLE IF EXISTS OAUTH_ACCESS_TOKEN;
CREATE TABLE OAUTH_ACCESS_TOKEN (
  token_id          VARCHAR(256),
  token             LONGVARBINARY,
  authentication_id VARCHAR(256),
  user_name         VARCHAR(256),
  client_id         VARCHAR(256),
  authentication    LONGVARBINARY,
  refresh_token     VARCHAR(256)
);

DROP TABLE IF EXISTS OAUTH_REFRESH_TOKEN;
CREATE TABLE OAUTH_REFRESH_TOKEN (
  token_id       VARCHAR(256),
  token          LONGVARBINARY,
  authentication LONGVARBINARY
);

DROP TABLE IF EXISTS OAUTH_CODE;
CREATE TABLE OAUTH_CODE (
  code           VARCHAR(256),
  authentication LONGVARBINARY
);

DROP TABLE IF EXISTS OAUTH_APPROVALS;
CREATE TABLE OAUTH_APPROVALS (
  userid         VARCHAR(256),
  clientid       VARCHAR(256),
  scope          VARCHAR(256),
  status         VARCHAR(10),
  expiresat      TIMESTAMP,
  lastmodifiedat TIMESTAMP
);

DROP TABLE IF EXISTS AUTHORITY;
CREATE TABLE AUTHORITY (
  id        INTEGER,
  authority VARCHAR(255),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS  APP_USER;
CREATE TABLE APP_USER (
  user_id VARCHAR(64) PRIMARY KEY,
  app_id VARCHAR(64) NOT NULL,
  username VARCHAR(128) NOT NULL UNIQUE,
  password VARCHAR(256),
  enabled BOOLEAN);
  
  
DROP TABLE IF EXISTS  APP;
CREATE TABLE APP (
  app_id VARCHAR(64) PRIMARY KEY,
  name VARCHAR(64),
);
