CREATE TABLE IF NOT EXISTS `books` (
  `book_id`         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `file_name`       TEXT    NOT NULL COLLATE NOCASE,
  `author_name`     TEXT    NOT NULL COLLATE NOCASE,
  `book_name`       TEXT    NOT NULL COLLATE NOCASE,
  `resource_image`  INTEGER NOT NULL COLLATE NOCASE,
  `quiz_id`         INTEGER NOT NULL COLLATE NOCASE
);
CREATE UNIQUE `INDEX index_books_book_name`
                ON `books` (`book_name`);

CREATE TABLE IF NOT EXISTS `users` (
  `user_id`         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `user_name`       TEXT NOT NULL COLLATE NOCASE,
  `email`           TEXT NOT NULL COLLATE NOCASE
);
CREATE UNIQUE INDEX `index_users_user_name`
                ON  `users` (`user_name`);

CREATE TABLE IF NOT EXISTS `booksRead` (
  `books_read_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `user_id`         INTEGER NOT NULL,
  `book_id`         INTEGER NOT NULL,
  `book_name`       TEXT NOT NULL,
  `book_read_time`  INTEGER NOT NULL,
                    FOREIGN KEY(`user_id`)
                    REFERENCES `users`(`user_id`)
                    ON UPDATE NO ACTION ON DELETE NO ACTION ,
                    FOREIGN KEY(`book_id`)
                    REFERENCES `books`(`book_id`)
                    ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE UNIQUE INDEX `index_booksRead_books_read_id_user_id_book_id_book_name`
                ON  `booksRead` (`books_read_id`, `user_id`, `book_id`, `book_name`);

CREATE TABLE IF NOT EXISTS `quiz` (
  `quiz_id`         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `file_name`       TEXT NOT NULL
);
CREATE UNIQUE INDEX `index_quiz_quiz_id`
                ON  `quiz` (`quiz_id`);

CREATE TABLE IF NOT EXISTS `room_master_table` (
  `id`              INTEGER PRIMARY KEY,
  `identity_hash`   TEXT),
                    INSERT OR REPLACE INTO `room_master_table` (
                    `id`,`identity_hash`)
                    VALUES (`42`, `79c9f4e9d26db112410d07faf7d18774`
);