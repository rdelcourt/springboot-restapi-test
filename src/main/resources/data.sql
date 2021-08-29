DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS customerFolder;
 
CREATE TABLE message (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  date DATE NOT NULL,
  author VARCHAR(250) NOT NULL,
  content TEXT NOT NULL,
  channel VARCHAR(250) NOT NULL,
  customer_folder_id INT
);

CREATE TABLE customer_folder (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_name VARCHAR(250) NOT NULL,
  open_date DATE NOT NULL,
  reference VARCHAR(250)
);

 ALTER TABLE message
    ADD FOREIGN KEY (customer_folder_id) 
    REFERENCES customer_folder(id);