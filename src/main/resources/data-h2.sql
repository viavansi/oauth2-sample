INSERT INTO AUTHORITY VALUES (0, 'role_oauth_admin');
INSERT INTO AUTHORITY VALUES (1, 'role_admin');
INSERT INTO AUTHORITY VALUES (2, 'role_user');
INSERT INTO CREDENTIALS VALUES (0, true, 'oauth_admin', 'admin', 0);
INSERT INTO CREDENTIALS VALUES (1, true, 'resource_admin', 'admin', 0);
INSERT INTO CREDENTIALS VALUES (2, true, 'user', 'user', 0);
INSERT INTO CREDENTIALS_AUTHORITIES VALUES (0, 0);
INSERT INTO CREDENTIALS_AUTHORITIES VALUES (1, 1);
INSERT INTO CREDENTIALS_AUTHORITIES VALUES (2, 2);


INSERT INTO OAUTH_CLIENT_DETAILS
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('adminClientId', 'adminSecret', 'admin','read,write,password,authorization_code,refresh_token', 
    null, 'role_admin', 36000, 36000, null, true);

INSERT INTO OAUTH_CLIENT_DETAILS
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('userClientId', 'userSecret', 'user','read,password,authorization_code,refresh_token', 
    null, 'role_user', 36000, 36000, null, true);
    
insert into APP_USER (user_id,app_id,username,password,enabled) 
values ('admin','test_app_id','admin','$2a$04$kWC530uC2RxvfGyFEYlAG.BraCpzY2EwUJdEbJXNnfXx2FcyjjoYO',true);

insert into APP_USER (user_id,app_id,username,password,enabled) 
values ('user','test_app_id','user','$2a$04$kWC530uC2RxvfGyFEYlAG.BraCpzY2EwUJdEbJXNnfXx2FcyjjoYO',true);

insert into APP (app_id,name) 
values ('test_app_id','test_app_name');
