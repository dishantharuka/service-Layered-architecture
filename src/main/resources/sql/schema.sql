drop
database if exists service;
create database if not exists service;

use service;

CREATE TABLE User
(
    UserID   VARCHAR(50) PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    Email    VARCHAR(50) NOT NULL
);

insert into Uservalues ('001', 'kamal', '1234', 'admin');

CREATE TABLE Supplier
(
    SupplierID         VARCHAR(50) PRIMARY KEY,
    SupplierName       VARCHAR(50) NOT NULL,
    ContactInformation VARCHAR(50) NOT NULL,
    Address            VARCHAR(50) NOT NULL,
    UserID             VARCHAR(50) NOT NULL,
    CONSTRAINT foreign key (UserID) REFERENCES User (UserID) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE inventoryItem
(
    ItemID    VARCHAR(50) PRIMARY KEY,
    ItemName  VARCHAR(50)    NOT NULL,
    Quantity  VARCHAR(50)    NOT NULL,
    UnitPrice DECIMAL(10, 2) NOT NULL
);

CREATE TABLE SupplierInventory
(
    SupplierID VARCHAR(50) NOT NULL,
    ItemID     VARCHAR(50) NOT NULL,
    Date       VARCHAR(50) NOT NULL,
    Time       VARCHAR(50) NOT NULL,
    PRIMARY KEY (SupplierID, ItemID),
    constraint FOREIGN KEY (SupplierID) REFERENCES Supplier (SupplierID) on update cascade on delete cascade,
    constraint FOREIGN KEY (ItemID) REFERENCES InventoryItem (ItemID) on update cascade on delete cascade
);

CREATE TABLE Payment
(
    PaymentID     VARCHAR(50) PRIMARY KEY,
    Amount        VARCHAR(50) NOT NULL,
    PaymentMethod VARCHAR(50) NOT NULL,
    ItemID        VARCHAR(50) NOT NULL,
    CONSTRAINT fk_ItemID FOREIGN KEY (ItemID) REFERENCES inventoryItem (ItemID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Customer
(
    CustomerID  VARCHAR(50) PRIMARY KEY,
    Name        VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL,
    Email       VARCHAR(50) NOT NULL,
    Address     VARCHAR(50) NOT NULL,
    UserId      VARCHAR(50) not null,
    constraint foreign key (UserID) references User (UserID) on update cascade on delete cascade
);

CREATE TABLE Orders
(
    OrderId    VARCHAR(50) PRIMARY KEY,
    date       DATE not null,
    CustomerID VARCHAR(50),
    constraint foreign key (CustomerID) references Customer (CustomerID) on update cascade on delete cascade
);

CREATE TABLE OrderDetails
(
    OrderId    VARCHAR(50) ,
    ItemID      VARCHAR(50) ,
    qty int,
    constraint foreign key (OrderId) references Orders (OrderId) on update cascade on delete cascade,
    constraint foreign key (ItemID) references inventoryItem (ItemID) on update cascade on delete cascade
);

CREATE TABLE Employee
(
    EmployeeID         VARCHAR(50) PRIMARY KEY,
    Name               VARCHAR(255) NOT NULL,
    ContactInformation VARCHAR(50)  NOT NULL,
    Role               VARCHAR(50)  NOT NULL,
    Salary             VARCHAR(50)  NOT NULL,
    UserID             VARCHAR(50)  not null,
    constraint foreign key (UserID) references User (UserID) on update cascade on delete cascade
);

CREATE TABLE Vehicle
(
    VehicleID    VARCHAR(50) PRIMARY KEY,
    Model        VARCHAR(50) NOT NULL,
    Year         VARCHAR(50) NOT NULL,
    LicensePlate VARCHAR(20) NOT NULL,
    CustomerID   VARCHAR(50) not null,
    constraint FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) on update cascade on delete cascade
);

CREATE TABLE Appointment
(
    AppointmentID VARCHAR(50) PRIMARY KEY,
    Date          VARCHAR(50) NOT NULL,
    Time          VARCHAR(50) NOT NULL,
    ServiceType   VARCHAR(50) NOT NULL,
    VehicleID     VARCHAR(50) NOT NULL,
    constraint foreign key (VehicleID) references Vehicle (VehicleID) on update cascade on delete cascade
);



CREATE TABLE ServiceRecord
(
    RecordID           VARCHAR(50) PRIMARY KEY,
    VehicleID          VARCHAR(50)  NOT NULL,
    ServiceDate        VARCHAR(50)  NOT NULL,
    ServiceDescription VARCHAR(255) NOT NULL,
    TechnicianName     VARCHAR(50)  NOT NULL,
    constraint FOREIGN KEY (VehicleID) REFERENCES Vehicle (VehicleID) on update cascade on delete cascade
);

CREATE TABLE Salary
(
    SalaryID   VARCHAR(50) PRIMARY KEY,
    EmployeeID VARCHAR(50) NOT NULL,
    Month      VARCHAR(50) NOT NULL,
    Year       VARCHAR(50) NOT NULL,
    Amount     VARCHAR(50) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee (EmployeeID) on update cascade on delete cascade
);