CREATE TABLE stored_images (             
              id int(5) NOT NULL auto_increment,   
              image blob,
              create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
              PRIMARY KEY  (`id`)                   
 );
