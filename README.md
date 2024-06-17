# LittleBlackBookApi

## To Do List

- Create






## Endpoint Ideas
- User
  - Get User ( hardcoded user to start? )
  - Get User_Contacts
- Module


## Table Ideas
- User
  - CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone_number VARCHAR(20),
    address VARCHAR(255)
    );
  - Name
  - Phone Number
  - Image (?)
- General Information about user
- User_Contacts
  - Join Table:
    - CREATE TABLE User_Contacts (
      user_id INT,
      contact_id INT,
      PRIMARY KEY (user_id, contact_id),
      FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
      FOREIGN KEY (contact_id) REFERENCES User(id) ON DELETE CASCADE
      );
- Module
- User_Module join table
- Ingredient



Thoughts:

- User has basic info such as name, phone number, username
- User has Many to Many table for List of Contacts
  - this implies that the contact list is an IMPLIED module, a given, included, or maybe one of the initial Modules
- User should have a many to many table for Lists, but how do we have different TYPES of lists?
Possible lists:
- List Simple: just a `List<String>`
  - Ingredients
- Recipe List - This should be it's own table, probably. keep it simple by having specific items:
  - Title
  - Ingredient List ( how do we do a list when we don't want it in another table? or do we have ingredients in another table? mayb )
  - Recipe_Ingredient many-to-many table would also have a 'amount' column SPECIFIC to that recipe and to that ingredient
