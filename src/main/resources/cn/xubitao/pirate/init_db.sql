CREATE TABLE contract
(
    id INT(11) PRIMARY KEY NOT NULL,
    response TEXT,
    request TEXT,
    name VARCHAR(50),
    providerId INT(11) NOT NULL,
    description TEXT,
    deleteStatus INT(11) DEFAULT '0',
    server VARCHAR(200) NOT NULL,
    excludeFields VARCHAR(200)
);
CREATE TABLE provider
(
    id INT(11) PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    version VARCHAR(50),
    deleteStatus INT(11) DEFAULT '0'
);
CREATE TABLE record
(
    id INT(11) PRIMARY KEY NOT NULL,
    request TEXT NOT NULL,
    response TEXT,
    contractId INT(11) NOT NULL,
    deleteStatus INT(11) DEFAULT '0' NOT NULL,
    isHit INT(11) NOT NULL,
    createTime VARCHAR(50),
    name VARCHAR(50),
    server VARCHAR(200)
);
