SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE quotes RESTART IDENTITY;
TRUNCATE TABLE votes RESTART IDENTITY;
TRUNCATE TABLE users RESTART IDENTITY;
SET REFERENTIAL_INTEGRITY TRUE;
