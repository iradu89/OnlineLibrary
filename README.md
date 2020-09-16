# Online Library Web App

## Homepage & Navigation bar
The homepage/landing page will contain some information about the latest books, as well as some bestsellers. It should have a filtration menu in which users can see the book categories/genres and can make a quick selection. Other than that some ads which will change depending on the season. 
The navigation bar has a button for browsing a greater selection of books and some other generic website buttons (contact, about us, jobs, etc.)

## User Profile
Here the user will be able to see, edit, or delete his information. He will also be able to see a buy history with each acquisition containing the product/products and date it was purchased on. 

## Book page
Here the user, or anyone visiting the web app, will be able to see information about a certain book as well as other user-reviews for that book. Only logged in users can leave a review or buy the book. If prompted they will be asked to log-in. 

## Purchase confirmation page
The user will be able to confirm his information one last time before clicking the order button. He will be able to change his information/address. This page will have validation for product stocks. If the product is no longer available the user will receive a message.

## CRUD for books
This page or pages will only be accessable by the Book Keeper. His responsability will be to add new books, edit existing ones and/or deleting them. Basically this is a CRUD for books.

## CRUD for users 
Here only the admin will have access. He will be able to do all the CRUD operations on users. Also, regular users will be able to create and modify their own accounts.

### Entity Relationship Diagram:
![ERD](https://i.imgur.com/QBhIYb2.png)

### Flows separated between teammates:
![TEAMFLOW](https://i.imgur.com/t7yTjv0.png)

## Additional details
App will have Spring Security implementation. All forms and fields will have validation with a message displayed to the user.

## Web scraper
In the testing phase of the app a web scraper will be created to automatically import books from the best-selling list of books on wikipedia. It will be helpful because it will automatically work for all the team-members and they won't have to manually add books for testing.
