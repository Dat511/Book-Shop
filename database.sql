CREATE DATABASE BookShop
GO
USE [BookShop]
GO

CREATE TABLE tblUser (
    id INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	phone_number VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
	role BIT NOT NULL
);

CREATE TABLE category (
    category_id INT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL
);

INSERT INTO category (category_id, name) VALUES
(1, 'Classics'),
(2, 'Fiction'),
(3, 'Mystery and Crime'),
(4, 'Science Fiction and Fantasy'),
(5, 'Historical Fiction'),
(6, 'Self-Help and Philosophy');

CREATE TABLE vegetable (
    vegetable_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    origin NVARCHAR(100) NOT NULL,
    pack INT NOT NULL,
    img VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    status NVARCHAR(50) NOT NULL,
    description NVARCHAR(255),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE Orders (
    [order_id] INT IDENTITY(1,1) PRIMARY KEY,
    [user_id] INT NOT NULL,
    [order_date] DATETIME NOT NULL DEFAULT GETDATE(),
    [status] NVARCHAR(50) NOT NULL DEFAULT N'processing',
    [total_amount] DECIMAL(10, 2) NOT NULL,
    CONSTRAINT Orders_User FOREIGN KEY (user_id)
        REFERENCES tblUser(id)
);

CREATE TABLE OrderDetails (
    [order_detail_id] INT IDENTITY(1,1) PRIMARY KEY,
    [order_id] INT NOT NULL,
    [vegetable_id] INT NOT NULL,
    [quantity] INT NOT NULL DEFAULT 1,
    CONSTRAINT OrderDetails_Order FOREIGN KEY (order_id)
        REFERENCES Orders(order_id),
    CONSTRAINT OrderDetails_Vegetable FOREIGN KEY (vegetable_id)
        REFERENCES vegetable(vegetable_id) 
);

INSERT INTO tblUser (id, username, fullname, email, phone_number, password, role) VALUES
('01', 'Quy', 'Phu Quy', 'quy123@gmail.com', '0123456789', '12345', '1'),
('02', 'Hung', 'Quoc Hung', 'hung123@gmail.com', '0987654321', '12345', '1'),
('03', 'Tien', 'Thao Tien','tien123@gmail.com', '0135678329', '12345', '1'),
('04', 'Thuan', 'Thuan', 'Thuan123@gmail.com', '0918273645', '12345', '0'),
('05', 'Dung', 'Dung Huynh', 'Dung123@gmail.com', '0564738291', '12345', '0');


INSERT INTO vegetable (name, origin, pack, img, price, status, description, category_id) VALUES
( 'To Kill a Mockingbird', 'USA', 5, 'mockingbird.jpg', 199.99, 'in stock', 'A classic novel by Harper Lee, exploring racial injustice in the Deep South.', 1),
( '1984', 'United Kingdom', 3, '1984.jpg', 149.99, 'in stock', 'George Orwell’s dystopian masterpiece about a totalitarian regime.', 1),
( 'Pride and Prejudice', 'United Kingdom', 5, 'pride_and_prejudice.jpg', 179.99, 'in stock', 'Jane Austen’s classic romantic novel set in Georgian England.', 2),
( 'The Great Gatsby', 'USA', 5, 'great_gatsby.jpg', 159.99, 'in stock', 'F. Scott Fitzgerald’s iconic story of the Jazz Age.', 2),
( 'Moby Dick', 'USA', 5, 'moby_dick.jpg', 209.99, 'in stock', 'Herman Melville’s epic tale of obsession and revenge.', 3),
( 'War and Peace', 'Russia', 5, 'war_and_peace.jpg', 299.99, 'in stock', 'Leo Tolstoy’s monumental novel about Russian society during the Napoleonic era.', 3),
( 'The Catcher in the Rye', 'USA', 5, 'catcher_in_the_rye.jpg', 129.99, 'in stock', 'J.D. Salinger’s story of teenage rebellion and identity.', 4),
( 'Brave New World', 'United Kingdom', 5, 'brave_new_world.jpg', 149.99, 'in stock', 'Aldous Huxley’s vision of a dystopian future.', 4),
( 'The Hobbit', 'United Kingdom', 5, 'the_hobbit.jpg', 179.99, 'in stock', 'J.R.R. Tolkien’s beloved prelude to The Lord of the Rings.', 5),
( 'Crime and Punishment', 'Russia', 5, 'crime_and_punishment.jpg', 249.99, 'in stock', 'Fyodor Dostoevsky’s psychological exploration of guilt and redemption.', 5),
( 'The Alchemist', 'Brazil', 5, 'the_alchemist.jpg', 129.99, 'in stock', 'Paulo Coelho’s tale of self-discovery and following one’s dreams.', 6),
( 'Harry Potter and the Sorcerers Stone', 'United Kingdom', 5, 'harry_potter.jpg', 199.99, 'in stock', 'The first book in J.K. Rowling’s legendary Harry Potter series.', 6),
( 'The Book Thief', 'Germany', 5, 'the_book_thief.jpg', 159.99, 'in stock', 'Markus Zusak’s poignant story set during World War II.', 1),
( 'Anna Karenina', 'Russia', 5, 'anna_karenina.jpg', 259.99, 'in stock', 'Leo Tolstoy’s tale of love, betrayal, and society.', 2),
( 'The Adventures of Sherlock Holmes', 'United Kingdom', 5, 'sherlock_holmes.jpg', 139.99, 'in stock', 'Arthur Conan Doyle’s timeless detective stories.', 3),
( 'The Kite Runner', 'Afghanistan', 5, 'the_kite_runner.jpg', 179.99, 'in stock', 'Khaled Hosseini’s powerful tale of friendship and redemption.', 4),
( 'Don Quixote', 'Spain', 5, 'don_quixote.jpg', 199.99, 'in stock', 'Miguel de Cervantes’s comedic and tragic masterpiece.', 5),
('Wuthering Heights', 'United Kingdom', 5, 'wuthering_heights.jpg', 149.99, 'in stock', 'Emily Brontë’s passionate story of love and revenge.', 6),
('Les Misérables', 'France', 5, 'les_miserables.jpg', 249.99, 'in stock', 'Victor Hugo’s epic story of love, justice, and humanity.', 2),
('A Tale of Two Cities', 'United Kingdom', 5, 'tale_of_two_cities.jpg', 199.99, 'in stock', 'Charles Dickens’s historical novel set during the French Revolution.', 2);






SELECT vegetable_id, name, origin, pack, img, price, status, description FROM vegetable WHERE category_id = 1;