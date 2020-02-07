DROP TABLE IF EXISTS stock;
 
CREATE TABLE stock (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  current_Price VARCHAR(50) NOT NULL,
  last_Update Datetime DEFAULT NULL
);

DROP TABLE IF EXISTS stock_history;
 
CREATE TABLE stock_history (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_Stock INT NOT NULL,
  current_Price VARCHAR(50) NOT NULL,
  last_Update Datetime DEFAULT NULL,
  FOREIGN KEY (id_Stock) REFERENCES stock(id)
);